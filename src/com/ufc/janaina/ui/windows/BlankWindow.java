package com.ufc.janaina.ui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.ufc.janaina.ui.views.View;

/*
 * TODO
 * - Adicionar funcionalidae ao botão de login
 * 
 */

public class BlankWindow implements Window{
	private JFrame mainFrame;
	private View view;
	private Dimension d;
	private String windowTitle;
	private boolean isResizable;
	private boolean isMaximized;
	
	public BlankWindow(View view, Dimension d, String windowTitle, boolean isResizible, boolean isMaximized) {
		this.view = view;
		this.d = d;
		this.windowTitle = windowTitle;
		this.isResizable = isResizible;
		this.isMaximized = isMaximized;
	}
	
	private void make() {
		// Janela principal
		mainFrame = new JFrame(windowTitle);
		
		if(this.d != null) {
			mainFrame.setPreferredSize(d);
		}
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setResizable(this.isResizable);
		
		mainFrame.add(view, BorderLayout.CENTER);
		
		mainFrame.pack();
		
		if(isMaximized) {
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		
		mainFrame.setLocationRelativeTo(null);
	}
	
	@Override
	public void launch() {
		make();
		
		mainFrame.setVisible(true);;
	}
}
