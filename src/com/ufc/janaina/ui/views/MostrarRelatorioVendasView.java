package com.ufc.janaina.ui.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;

import com.ufc.janaina.jdbc.dao.VendaDAO;
import com.ufc.janaina.jdbc.filters.VendaFilter;
import com.ufc.janaina.models.Orcamento;
import com.ufc.janaina.models.Veiculo;
import com.ufc.janaina.models.Venda;

import net.miginfocom.swing.MigLayout;

public class MostrarRelatorioVendasView extends View {
	private Veiculo veiculo;
	private Orcamento orcamento;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JPanel panel;
	private JLabel DataInicial;
	private JLabel DataFinal;
	private JButton GerarRelatorio;
	private JPanel panel_2;
	private JFormattedTextField DataInicialCaixa;
	private JFormattedTextField DataFinalCaixa;
	
	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	public MostrarRelatorioVendasView() {
		
		//claro cancela
		
		
		setPreferredSize(new Dimension(560, 444));
		setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new MigLayout("", "[grow]", "[][][]"));
		
		DataInicial = new JLabel("Data Inicial");
		panel.add(DataInicial, "flowx,cell 0 0,alignx left");
		
		DataFinal = new JLabel("Data Final");
		panel.add(DataFinal, "flowx,cell 0 1,alignx left");
		
		GerarRelatorio = new JButton("Gerar Relatório");
		panel.add(GerarRelatorio, "cell 0 2");
		
		try {
			DataInicialCaixa = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel.add(DataInicialCaixa, "cell 0 0,growx");
		
		try {
			DataFinalCaixa = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		panel.add(DataFinalCaixa, "cell 0 1,growx");
		
		GerarRelatorio.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//*******TESTE FIM********
				String s = "RELATÓRIO DE VENDAS DE "+DataInicialCaixa.getText()+" A "+DataFinalCaixa.getText();
				
				VendaFilter filter = new VendaFilter();
				filter.formaPagamento = null;
				filter.status = null;
				filter.chassi = "";
				filter.nome = "";
				filter.placa = "";
				filter.rg = "";
				
				Date dInicio = null;
				Date dFim = null;
				
				System.out.println("KING CRIMSON");
				
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dInicio = format.parse(DataInicialCaixa.getText());
					System.out.println("PORRAAAAAAA");
				} catch (ParseException e) {return;}
				
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dFim = format.parse(DataFinalCaixa.getText());
				} catch (ParseException e) {return;}
				
				filter.periodoInicio = dInicio;
				filter.periodoFim = dFim;
				
				for(Venda i : new VendaDAO().getAll(filter)) {
					s = s+"\n VENDA -------------------------------------------------------------------------------------------------------------------------------------------";
					s = s+"\n Id:" + i.getId() 
							+ "\n Cliente:" + (isNull(i.getCliente()) ? "" :  i.getCliente().getNome()) +"\n "
							+ " RG:" + (isNull(i.getCliente()) ? "" :  i.getCliente().getRg())
							+" \n Telefone" + (isNull(i.getCliente()) ? "" :  i.getCliente().getTelefone()) +" \n"
							+ "Status:" + (isNull(i.getStatus()) ? "" : Venda.getStatusAsString(i.getStatus())) 
							+ "\n Data:" + (isNull(i.getData()) ? "" : i.getData())
							+ "\n Forma de Pagamento:" + (isNull(i.getFormaPagamento()) ? "" : i.getFormaPagamento().getPagamentoTipo()) +" \n"
							+"Chassi:"+ (isNull(i.getVeiculo()) ? "" :  i.getVeiculo().getChassi()) 
							+ "\n Placa:" + (isNull(i.getVeiculo()) ? "" :  i.getVeiculo().getPlaca())
							+ "\n Preço:" + (isNull(i.getVeiculo()) ? "" :  i.getVeiculo().getPreco());
					
					s = s+"\n ---------------------------------------------------------------------------------------------------------------------------------------------";
				
				}
				
				textArea.setText(s);
				//textArea.setText(i.toString());
			}});
	}

	private boolean isNull(Object e) {
		return e == null;
	}
	
	/*-------------------------------FIM DA SEÇÃO DO CONSTRUTOR----------------------------------*/
	
	/*TODA VIEW DO TIPO CADASTRAR DEVE POSSUIR AS FUNÇÕES ABAIXO (ADAPTADAS PARA ELA) */
	
	/*
	 * Função utilitária para fechar a janela atual
	 * 
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
