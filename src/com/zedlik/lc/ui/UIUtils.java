/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.ui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Helper class to provide auxiliary routines for the UI.
 *
 */
public class UIUtils {
	
	/**
	 * Creates and initialises a GridBagConstraints object.
	 */
	public static GridBagConstraints buildConstraints(int gridx, int gridy, int fill, Insets insets) {
		return buildConstraints(gridx, gridy, fill, 1f, 1f, insets);
	}
	
	/**
	 * Creates and initialises a GridBagConstraints object.
	 */
	public static GridBagConstraints buildConstraints(int gridx, int gridy, int fill, float weightx, float weighty, Insets insets) {
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = fill;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = gridx;
		c.gridy = gridy;
		c.insets = insets;
		c.weightx = weightx;
		c.weighty = weighty;
		
		return c;
	}

}
