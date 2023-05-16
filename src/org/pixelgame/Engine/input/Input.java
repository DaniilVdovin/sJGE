package org.pixelgame.Engine.input;
import org.pixelgame.Engine.Core.Vector2;

import java.awt.event.*;
public class Input implements KeyListener, MouseMotionListener {

    private static final boolean[] currentKeys =  new boolean[196];
    private static final Vector2<Integer> MousePosition = new Vector2<>(0,0);

    public static Vector2<Integer> getMousePosition(){
        return MousePosition;
    }
    public  static boolean getKey(int keyCode){
        return currentKeys[keyCode];
    }
    @Override public void keyTyped(KeyEvent e) {

    }
    @Override public void keyPressed(KeyEvent e) {
        currentKeys[e.getKeyCode()] = true;
    }
    @Override public void keyReleased(KeyEvent e) {
        currentKeys[e.getKeyCode()] = false;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        MousePosition.x = e.getX();
        MousePosition.y = e.getY();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        MousePosition.x = e.getX();
        MousePosition.y = e.getY();
    }
}
