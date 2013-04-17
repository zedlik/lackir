/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.convert;

import java.util.List;
import java.util.regex.Pattern;

import com.zedlik.lc.callback.IConvertCallback;

/**
 * Thread class applying conversion rules to the source text.
 *
 */
public class ConvertThread extends Thread {
	
	private String text;
	private List<ConvertRule> ruleList;
	private IConvertCallback callback;
	
	private boolean forceStop = false;

	/**
	 * Creates a new processing thread.
	 * 
	 * @param text Text to convert
	 * @param ruleList List of rules that have to be applied to the text
	 * @param callback Callback class to trigger events indicating conversion status
	 */
	public ConvertThread(String text, List<ConvertRule> ruleList, IConvertCallback callback) {
		this.text = text;
		this.ruleList = ruleList;
		this.callback = callback;
	}
	
	/**
	 * Main thread routine.
	 */
	public void run() {
		if (text != null && text.length() > 0 && ruleList != null) {
			callback.conversionStarted();
			
			for (int i = 0; i < ruleList.size(); i++) {
				if (forceStop) {
					break;
				}
				
				ConvertRule rule = ruleList.get(i);
				String pattern = Pattern.quote(rule.getPattern());
				if (rule.isIgnoreCase()) {
					pattern = "(?iu)" + pattern;
				}
						
				text = text.replaceAll(pattern, rule.getReplacement());
				callback.conversionProgress(((i + 1) * 100) / ruleList.size());
			}
			
			String resultText = null;
			if (!forceStop) {
				resultText = text;
				callback.conversionFinished(resultText);
			}
			else {
				callback.conversionCanceled();
			}
		}
	}
	
	/**
	 * Requests the thread to terminate.
	 */
	public void forceStop() {
		this.forceStop = true;
	}

}
