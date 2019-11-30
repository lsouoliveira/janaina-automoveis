package com.ufc.janaina.ui;

import com.ufc.janaina.ui.windows.Window;
import com.ufc.janaina.ui.windows.WindowFactory;

public class AppUI {
	public void open(String windowName){
		WindowFactory windowFactory = new WindowFactory();
		Window window = windowFactory.getWindow(windowName);
		
		if(window == null) {
			throw new RuntimeException("Window " + windowName + " is undefined");
		}
		
		window.launch();
	}
}
