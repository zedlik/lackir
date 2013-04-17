/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.convert;

/**
 * Stores information about a single conversion rule.
 *
 */
public class ConvertRule {
	
	private String pattern;
	private String replacement;
	private boolean extraRule;
	private boolean ignoreCase;

	/**
	 * Creates a conversion rule
	 * @param pattern Pattern to search in the source text
	 * @param replacement Replacement string to replace the matched pattern
	 * @param extraRule Defines whether the rule is an advanced rule
	 * @param ignoreCase Defines whether the pattern search should be done in case-insensitive way
	 */
	public ConvertRule(String pattern, String replacement, boolean extraRule, boolean ignoreCase) {
		super();
		this.pattern = pattern;
		this.replacement = replacement;
		this.extraRule = extraRule;
		this.ignoreCase = ignoreCase;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	public boolean isExtraRule() {
		return extraRule;
	}

	public void setExtraRule(boolean extraRule) {
		this.extraRule = extraRule;
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

}
