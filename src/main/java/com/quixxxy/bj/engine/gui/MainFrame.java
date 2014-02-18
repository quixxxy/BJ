package com.quixxxy.bj.engine.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.quixxxy.bj.engine.Engine;

public class MainFrame extends Engine implements ActionListener {

	private JFrame mainFrame;
	private JPanel contentPanel;
	private JTextArea textArea;

	private JPanel buttonPanel;
	private JButton stayButton;
	private JButton moreButton;

	private JButton startNewGameButton;

	// OMG OMG :((
	private String command;

	public MainFrame() {
		init();
	}

	private void init() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 600);
		mainFrame.setContentPane(getContentPanel());
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.mainFrame.setVisible(true);
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getTextArea(), BorderLayout.CENTER);
			contentPanel.add(getButtonPanel(), BorderLayout.SOUTH);

			startNewGameButton = new JButton();
			startNewGameButton.addActionListener(this);
			startNewGameButton.setText("new");

			contentPanel.add(startNewGameButton, BorderLayout.NORTH);
		}
		return contentPanel;
	}

	private Component getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}

	private Component getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			buttonPanel.add(getMoreButton(), BorderLayout.WEST);
			buttonPanel.add(getStayButton(), BorderLayout.EAST);
		}
		return buttonPanel;

	}

	private Component getStayButton() {
		if (stayButton == null) {
			stayButton = new JButton();
			stayButton.setText("Stay");
			stayButton.addActionListener(this);
		}
		return stayButton;
	}

	private Component getMoreButton() {
		if (moreButton == null) {
			moreButton = new JButton();
			moreButton.setText("More");
			moreButton.addActionListener(this);
		}
		return moreButton;

	}

	@Override
	protected String getCommand() {
		while (command == null) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
		return command;

	}

	@Override
	protected void print(Object message) {
		textArea.setText(textArea.getText() + "\n" + message.toString());
		textArea.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if ("new".equalsIgnoreCase(action)) {
			startNewGameButton.setVisible(false);
		} else {
			command = action.toLowerCase();
		}
	}

}
