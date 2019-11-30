package com.ufc.janaina.ui.views;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class ViewManager extends JPanel{
	private CardLayout cardLayout;
	
	public ViewManager() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
	}
	
	public void setView(String viewName, View view) {
		add(view, viewName);
	}
	
	public void getView(String viewName) {
		cardLayout.show(this, viewName);
	}
}
