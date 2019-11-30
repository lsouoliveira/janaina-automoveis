package com.ufc.janaina.base;

import java.awt.MediaTracker;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.ufc.janaina.jdbc.DatabaseConnection;
import com.ufc.janaina.ui.AppUI;
import com.ufc.janaina.ui.views.BuscarTestesView;
import com.ufc.janaina.ui.views.CadastrarClienteView;
import com.ufc.janaina.ui.views.CadastrarTesteView;
import com.ufc.janaina.ui.windows.BlankWindow;

public class App {
	private AppUI appUI;
	
	public App() {
		appUI = new AppUI();
	}
	
	private void init() {
		// Init HIBERNATE session
		DatabaseConnection.getInstance().init();
		
//			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//			    if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {   
//			       try {
//					UIManager.setLookAndFeel(info.getClassName());
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (UnsupportedLookAndFeelException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			       break;
//			     } 
//			}
	}
	
	public void launch() {
		// Show splash screen
		ImageIcon loadingImg = new ImageIcon(getClass().getResource("/resources/janaina_splash.png"));
		
		while(loadingImg.getImageLoadStatus() != MediaTracker.COMPLETE);
		
		JLabel loadingImage = new JLabel(loadingImg);
		JFrame splashScreen = new JFrame();
		splashScreen.setUndecorated(true);
		splashScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		splashScreen.add(loadingImage);
		splashScreen.pack();
		splashScreen.setLocationRelativeTo(null);
		splashScreen.setVisible(true);
		
		// Initialize the system
		init();
		
		// Destroy splash screen after loading is complete
		splashScreen.dispose();
		
		appUI.open("LoginWindow");
	}
}
