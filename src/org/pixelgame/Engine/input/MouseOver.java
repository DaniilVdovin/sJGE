package org.pixelgame.Engine.input;

import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseOver implements IComponent, MouseListener, IUpdatable {
    public boolean isHover = false;
    public Rectangle rect;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        isHover = rect.intersects(e.getX()-1,e.getY()-1,e.getX()+1,e.getY()+1);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        isHover = rect.intersects(e.getX()-1,e.getY()-1,e.getX()+1,e.getY()+1);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void fixedupdate(float deltaTime) {

    }
    @Override
    public void render(Graphics g) {

    }

    @Override
    public Sprite GetParent() {
        return null;
    }
}
