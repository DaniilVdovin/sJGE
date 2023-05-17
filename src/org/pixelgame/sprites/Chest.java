package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collider;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Physics;

import java.awt.*;

public class Chest extends Sprite {
    SpriteImage ChestOpenSkin = new SpriteImage("/image/Chest_open.png");
    SpriteImage ChestSkin = new SpriteImage("/image/chest.png");
    boolean SkinIsChanged = false;


    public Chest(int id, Vector2<Integer> pos) {
        super(id, pos);
        Physics physics = (Physics) AddComponent(new Physics(this));
        AddComponent(new Collision(this));
        AddComponent(new Collider(this,true));
        physics.mass = 100;
        SetSize(25);
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);




    }

    @Override
    public void fixedupdate(float deltaTime) {
        super.fixedupdate(deltaTime);
        if(SkinIsChanged == false)
        {
            SetImage(ChestOpenSkin);
            SkinIsChanged = true;
            SetSize(50);
        }
        else{
            SetImage(ChestSkin);
            SkinIsChanged = false;
            SetSize(25);

        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
