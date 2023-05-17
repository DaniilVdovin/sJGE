package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.EventSystem.IOnCollisionListener;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collider;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Physics;

import java.awt.*;

public class Chest extends Sprite {
    SpriteImage ChestOpenSkin = new SpriteImage("/image/Chest_open.png");
    SpriteImage ChestSkin = new SpriteImage("/image/chest.png");

    public Chest(int id, Vector2<Integer> pos) {
        super(id, pos);
        SetSize(20);
        AddComponent(new Collider(this,true));
        Physics physics = (Physics) AddComponent(new Physics(this));
        Collision collision = (Collision) AddComponent(new Collision(this));
        SetImage(ChestSkin);
        physics.mass = 20;
        physics._isStatic = true;
        collision.isDebug = true;
        collision.addListener(new IOnCollisionListener() {
            @Override
            public void CollisionEnter(Physics sender) {
                System.out.println("Chest ("+ id +") CollisionEnter: sender.id("+sender.GetParent().id+")");
                if(sender.GetParent().id == -1){SetImage(ChestOpenSkin);}
            }
            @Override
            public void CollisionExit(Physics sender) {
                System.out.println("Chest ("+ id +") CollisionExit: sender.id("+sender.GetParent().id+")");
                if(sender.GetParent().id == -1){SetImage(ChestSkin);}
            }
        });
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
