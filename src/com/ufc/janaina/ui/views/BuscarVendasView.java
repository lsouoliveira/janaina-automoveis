package com.ufc.janaina.ui.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.ufc.janaina.jdbc.dao.FormaPagamentoDAO;
import com.ufc.janaina.jdbc.dao.VendaDAO;
import com.ufc.janaina.jdbc.filters.VendaFilter;
import com.ufc.janaina.models.FormaPagamento;
import com.ufc.janaina.models.Venda;
import com.ufc.janaina.ui.windows.BlankWindow;
import com.ufc.janaina.utils.DateFormat;

import net.miginfocom.swing.MigLayout;

public class BuscarVendasView extends View {
	private JTable table;
	private JLabel labelDadosInvalidos;
	
	// BUTTONS
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JButton btnEditar;
	private JPanel panel_3;
	private JPanel clientePanel;
	private JPanel panel_5;
	private JLabel rgLabel;
	private JTextField rg;
	private JLabel lblNome;
	private JTextField nome;
	private JPanel veiculoPanel;
	private JLabel lblPlaca;
	private JTextField placa;
	private JLabel lblChassi;
	private JTextField chassi;
	private JPanel vendaPanel;
	private JLabel lblFormaDePagamento;
	private JLabel lblPerodo;
	private JLabel lblStauts;
	private JLabel lblAt;
	private JComboBox status;
	private JComboBox pagamento;
	private JPanel panel_2;
	private JFormattedTextField inicio;
	private JFormattedTextField fim;
	private JButton btnGerarNotaFiscal;
	
