package org.pixelgame.Engine.uitoolkit.components;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.uitoolkit.UIComponent;

import java.awt.*;

public class Grid extends UIComponent {
    public Grid(int width, int height, Vector2<Integer> position) {
        super(1, width, height, position);
    }
    @Override
    public void update(float deltatime) {
        super.update(deltatime);
    }
    @Override
    public void fixedupdate(float deltatime) {
        super.fixedupdate(deltatime);
    }
    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.lightGray);
        g.drawRect(position.x, position.y,Width,Height);
    }
}
