package org.pixelgame.Engine.object;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;

import java.awt.*;

public class Text extends Sprite{
    private final Vector2Int position;
    public StringBuilder Text;
    public Text(Vector2Int pos) {
        this(new StringBuilder(),pos);
    }
    public Text(StringBuilder Text, Vector2Int pos) {
        super(-10,pos);
        position = pos;
        SetSize(0);
        this.Text = Text;
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        for (String line : Text.toString().split("\n"))
            g.drawString(line, position.x, position.y += g.getFontMetrics().getHeight());
    }
}
