package com.ufc.janaina.ui.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ufc.janaina.jdbc.dao.VeiculoDAO;
import com.ufc.janaina.models.Orcamento;
import com.ufc.janaina.models.Veiculo;
import com.ufc.janaina.ui.windows.BlankWindow;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

public class GerarOrcamentoVeiculoView extends View {
	private JTextField chassiText;
	private JTable table;
	private JTextField marcaText;
	private JTextField corText;
	private JTextField anoText;
	private JTextField placaText;
	private JLabel labelDadosInvalidos;
	
	// BUTTONS
	private JButton btnSimular;
	private JButton btnPesquisar;
	private JPanel panel_3;
	private JLabel lblNPrestacoes;
	private JComboBox PrestacoesComboBox;
	private JLabel lblSeguro;
	private JCheckBox seguroCheckBox;
	private JLabel lblTanqueCheio;
	private JCheckBox tanqueCheioCheckBox;
	private JLabel lblPrecoMximo;
	private JTextField precoText;
	private JLabel lblErroSelecioneVeiculo;

	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	public GerarOrcamentoVeiculoView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Filtros de Busca do Carro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][471.00,grow]", "[][][]"));
		
		JLabel lblNDeChassi = new JLabel("N° de Chassi");
		panel.add(lblNDeChassi, "flowy,cell 0 0,alignx trailing");
		
		chassiText = new JTextField();
		panel.add(chassiText, "flowx,cell 1 0,alignx left");
		chassiText.setColumns(17);
		
		JLabel lblMarca = new JLabel("Marca");
		panel.add(lblMarca, "cell 0 1,alignx trailing");
		
		marcaText = new JTextField();
		panel.add(marcaText, "flowx,cell 1 1");
		marcaText.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa");
		panel.add(lblPlaca, "cell 1 0");
		
		placaText = new JTextField();
		panel.add(placaText, "cell 1 0");
		placaText.setColumns(7);
		
		JLabel lblAno = new JLabel("Ano");
		panel.add(lblAno, "cell 1 0");
		
		JLabel lblNewLabel = new JLabel("Cor");
		panel.add(lblNewLabel, "cell 1 1,alignx trailing");
		
		corText = new JTextField();
		panel.add(corText, "cell 1 1");
		corText.setColumns(7);
		
		anoText = new JTextField();
		panel.add(anoText, "cell 1 0");
		anoText.setColumns(4);
		
		labelDadosInvalidos = new JLabel("Erro: Dados inválidos");
		labelDadosInvalidos.setForeground(Color.RED);
		labelDadosInvalidos.setVisible(false);
		
		btnPesquisar = new JButton("Pesquisar Carros");
		panel.add(btnPesquisar, "flowx,cell 1 2");
		panel.add(labelDadosInvalidos, "flowy,cell 1 2,alignx right");
		
		lblPrecoMximo = new JLabel("Preço máximo");
		panel.add(lblPrecoMximo, "cell 1 1,alignx left");
		
		precoText = new JTextField();
		panel.add(precoText, "cell 1 1");
		precoText.setColumns(10);
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Informa\u00E7\u00F5es do Or\u00E7amento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new MigLayout("", "[][124px,grow]", "[19px][][]"));
		
		lblNPrestacoes = new JLabel("Nº Prestações");
		lblNPrestacoes.setLabelFor(this);
		panel_3.add(lblNPrestacoes, "cell 0 0,alignx trailing");
		
		PrestacoesComboBox = new JComboBox();
		PrestacoesComboBox.setModel(new DefaultComboBoxModel(new String[] {"6", "12", "24", "36", "48", "72"}));
		PrestacoesComboBox.setSelectedIndex(1);
		panel_3.add(PrestacoesComboBox, "cell 1 0,growx");
		
		lblSeguro = new JLabel("Seguro");
		panel_3.add(lblSeguro, "cell 0 1");
		
		seguroCheckBox = new JCheckBox("");
		seguroCheckBox.setSelected(true);
		panel_3.add(seguroCheckBox, "cell 1 1");
		
		lblTanqueCheio = new JLabel("Tanque Cheio");
		panel_3.add(lblTanqueCheio, "cell 0 2");
		
		tanqueCheioCheckBox = new JCheckBox("");
		tanqueCheioCheckBox.setSelected(true);
		panel_3.add(tanqueCheioCheckBox, "cell 1 2");
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[1px][grow]", "[grow][1px][grow][][grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "cell 1 2,grow");
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "N\u00B0 de Chassi", "Placa", "Ano", "Modelo", "Cor","Preço"
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
		panel_2.setLayout(new MigLayout("", "[][][][][][][][][][160.00]", "[]"));
		
		btnSimular = new JButton("Simular");
		btnSimular.setBackground(UIManager.getColor("Button.focus"));
		panel_2.add(btnSimular, "cell 8 0");
		
		lblErroSelecioneVeiculo = new JLabel("Erro: Selecione um Veículo");
		lblErroSelecioneVeiculo.setVisible(false);
		lblErroSelecioneVeiculo.setForeground(Color.RED);
		panel_2.add(lblErroSelecioneVeiculo, "cell 9 0");
		
		// define as funções dos botões
		setButtonHandlers();
	}
	
	/*-------------------------------FIM DA SEÇÃO DO CONSTRUTOR----------------------------------*/
	
	
	/*TODA VIEW DO TIPO BUSAR DEVE POSSUIR AS FUNÇÕES ABAIXO (ADAPTADAS PARA ELA) */
	
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
			}
		});
		
		/*
		 * Ao clicar no botão de adicionar
		 * abre uma view "CadastrarVeiculoView"
		 */
		btnSimular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = getSelectedVeiculoId();
				
				if(id == -1) {
					lblErroSelecioneVeiculo.setVisible(true);
					return;
				}
				
				lblErroSelecioneVeiculo.setVisible(false);
				// Busca o veículo com o id da linha selecionada
				VeiculoDAO veiculoDAO = new VeiculoDAO();
				Veiculo v = (Veiculo) veiculoDAO.get(id);
				
				if(v == null) {
					return;
				}
				
				//Carrega os dados do orcamento
				int qtdParcelas = Integer.valueOf(PrestacoesComboBox.getSelectedItem().toString());
				boolean seguro  = seguroCheckBox.isSelected();
				boolean gasosa  = tanqueCheioCheckBox.isSelected();
				
				// Abre a janela com a view CadastrarVeiculoView e passa o veículo buscado
				new BlankWindow(new MostrarOrcamentoVeiculoView(v,qtdParcelas,gasosa,seguro), null, "Orçamento", true, false).launch();
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
		
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		
		// Setup filter
		Veiculo filter = new Veiculo();
		
		filter.setChassi(chassiText.getText());
		filter.setCor(corText.getText());
		filter.setModelo(marcaText.getText());
		filter.setPlaca(placaText.getText());
		int ano = -1;
		Double preco = -1.0;
		
		try {
			if(!anoText.getText().contentEquals("")) {
				ano = Integer.parseInt(anoText.getText());
			}
			if(!precoText.getText().contentEquals("")) {
				preco = Double.valueOf(precoText.getText());
			}
		} catch(Exception e) {
			labelDadosInvalidos.setVisible(true);
			return;
		}
		
		filter.setAno(ano);
		filter.setPreco(preco);
		
		
		
		// Retorna os dados de acordo com o filtro especificado
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		// limpa a tabela
		model.setRowCount(0);
		
		for(Veiculo v : veiculoDAO.getAll(filter)) {
			model.addRow(new Object[] {
					v.getId(),
					v.getChassi(),
					v.getPlaca(),
					v.getAno(),
					v.getModelo(),
					v.getCor(),
					v.getPreco()
			});
		}
	}
	
	private int getSelectedVeiculoId() {
		int selectedRow = table.getSelectedRow();				
		
		if(selectedRow == -1) {
			return -1;
		}
		
		int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		
		return id;
	}
}
