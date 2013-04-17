/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.localization;

import java.util.HashMap;
import java.util.Map;

/**
 * Localisation class allowing to access localisations in different languages.
 *
 */
public abstract class Messages implements IMessages {
	
	private Map<String, String> strings;
	
	public Messages() {
		strings = new HashMap<String, String>();
		init();
	}
	
	@Override
	public String get(String key) {
		String s = strings.get(key);
		if (s == null) {
			s = key;
		}
		
		return s;
	}

	/**
	 * Initialises the dictionary.
	 */
	protected abstract void init();
	
	/**
	 * Adds a localised string into the dictionary.
	 */
	protected void add(String key, String value) {
		strings.put(key, value);
	}

}
