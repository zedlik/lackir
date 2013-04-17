/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 * 
 * Rule set by Vitali Stanisheuski.
 */
package com.zedlik.lc;

import com.zedlik.lc.controller.StartupController;
import com.zedlik.lc.localization.IMessages;
import com.zedlik.lc.localization.MessagesBe;

/**
 * Startup class.
 *
 */
public class LacKir {
	
	public static void main(String[] args) {
		IMessages messages = new MessagesBe();
		if (StartupController.init(messages)) {
			StartupController.start(messages);
		}
	}
}
