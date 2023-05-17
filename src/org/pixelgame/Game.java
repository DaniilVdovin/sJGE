package org.pixelgame;

import org.pixelgame.EditorV2.Windows.MainWindow;

import javax.swing.*;

public class Game{
    public static void main(String[] args) {
        //javax.swing.SwingUtilities.invokeLater(MainWindow::new);
        new MainWindow();
        //new GameApp().Init();
    }
}
