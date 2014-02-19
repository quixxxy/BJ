package com.quixxxy.bj.engine.gui;

import com.quixxxy.bj.model.Box;
import com.quixxxy.bj.model.Table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame implements ActionListener {

    private JFrame mainFrame;
    private JPanel contentPanel;
    private JTextArea textArea;

    private JPanel buttonPanel;
    private JButton stayButton;
    private JButton moreButton;
    private JButton clearButton;

    private JButton startNewGameButton;

    private Table table;
    private Box playerBox;

    public MainFrame() {
        init();
    }

    private void init() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(640, 480);
        mainFrame.setContentPane(getContentPanel());
        mainFrame.setTitle("BJ");
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
        return new JScrollPane(textArea);
    }

    private Component getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout());
            buttonPanel.add(getMoreButton(), BorderLayout.WEST);
            buttonPanel.add(getStayButton(), BorderLayout.EAST);
            buttonPanel.add(getClearButton(), BorderLayout.CENTER);
            buttonPanel.setVisible(false);
        }
        return buttonPanel;

    }

    private Component getClearButton() {
        if (clearButton == null) {
            clearButton = new JButton();
            clearButton.setText("Clear");
            clearButton.addActionListener(this);
        }
        return clearButton;
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

    private void startNewGame() {
        startNewGameButton.setVisible(false);
        buttonPanel.setVisible(true);
        textArea.setVisible(true);

        if (table == null) {
            //first game
            table = new Table();
        } else {
            //continue with the same shoe
            table = new Table(table.getPlayerBoxes().size(), table.getShoe());
        }
        playerBox = table.getPlayerBoxes().get(0);

        print(table.getShoe());
        
        table.dealToAllBoxes();
        table.dealToPlayerBoxes();

        print("Dealer box: " + table.getDealerBox());
        print("Player box: " + playerBox);
    }

    private void calucateResults() {
        print("Dealer box: " + table.getDealerBox());

        String winner = "Winnrer(s): " + table.getWinner().toString();
        print(winner);

        int reply = JOptionPane.showConfirmDialog(mainFrame, winner, "Start new game?",
                JOptionPane.YES_NO_OPTION);

        switch (reply) {
        case JOptionPane.YES_OPTION:
            startNewGame();
            break;
        case JOptionPane.NO_OPTION:
            mainFrame.dispose();
            break;
        }
    }

    private void print(Object message) {
        textArea.append(message.toString() + "\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand().toLowerCase();
        switch (action) {
        case "new":
            startNewGame();
            break;
        case "stay":
            table.dealCardsToDealer();
            calucateResults();
            break;
        case "more":
            table.dealToBox(playerBox);
            print("Player box: " + playerBox);
            if (playerBox.isBurned()) {
                calucateResults();
            }
            break;
        case "clear":
            textArea.setText(null);
            break;
        }
    }

}
