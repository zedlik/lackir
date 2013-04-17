/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.ui;

import javax.swing.JApplet;
import javax.swing.JFrame;

import com.zedlik.lc.controller.StartupController;
import com.zedlik.lc.localization.IMessages;
import com.zedlik.lc.localization.MessagesBe;

/**
 * Startup class for the applet.
 *
 */
public class LacKirApplet extends JApplet {
	
	private static final long serialVersionUID = 4722397155737120831L;

	public void init() {
		IMessages messages = new MessagesBe();
		if (StartupController.init(messages)) {
			JFrame frame = StartupController.createMainFrame(messages);
			add(frame.getContentPane());
		}
	}
}
