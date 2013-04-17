/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.callback;

import javax.swing.SwingUtilities;

import com.zedlik.lc.localization.IMessages;
import com.zedlik.lc.ui.MainFrame;

/**
 * Implementation of {@link IConvertCallback} interface. Updates the UI and
 * provides feedback to the user.
 * 
 */
public class ConvertCallbackImpl implements IConvertCallback {

	private MainFrame frame;
	private IMessages messages;
	
	public ConvertCallbackImpl(MainFrame frame, IMessages messages) {
		this.frame = frame;
		this.messages = messages;
	}
	
	@Override
	public void conversionStarted() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.getBtnConvert().setText(messages.get(IMessages.BTN_STOP));
				frame.getPbStatus().setValue(frame.getPbStatus().getMinimum());
				frame.getPbStatus().setVisible(true);
			}
		});
	}

	@Override
	public void conversionFinished(final String text) {
		if (text != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					frame.getDest().setText(text);
					frame.getBtnConvert().setText(messages.get(IMessages.BTN_CONVERT));
					frame.getPbStatus().setVisible(false);
				}
			});
		}
	}

	@Override
	public void conversionCanceled() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.getBtnConvert().setText(messages.get(IMessages.BTN_CONVERT));
				frame.getPbStatus().setVisible(false);
			}
		});
	}

	@Override
	public void conversionProgress(final int progress) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.getPbStatus().setValue(progress);
			}
		});
	}

}
