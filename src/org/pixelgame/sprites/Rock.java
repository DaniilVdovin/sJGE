package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Gravity;

import java.awt.*;

public class Rock extends Sprite {
    public Rock(int id, int posX, int posY) {
        this(posX,posY);
    }
    public Rock(int posX, int posY){
        super(0, posX, posY);
        mass = 1;
        _isStatic = true;
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
