package com.ufc.janaina.ui.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.ufc.janaina.jdbc.dao.ClienteDAO;
import com.ufc.janaina.jdbc.dao.FormaPagamentoDAO;
import com.ufc.janaina.jdbc.dao.TesteDAO;
import com.ufc.janaina.jdbc.dao.VeiculoDAO;
import com.ufc.janaina.jdbc.dao.VendaDAO;
import com.ufc.janaina.models.Cliente;
import com.ufc.janaina.models.Teste;
import com.ufc.janaina.models.Veiculo;
import com.ufc.janaina.models.Venda;
import com.ufc.janaina.utils.DateFormat;

public class CadastrarVendaView extends View {
	private JPanel cardLayoutPanel;
	private JPanel panel_1;
	private JButton voltarBtn;
	private JButton proximoBtn;
	private JButton finalizarBtn;
	private CardLayout cardLayout;
	
	private int currentView;
	
	BuscarClientesView bclientes = new BuscarClientesView(false);
	BuscarVeiculosView bveiculos = new BuscarVeiculosView(false);
	VendaInfoView vendaInfoView = new VendaInfoView();
	
	private Cliente clienteSelecionado = null;
	private Veiculo veiculoSelecionado = null;
	private Venda vendaEditar = null;
	
	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	public CadastrarVendaView() {
		currentView = 0;
		
		setLayout(new BorderLayout(0, 0));
		
		cardLayoutPanel = new JPanel();
		add(cardLayoutPanel, BorderLayout.CENTER);
		cardLayout = new CardLayout(0, 0);
		cardLayoutPanel.setLayout(cardLayout);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		voltarBtn = new JButton("Voltar");
		panel_1.add(voltarBtn);
		
		proximoBtn = new JButton("Próximo");
		panel_1.add(proximoBtn);
		
		finalizarBtn = new JButton("Finalizar");
		panel_1.add(finalizarBtn);
		
		//////////////////////////////////////////
		cardLayoutPanel.add(vendaInfoView, "VendaInfoView");
		cardLayoutPanel.add(bclientes, "BuscarClientesView");
		cardLayoutPanel.add(bveiculos, "BuscarVeiculosView");
		updateView();
		
		setButtonsAction();
	}
	
	private void setButtonsAction() {
		proximoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentView++;
				
				if(currentView > 2) {
					currentView = 2;
				}
				
				if(!updateView()) {
					currentView--;
				}
			}
		});
		
		voltarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentView--;
				
				if(currentView < 0) {
					currentView = 0;
				}
				
				if(!updateView()) {
					currentView++;
				}
			}
		});
		
		finalizarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!validateFields()) {
					return;
				}
				
				if(vendaEditar == null) {
					inserir(e);
				}else {
					atualizar(e);
				}
			}
		});
	}
	
	private boolean updateView() {
		switch(currentView) {
			case 0:
				cardLayout.show(cardLayoutPanel, "BuscarClientesView");
				voltarBtn.setEnabled(false);
				proximoBtn.setEnabled(true);
				finalizarBtn.setEnabled(false);
			break;
			case 1:
				if(bclientes.getSelectedClienteId() == -1) {
					return false;
				}
				
				clienteSelecionado = (Cliente)new ClienteDAO().get(bclientes.getSelectedClienteId());
				cardLayout.show(cardLayoutPanel, "BuscarVeiculosView");
				voltarBtn.setEnabled(false);
				proximoBtn.setEnabled(true);
				finalizarBtn.setEnabled(false);
			break;
			case 2:
				if(bveiculos.getSelectedVeiculoId() == -1) {
					return false;
				}
				
				veiculoSelecionado = (Veiculo)new VeiculoDAO().get(bveiculos.getSelectedVeiculoId());
				
				cardLayout.show(cardLayoutPanel, "VendaInfoView");
				voltarBtn.setEnabled(false);
				proximoBtn.setEnabled(false);
				finalizarBtn.setEnabled(true);
				
				vendaInfoView.nomeLabel.setText(clienteSelecionado.getNome());
				vendaInfoView.rgLabel.setText(clienteSelecionado.getRg());
				vendaInfoView.chassiLabel.setText(veiculoSelecionado.getChassi());
				vendaInfoView.placaLabel.setText(veiculoSelecionado.getPlaca());
				
			break;
		}
		
		return true;
	}
	
	public CadastrarVendaView(Venda venda) {
		this();
		
		if(venda == null) {
			return;
		}
		
		vendaEditar = venda;
		
		if(venda.getCliente() != null) {
			ArrayList<Cliente> c = new ArrayList<Cliente>();
			c.add(venda.getCliente());
			bclientes.updateTableItems(c);
			bclientes.selectFirst();
		}
		
		if(venda.getVeiculo() != null) {
			ArrayList<Veiculo> c = new ArrayList<Veiculo>();
			c.add(venda.getVeiculo());
			bveiculos.updateTableItems(c);
			bveiculos.selectFirst();
		}
		
		if(venda.getStatus() != null)
			vendaInfoView.status.setSelectedItem(Venda.getStatusAsString(venda.getStatus()));
		
		if(venda.getFormaPagamento() != null)
			vendaInfoView.pagamento.setSelectedItem(venda.getFormaPagamento().getPagamentoTipo());
		
		vendaInfoView.descricao.setText(venda.getDescricao());
	}
	
	/*-------------------------------FIM DA SEÇÃO DO CONSTRUTOR----------------------------------*/
	
	/*TODA VIEW DO TIPO CADASTRAR DEVE POSSUIR AS FUNÇÕES ABAIXO (ADAPTADAS PARA ELA) */
	
	/*
	 * Função utilitária para fechar a janela atual
	 */
	private void closeFrame(ActionEvent e) {
		Component component = (Component) e.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
		
		frame.dispose();
	}
	
	private void inserir(ActionEvent e) {
		Venda venda = null;
		
		if(validateFields()) {
			venda = getVenda();
		}
		
		(new VendaDAO()).insert(venda);
		
		closeFrame(e);
	}
	
	private boolean validateFields() {
		if(vendaInfoView.descricao.getText().isEmpty()
				|| ((String)vendaInfoView.status.getSelectedItem()).contentEquals("Selecione uma opção")
				|| ((String)vendaInfoView.pagamento.getSelectedItem()).contentEquals("Selecione uma opção")) {
			return false;
		}
		return true;
	}
	
	private Venda getVenda() {
		Venda venda = new Venda();
		
		venda.setCliente(clienteSelecionado);
		venda.setVeiculo(veiculoSelecionado);
		venda.setStatus(Venda.toStatus((String) vendaInfoView.status.getSelectedItem()));
		venda.setFormaPagamento(new FormaPagamentoDAO().getByTipo((String) vendaInfoView.pagamento.getSelectedItem()));
		venda.setData(new Date());
		venda.setDescricao(vendaInfoView.descricao.getText());
		
		return venda;
	}
	
	private void atualizar(ActionEvent e) {
		Venda venda = null;
		
		if(validateFields()) {
			venda = getVenda();
		}else {
			return;
		}
		
		venda.setId(vendaEditar.getId());
		(new VendaDAO()).update(venda);
		
		closeFrame(e);
	}
}
