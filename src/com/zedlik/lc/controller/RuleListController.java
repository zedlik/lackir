/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.controller;

import java.util.ArrayList;
import java.util.List;

import com.zedlik.lc.convert.ConvertRule;

/**
 * Stores the list of conversion rules. Provides a respective rule list 
 * depending on request.  
 *
 */
public class RuleListController {
	
	private static RuleListController instance;
	
	private List<ConvertRule> fullRuleList = null;
	private List<ConvertRule> basicRuleList = null;
	
	private RuleListController() {
		// Hiding the constructor
	}
	
	/**
	 * Returns an instance of the controller class.
	 * 
	 */
	public static RuleListController getInstance() {
		if (instance == null) {
			instance = new RuleListController();
		}
		
		return instance;
	}
	
	/**
	 * Sets the rule list.
	 * 
	 * @param rules List of conversion rules
	 */
	public void setRules(List<ConvertRule> rules) {
		this.fullRuleList = rules;
	}
	
	/**
	 * Returns the full list of conversion rules.
	 * 
	 * @return List of conversion rules 
	 */
	public List<ConvertRule> getFullRuleList() {
		
		if (fullRuleList == null) {
			fullRuleList = new ArrayList<ConvertRule>();
		}
		
		return fullRuleList;
	}
	
	/**
	 * Returns the list containing only basic conversion rules.
	 * 
	 * @return List of conversion rules
	 */
	public List<ConvertRule> getBasicRules() {
		if (basicRuleList == null) {
			basicRuleList = new ArrayList<ConvertRule>();
			
			if (fullRuleList != null) {
				for (int i = 0; i < fullRuleList.size(); i++) {
					if (!fullRuleList.get(i).isExtraRule()) {
						basicRuleList.add(fullRuleList.get(i));
					}
				}
			}
		}
		
		return basicRuleList;
	}
}
