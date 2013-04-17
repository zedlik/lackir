/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.controller;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.zedlik.lc.convert.ConvertRule;
import com.zedlik.lc.localization.IMessages;
import com.zedlik.lc.ui.MainFrame;

/**
 * Class controlling application startup.
 *
 */
public class StartupController {
	
	private static final String RULES_FILE_PATH = "rules.properties";
	private static final String ENCODING_BOM = "\uFEFF";
	
	private StartupController() {
		// Hiding the constructor
	}

	/**
	 * Main routine starting the application.
	 * @param messages Class providing localisation of messages
	 */
	public static void start(final IMessages messages) {
		// Setting the UI
		setLookAndFeel();
		
		// Load the conversion rules
		List<ConvertRule> ruleList = loadRules(messages);
		if (ruleList != null) {
			RuleListController.getInstance().setRules(ruleList);
			
			// Creating the UI
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					MainFrame frame = new MainFrame(messages);
					
					List<Image> icons = new ArrayList<Image>();
					icons.add(Toolkit.getDefaultToolkit().getImage("resources/32.png"));
					icons.add(Toolkit.getDefaultToolkit().getImage("resources/48.png"));
					icons.add(Toolkit.getDefaultToolkit().getImage("resources/16.png"));
					frame.setIconImages(icons);

					frame.setVisible(true);
				}
			});
		}
	}
	
	/**
	 * Sets look and feel of the application.
	 */
	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			// ignore
		}
	}
	
	/**
	 * Loads the list of conversion rules.
	 * 
	 * @return List of rules
	 */
	private static List<ConvertRule> loadRules(IMessages messages) {
		
		String rules = readRules(messages);
		if (rules != null) {
			return parseRules(messages, rules);
		}
		
		return null;
	}
	
	/**
	 * Reads conversion rules from a file on disk.
	 */
	private static String readRules(IMessages messages) {

		try {
			String rules = readFile(RULES_FILE_PATH);
			return rules;
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(null, 
					String.format(messages.get(IMessages.MSG_RULE_LOADING_FAILED), RULES_FILE_PATH, e.getMessage()), 
					messages.get(IMessages.MSG_RULE_LOADING_FAILED_HEADER), JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
	
	/**
	 * Parses content of conversion rules file and build a list of rules.
	 * 
	 * @param messages Localisation class
	 * @param ruleString Conversion rules as a string
	 * @return
	 */
	private static List<ConvertRule> parseRules(IMessages messages, String ruleString) {
		if (ruleString != null) {
			List<ConvertRule> convertRules = new ArrayList<ConvertRule>();
			
			ruleString = ruleString.trim();
			ruleString = ruleString.replaceAll("\r", "");
			String[] rules = ruleString.split("\n");
			
			for (int i = 0; i < rules.length; i++) {
				String rule = rules[i];
				rule = rule.replaceAll("^(.*?)(;.*)?$", "$1");
				rule = rule.trim();
				if (!rule.isEmpty()) {
					String[] ruleComponents = rule.split(" ");
					if (ruleComponents.length >= 2) {
						boolean isExtra = false;
						boolean ignoreCase = false;
						
						if (ruleComponents.length >= 3) {
							isExtra = ruleComponents[2].contains("~");
							ignoreCase = ruleComponents[2].contains("*");
						}
						
						if (convertRules.size() == 0) {
							if (ruleComponents[0].startsWith(ENCODING_BOM)) {
								ruleComponents[0] = ruleComponents[0].substring(1);
							}
						}
						
						ConvertRule convertRule = new ConvertRule(ruleComponents[0], ruleComponents[1], isExtra, ignoreCase);
						convertRules.add(convertRule);
					}
				}
			}
			
			if (convertRules.size() == 0) {
				JOptionPane.showMessageDialog(null, String.format(messages.get(IMessages.MSG_EMPTY_RULE_FILE), RULES_FILE_PATH), 
						messages.get(IMessages.MSG_EMPTY_RULE_FILE_HEADER), JOptionPane.ERROR_MESSAGE);
				return null;
			}
			
			return convertRules;
		}
		
		return null;
	}
	
	/**
	 * Reads a file on disk.
	 * 
	 * @param filePath Path to the file
	 * @return File content
	 * @throws IOException Thrown in case of a problem to read the file
	 */
	private static String readFile(String filePath) throws IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
        char[] buf = new char[2048];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            buffer.append(readData);
        }
        
        reader.close();
        return buffer.toString();
    }

}
