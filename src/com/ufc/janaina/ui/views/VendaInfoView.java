package com.ufc.janaina.ui.views;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import com.ufc.janaina.jdbc.dao.FormaPagamentoDAO;
import com.ufc.janaina.models.FormaPagamento;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class VendaInfoView extends View {
	private JPanel clientePanel;
	private JPanel panel_5;
	public JLabel rg;
	private JLabel lblNome;
	private JPanel veiculoPanel;
	private JLabel lblPlaca;
	private JLabel lblChassi;
	public JPanel vendaPanel;
	private JLabel lblFormaDePagamento;
	private JLabel lblStauts;
	public JComboBox status;
	public JComboBox pagamento;
	public JLabel rgLabel;
	public JLabel nomeLabel;
	public JLabel placaLabel;
	public JLabel chassiLabel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	public JTextPane descricao;
	private JLabel lblDescrio;
	
	/**
	 * Create the panel.
	 */
	public VendaInfoView() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_5 = new JPanel();
		panel.add(panel_5, BorderLayout.CENTER);
		
		panel_5.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_5.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		clientePanel = new JPanel();
		panel_2.add(clientePanel);
		clientePanel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		clientePanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		rg = new JLabel("RG");
		clientePanel.add(rg, "cell 0 0,alignx trailing");
		
		rgLabel = new JLabel("New label");
		clientePanel.add(rgLabel, "cell 1 0");
		
		lblNome = new JLabel("Nome");
		clientePanel.add(lblNome, "cell 0 1,alignx trailing");
		
		nomeLabel = new JLabel("New label");
		clientePanel.add(nomeLabel, "cell 1 1");
		
		veiculoPanel = new JPanel();
		panel_2.add(veiculoPanel);
		veiculoPanel.setBorder(new TitledBorder(null, "Ve\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		veiculoPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		lblPlaca = new JLabel("Placa");
		veiculoPanel.add(lblPlaca, "cell 0 0,alignx trailing");
		
		placaLabel = new JLabel("New label");
		veiculoPanel.add(placaLabel, "cell 1 0");
		
		lblChassi = new JLabel("Chassi");
		veiculoPanel.add(lblChassi, "cell 0 1,alignx trailing");
		
		chassiLabel = new JLabel("New label");
		veiculoPanel.add(chassiLabel, "cell 1 1");
		
		vendaPanel = new JPanel();
		panel_2.add(vendaPanel);
		vendaPanel.setBorder(new TitledBorder(null, "Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		vendaPanel.setLayout(new MigLayout("", "[]", "[][][]"));
		
		lblStauts = new JLabel("Status");
		vendaPanel.add(lblStauts, "flowx,cell 0 0");
		
		lblFormaDePagamento = new JLabel("Forma de Pagamento");
		vendaPanel.add(lblFormaDePagamento, "flowx,cell 0 1");
		
		status = new JComboBox();
		status.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma opção", "CANCELADO", "AGUARDANDO PAGAMENTO", "PAGO"}));
		vendaPanel.add(status, "cell 0 0");
		
		FormaPagamentoDAO fpDAO = new FormaPagamentoDAO();
		List<FormaPagamento> fpList = fpDAO.getAll();
		
		String[] pagamentoModelContent = new String[fpList.size() + 1];
		
		pagamentoModelContent[0] = "Selecione uma opção";
		for(int i = 0; i < fpList.size(); i++) {
			pagamentoModelContent[i + 1] = fpList.get(i).getPagamentoTipo();
		}
		
		pagamento = new JComboBox();
		pagamento.setModel(new DefaultComboBoxModel(pagamentoModelContent));
		vendaPanel.add(pagamento, "cell 0 1");
		
		lblDescrio = new JLabel("Descrição:");
		vendaPanel.add(lblDescrio, "cell 0 2");
		
		panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		descricao = new JTextPane();
		panel_3.add(descricao);
	}
}
