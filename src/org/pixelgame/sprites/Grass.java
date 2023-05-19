package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collider;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Physics;

import java.awt.*;

public class Grass extends Sprite {
    Physics physics;
    public Grass(int id, Vector2<Integer> pos) {
        super(id, pos);
        SetSize(25);
        physics = (Physics) AddComponent(new Physics(this));
        physics._isStatic = true;
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
    @Override
    public void render(Graphics g){
        super.render(g);
    }
}
