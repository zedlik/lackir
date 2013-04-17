/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.localization;

/**
 * Localisation interface.
 *
 */
public interface IMessages {
	
	public static final String LBL_WINDOW_HEADER = "lbl.window.header";
	public static final String LBL_USE_ADVANCED_RULES = "lbl.use.advanced.rules";
	public static final String LBL_SOURCE_TEXT = "lbl.source.text";
	public static final String LBL_DESTINATION_TEXT = "lbl.destination.text";
	
	public static final String BTN_CONVERT = "btn.convert";
	public static final String BTN_STOP = "btn.stop";

	public static final String MSG_RULE_LOADING_FAILED = "msg.rule.loading.failed";
	public static final String MSG_RULE_LOADING_FAILED_HEADER = "rule.loading.failed.header";
	public static final String MSG_EMPTY_RULE_FILE = "msg.empty.rule.file";
	public static final String MSG_EMPTY_RULE_FILE_HEADER = "msg.empty.rule.file.header";
	
	
	/**
	 * Lookups and returns s localised string. 
	 */
	public String get(String key);

}
