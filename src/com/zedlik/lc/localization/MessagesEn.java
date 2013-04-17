/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.localization;

/**
 * Provides localisation into English.
 *
 */
public class MessagesEn extends Messages implements IMessages {
	
	@Override
	protected void init() {
		add(LBL_WINDOW_HEADER, "Łacinka › Kirylica kanvertar");
		add(LBL_USE_ADVANCED_RULES, "Use advanced rules (slower)");
		add(LBL_SOURCE_TEXT, "Text in Łacinka:");
		add(LBL_DESTINATION_TEXT, "Text in Cyrillics:");
		
		add(BTN_CONVERT, "Convert");
		add(BTN_STOP, "Stop");

		add(MSG_RULE_LOADING_FAILED, "Unable to load convertion rules from file %s. Exitting.\n\nError message: %s");
		add(MSG_RULE_LOADING_FAILED_HEADER, "Rules loading failed");
		
		add(MSG_EMPTY_RULE_FILE, "Unable to load convertion rules from file %s. The file is empty. Exitting.");
		add(MSG_EMPTY_RULE_FILE_HEADER, "Rules loading failed");
	}
}
