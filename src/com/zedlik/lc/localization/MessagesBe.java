/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.localization;

/**
 * Provides localisation into Belarusian.
 *
 */
public class MessagesBe extends Messages implements IMessages {
	
	@Override
	protected void init() {
		add(LBL_WINDOW_HEADER, "Łacinka › кірыліца");
		add(LBL_USE_ADVANCED_RULES, "Выкарыстоўваць дадатковыя правілы (марудней)");
		add(LBL_SOURCE_TEXT, "Тэкст лацінкай:");
		add(LBL_DESTINATION_TEXT, "Тэкст кірыліцай:");

		add(BTN_CONVERT, "У кірыліцу");
		add(BTN_STOP, "Спыніць");

		add(MSG_RULE_LOADING_FAILED, "Немагчыма загрузіць правілы з файла %s. Праверце, што файл існуе.\n\nПамылка: %s");
		add(MSG_RULE_LOADING_FAILED_HEADER, "Памылка загрузкі правілаў");
		add(MSG_EMPTY_RULE_FILE, "Немагчыма загрузіць правілы з файла %s: файл пусты.");
		add(MSG_EMPTY_RULE_FILE_HEADER, "Памылка загрузкі правілаў");
	}
}
