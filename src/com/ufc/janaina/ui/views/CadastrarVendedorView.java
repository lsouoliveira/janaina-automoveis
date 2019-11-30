package com.ufc.janaina.ui.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.ufc.janaina.jdbc.dao.UserDAO;
import com.ufc.janaina.jdbc.dao.VendedorDAO;
import com.ufc.janaina.models.Usuario;
import com.ufc.janaina.models.Vendedor;

import net.miginfocom.swing.MigLayout;

public class CadastrarVendedorView extends View {
	private Vendedor vendedor;
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
	private JLabel passwordLabel;
	private JPasswordField password;
	private JLabel Usuário;
	private JTextField usuario;
	
	/*
	 * Se o id do veículo passado não estiver definido
	 * assumimos que o objetivo é criar um novo veículo
	 * caso contrário, queremos atualizá-lo
	 * 
	 * Se então formos atualizá-lo, preenchemos
	 * os campos do formulário com as informações do veículo passado
	 */
	public CadastrarVendedorView(Vendedor vendedor) {
		this();
		this.vendedor = vendedor;
		
		// Se o objetivo for editar o veículo
		// Atualizamos os campos com os valores do veículo passado
		if(vendedor.getId() != -1) {
			// Altera o botão de salvar para "Atualizar"
			salvarBtn.setText("Atualizar");
			
			if(vendedor.getUsuario() != null) {
				usuario.setText(vendedor.getUsuario().getUsername());
				password.setText(vendedor.getUsuario().getPassword());
			}
			bairro.setText(vendedor.getBairro());
			cidade.setText(vendedor.getCidade());
			rg.setText(vendedor.getRg());
			endereco.setText(vendedor.getEndereco());
			nome.setText(vendedor.getNome());
			numero.setText(String.valueOf(vendedor.getNumero()));
			telefone.setText(vendedor.getTelefone());
			uf.setSelectedItem(vendedor.getUf());
			
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
	public CadastrarVendedorView() {
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
		panel.setLayout(new MigLayout("", "[120][grow][grow]", "[][][19px][][][][][][][][][]"));
		
		Usuário = new JLabel("Usuário");
		panel.add(Usuário, "cell 0 0,alignx trailing");
		
		usuario = new JTextField();
		panel.add(usuario, "cell 1 0,growx");
		usuario.setColumns(10);
		
		passwordLabel = new JLabel("Password");
		panel.add(passwordLabel, "cell 0 1,alignx trailing");
		
		password = new JPasswordField();
		panel.add(password, "cell 1 1,growx");
		
		lblRg = new JLabel("RG");
		panel.add(lblRg, "cell 0 2,alignx trailing,aligny center");
		
		rg = new JTextField();
		panel.add(rg, "cell 1 2");
		rg.setColumns(10);
		rg.setMinimumSize(new Dimension(150, 19));
		
		lblNome = new JLabel("Nome");
		panel.add(lblNome, "cell 0 3,alignx trailing");
		
		nome = new JTextField();
		nome.setMinimumSize(new Dimension(240, 19));
		nome.setColumns(10);
		panel.add(nome, "cell 1 3");
		
		lblTelefone = new JLabel("Telefone");
		panel.add(lblTelefone, "cell 0 4,alignx trailing");
		
		telefone = new JTextField();
		telefone.setMinimumSize(new Dimension(120, 19));
		telefone.setColumns(10);
		panel.add(telefone, "cell 1 4");
		
		lblEndereco = new JLabel("Endereço");
		panel.add(lblEndereco, "cell 0 5,alignx trailing");
		
		endereco = new JTextField();
		endereco.setMinimumSize(new Dimension(240, 19));
		endereco.setColumns(10);
		panel.add(endereco, "cell 1 5");
		
		lblBairro = new JLabel("Bairro");
		panel.add(lblBairro, "cell 0 6,alignx trailing");
		
		bairro = new JTextField();
		bairro.setMinimumSize(new Dimension(150, 19));
		bairro.setColumns(10);
		panel.add(bairro, "cell 1 6");
		
		lblCidade = new JLabel("Número");
		panel.add(lblCidade, "cell 0 7,alignx trailing");
		
		numero = new JTextField();
		numero.setMinimumSize(new Dimension(120, 19));
		numero.setColumns(10);
		panel.add(numero, "cell 1 7");
		
		lblUf = new JLabel("Cidade");
		panel.add(lblUf, "cell 0 8,alignx trailing");
		
		cidade = new JTextField();
		cidade.setMinimumSize(new Dimension(150, 19));
		cidade.setColumns(10);
		panel.add(cidade, "flowx,cell 1 8");
		
		uf = new JComboBox();
		uf.setModel(new DefaultComboBoxModel(new String[] {"UF", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		panel.add(uf, "cell 1 8,growx");
		
		salvarBtn = new JButton("Salvar");
		panel.add(salvarBtn, "flowx,cell 1 9");
		
		cancelarBtn = new JButton("Cancelar");
		panel.add(cancelarBtn, "cell 1 9");
		
		erroMsgLabel = new JLabel("Mensagem de erro");
		erroMsgLabel.setForeground(Color.RED);
		erroMsgLabel.setText("");
		panel.add(erroMsgLabel, "cell 1 10");
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
				|| usuario.getText().isEmpty()
				|| password.getText().isEmpty()
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
	public Vendedor getVendedor() {
		Vendedor v = new Vendedor();
		
		Usuario u = new Usuario();
		u.setUsername(usuario.getText());
		u.setPassword(password.getText());
		u.setAdminLevel(Usuario.AdminLevel.VENDEDOR);
		
		v.setUsuario(u);
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
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		Vendedor v = null;
		
		if(validateFields()) {
			v = getVendedor();
		}else {
			return;
		}
		
		vendedorDAO.insert(v);
		
		closeFrame(e);
	}
	
	// Atualiza o veículo do banco de dados
	private void atualizar(ActionEvent e) {
		VendedorDAO vendedorDAO = new VendedorDAO();
		
		Vendedor v = null;
		
		if(validateFields()) {
			v = getVendedor();
		}else {
			return;
		}
		
		v.setId(vendedor.getId());
		v.getUsuario().setId(vendedor.getUsuario().getId());
		
		vendedorDAO.update(v);
		
		closeFrame(e);
	}
}
