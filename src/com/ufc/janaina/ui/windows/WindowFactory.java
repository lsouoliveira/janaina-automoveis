package com.ufc.janaina.ui.windows;

public class WindowFactory {
	public Window getWindow(String windowName) {
		if(windowName.contentEquals("LoginWindow")) {
			return new LoginWindow();
		} else if(windowName.contentEquals("SystemWindow")) {
			return new SystemWindow();
		}
		
		return null;
	}
}
