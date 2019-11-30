package com.ufc.janaina.ui.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.ufc.janaina.jdbc.dao.ClienteDAO;
import com.ufc.janaina.jdbc.dao.TesteDAO;
import com.ufc.janaina.jdbc.dao.VeiculoDAO;
import com.ufc.janaina.models.Cliente;
import com.ufc.janaina.models.Teste;
import com.ufc.janaina.models.Veiculo;
import com.ufc.janaina.utils.DateFormat;

public class CadastrarTesteView extends View {
	
	private JPanel centroCard;
	private JPanel botoes;
	private JButton voltar;
	private JButton proximo;
	private JPanel panel;
	private JLabel textoSelecao;
	private Teste testeEditar;
	
	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	
	CardLayout cl_centroCard;
	
	BuscarClientesView bclientes = new BuscarClientesView(false);
	BuscarVeiculosView bveiculos = new BuscarVeiculosView(false);
	TesteCadastro2View testeCadastro = new TesteCadastro2View();
	
	private int tela_atual = 0;
	
	private int cliente_selecionado;
	private int veiculo_selecionado;
	
	private Cliente cliente;
	private Veiculo veiculo;
	
	private boolean confirmar = false;
	
	public CadastrarTesteView() {
		setLayout(new BorderLayout(0, 0));
		
		centroCard = new JPanel();
		add(centroCard, BorderLayout.CENTER);
		
        cl_centroCard = new CardLayout(0,0);
		
		centroCard.setLayout(cl_centroCard);
		
		centroCard.add(bclientes, "0");
		centroCard.add(bveiculos, "1");
		centroCard.add(testeCadastro,"2");
		
		cl_centroCard.show(centroCard, "0");
		
		botoes = new JPanel();
		add(botoes, BorderLayout.SOUTH);
		
		voltar = new JButton("  Voltar  ");
		voltar.setEnabled(false);
		botoes.add(voltar);
		
		proximo = new JButton("Pŕoximo");
		botoes.add(proximo);
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		textoSelecao = new JLabel("Selecione um cliente");
		panel.add(textoSelecao);
		
		proximo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( confirmar ) {
					
					if(testeEditar != null)
						atualizar(e);
					else
						inserir(e);
				}else {
					tela_atual++;
					if(!MudarTela()) {
						tela_atual--;
					}
				}
				
			}
			
		});
		
		voltar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				tela_atual--;
				if(!MudarTela()) {
					tela_atual++;
				}
			}
			
		});
		
	}
	
	public CadastrarTesteView(Teste teste) {
		this();
		
		if(teste == null) {
			return;
		}
		
		testeEditar = teste;
		
		if(teste.getCliente() != null) {
			ArrayList<Cliente> c = new ArrayList<Cliente>();
			c.add(teste.getCliente());
			bclientes.updateTableItems(c);
			bclientes.selectFirst();
		}
		
		if(teste.getVeiculo() != null) {
			ArrayList<Veiculo> c = new ArrayList<Veiculo>();
			c.add(teste.getVeiculo());
			bveiculos.updateTableItems(c);
			bveiculos.selectFirst();
		}
		
		if(teste.getData_realizacao() != null) {
			testeCadastro.setData(DateFormat.getFullDate(teste.getData_realizacao()));
		}
		
		if(teste.getHorario_realizacao() != null) {
			testeCadastro.setHorario(DateFormat.getFullTime(teste.getHorario_realizacao()));
		}
	}
	
	
	private boolean MudarTela() {
		
		switch( tela_atual ) {
		case 0:
			textoSelecao.setText("Selecione um cliente");
			cliente = null;
			confirmar = false;
			cl_centroCard.show(centroCard, "0");
			voltar.setEnabled(false);
			proximo.setText("Próximo");
			proximo.setEnabled(true);
			break;
		case 1:
			cliente_selecionado = bclientes.getSelectedClienteId();
			veiculo = null;
			confirmar = false;
			if( cliente_selecionado != -1 ) {
				cliente = (Cliente) (new ClienteDAO()).get(cliente_selecionado);
				textoSelecao.setText("Selecione um veículo");
				cl_centroCard.show(centroCard, "1");
				voltar.setEnabled(true);
				proximo.setText("Próximo");
				proximo.setEnabled(true);
			
			}else {
				return false;
			}
			
			break;
			
		case 2:
			veiculo_selecionado = bveiculos.getSelectedVeiculoId();
			confirmar = true;
			if( veiculo_selecionado != -1 ) {
				veiculo = (Veiculo) (new VeiculoDAO()).get(veiculo_selecionado);
				textoSelecao.setText("Confirme e Informe os demais Dados");
				voltar.setEnabled(true);
				proximo.setEnabled(true);
				if(testeEditar != null)
					proximo.setText("Atualizar");
				else
					proximo.setText("Cadastrar");
				cl_centroCard.show(centroCard, "2");
				testeCadastro.setCliente(cliente);
				testeCadastro.setVeiculo(veiculo);
				
			}else {
				return false;	
			}
			
		}
		
		return true;
		
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
		Teste teste = null;
		
		if(validateFields()) {
			teste = getTeste();
		}
		
		(new TesteDAO()).insert(teste);
		
		closeFrame(e);
	}
	
	private boolean validateFields() {
		if(testeCadastro.getData().isEmpty() || testeCadastro.getHorario().isEmpty()) {
			return false;
		}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String date = testeCadastro.getData() + " " + testeCadastro.getHorario();
			format.parse(date);
		} catch (ParseException e) {
			return false;
		}
		
		return true;
	}
	
	private Teste getTeste() {
		Teste teste = new Teste();
		
		teste.setCliente(cliente);
		teste.setVeiculo(veiculo);
		teste.setData_cadastro(new Date());
		
		Date dRealizacao = null;
		Date dCadastro = null;
		Date hRealizacao = null;
		Date hCadastro = null;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String date = testeCadastro.getData();
			dRealizacao = format.parse(date);
		} catch (ParseException e) {}
		
		dCadastro = new Date();
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
			String date = testeCadastro.getHorario();
			hRealizacao = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		hCadastro = (Date) dCadastro.clone();
		
		teste.setData_realizacao(dRealizacao);
		teste.setData_cadastro(dCadastro);
		teste.setHorario_realizacao(hRealizacao);
		teste.setHorario_cadastro(hCadastro);
		
		return teste;
	}
	
	private void atualizar(ActionEvent e) {
		Teste teste = null;
		
		if(validateFields()) {
			teste = getTeste();
		}else {
			return;
		}
		
		teste.setId(testeEditar.getId());
		(new TesteDAO()).update(teste);
		
		closeFrame(e);
	}
}
