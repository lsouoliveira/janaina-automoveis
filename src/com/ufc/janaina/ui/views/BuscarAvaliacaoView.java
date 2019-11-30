package com.ufc.janaina.ui.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ufc.janaina.jdbc.dao.AvaliacaoDAO;
import com.ufc.janaina.jdbc.dao.FormaPagamentoDAO;
import com.ufc.janaina.jdbc.filters.AvaliacaoFilter;
import com.ufc.janaina.models.Avaliacao;
import com.ufc.janaina.models.FormaPagamento;
import com.ufc.janaina.utils.DateFormat;

import net.miginfocom.swing.MigLayout;

public class BuscarAvaliacaoView extends View {
	private JTable table;
	private JLabel labelDadosInvalidos;
	private JButton btnPesquisar;
	private JPanel panel_3;
	private JPanel veiculoPanel;
	private JLabel lblModelo;
	private JTextField modelo;
	private JLabel lblChassi;
	private JTextField chassi;
	private JPanel panel_2;
	private JButton btnRemover;
	private JPanel panel_5;
	private JPanel panel_4;
	private JLabel lblPerodoDeTeste;
	private JFormattedTextField formattedTextField;
	private JLabel lblA;
	private JFormattedTextField formattedTextField_1;
	
	/**
	 * Create the panel.
	 */
	public BuscarAvaliacaoView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		veiculoPanel = new JPanel();
		panel_5.add(veiculoPanel);
		veiculoPanel.setBorder(new TitledBorder(null, "Ve\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		veiculoPanel.setLayout(new MigLayout("", "[fill][grow,fill]", "[19px][]"));
		
		lblModelo = new JLabel("Modelo");
		veiculoPanel.add(lblModelo, "cell 0 0,alignx left,aligny center");
		
		modelo = new JTextField();
		veiculoPanel.add(modelo, "cell 1 0,alignx center,aligny center");
		modelo.setColumns(10);
		
		lblChassi = new JLabel("Chassi");
		veiculoPanel.add(lblChassi, "cell 0 1,alignx left,aligny center");
		
		chassi = new JTextField();
		veiculoPanel.add(chassi, "cell 1 1,alignx center,aligny center");
		chassi.setColumns(10);
		
		panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setLayout(new MigLayout("", "[][grow][grow]", "[]"));
		
		lblPerodoDeTeste = new JLabel("Período de Teste");
		panel_4.add(lblPerodoDeTeste, "cell 0 0,alignx trailing");
		
		formattedTextField = new JFormattedTextField();
		panel_4.add(formattedTextField, "flowx,cell 1 0,growx");
		
		lblA = new JLabel("a");
		panel_4.add(lblA, "cell 1 0");
		
		formattedTextField_1 = new JFormattedTextField();
		panel_4.add(formattedTextField_1, "cell 2 0,growx");
		
		panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		btnPesquisar = new JButton("Pesquisar");
		panel_3.add(btnPesquisar);
		
		labelDadosInvalidos = new JLabel("Erro: Dados inválidos");
		panel_3.add(labelDadosInvalidos);
		labelDadosInvalidos.setForeground(Color.RED);
		labelDadosInvalidos.setVisible(false);
		
		// Carrega as formas de pagamento
		FormaPagamentoDAO fpDAO = new FormaPagamentoDAO();
		List<FormaPagamento> fpList = fpDAO.getAll();
		
		String[] pagamentoModelContent = new String[fpList.size() + 1];
		
		pagamentoModelContent[0] = "Selecione uma opção";
		for(int i = 0; i < fpList.size(); i++) {
			pagamentoModelContent[i] = fpList.get(i).getPagamentoTipo();
		}
		
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
				"id", "Modelo", "Chassi", "Avaliação", "Comentário", "Data"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			
		});
		scrollPane.setViewportView(table);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		add(panel_2, BorderLayout.SOUTH);
		
		btnRemover = new JButton("Remover");
		panel_2.add(btnRemover);
		
		setButtonHandlers();
	}
	
/*-------------------------------FIM DA SEÇÃO DO CONSTRUTOR----------------------------------*/
	
	
	/*TODA VIEW DO TIPO BUSCAR DEVE POSSUIR AS FUNÇÕES ABAIXO (ADAPTADAS PARA ELA) */
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
		
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
				
				int id = getSelectedAvaliacaoId();
				
				if(id == -1) {
					return;
				}
				
				Avaliacao v = new Avaliacao();
				v.setId(id);
				
				avaliacaoDAO.delete(v);
				updateTable();
			}
		});
	}
	/*
	 * FUNÇÕES RELACIONADAS A TABELA DE RESULTADOS
	 * contém:
	 * updateTable: consulta o banco de dados e insere os resultados
	 * getSelectedAvaliacaoId: retorna o id da linha da tabela selecionada
	 */
	
	private void updateTable() {
		labelDadosInvalidos.setVisible(false);
		
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		
		// Setup filter
		AvaliacaoFilter filter = new AvaliacaoFilter();
		
		filter.modelo = modelo.getText();
		filter.chassi = chassi.getText();
		filter.periodoInicio = null;
		filter.periodoFim = null;
		
		// Retorna os dados de acordo com o filtro especificado
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		// limpa a tabela
		model.setRowCount(0);
		
		for(Avaliacao v : avaliacaoDAO.getAll(filter)) {
			model.addRow(new Object[] {
					v.getId(),
					(isNull(v.getTeste()) ? "" :  v.getTeste().getVeiculo().getModelo()),
					(isNull(v.getTeste()) ? "" :  v.getTeste().getVeiculo().getChassi()),
					v.getNota(),
					v.getComentario(),
					(isNull(v.getData()) ? "" :  DateFormat.getDate(v.getData())),
			});
		}
	}
	
	private boolean isNull(Object e) {
		return e == null;
	}
	
	public int getSelectedAvaliacaoId() {
		int selectedRow = table.getSelectedRow();				
		
		if(selectedRow == -1) {
			return -1;
		}
		
		int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		
		return id;
	}
}
