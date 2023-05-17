package org.pixelgame.Editor.Windows;

import javax.swing.*;

public class WelcomePage {
    private JPanel panel1;
    private JButton NavigateButton;
    private JPanel ProjectPanel;

    public static void main(String[] args) {

        JFrame frame = new JFrame("WelcomePage");
        frame.setContentPane(new WelcomePage().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
