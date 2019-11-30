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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.ufc.janaina.jdbc.dao.VeiculoDAO;
import com.ufc.janaina.models.NotaFiscal;
import com.ufc.janaina.models.Orcamento;
import com.ufc.janaina.models.Veiculo;
import com.ufc.janaina.models.Venda;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class GerarNotaFiscalVendaView extends View {
	private Venda venda;
	private NotaFiscal notaFiscal;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	
	// BUTTONS
	private JButton sairBtn;
	private JButton salvarBtn;
	private JTextArea notaFiscalTextPane;
	private JScrollPane scroll;
	
	/*
	 * Se o id do veículo passado não estiver definido
	 * assumimos que o objetivo é criar um novo veículo
	 * caso contrário, queremos atualizá-lo
	 * 
	 * Se então formos atualizá-lo, preenchemos
	 * os campos do formulário com as informações do veículo passado
	 */
	public GerarNotaFiscalVendaView(Venda venda) {
		this();
		this.venda = venda;
		
		this.notaFiscal = new NotaFiscal(venda);
		
		notaFiscalTextPane.setText(notaFiscal.gerarNotaFiscal());
		
		System.out.print(notaFiscal.gerarNotaFiscal());
		//Define a funcao do botao de cancelar
		sairBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelar(e);
			}
		});
		
	}
	
	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	public GerarNotaFiscalVendaView() {
		
		setPreferredSize(new Dimension(560, 444));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{628, 0};
		gridBagLayout.rowHeights = new int[]{468, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{312, 0};
		gbl_panel.rowHeights = new int[]{383, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		notaFiscalTextPane = new JTextArea();
		notaFiscalTextPane.setEditable(false);
		notaFiscalTextPane.setTabSize(6);
		notaFiscalTextPane.setColumns(42);
		notaFiscalTextPane.setLineWrap(true);
		notaFiscalTextPane.setRows(25);
		GridBagConstraints gbc_notaFiscalTextPane = new GridBagConstraints();
		gbc_notaFiscalTextPane.insets = new Insets(0, 0, 5, 0);
		gbc_notaFiscalTextPane.fill = GridBagConstraints.BOTH;
		gbc_notaFiscalTextPane.gridx = 0;
		gbc_notaFiscalTextPane.gridy = 0;
		panel.add(notaFiscalTextPane, gbc_notaFiscalTextPane);
		
		//claor
		this.scroll= new JScrollPane(notaFiscalTextPane); 
		panel.add(scroll);
		//calro
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
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
		
		sairBtn = new JButton("Sair");
		panel_3.add(sairBtn);
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
	
	private void cancelar(ActionEvent e) {
		closeFrame(e);
	}
	
}

