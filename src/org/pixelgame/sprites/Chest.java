package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.EventSystem.CollisionEvent;
import org.pixelgame.Engine.EventSystem.IOnCollisionListener;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collider;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Physics;
import org.pixelgame.Engine.world.World;

import java.awt.*;

public class Chest extends Sprite {
    SpriteImage ChestOpenSkin = new SpriteImage("/image/Chest_open.png");
    SpriteImage ChestSkin = new SpriteImage("/image/chest.png");

    public Chest(int id, Vector2<Integer> pos) {
        super(id, pos);
        SetSize(25);
        Collider open_trigger = (Collider) AddComponent(new Collider(this,false));
        open_trigger.isTrigger = false;
        AddComponent(
                new Collider(this,new Rectangle(pos.x,pos.y,Width+60,Height+30),true,false)
                .setOffset(new Vector2<>(30,30))
        );
        Physics physics = (Physics) AddComponent(new Physics(this));
        Collision collision = (Collision) AddComponent(new Collision(this));
        SetImage(ChestSkin);
        physics.mass = 20;
        physics._isStatic = true;
        collision.addListener(new IOnCollisionListener() {
            @Override
            public void CollisionEnter(Physics sender) {
                System.out.println("Chest ("+ id +") CollisionEnter(): sender.id("+sender.GetParent().id+")");
                if(sender.GetParent().id == -1){
                    SetImage(ChestOpenSkin);
                }
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
