package com.ufc.janaina.ui.windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ufc.janaina.services.AuthService;

/*
 * TODO
 * - Adicionar funcionalidae ao botão de login
 * 
 */

public class LoginWindow implements Window{
	private JFrame mainFrame;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private void make() {
		// Login container
		JPanel loginContainer = new JPanel();
		loginContainer.setLayout(new GridBagLayout());
		
		// Componentes de loginContainer
		JLabel usernameLabel = new JLabel("Usuário");
		JLabel passwordLabel = new JLabel("Senha");
		JLabel errorMsg = new JLabel("");
		errorMsg.setForeground(new Color(255, 0, 0));
		
		JTextField usernameText = new JTextField();
		JPasswordField passwordText = new JPasswordField();
		JButton loginBtn = new JButton("Login");
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				errorMsg.setText("");
				
				AuthService.getInstance().authenticate(usernameText.getText(), passwordText.getText());
				
				if(AuthService.getInstance().isAuthenticated()) {
					new WindowFactory().getWindow("SystemWindow").launch();
					mainFrame.dispose();
				}else {
					errorMsg.setText("Usuário ou password incorretos");
				}
				
			}
			
		});
		
		GridBagConstraints gbl = new GridBagConstraints();
		
		// Adiciona os componentes do Login Container
		gbl.fill = GridBagConstraints.HORIZONTAL;
		gbl.gridx = 0;
		gbl.gridy = 0;
		gbl.weightx = 0;
		loginContainer.add(usernameLabel, gbl);
		
		usernameText.setPreferredSize(new Dimension(400, 25));
		gbl.fill = GridBagConstraints.HORIZONTAL;
		gbl.gridx = 0;
		gbl.gridy = 1;
		gbl.weightx = 0;
		loginContainer.add(usernameText, gbl);
		
		// Adiciona os componentes do Login Container
		gbl.fill = GridBagConstraints.HORIZONTAL;
		gbl.gridx = 0;
		gbl.gridy = 2;
		gbl.weightx = 0;
		gbl.insets = new Insets(10, 0, 0, 0);
		loginContainer.add(passwordLabel, gbl);

		passwordText.setPreferredSize(new Dimension(400, 25));
		gbl.fill = GridBagConstraints.HORIZONTAL;
		gbl.gridx = 0;
		gbl.gridy = 3;
		gbl.weightx = 0;
		gbl.insets = new Insets(0, 0, 0, 0);
		loginContainer.add(passwordText, gbl);
		
		gbl.fill = GridBagConstraints.HORIZONTAL;
		gbl.gridx = 0;
		gbl.gridy = 4;
		gbl.weightx = 0;
		gbl.insets = new Insets(10, 0, 0, 0);
		loginContainer.add(loginBtn, gbl);
		
		gbl.fill = GridBagConstraints.HORIZONTAL;
		gbl.gridx = 0;
		gbl.gridy = 5;
		gbl.weightx = 0;
		gbl.insets = new Insets(10, 0, 0, 0);
		loginContainer.add(errorMsg, gbl);
		
		// Janela principal
		mainFrame = new JFrame("Login");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(640, 480);
		mainFrame.setLocationRelativeTo(null); // centraliza o Jframe
		mainFrame.getContentPane().setLayout(new GridBagLayout());
		mainFrame.setMinimumSize(new Dimension(640, 480));
		
		mainFrame.getContentPane().add(loginContainer);
	}
	
	@Override
	public void launch() {
		make();
		
		mainFrame.setVisible(true);;
	}
}
