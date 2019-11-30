package com.ufc.janaina.ui.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.ufc.janaina.jdbc.dao.TesteDAO;
import com.ufc.janaina.jdbc.filters.TesteFilter;
import com.ufc.janaina.models.Teste;
import com.ufc.janaina.ui.windows.BlankWindow;
import com.ufc.janaina.utils.DateFormat;

import net.miginfocom.swing.MigLayout;

public class BuscarTestesView extends View {
	private JTable table;
	private JLabel labelDadosInvalidos;
	
	// BUTTONS
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JButton btnEditar;
	private JCheckBox apenasRealizados;
	private JCheckBox apenasAvaliados;
	private JTextField rg;
	private JTextField nome;
	private JButton btnRealizar;
	private JButton btnAvaliar;
	private JLabel lblModeloDoVeculo;
	private JLabel lblPlacaDoVeculo;
	private JTextField modelo;
	private JTextField placa;
	private JLabel lblNewLabel;
	private JLabel lblVeculo;
	private JLabel lblNewLabel_1;
	private JTextField cor;
	private JLabel lblNewLabel_2;
	private JTextField chassi;
	private JLabel lblData;
	private JLabel lblHorrioDeRealizao;
	private JLabel lblHorrioDeCadastro;
	private JFormattedTextField dataRealizacao;
	private JFormattedTextField dataCadastro;
	private JFormattedTextField horarioRealizacao;
	private JFormattedTextField horarioCadastro;
	