	/**
	 * Create the panel.
	 */
	public BuscarVendasView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3, BorderLayout.SOUTH);
		
		btnPesquisar = new JButton("Pesquisar");
		panel_3.add(btnPesquisar);
		
		labelDadosInvalidos = new JLabel("Erro: Dados inválidos");
		panel_3.add(labelDadosInvalidos);
		labelDadosInvalidos.setForeground(Color.RED);
		
		panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		clientePanel = new JPanel();
		clientePanel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.add(clientePanel);
		clientePanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		rgLabel = new JLabel("RG");
		clientePanel.add(rgLabel, "cell 0 0,alignx trailing");
		
		rg = new JTextField();
		clientePanel.add(rg, "cell 1 0,growx");
		rg.setColumns(10);
		
		lblNome = new JLabel("Nome");
		clientePanel.add(lblNome, "cell 0 1,alignx trailing");
		
		nome = new JTextField();
		clientePanel.add(nome, "cell 1 1,growx");
		nome.setColumns(10);
		
		veiculoPanel = new JPanel();
		veiculoPanel.setBorder(new TitledBorder(null, "Ve\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.add(veiculoPanel);
		veiculoPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		lblPlaca = new JLabel("Placa");
		veiculoPanel.add(lblPlaca, "cell 0 0,alignx trailing");
		
		placa = new JTextField();
		veiculoPanel.add(placa, "cell 1 0,growx");
		placa.setColumns(10);
		
		lblChassi = new JLabel("Chassi");
		veiculoPanel.add(lblChassi, "cell 0 1,alignx trailing");
		
		chassi = new JTextField();
		veiculoPanel.add(chassi, "cell 1 1,growx");
		chassi.setColumns(10);
		
		vendaPanel = new JPanel();
		vendaPanel.setBorder(new TitledBorder(null, "Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.add(vendaPanel);
		vendaPanel.setLayout(new MigLayout("", "[]", "[][][]"));
		
		lblPerodo = new JLabel("Período");
		vendaPanel.add(lblPerodo, "flowx,cell 0 0,alignx left");
		
		lblStauts = new JLabel("Status");
		vendaPanel.add(lblStauts, "flowx,cell 0 1");
		
		lblFormaDePagamento = new JLabel("Forma de Pagamento");
		vendaPanel.add(lblFormaDePagamento, "flowx,cell 0 2");
		
		status = new JComboBox();
		status.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma opção", "CANCELADO", "AGUARDANDO PAGAMENTO", "PAGO"}));
		vendaPanel.add(status, "cell 0 1");
		
		// Carrega as formas de pagamento
		FormaPagamentoDAO fpDAO = new FormaPagamentoDAO();
		List<FormaPagamento> fpList = fpDAO.getAll();
		
		String[] pagamentoModelContent = new String[fpList.size() + 1];
		
		pagamentoModelContent[0] = "Selecione uma opção";
		for(int i = 1; i < fpList.size(); i++) {
			pagamentoModelContent[i] = fpList.get(i).getPagamentoTipo();
		}
		
		pagamento = new JComboBox();
		pagamento.setModel(new DefaultComboBoxModel(pagamentoModelContent));
		vendaPanel.add(pagamento, "cell 0 2");
		
		try {
			inicio = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vendaPanel.add(inicio, "cell 0 0,growx");
		
		lblAt = new JLabel("até");
		vendaPanel.add(lblAt, "cell 0 0,alignx trailing");
		
		try {
			fim = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vendaPanel.add(fim, "cell 0 0,growx");
		labelDadosInvalidos.setVisible(false);
		
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
				"id", "RG", "Nome", "Placa", "Chassi", "Descrição", "Data", "Forma de Pagamento", "Status"
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
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new MigLayout("", "[][][][]", "[]"));
		
		btnAdicionar = new JButton("Adicionar");
		panel_2.add(btnAdicionar, "cell 0 0");
		
		btnEditar = new JButton("Editar");
		panel_2.add(btnEditar, "cell 1 0");
		
		btnRemover = new JButton("Remover");
		panel_2.add(btnRemover, "cell 2 0");
		
		btnGerarNotaFiscal = new JButton("Gerar Nota Fiscal");
		panel_2.add(btnGerarNotaFiscal, "cell 3 0");
		
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
				
				inicio.setValue(null);
				fim.setValue(null);
			}
		});
		
		/*
		 * Ao clicar no botão de adicionar
		 * abre uma view "CadastrarVeiculoView"
		 */
		btnAdicionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new BlankWindow(new CadastrarVendaView(), null, "Cadastrar Venda", true, false).launch();
			}
			
		});
		
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = getSelectedClienteId();
				
				if(id == -1) {
					return;
				}
				
				// Busca o veículo com o id da linha selecionada
				VendaDAO vendaDAO = new VendaDAO();
				Venda v = (Venda) vendaDAO.get(id);
				
				if(v == null) {
					return;
				}
				
				// Abre a janela com a view CadastrarVeiculoView e passa o veículo buscado
				new BlankWindow(new CadastrarVendaView(v), null, "Editar Venda", true, false).launch();
			}
			
		});
		
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VendaDAO vendaDAO = new VendaDAO();
				
				int id = getSelectedClienteId();
				
				if(id == -1) {
					return;
				}
				
				Venda v = new Venda();
				v.setId(id);
				
				vendaDAO.delete(v);
				updateTable();
			}
		});
		
		btnGerarNotaFiscal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VendaDAO vendaDAO = new VendaDAO();
				
				int id = getSelectedClienteId();
				
				if(id == -1) {
					return;
				}
				
				Venda venda = (Venda)new VendaDAO().get(id);
				new BlankWindow(new GerarNotaFiscalVendaView(venda), null, "Gerar Nota Fiscal", true, false).launch();
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
		
		VendaDAO vendaDAO = new VendaDAO();
		
		// Setup filter
		VendaFilter filter = new VendaFilter();
		
		filter.rg = rg.getText();
		filter.nome = nome.getText();
		filter.chassi = chassi.getText();
		filter.placa = placa.getText();
		filter.status = Venda.toStatus((String) status.getSelectedItem());
		
		if(new FormaPagamentoDAO().getByTipo((String) pagamento.getSelectedItem()) != null) {
			filter.formaPagamento = new FormaPagamento((String) pagamento.getSelectedItem());
		}else {
			filter.formaPagamento = null;
		}
		
		filter.periodoInicio = null;
		filter.periodoFim = null;
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("##/##/####");
			Date d = format.parse(inicio.getText());
			filter.periodoInicio = d;
		} catch(Exception e) {
			//labelDadosInvalidos.setVisible(true);
			//return;
		}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("##/##/####");
			Date d = format.parse(fim.getText());
			filter.periodoFim = d;
		} catch(Exception e) {
			//labelDadosInvalidos.setVisible(true);
			//return;
		}
		
		// Retorna os dados de acordo com o filtro especificado
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		// limpa a tabela
		model.setRowCount(0);
		
		for(Venda v : vendaDAO.getAll(filter)) {
			model.addRow(new Object[] {
					v.getId(),
					(isNull(v.getCliente()) ? "" :  v.getCliente().getRg()),
					(isNull(v.getCliente()) ? "" :  v.getCliente().getNome()),
					(isNull(v.getVeiculo()) ? "" :  v.getVeiculo().getPlaca()),
					(isNull(v.getVeiculo()) ? "" :  v.getVeiculo().getChassi()),
					v.getDescricao(),
					(isNull(v.getData()) ? "" :  DateFormat.getDate(v.getData())),
					(isNull(v.getFormaPagamento()) ? "" :  v.getFormaPagamento().getPagamentoTipo()),
					(isNull(v.getStatus()) ? "" : Venda.getStatusAsString(v.getStatus()))
			});
		}
	}
	
	private boolean isNull(Object e) {
		return e == null;
	}
	
	private int getSelectedClienteId() {
		int selectedRow = table.getSelectedRow();				
		
		if(selectedRow == -1) {
			return -1;
		}
		
		int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		
		return id;
	}
	
	public void update() {
		FormaPagamentoDAO fpDAO = new FormaPagamentoDAO();
		List<FormaPagamento> fpList = fpDAO.getAll();
		
		String[] pagamentoModelContent = new String[fpList.size() + 1];
		
		pagamentoModelContent[0] = "Selecione uma opção";
		for(int i = 1; i < fpList.size(); i++) {
			pagamentoModelContent[i] = fpList.get(i).getPagamentoTipo();
		}
		
		pagamento.setModel(new DefaultComboBoxModel(pagamentoModelContent));
	}
}
