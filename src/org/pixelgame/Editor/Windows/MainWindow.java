package org.pixelgame.Editor.Windows;
import org.pixelgame.Engine.uitoolkit.components.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow extends Frame
{
    private Panel ProjectPanel;
    private TextArea allMessagesArea;
    private TextArea inputArea;
    private Button buttonSend;
    private Button buttonExit;
    private String login;
    public MainWindow()
    {
        //Config Window
        setSize(800,600);
        welcomePage();
        setVisible(true);

    }

    public void welcomePage()
    {

        //Create widgets
        Panel ParentPanel = new Panel( new FlowLayout(FlowLayout.LEFT, 0, 0) );
        ParentPanel.setSize(600,800);

        ParentPanel.setBackground(Color.BLUE);

        ProjectPanel = new Panel();
        ProjectPanel.setSize(640,580);

        ProjectPanel.setBackground(Color.GREEN);



        ParentPanel.add(ProjectPanel);






        this.add(ParentPanel);
    }

    public static void main(String[] args)
    {

        MainWindow frame = new MainWindow();
    }
}