package org.pixelgame.Engine.uitoolkit.components;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.EventSystem.ClickEvent;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.uitoolkit.UIComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends UIComponent {
    int testClicked = 0;
    public String Text;
    public Button(Vector2<Integer> position) {
        this(2, 50, 20, position);
    }
    public Button(int layer, int width, int height, Vector2<Integer> position) {
        super(layer, width, height, position);

    }

    @Override
    public void update(float deltaTime){

    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(position.x, position.y, Width,Height);
        g.setColor(Color.black);
        g.drawString(Text, position.x+5, position.y+15);

        g.setColor(Color.red);
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }
}
