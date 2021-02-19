package com.ufc.janaina.ui.views;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JanelaPrincipalView extends View {

	/**
	 * Create the panel.
	 */
	public JanelaPrincipalView() {
		setLayout(new BorderLayout(0, 0));
		
		//JLabel lblBemVindo = new JLabel("BEM VINDO #_#");
		JLabel lblBemVindo = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("janaina_bemvindo.png")));
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblBemVindo, BorderLayout.CENTER);
	}
}
