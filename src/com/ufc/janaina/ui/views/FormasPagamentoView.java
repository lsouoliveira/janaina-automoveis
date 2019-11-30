package com.ufc.janaina.ui.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.ufc.janaina.jdbc.dao.FormaPagamentoDAO;
import com.ufc.janaina.models.Cliente;
import com.ufc.janaina.models.FormaPagamento;

public class FormasPagamentoView extends View {
	private Cliente cliente;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JList list;
	private JLabel lblFormaDePagamento;
	private JTextField formaPagamentoText;
	private JButton adicionarBtn;
	private JButton removerBtn;
	
	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	public FormasPagamentoView() {
		setPreferredSize(new Dimension(640, 480));
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1, BorderLayout.NORTH);
		
		lblFormaDePagamento = new JLabel("Forma de Pagamento");
		panel_1.add(lblFormaDePagamento);
		
		formaPagamentoText = new JTextField();
		panel_1.add(formaPagamentoText);
		formaPagamentoText.setColumns(10);
		
		adicionarBtn = new JButton("Adicionar");
		panel_1.add(adicionarBtn);
		
		removerBtn = new JButton("Remover");
		panel_1.add(removerBtn);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		list = new JList();
		list.setModel(new DefaultListModel());
		
		panel_2.add(list);
		
		// Inicialização
		atualizarLista();
		
		// ActionListerner dos botões
		adicionarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inserir(e);
			}
		});
		
		removerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
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
	
	// Valida os campos do formulário
	private boolean validateFields() {
		
		if(formaPagamentoText.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	// Cria um veículo com as informações preenchidas no formulário
	public FormaPagamento getFormaPagamento() {
		FormaPagamento formaPagamento = new FormaPagamento();
		
		formaPagamento.setPagamentoTipo(formaPagamentoText.getText());
		
		return formaPagamento;
	}
	
	// Insere um veículo no banco de dados
	private void inserir(ActionEvent e) {
		FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();
		
		FormaPagamento formaPagamento = null;
		
		if(validateFields()) {
			formaPagamento = getFormaPagamento();
		}else {
			JOptionPane.showMessageDialog(this, "Informe a forma de pagamento");
			return;
		}
		
		formaPagamentoDAO.insert(formaPagamento);
		formaPagamentoText.setText("");
		atualizarLista();
	}
	
	private void atualizarLista() {
		limparLista();
		
		for(FormaPagamento e : new FormaPagamentoDAO().getAll()) {
			((DefaultListModel)list.getModel()).addElement(e.getPagamentoTipo());
		}
	}
	
	private void limparLista() {
		((DefaultListModel)list.getModel()).clear();
	}
	
	private void remover() {
		if(list.getSelectedIndex() == -1){ 
			return;
		}
		
		FormaPagamentoDAO fpDAO = new FormaPagamentoDAO();
		
		FormaPagamento fp = fpDAO.getByTipo((String)list.getSelectedValue());
		fpDAO.delete(fp);
		atualizarLista();
	}
}
