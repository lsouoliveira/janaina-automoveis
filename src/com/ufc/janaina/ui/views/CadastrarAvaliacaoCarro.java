package com.ufc.janaina.ui.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.ufc.janaina.jdbc.dao.AvaliacaoDAO;
import com.ufc.janaina.jdbc.dao.TesteDAO;
import com.ufc.janaina.models.Avaliacao;
import com.ufc.janaina.models.Teste;
import com.ufc.janaina.models.Veiculo;

import net.miginfocom.swing.MigLayout;

public class CadastrarAvaliacaoCarro extends View {
	private Veiculo veiculo;
	private JPanel panel;
	private JLabel lblOTesteDrive;
	private JLabel lblAtribuaUmaNota;
	private JComboBox nota;
	private JLabel lblComentrio;
	private JLabel modeloLabel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JTextPane comentario;
	private Teste teste = null;
	
	/**
	 * INÍCIO DA SEÇÃO DO CONSTRUTOR
	 * ESTA PARTE FOI GERADA PELO WINDOW BUILDER
	 * IGNORE
	*/
	public CadastrarAvaliacaoCarro() {
		
		setPreferredSize(new Dimension(500,350));
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][grow]"));
		
		lblOTesteDrive = new JLabel("O teste drive foi no");
		lblOTesteDrive.setFont(new Font("Dialog", Font.BOLD, 15));
		lblOTesteDrive.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblOTesteDrive, "flowx,cell 0 0,alignx center");
		
		lblAtribuaUmaNota = new JLabel("Atribua uma nota de 1 a 5 (1 - péssimo, 5 - ótimo)");
		panel.add(lblAtribuaUmaNota, "cell 0 1");
		
		nota = new JComboBox();
		nota.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma opção", "1", "2", "3", "4", "5"}));
		panel.add(nota, "cell 0 2,alignx left");
		
		lblComentrio = new JLabel("Comentário");
		panel.add(lblComentrio, "cell 0 3");
		
		modeloLabel = new JLabel("CARRO");
		modeloLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		modeloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(modeloLabel, "cell 0 0,alignx center");
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		comentario = new JTextPane();
		panel_1.add(comentario);
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		btnConfirmar = new JButton("Confirmar");
		panel_2.add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		panel_2.add(btnCancelar);
		
		btnConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				inserir(e);
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				closeFrame(e);
			}
		});
	}
	
	public CadastrarAvaliacaoCarro(Teste t) {
		this();
		
		if(t == null) {
			return;
		}
		
		this.teste = t;
		
		modeloLabel.setText(t.getVeiculo().getModelo());
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
	
	// Valida os campos do formulário
	private boolean validateFields() {
		
		
		if(comentario.getText().isEmpty()) {
			return false;
		}
		if(((String)nota.getSelectedItem()).contentEquals("Selecione uma opção")) {
			return false;
		}
			
		return true;
	}
	

	public Avaliacao getAvaliacao() {
		Avaliacao v = new Avaliacao();
		
		v.setComentario(comentario.getText());
		v.setNota((String)nota.getSelectedItem());
		v.setTeste(teste);
		v.setData(new Date());
		
		return v;
	}
	
	private void inserir(ActionEvent e) {
		AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
		
		Avaliacao v = null;
		
		if(validateFields()) {
			v = getAvaliacao();
		}else {
			return;
		}
		
		avaliacaoDAO.insert(v);
		
		teste.setAvaliado(true);
		new TesteDAO().update(teste);
		
		closeFrame(e);
	}
	
	private void cancelar(ActionEvent e) {
		closeFrame(e);
	}
	

}
