/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.controller;

import java.util.List;

import com.zedlik.lc.callback.ConvertCallbackImpl;
import com.zedlik.lc.callback.IConvertCallback;
import com.zedlik.lc.convert.ConvertRule;
import com.zedlik.lc.convert.ConvertThread;
import com.zedlik.lc.localization.IMessages;
import com.zedlik.lc.ui.MainFrame;

/**
 * Class starting conversion of text. Requests the appropriate conversion rule
 * list and starts a thread applying rule list to the text to convert.
 * 
 */
public class ConvertController {
	
	private static ConvertController instance;
	
	private ConvertThread convertThread = null;
	
	private ConvertController() {
		// Hiding the constructor
	}
	
	/**
	 * Returns an instance of the controller class.
	 * 
	 */
	public static ConvertController getInstance() {
		if (instance == null) {
			instance = new ConvertController();
		}
		
		return instance;
	}
	
	/**
	 * Starts the conversion of the text. Requests the respective list of
	 * conversion rules and starts a thread to process the text with the means
	 * of the obtained rules.
	 * 
	 * @param frame
	 *            Main frame instance to provide feedback to the user
	 * @param messages
	 *            Localisation class
	 * @param text
	 *            Text to convert
	 * @param useAdditionalRules
	 *            Defines whether basic or extended rule set should be used for
	 *            conversion
	 */
	public void runConverting(MainFrame frame, IMessages messages, String text, boolean useAdditionalRules) {
		if (convertThread == null || !convertThread.isAlive()) {
			
			List<ConvertRule> ruleList;
			
			if (useAdditionalRules) {
				ruleList = RuleListController.getInstance().getFullRuleList();
			}
			else {
				ruleList = RuleListController.getInstance().getBasicRules();
			}
			
			IConvertCallback callback = new ConvertCallbackImpl(frame, messages);
			
			// start new conversion
			convertThread = new ConvertThread(text, ruleList, callback);
			convertThread.start();
		}
		else {
			// stop conversion
			convertThread.forceStop();
		}
			
	}
	
}
