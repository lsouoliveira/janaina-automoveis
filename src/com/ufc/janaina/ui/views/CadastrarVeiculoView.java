package com.ufc.janaina.ui.views;

import java.awt.BorderLayout;
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

import com.ufc.janaina.jdbc.dao.VeiculoDAO;
import com.ufc.janaina.models.Veiculo;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class CadastrarVeiculoView extends View {
	private Veiculo veiculo;
	private JPanel panel;
	private JLabel lblNDeChassi;
	private JLabel lblModelo;
	private JLabel lblModelo_1;
	private JLabel lblAno;
	private JLabel lblCor;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel formGroup;
	private JTextField chassiText;
	private JPanel panel_4;
	private JTextField placaText;
	private JPanel panel_5;
	private JTextField modeloText;
	private JPanel panel_6;
	private JTextField anoText;
	private JPanel panel_7;
	private JTextField corText;
	
	// LABEL PARA A MENSAGEM DE ERRO
	private JLabel erroMsgLabel;
	
	// BUTTONS
	private JButton cancelarBtn;
	private JButton salvarBtn;
	private JLabel lblPreo;
	private JPanel panel_8;
	private JTextField precoText;
	
	/*
	 * Se o id do veículo passado não estiver definido
	 * assumimos que o objetivo é criar um novo veículo
	 * caso contrário, queremos atualizá-lo
	 * 
	 * Se então formos atualizá-lo, preenchemos
	 * os campos do formulário com as informações do veículo passado
	 */
	public CadastrarVeiculoView(Veiculo veiculo) {
		this();
		this.veiculo = veiculo;
		
		//Define a funcao do botao de cancelar
		cancelarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar(e);
			}
		});
		
		// Se o objetivo for editar o veículo
		// Atualizamos os campos com os valores do veículo passado
		if(veiculo.getId() != -1) {
			// Altera o botão de salvar para "Atualizar"
			salvarBtn.setText("Atualizar");
			
			// Preenche os campos do formulário
			chassiText.setText(veiculo.getChassi());
			placaText.setText(veiculo.getPlaca());
			anoText.setText(String.valueOf(veiculo.getAno()));
			modeloText.setText(veiculo.getModelo());
			corText.setText(veiculo.getCor());
			
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
	public CadastrarVeiculoView() {
		
		//claro cancela
		
		
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
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblNDeChassi = new JLabel("N° de chassi");
		GridBagConstraints gbc_lblNDeChassi = new GridBagConstraints();
		gbc_lblNDeChassi.anchor = GridBagConstraints.EAST;
		gbc_lblNDeChassi.ipadx = 15;
		gbc_lblNDeChassi.ipady = 15;
		gbc_lblNDeChassi.insets = new Insets(0, 0, 5, 5);
		gbc_lblNDeChassi.gridx = 0;
		gbc_lblNDeChassi.gridy = 0;
		panel.add(lblNDeChassi, gbc_lblNDeChassi);
		
		formGroup = new JPanel();
		GridBagConstraints gbc_formGroup = new GridBagConstraints();
		gbc_formGroup.fill = GridBagConstraints.BOTH;
		gbc_formGroup.insets = new Insets(0, 0, 5, 0);
		gbc_formGroup.gridx = 1;
		gbc_formGroup.gridy = 0;
		panel.add(formGroup, gbc_formGroup);
		formGroup.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		chassiText = new JTextField();
		formGroup.add(chassiText);
		chassiText.setColumns(13);
		
		lblModelo = new JLabel("Placa");
		GridBagConstraints gbc_lblModelo = new GridBagConstraints();
		gbc_lblModelo.ipadx = 15;
		gbc_lblModelo.ipady = 15;
		gbc_lblModelo.anchor = GridBagConstraints.EAST;
		gbc_lblModelo.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelo.gridx = 0;
		gbc_lblModelo.gridy = 1;
		panel.add(lblModelo, gbc_lblModelo);
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 1;
		panel.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		placaText = new JTextField();
		placaText.setColumns(7);
		panel_4.add(placaText);
		
		lblModelo_1 = new JLabel("Modelo");
		GridBagConstraints gbc_lblModelo_1 = new GridBagConstraints();
		gbc_lblModelo_1.ipadx = 15;
		gbc_lblModelo_1.ipady = 15;
		gbc_lblModelo_1.anchor = GridBagConstraints.EAST;
		gbc_lblModelo_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblModelo_1.gridx = 0;
		gbc_lblModelo_1.gridy = 2;
		panel.add(lblModelo_1, gbc_lblModelo_1);
		
		panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 2;
		panel.add(panel_5, gbc_panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		modeloText = new JTextField();
		modeloText.setColumns(10);
		panel_5.add(modeloText);
		
		lblAno = new JLabel("Ano");
		GridBagConstraints gbc_lblAno = new GridBagConstraints();
		gbc_lblAno.ipadx = 15;
		gbc_lblAno.ipady = 15;
		gbc_lblAno.anchor = GridBagConstraints.EAST;
		gbc_lblAno.insets = new Insets(0, 0, 5, 5);
		gbc_lblAno.gridx = 0;
		gbc_lblAno.gridy = 3;
		panel.add(lblAno, gbc_lblAno);
		
		panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 3;
		panel.add(panel_6, gbc_panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		anoText = new JTextField();
		anoText.setColumns(4);
		panel_6.add(anoText);
		
		lblCor = new JLabel("Cor");
		GridBagConstraints gbc_lblCor = new GridBagConstraints();
		gbc_lblCor.ipadx = 15;
		gbc_lblCor.ipady = 15;
		gbc_lblCor.anchor = GridBagConstraints.EAST;
		gbc_lblCor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCor.gridx = 0;
		gbc_lblCor.gridy = 4;
		panel.add(lblCor, gbc_lblCor);
		
		panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 4;
		panel.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		corText = new JTextField();
		corText.setColumns(10);
		panel_7.add(corText);
		
		lblPreo = new JLabel("Preço    ");
		GridBagConstraints gbc_lblPreo = new GridBagConstraints();
		gbc_lblPreo.anchor = GridBagConstraints.EAST;
		gbc_lblPreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreo.gridx = 0;
		gbc_lblPreo.gridy = 5;
		panel.add(lblPreo, gbc_lblPreo);
		
		panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.anchor = GridBagConstraints.WEST;
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.VERTICAL;
		gbc_panel_8.gridx = 1;
		gbc_panel_8.gridy = 5;
		panel.add(panel_8, gbc_panel_8);
		
		precoText = new JTextField();
		panel_8.add(precoText);
		precoText.setColumns(10);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 6;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel_1.add(panel_2);
		
		salvarBtn = new JButton("Salvar");
		panel_2.add(salvarBtn);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(0);
		panel_1.add(panel_3);
		
		cancelarBtn = new JButton("Cancelar");
		panel_3.add(cancelarBtn);
		
		erroMsgLabel = new JLabel("Mensagem de erro");
		erroMsgLabel.setForeground(Color.RED);
		erroMsgLabel.setText("");
		GridBagConstraints gbc_erroMsgLabel = new GridBagConstraints();
		gbc_erroMsgLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_erroMsgLabel.gridx = 1;
		gbc_erroMsgLabel.gridy = 7;
		panel.add(erroMsgLabel, gbc_erroMsgLabel);
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
			Integer.parseInt(anoText.getText());
			Double.valueOf(precoText.getText());
		} catch(Exception e) {
			erroMsgLabel.setText("Erro: Valores inválidos");
			return false;
		}
		
		if(chassiText.getText().isEmpty() || corText.getText().isEmpty()
				|| placaText.getText().isEmpty() || placaText.getText().isEmpty()) {
			erroMsgLabel.setText("Erro: Preencha todos os campos");
			return false;
		}
		
		erroMsgLabel.setText("");
		
		return true;
	}
	
	// Cria um veículo com as informações preenchidas no formulário
	public Veiculo getVeiculo() {
		Veiculo v = new Veiculo();
		
		v.setAno(Integer.parseInt(anoText.getText()));
		v.setChassi(chassiText.getText());
		v.setCor(corText.getText());
		v.setModelo(modeloText.getText());
		v.setPlaca(placaText.getText());
		v.setPreco(Double.valueOf(precoText.getText()));
		
		return v;
	}
	
	// Insere um veículo no banco de dados
	private void inserir(ActionEvent e) {
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		
		Veiculo v = null;
		
		if(validateFields()) {
			v = getVeiculo();
		}else {
			return;
		}
		
		veiculoDAO.insert(v);
		closeFrame(e);
	}
	
	private void cancelar(ActionEvent e) {
		closeFrame(e);
	}
	
	// Atualiza o veículo do banco de dados
	private void atualizar(ActionEvent e) {
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		
		Veiculo v = null;
		
		if(validateFields()) {
			v = getVeiculo();
		}else {
			return;
		}
		
		v.setId(veiculo.getId());
		
		veiculoDAO.update(v);
		closeFrame(e);
	}
}
