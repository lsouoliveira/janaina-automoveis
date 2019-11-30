package com.ufc.janaina.ui.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import com.ufc.janaina.models.Veiculo;
import com.ufc.janaina.ui.windows.BlankWindow;

import net.miginfocom.swing.MigLayout;

public class BuscarVeiculosView extends View {
	private JTextField chassiText;
	private JTable table;
	private JTextField marcaText;
	private JTextField corText;
	private JTextField anoText;
	private JTextField placaText;
	private JLabel labelDadosInvalidos;
	
	// BUTTONS
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JButton btnEditar;
	private JLabel lblPrecoMximo;
	private JTextField precoText;

	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	public BuscarVeiculosView( boolean botoes ) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		btnPesquisar = new JButton("Pesquisar");
		panel.add(btnPesquisar, "cell 0 2");
		
		anoText = new JTextField();
		panel.add(anoText, "cell 1 0");
		anoText.setColumns(4);
		
		labelDadosInvalidos = new JLabel("Erro: Dados inválidos");
		labelDadosInvalidos.setForeground(Color.RED);
		labelDadosInvalidos.setVisible(false);
		panel.add(labelDadosInvalidos, "cell 1 2");
		
		lblPrecoMximo = new JLabel("Preço máximo");
		panel.add(lblPrecoMximo, "cell 1 1,alignx left");
		
		precoText = new JTextField();
		panel.add(precoText, "cell 1 1");
		precoText.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[1px][grow]", "[1px][grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, "cell 1 1,grow");
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"", "N\u00B0 de Chassi", "Placa", "Ano", "Modelo", "Cor","Preco"
			} //TODO coloca preco aqui
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		
		if( botoes ) {
		
			add(panel_2, BorderLayout.SOUTH);
		
		}
		panel_2.setLayout(new MigLayout("", "[][][]", "[]"));
		
		btnAdicionar = new JButton("Adicionar");
		panel_2.add(btnAdicionar, "cell 0 0");
		
		btnEditar = new JButton("Editar");
		panel_2.add(btnEditar, "cell 1 0");
		
		btnRemover = new JButton("Remover");
		panel_2.add(btnRemover, "cell 2 0");
		
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
			}
		});
		
		/*
		 * Ao clicar no botão de adicionar
		 * abre uma view "CadastrarVeiculoView"
		 */
		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Cria um veículo com id -1
				 * Na CadastrarVeiculoView o id -1
				 * indicará que o objetivo é adicionar um novo veículo
				 */
				
				Veiculo v = new Veiculo();
				v.setId(-1);
				
				new BlankWindow(new CadastrarVeiculoView(v), null, "Cadastrar Veículo", true, false).launch();
			}
			
		});
		
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = getSelectedVeiculoId();
				
				if(id == -1) {
					return;
				}
				
				// Busca o veículo com o id da linha selecionada
				VeiculoDAO veiculoDAO = new VeiculoDAO();
				Veiculo v = (Veiculo) veiculoDAO.get(id);
				
				if(v == null) {
					return;
				}
				
				// Abre a janela com a view CadastrarVeiculoView e passa o veículo buscado
				new BlankWindow(new CadastrarVeiculoView(v), null, "Editar Veículo", true, false).launch();
			}
			
		});
		
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VeiculoDAO veiculoDAO = new VeiculoDAO();
				
				int id = getSelectedVeiculoId();
				
				if(id == -1) {
					return;
				}
				
				Veiculo v = new Veiculo();
				v.setId(id);
				
				veiculoDAO.delete(v);
				updateTable();
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
		filter.setPreco(preco); //TODO colocar o preco
		
		updateTableItems(veiculoDAO.getAll(filter));
	}
	
	public void updateTableItems(List<Veiculo> veiculos) {
DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		// limpa a tabela
		model.setRowCount(0);
		
		for(Veiculo v : veiculos) {
			model.addRow(new Object[] {
					v.getId(),
					v.getChassi(),
					v.getPlaca(),
					v.getAno(),
					v.getModelo(),
					v.getCor(),
					String.format("%.02f", v.getPreco())
			});
		}
	}
	
	public void selectFirst() {
		table.setRowSelectionInterval(0, 0);
	}
	
	
	public int getSelectedVeiculoId() {
		int selectedRow = table.getSelectedRow();				
		
		if(selectedRow == -1) {
			return -1;
		}
		
		int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		
		return id;
	}
}
