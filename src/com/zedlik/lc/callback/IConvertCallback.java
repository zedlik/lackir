/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.callback;

/**
 * Callback interface providing methods called when corresponding event happen.
 * Allows updating the UI and providing feedback to the user.
 * 
 */
public interface IConvertCallback {
	
	/**
	 * Called on conversion start.
	 * 
	 */
	public void conversionStarted();
	
	/**
	 * Called on conversion finish.
	 * 
	 * @param text Converted text
	 */
	public void conversionFinished(String text);
	
	/**
	 * Called on conversion cancel.
	 */
	public void conversionCanceled();

	/**
	 * Called on conversion progress to update the UI.
	 * 
	 * @param progress Current conversion progress value
	 */
	public void conversionProgress(int progress);

}
