package com.ufc.janaina.ui.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ufc.janaina.jdbc.dao.ClienteDAO;
import com.ufc.janaina.models.Cliente;
import com.ufc.janaina.ui.windows.BlankWindow;

import net.miginfocom.swing.MigLayout;

public class BuscarClientesView extends View {
	private JTextField rg;
	private JTable table;
	private JTextField nome;
	private JTextField endereco;
	private JTextField numero;
	private JTextField telefone;
	private JTextField cidade;
	private JTextField bairro;
	private JComboBox uf;
	private JLabel labelDadosInvalidos;
	
	// BUTTONS
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnPesquisar;
	private JButton btnEditar;
	
	/**
	 * Create the panel.
	 */
	public BuscarClientesView( boolean botoes ) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[]", "[][][][][]"));
		
		JLabel lblNDeChassi = new JLabel("RG");
		panel.add(lblNDeChassi, "flowx,cell 0 0,alignx left");
		
		JLabel lblMarca = new JLabel("Nome");
		panel.add(lblMarca, "flowx,cell 0 1,alignx left");
		
		JLabel lblEndereo = new JLabel("Endereço");
		panel.add(lblEndereo, "flowx,cell 0 2");
		
		JLabel lblCidade = new JLabel("Cidade");
		panel.add(lblCidade, "flowx,cell 0 3");
		
		btnPesquisar = new JButton("Pesquisar");
		panel.add(btnPesquisar, "flowx,cell 0 4");
		
		rg = new JTextField();
		panel.add(rg, "cell 0 0,alignx left");
		rg.setColumns(14);
		
		nome = new JTextField();
		panel.add(nome, "cell 0 1");
		nome.setColumns(40);
		
		endereco = new JTextField();
		panel.add(endereco, "cell 0 2");
		endereco.setColumns(40);
		
		JLabel lblBairro = new JLabel("Bairro");
		panel.add(lblBairro, "cell 0 2");
		
		bairro = new JTextField();
		panel.add(bairro, "cell 0 2");
		bairro.setColumns(10);
		
		JLabel lblNmero = new JLabel("Número");
		panel.add(lblNmero, "cell 0 2");
		
		numero = new JTextField();
		panel.add(numero, "cell 0 2");
		numero.setColumns(5);
		
		JLabel lblTelefone = new JLabel("Telefone");
		panel.add(lblTelefone, "cell 0 1");
		
		telefone = new JTextField();
		panel.add(telefone, "cell 0 1,alignx left,aligny top");
		telefone.setColumns(11);
		
		cidade = new JTextField();
		panel.add(cidade, "cell 0 3");
		cidade.setColumns(15);
		
		JLabel lblEstado = new JLabel("Estado");
		panel.add(lblEstado, "cell 0 3");
		
		uf = new JComboBox();
		uf.setModel(new DefaultComboBoxModel(new String[] {"UF", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		panel.add(uf, "cell 0 3");
		
		labelDadosInvalidos = new JLabel("Erro: Dados inválidos");
		labelDadosInvalidos.setForeground(Color.RED);
		labelDadosInvalidos.setVisible(false);
		panel.add(labelDadosInvalidos, "cell 0 4");
		
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
				"", "RG", "Nome", "Telefone", "Endereço", "Bairro", "Número", "Cidade", "Estado"
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
				
				Cliente v = new Cliente();
				v.setId(-1);
				
				new BlankWindow(new CadastrarClienteView(v), null, "Cadastrar Cliente", true, false).launch();
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
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente v = (Cliente) clienteDAO.get(id);
				
				if(v == null) {
					return;
				}
				
				// Abre a janela com a view CadastrarVeiculoView e passa o veículo buscado
				new BlankWindow(new CadastrarClienteView(v), null, "Editar Cliente", true, false).launch();
			}
			
		});
		
		btnRemover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClienteDAO clienteDAO = new ClienteDAO();
				
				int id = getSelectedClienteId();
				
				if(id == -1) {
					return;
				}
				
				Cliente v = new Cliente();
				v.setId(id);
				
				clienteDAO.delete(v);
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
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		// Setup filter
		Cliente filter = new Cliente();
		
		filter.setBairro(bairro.getText());
		filter.setCidade(cidade.getText());
		filter.setRg(rg.getText());
		filter.setEndereco(endereco.getText());
		filter.setNome(nome.getText());
		// filter.setNumero(numero.getText());
		filter.setTelefone(telefone.getText());
		filter.setUf(uf.getSelectedItem().toString());
		
		int num = -1;
		
		try {
			if(!numero.getText().contentEquals("")) {
				num = Integer.parseInt(numero.getText());
			}
		} catch(Exception e) {
			labelDadosInvalidos.setVisible(true);
			return;
		}
		
		filter.setNumero(num);
		
		// Retorna os dados de acordo com o filtro especificado
		updateTableItems(clienteDAO.getAll(filter));
	}
	
	public void updateTableItems(List<Cliente> clientes) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				
				for(Cliente v : clientes) {
					model.addRow(new Object[] {
							v.getId(),
							v.getRg(),
							v.getNome(),
							v.getTelefone(),
							v.getEndereco(),
							v.getBairro(),
							v.getNumero(),
							v.getCidade(),
							v.getUf()
					});
				}
	}
	
	public void selectFirst() {
		table.setRowSelectionInterval(0, 0);
	}
	
	public int getSelectedClienteId() {
		int selectedRow = table.getSelectedRow();				
		
		if(selectedRow == -1) {
			return -1;
		}
		
		int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
		
		return id;
	}

	
	
}
