package org.pixelgame.Engine.object;

import org.pixelgame.Engine.Core.Vector2;

import java.awt.*;

public class Text extends Sprite{
    private Vector2<Integer> position;
    public StringBuilder Text;
    public Text(Vector2<Integer> pos) {
        this(new StringBuilder(),pos);
    }
    public Text(StringBuilder Text, Vector2<Integer> pos) {
        super(-10,pos);
        position = pos;
        SetSize(0);
        this.Text = Text;
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        for (String line : Text.toString().split("\n"))
        {
            position = position.plusEquals(g.getFontMetrics().getHeight()).toInt();
            g.drawString(line, position.x,position.y);
        }

    }
}
