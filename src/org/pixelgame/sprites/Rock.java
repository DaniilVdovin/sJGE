package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Sprite;

import java.awt.*;

public class Rock extends Sprite {

    /**
     * Constructor
     *
     * @param id
     * @param pos
     */
    public Rock(int id, Vector2<Integer> pos) {
        super(id, pos);
        SetSize(25);
        SetColor(Color.DARK_GRAY);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void fixedupdate(float deltaTime) {
        super.fixedupdate(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