	/**
	 * Create the panel.
	 */
	public BuscarTestesView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][]", "[][][][][][][][][][][][]"));
		
		lblNewLabel = new JLabel("Cliente");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		panel.add(lblNewLabel, "cell 0 0");
		
		JLabel lblMarca = new JLabel("   Nome:");
		panel.add(lblMarca, "flowx,cell 0 1,alignx left");
		
		JLabel lblNDeChassi = new JLabel("   RG:");
		panel.add(lblNDeChassi, "flowx,cell 0 2,alignx left");
		
		lblVeculo = new JLabel("Veículo");
		lblVeculo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblVeculo.setForeground(new Color(0, 0, 128));
		panel.add(lblVeculo, "cell 0 3");
		
		lblModeloDoVeculo = new JLabel("   Modelo:");
		panel.add(lblModeloDoVeculo, "flowx,cell 0 4");
		
		lblNewLabel_1 = new JLabel("Cor:");
		panel.add(lblNewLabel_1, "flowx,cell 1 4,alignx left");
		
		lblPlacaDoVeculo = new JLabel("   Placa:");
		panel.add(lblPlacaDoVeculo, "flowx,cell 0 5");
		
		lblNewLabel_2 = new JLabel("Chassi:");
		panel.add(lblNewLabel_2, "flowx,cell 1 5,alignx left");
		
		lblData = new JLabel("Data e Horário");
		lblData.setFont(new Font("Dialog", Font.BOLD, 13));
		lblData.setForeground(new Color(0, 0, 128));
		panel.add(lblData, "cell 0 6");
		
		JLabel lblEndereo = new JLabel("   Data de Realização:");
		panel.add(lblEndereo, "flowx,cell 0 7");
		
		lblHorrioDeRealizao = new JLabel("Horário de Realização:");
		panel.add(lblHorrioDeRealizao, "flowx,cell 1 7");
		
		JLabel lblCidade = new JLabel("   Data de Cadastro:");
		panel.add(lblCidade, "flowx,cell 0 8");
		
		lblHorrioDeCadastro = new JLabel("Horário de Cadastro:");
		panel.add(lblHorrioDeCadastro, "flowx,cell 1 8");
		
		apenasAvaliados = new JCheckBox("Apenas Testes não avaliados");
		panel.add(apenasAvaliados, "cell 1 9");
		
		btnPesquisar = new JButton("Pesquisar");
		panel.add(btnPesquisar, "flowx,cell 0 11");
		
		labelDadosInvalidos = new JLabel("Erro: Dados inválidos");
		labelDadosInvalidos.setForeground(Color.RED);
		labelDadosInvalidos.setVisible(false);
		panel.add(labelDadosInvalidos, "cell 0 11");
		
		apenasRealizados = new JCheckBox("Apenas Testes não realizados");
		panel.add(apenasRealizados, "flowx,cell 0 9");
		
		placa = new JTextField();
		panel.add(placa, "cell 0 5,growx");
		placa.setColumns(10);
		
		modelo = new JTextField();
		panel.add(modelo, "cell 0 4,growx");
		modelo.setColumns(10);
		
		rg = new JTextField();
		panel.add(rg, "cell 0 2,growx");
		rg.setColumns(10);
		
		nome = new JTextField();
		panel.add(nome, "cell 0 1,growx");
		nome.setColumns(10);
		
		cor = new JTextField();
		panel.add(cor, "cell 1 4,growx");
		cor.setColumns(10);
		
		chassi = new JTextField();
		panel.add(chassi, "cell 1 5,growx");
		chassi.setColumns(10);
		
		try {
			dataRealizacao = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(dataRealizacao, "cell 0 7,growx");
		
		try {
			dataCadastro = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(dataCadastro, "cell 0 8,growx");
		
		try {
			horarioRealizacao = new JFormattedTextField(new MaskFormatter("##:##:##"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(horarioRealizacao, "cell 1 7,growx");
		
		try {
			horarioCadastro = new JFormattedTextField(new MaskFormatter("##:##:##"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.add(horarioCadastro, "cell 1 8,growx");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[1px][grow]", "[1px][grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "cell 1 1,grow");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Realizada" , "Avaliada" ,"Data Cadastro", "Horário Cadastro", "Data Realização", "Horário Realização", "Cliente RG", "Cliente Nome", "Veículo Modelo", "Veículo Placa", "Chassi", "Cor"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		
		add(panel_2, BorderLayout.SOUTH);
		
		panel_2.setLayout(new MigLayout("", "[][][][][]", "[]"));
		
		btnAdicionar = new JButton("Adicionar");
		panel_2.add(btnAdicionar, "cell 0 0");
		
		btnEditar = new JButton("Editar");
		panel_2.add(btnEditar, "cell 1 0");
		
		btnRemover = new JButton("Remover");
		panel_2.add(btnRemover, "cell 2 0");
		
		btnRealizar = new JButton("Realizar");
		btnRealizar.setForeground(new Color(255, 255, 255));
		btnRealizar.setBackground(new Color(0, 139, 139));
		panel_2.add(btnRealizar, "cell 3 0");
		
		btnAvaliar = new JButton("Avaliar");
		btnAvaliar.setForeground(new Color(255, 255, 255));
		btnAvaliar.setBackground(SystemColor.info);
		panel_2.add(btnAvaliar, "cell 4 0");
		
		// define as funções dos botões
		setButtonHandlers();
	}
	
/*-------------------------------FIM DA SEÇÃO DO CONSTRUTOR----------------------------------*/
	
	
	/*TODA VIEW DO TIPO BUSCAR DEVE POSSUIR AS FUNÇÕES ABAIXO (ADAPTADAS PARA ELA) */
	
	/*
	 * FUNÇÃO QUE DEFINE AS FUNÇÕES DE CADA BOTÃO
	 */
	
	void setButtonHandlers() {
		/*
		 * Ao clicar no botão de pesquisar
		 * atualiza os resultados da tabela
		 */
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateTable();
				dataRealizacao.setValue(null);
				horarioRealizacao.setValue(null);
				horarioCadastro.setValue(null);
				dataCadastro.setValue(null);
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = getSelectedTesteId();
				
				if(id == -1) {
					return;
				}
				
				TesteDAO testeDAO = new TesteDAO();
				Teste teste = (Teste)testeDAO.get(id);
				
				new BlankWindow(new CadastrarTesteView(teste), null, "Atualizar Teste", true, false).launch();
			}
		});
		
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remover(e);
			}
		});
		
		/*
		 * Ao clicar no botão de adicionar
		 * abre uma view "CadastrarVeiculoView"
		 */
		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BlankWindow(new CadastrarTesteView(), null, "Cadastrar teste", true, false).launch();
			}
			
		});
		
		btnRealizar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selec = getSelectedTesteId();
				
				if( selec == -1 ) {
					
				}else {
					
					TesteDAO testeDAO = new TesteDAO();
					
					Teste teste = (Teste) testeDAO.get(selec); 
					
					teste.setRealizado(!teste.isRealizado());
					testeDAO.update(teste);
					
					updateTable();
				}
				
			}
		});
		
		btnAvaliar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selec = getSelectedTesteId();
				
				if( selec == -1 ) {
					
				}else {
					
					TesteDAO testeDAO = new TesteDAO();
					
					Teste teste = (Teste) testeDAO.get(selec); 
					
					//teste.setAvaliado(!teste.isAvaliado());
					//testeDAO.update(teste);
					
					new BlankWindow(new CadastrarAvaliacaoCarro(teste), null, "Cadastrar avaliação", true, false).launch();
				}
				
			}
		});
	}
	/*
	 * FUNÇÕES RELACIONADAS A TABELA DE RESULTADOS
	 * contém:
	 * updateTable: consulta o banco de dados e insere os resultados
	 * getSelectedVeiculoId: retorna o id da linha da tabela selecionada
	 */
	
	private void updateTable() {
		labelDadosInvalidos.setVisible(false);
		
		TesteDAO testeDAO = new TesteDAO();
		
		TesteFilter testeFilter = getTesteFilter();
		
		if(testeFilter == null) {
			//JOptionPanel.show(this, "foiewjfo");
			return;
		}
		
		updateTableItems(testeDAO.getAll(testeFilter));
	}
	
	public void updateTableItems(List<Teste> list) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		for(Teste t : list) {
			model.addRow(new Object[] {
					t.getId(),
					t.isRealizado(),
					t.isAvaliado(),
					DateFormat.getDate(t.getData_cadastro()),
					DateFormat.getTime(t.getHorario_cadastro()),
					DateFormat.getDate(t.getData_realizacao()),
					DateFormat.getTime(t.getHorario_realizacao()),
					t.getCliente().getRg(),
					t.getCliente().getNome(),
					t.getVeiculo().getModelo(),
					t.getVeiculo().getPlaca(),
					t.getVeiculo().getChassi(),
					t.getVeiculo().getCor()
			});
		}
	}
	
	public int getSelectedTesteId() {
		int selectedRow = table.getSelectedRow();				
		
		if(selectedRow == -1) {
			return -1;
		}
		
		int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		
		return id;
	}
	
	private TesteFilter getTesteFilter() {
		TesteFilter testeFilter = new TesteFilter();
	
		testeFilter.rg = rg.getText();
		testeFilter.nome = nome.getText();
		testeFilter.modelo = modelo.getText();
		testeFilter.cor = cor.getText();
		testeFilter.chassi = chassi.getText();
		testeFilter.placa = placa.getText();
		
		//=====================================
		Date dRealizacao = null;
		Date dCadastro = null;
		Date hRealizacao = null;
		Date hCadastro = null;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String date = dataRealizacao.getText();
			dRealizacao = (Date) format.parse(date);
		} catch (ParseException e) {}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String date = dataCadastro.getText();
			dCadastro = (Date) format.parse(date);
		} catch (ParseException e) {}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
			String date = horarioRealizacao.getText();
			hRealizacao = (Date) format.parse(date);
		} catch (ParseException e) {}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
			String date = horarioCadastro.getText();
			hCadastro = (Date) format.parse(date);
		} catch (ParseException e) {}
		
		testeFilter.dataRealizao = dRealizacao;
		testeFilter.dataCadastro = dCadastro;
		testeFilter.horarioRealizao = hRealizacao;
		testeFilter.horarioCadastro = hCadastro;
		
		testeFilter.testesNaoAvaliados = apenasAvaliados.isSelected();
		testeFilter.testesNaoRealizados = apenasRealizados.isSelected();
		//====================================
		
		return testeFilter;
	}
	
	private void remover(ActionEvent e) {
		int id = getSelectedTesteId();
		
		if(id == -1) {
			return;
		}
		
		TesteDAO testeDAO = new TesteDAO();
		Teste teste = (Teste)testeDAO.get(id);
		testeDAO.delete(teste);
		
		updateTable();
	}
}
