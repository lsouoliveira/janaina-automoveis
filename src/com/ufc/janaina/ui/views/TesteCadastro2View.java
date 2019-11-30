package com.ufc.janaina.ui.views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.text.MaskFormatter;

import com.ufc.janaina.jdbc.dao.ClienteDAO;
import com.ufc.janaina.models.Cliente;
import com.ufc.janaina.models.Veiculo;
import com.ufc.janaina.ui.windows.BlankWindow;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;

public class TesteCadastro2View extends View {
	
	// BUTTONS
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnEditar;
	private JLabel lblRgDoClient;
	private JLabel lblNomeDoCliente;
	private JLabel lblTelefone;
	private JLabel lblTelefoneDoCliente;
	private JLabel lblModeloDoVeiculo;
	private JLabel lblAnoDoVeiculo;
	private JLabel lblPlacaDoVeiculo;
	private JLabel lblCorDoVeiculo;
	private JFormattedTextField fTFDataDoAgendamento;
	private JFormattedTextField fTFHorarioAgendamento;
	private JLabel lblChassiDoVeiculo;
	/**
	 * Create the panel.
	 */
	public TesteCadastro2View() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_centro = new JPanel();
		add(panel_centro, BorderLayout.CENTER);
		panel_centro.setLayout(new BoxLayout(panel_centro, BoxLayout.Y_AXIS));
		
		JPanel panel_cliente = new JPanel();
		panel_cliente.setForeground(Color.WHITE);
		panel_centro.add(panel_cliente);
		panel_cliente.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(47, 79, 79)));
		panel_cliente.setLayout(new MigLayout("", "[]", "[][][]"));
		
		JLabel lblRG = new JLabel("RG:");
		lblRG.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_cliente.add(lblRG, "flowx,cell 0 0,alignx left");
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_cliente.add(lblNome, "flowx,cell 0 1,alignx left");
		
		lblRgDoClient = new JLabel("RG do cliente aqui");
		lblRgDoClient.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblRgDoClient.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_cliente.add(lblRgDoClient, "cell 0 0");
		
		lblNomeDoCliente = new JLabel("Nome do cliente aqui");
		lblNomeDoCliente.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblNomeDoCliente.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_cliente.add(lblNomeDoCliente, "cell 0 1");
		
		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_cliente.add(lblTelefone, "flowx,cell 0 2");
		
		lblTelefoneDoCliente = new JLabel("Telefone do cliente aqui");
		lblTelefoneDoCliente.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblTelefoneDoCliente.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_cliente.add(lblTelefoneDoCliente, "cell 0 2");
		
		JPanel panel_veiculo = new JPanel();
		panel_veiculo.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Ve\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(47, 79, 79)));
		panel_centro.add(panel_veiculo);
		panel_veiculo.setLayout(new MigLayout("", "[][][][][][]", "[][][]"));
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_veiculo.add(lblModelo, "flowx,cell 0 0");
		
		lblModeloDoVeiculo = new JLabel("RG do cliente aqui");
		lblModeloDoVeiculo.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblModeloDoVeiculo.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_veiculo.add(lblModeloDoVeiculo, "cell 0 0");
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_veiculo.add(lblPlaca, "cell 2 0");
		
		JLabel label = new JLabel("");
		panel_veiculo.add(label, "flowx,cell 3 0");
		
		JLabel lblChassi = new JLabel("Chassi:");
		lblChassi.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_veiculo.add(lblChassi, "flowx,cell 0 1");
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_veiculo.add(lblAno, "flowx,cell 2 1");
		
		lblAnoDoVeiculo = new JLabel("RG do cliente aqui");
		lblAnoDoVeiculo.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblAnoDoVeiculo.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_veiculo.add(lblAnoDoVeiculo, "cell 3 1");
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_veiculo.add(lblCor, "flowx,cell 0 2");
		
		lblPlacaDoVeiculo = new JLabel("RG do cliente aqui");
		lblPlacaDoVeiculo.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblPlacaDoVeiculo.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_veiculo.add(lblPlacaDoVeiculo, "cell 3 0");
		
		lblChassiDoVeiculo = new JLabel("RG do cliente aqui");
		lblChassiDoVeiculo.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblChassiDoVeiculo.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_veiculo.add(lblChassiDoVeiculo, "cell 0 1");
		
		lblCorDoVeiculo = new JLabel("RG do cliente aqui");
		lblCorDoVeiculo.setForeground(UIManager.getColor("CheckBoxMenuItem.acceleratorForeground"));
		lblCorDoVeiculo.setFont(new Font("Dialog", Font.PLAIN, 14));
		panel_veiculo.add(lblCorDoVeiculo, "cell 0 2");
		
		JPanel panel_agendamento = new JPanel();
		panel_agendamento.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Agendamento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(47, 79, 79)));
		panel_centro.add(panel_agendamento);
		panel_agendamento.setLayout(new MigLayout("", "[][grow]", "[][][]"));
		
		JLabel lblData = new JLabel("Data:");
		panel_agendamento.add(lblData, "cell 0 0,alignx left");
		
		fTFDataDoAgendamento = new JFormattedTextField(createFormatter("##/##/####"));
		panel_agendamento.add(fTFDataDoAgendamento, "cell 1 0,growx");
		
		JLabel lblHorario = new JLabel("Horário:");
		panel_agendamento.add(lblHorario, "cell 0 1,alignx left");
		
		fTFHorarioAgendamento = new JFormattedTextField(createFormatter("##:##:##"));
		panel_agendamento.add(fTFHorarioAgendamento, "cell 1 1,growx");
		
		JPanel panel_2 = new JPanel();

		panel_2.setLayout(new MigLayout("", "[][][]", "[]"));
		
		btnAdicionar = new JButton("Adicionar");
		panel_2.add(btnAdicionar, "cell 0 0");
		
		btnEditar = new JButton("Editar");
		panel_2.add(btnEditar, "cell 1 0");
		
		btnRemover = new JButton("Remover");
		panel_2.add(btnRemover, "cell 2 0");
		
	}
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	public void setCliente( Cliente cliente ) {
		
		lblRgDoClient.setText(cliente.getRg());
		lblNomeDoCliente.setText(cliente.getNome());
		lblTelefoneDoCliente.setText(cliente.getTelefone());
		
	}
	
	public void setVeiculo( Veiculo veiculo ) {
		
		lblAnoDoVeiculo.setText(""+veiculo.getAno());
		lblCorDoVeiculo.setText(veiculo.getCor());
		lblModeloDoVeiculo.setText(veiculo.getModelo());
		lblPlacaDoVeiculo.setText(veiculo.getPlaca());
		lblChassiDoVeiculo.setText(veiculo.getChassi());
		
	}
	
	public String getHorario() {
		return fTFHorarioAgendamento.getText();
	}
	
	public String getData() {
		return fTFDataDoAgendamento.getText();
	}
	
	public void setHorario(String s) {
		fTFHorarioAgendamento.setText(s);
	}
	
	public void setData(String s) {
		fTFDataDoAgendamento.setText(s);
	}
		
/*-------------------------------FIM DA SEÇÃO DO CONSTRUTOR----------------------------------*/
	
	

	
	/*
	 * FUNÇÕES RELACIONADAS A TABELA DE RESULTADOS
	 * contém:
	 * updateTable: consulta o banco de dados e insere os resultados
	 * getSelectedVeiculoId: retorna o id da linha da tabela selecionada
	 */
	
	

	
	
}
