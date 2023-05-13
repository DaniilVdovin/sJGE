package org.pixelgame.Engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener {

    private static boolean[] currentKeys =  new boolean[196];
    private static boolean MousePressed = false;

    public  static boolean getKey(int keyCode){
        return currentKeys[keyCode];
    }
    public  static boolean getMouse(){
        return MousePressed;
    }
    @Override public void keyTyped(KeyEvent e) {

    }
    @Override public void keyPressed(KeyEvent e) {
        currentKeys[e.getKeyCode()] = true;
    }
    @Override public void keyReleased(KeyEvent e) {
        currentKeys[e.getKeyCode()] = false;
    }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {MousePressed = true;}
    @Override public void mouseReleased(MouseEvent e) {MousePressed = false;}
    @Override public void mouseEntered(MouseEvent e) {

    }
    @Override public void mouseExited(MouseEvent e) {

    }
}
