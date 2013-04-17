/**
 * Lacinka to Kirylica converter.
 * 
 * zedlik, 2013
 * jz53x <at> zedlik.com
 */
package com.zedlik.lc.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import com.zedlik.lc.controller.ConvertController;
import com.zedlik.lc.localization.IMessages;

/**
 * Main frame of the application.
 *
 */
public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 8362611765198248255L;
	
	private JButton btnConvert;
	private JTextArea source;
	private JTextArea dest;
	private JCheckBox cbUseAdvancedRules;
	private JProgressBar pbStatus;
	private IMessages messages;

	public MainFrame(IMessages messages) {
		super(messages.get(IMessages.LBL_WINDOW_HEADER));
		this.messages = messages;
		initComponents();
	}
	
	/**
	 * Initialises conponents of the frame.
	 */
	private void initComponents() {
		setLayout(new GridBagLayout());
		JPanel panel = createPanel();
		GridBagConstraints c = UIUtils.buildConstraints(0, 0, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10));
		add(panel, c);
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Creates main panel of the frame.
	 */
	private JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		JPanel sourcePanel = new JPanel(new GridBagLayout());
		source = new JTextArea(5, 40);
		initAndAddTextArea(sourcePanel, source, messages.get(IMessages.LBL_SOURCE_TEXT));
		GridBagConstraints c = UIUtils.buildConstraints(0, 0, GridBagConstraints.BOTH, new Insets(0, 0, 10, 0));
		panel.add(sourcePanel, c);
		
		JPanel destPanel = new JPanel(new GridBagLayout());
		dest = new JTextArea(5, 40);
		initAndAddTextArea(destPanel, dest, messages.get(IMessages.LBL_DESTINATION_TEXT));
		c = UIUtils.buildConstraints(0, 1, GridBagConstraints.BOTH, new Insets(0, 0, 10, 0));
		panel.add(destPanel, c);
		
		JPanel controlPanel = createControlPanel();
		c = UIUtils.buildConstraints(0, 2, GridBagConstraints.BOTH, 1f, 0f, new Insets(0, 0, 0, 0));
		panel.add(controlPanel, c);
		
		setPreferredSize(new Dimension(750, 500));
		setMinimumSize(new Dimension(500, 300));
		return panel;
	}

	/**
	 * Adds a text area onto the host panel.
	 */
	private void initAndAddTextArea(JPanel panel, JTextArea textArea, String header) {

		JLabel panelHeader = new JLabel(header);
		GridBagConstraints c = UIUtils.buildConstraints(0, 0, GridBagConstraints.HORIZONTAL, 1f, 0f, new Insets(0, 1, 5, 0));
		panel.add(panelHeader, c);
		
		JScrollPane sourceScrollPane = new JScrollPane(textArea);
		sourceScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		// Use Tab key for focus navigation
		Set<KeyStroke> strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("pressed TAB")));
		textArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, strokes);
		strokes = new HashSet<KeyStroke>(Arrays.asList(KeyStroke.getKeyStroke("shift pressed TAB")));
		textArea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, strokes);
    	
		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
		textArea.setFont(f);
		
		c = UIUtils.buildConstraints(0, 1, GridBagConstraints.BOTH, 1f, 1f, new Insets(0, 0, 0, 0));
		panel.add(sourceScrollPane, c);
	}
	
	/**
	 * Creates a control panel.
	 */
	private JPanel createControlPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		cbUseAdvancedRules = new JCheckBox(messages.get(IMessages.LBL_USE_ADVANCED_RULES));
		cbUseAdvancedRules.setSelected(true);
		GridBagConstraints c = UIUtils.buildConstraints(0, 0, GridBagConstraints.BOTH, 0f, 1f, new Insets(0, 0, 0, 0));
		panel.add(cbUseAdvancedRules, c);
		
		JPanel placeholder = new JPanel(new GridBagLayout());
		c = UIUtils.buildConstraints(1, 0, GridBagConstraints.BOTH, 1f, 1f, new Insets(0, 10, 0, 10));
		panel.add(placeholder, c);
		
		pbStatus = new JProgressBar();
		pbStatus.setMinimum(0);
		pbStatus.setMaximum(100);
		c = UIUtils.buildConstraints(0, 0, GridBagConstraints.BOTH, 1f, 1f, new Insets(0, 0, 0, 0));
		pbStatus.setPreferredSize(new Dimension(10, 25));
		placeholder.add(pbStatus, c);
		pbStatus.setVisible(false);
		
		btnConvert = new JButton(messages.get(IMessages.BTN_CONVERT));
		btnConvert.setPreferredSize(new Dimension(120, 25));
		btnConvert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConvertController.getInstance().runConverting(MainFrame.this, messages, source.getText(), cbUseAdvancedRules.isSelected());
			}
		});
		c = UIUtils.buildConstraints(2, 0, GridBagConstraints.BOTH, 0f, 1f, new Insets(0, 0, 0, 0));
		panel.add(btnConvert, c);
		
		return panel;
	}

	public JButton getBtnConvert() {
		return btnConvert;
	}

	public JTextArea getSource() {
		return source;
	}

	public JTextArea getDest() {
		return dest;
	}
	
	public JProgressBar getPbStatus() {
		return pbStatus;
	}
}
