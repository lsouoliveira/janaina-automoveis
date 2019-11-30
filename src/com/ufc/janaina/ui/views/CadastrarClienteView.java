package com.ufc.janaina.ui.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.ufc.janaina.jdbc.dao.ClienteDAO;
import com.ufc.janaina.models.Cliente;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CadastrarClienteView extends View {
	private Cliente cliente;
	private JPanel panel;
	private JLabel lblRg;
	private JTextField rg;
	private JLabel lblNome;
	private JTextField nome;
	private JLabel lblTelefone;
	private JLabel lblEndereco;
	private JLabel lblBairro;
	private JLabel lblCidade;
	private JLabel lblUf;
	private JTextField telefone;
	private JTextField endereco;
	private JTextField bairro;
	private JTextField numero;
	private JTextField cidade;
	private JComboBox uf;
	private JButton salvarBtn;
	private JButton cancelarBtn;
	private JLabel erroMsgLabel;
	
	/*
	 * Se o id do veículo passado não estiver definido
	 * assumimos que o objetivo é criar um novo veículo
	 * caso contrário, queremos atualizá-lo
	 * 
	 * Se então formos atualizá-lo, preenchemos
	 * os campos do formulário com as informações do veículo passado
	 */
	public CadastrarClienteView(Cliente cliente) {
		this();
		this.cliente = cliente;
		
		// Se o objetivo for editar o veículo
		// Atualizamos os campos com os valores do veículo passado
		if(cliente.getId() != -1) {
			// Altera o botão de salvar para "Atualizar"
			salvarBtn.setText("Atualizar");
			
			bairro.setText(cliente.getBairro());
			cidade.setText(cliente.getCidade());
			rg.setText(cliente.getRg());
			endereco.setText(cliente.getEndereco());
			nome.setText(cliente.getNome());
			numero.setText(String.valueOf(cliente.getNumero()));
			telefone.setText(cliente.getTelefone());
			uf.setSelectedItem(cliente.getUf());
			
			// Define a funçao do botão salvar para atualizar
			salvarBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					atualizar(e);
				}
			});
		}else {
			// Define a função do botão salvar para inserir
			salvarBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					inserir(e);
				}
				
			});
		}
	}
	
	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	public CadastrarClienteView() {
		setPreferredSize(new Dimension(480, 320));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{628, 0};
		gridBagLayout.rowHeights = new int[]{468, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new MigLayout("", "[120][grow][grow]", "[19px][][][][][][][][][]"));
		
		lblRg = new JLabel("RG");
		panel.add(lblRg, "cell 0 0,alignx trailing,aligny center");
		
		rg = new JTextField();
		panel.add(rg, "cell 1 0");
		rg.setColumns(10);
		rg.setMinimumSize(new Dimension(150, 19));
		
		lblNome = new JLabel("Nome");
		panel.add(lblNome, "cell 0 1,alignx trailing");
		
		nome = new JTextField();
		nome.setMinimumSize(new Dimension(240, 19));
		nome.setColumns(10);
		panel.add(nome, "cell 1 1");
		
		lblTelefone = new JLabel("Telefone");
		panel.add(lblTelefone, "cell 0 2,alignx trailing");
		
		telefone = new JTextField();
		telefone.setMinimumSize(new Dimension(120, 19));
		telefone.setColumns(10);
		panel.add(telefone, "cell 1 2");
		
		lblEndereco = new JLabel("Endereço");
		panel.add(lblEndereco, "cell 0 3,alignx trailing");
		
		endereco = new JTextField();
		endereco.setMinimumSize(new Dimension(240, 19));
		endereco.setColumns(10);
		panel.add(endereco, "cell 1 3");
		
		lblBairro = new JLabel("Bairro");
		panel.add(lblBairro, "cell 0 4,alignx trailing");
		
		bairro = new JTextField();
		bairro.setMinimumSize(new Dimension(150, 19));
		bairro.setColumns(10);
		panel.add(bairro, "cell 1 4");
		
		lblCidade = new JLabel("Número");
		panel.add(lblCidade, "cell 0 5,alignx trailing");
		
		numero = new JTextField();
		numero.setMinimumSize(new Dimension(120, 19));
		numero.setColumns(10);
		panel.add(numero, "cell 1 5");
		
		lblUf = new JLabel("Cidade");
		panel.add(lblUf, "cell 0 6,alignx trailing");
		
		cidade = new JTextField();
		cidade.setMinimumSize(new Dimension(150, 19));
		cidade.setColumns(10);
		panel.add(cidade, "flowx,cell 1 6");
		
		uf = new JComboBox();
		uf.setModel(new DefaultComboBoxModel(new String[] {"UF", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		panel.add(uf, "cell 1 6,growx");
		
		salvarBtn = new JButton("Salvar");
		panel.add(salvarBtn, "flowx,cell 1 7");
		
		cancelarBtn = new JButton("Cancelar");
		panel.add(cancelarBtn, "cell 1 7");
		
		erroMsgLabel = new JLabel("Mensagem de erro");
		erroMsgLabel.setForeground(Color.RED);
		erroMsgLabel.setText("");
		panel.add(erroMsgLabel, "cell 1 8");
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
		try {
			Integer.parseInt(numero.getText());
		} catch(Exception e) {
			erroMsgLabel.setText("Erro: Número inválido");
			return false;
		}
		
		if(rg.getText().isEmpty()
				|| nome.getText().isEmpty()
				|| cidade.getText().isEmpty()
				|| endereco.getText().isEmpty()
				|| bairro.getText().isEmpty()
				|| numero.getText().isEmpty()
				|| telefone.getText().isEmpty()
				|| uf.getSelectedItem().toString() == "UF"
				) {
			erroMsgLabel.setText("Erro: Preencha todos os campos");
			return false;
		}
		
		erroMsgLabel.setText("");
		
		return true;
	}
	
	// Cria um veículo com as informações preenchidas no formulário
	public Cliente getCliente() {
		Cliente v = new Cliente();
		
		v.setBairro(bairro.getText());
		v.setCidade(cidade.getText());
		v.setRg(rg.getText());
		v.setEndereco(endereco.getText());
		v.setNome(nome.getText());
		v.setNumero(Integer.parseInt(numero.getText()));
		v.setTelefone(telefone.getText());
		v.setUf(uf.getSelectedItem().toString());
		
		return v;
	}
	
	// Insere um veículo no banco de dados
	private void inserir(ActionEvent e) {
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente v = null;
		
		if(validateFields()) {
			v = getCliente();
		}else {
			return;
		}
		
		clienteDAO.insert(v);
		
		closeFrame(e);
	}
	
	// Atualiza o veículo do banco de dados
	private void atualizar(ActionEvent e) {
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente v = null;
		
		if(validateFields()) {
			v = getCliente();
		}else {
			return;
		}
		
		v.setId(cliente.getId());
		
		clienteDAO.update(v);
		
		closeFrame(e);
	}
}
