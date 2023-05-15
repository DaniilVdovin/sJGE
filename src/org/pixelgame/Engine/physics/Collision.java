package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Component;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.World;

import java.awt.*;

public class Collision implements Component {
    public boolean isEnable = true;
    public Sprite Object;
    public boolean isDebug = true;

    public Collision(Sprite object) {
        this(true, object, false);
    }

    public Collision(Sprite object, boolean isDebug) {
        this(true, object, isDebug);
    }

    public Collision(boolean isEnable, Sprite object, boolean isDebug) {
        this.isEnable = isEnable;
        Object = object;
        this.isDebug = isDebug;
    }

    @Override
    public void update(float deltaTime) {
        if (isEnable) {
            Object._isGround = false;
            for (Sprite sprite : World.curentWorld.sprites) {
                if (sprite == Object) continue;
                if (!sprite._isBounds) continue;
                Rectangle rect = new Rectangle((int) (Object.position.x - Object.Width / 2),
                        (int) (Object.position.y + Object.velocity.y * deltaTime - Object.Height / 2), Object.Width, Object.Height);
                Rectangle otherRect = new Rectangle((int) (sprite.position.x - sprite.Width / 2)
                        , (int) (sprite.position.y - sprite.Height / 2), sprite.Width, sprite.Height);
                if (rect.intersects(otherRect)) {
                    Object._isGround = true;
                    if (sprite.mass <= Object.mass) {
                        sprite.velocity.y += (Object.velocity.y / sprite.mass - Object.mass)*(sprite.position.y<Object.position.y?1:-1);
                    }
                    Object.velocity.y -= Object.velocity.y;
                }
                rect = new Rectangle((int) (Object.position.x + Object.velocity.x * deltaTime - Object.Width / 2),
                        (int) (Object.position.y - Object.Height / 2), Object.Width, Object.Height);
                if (rect.intersects(otherRect)) {
                    if (sprite.mass <= Object.mass) {
                        sprite.velocity.x += (Object.velocity.x / sprite.mass - Object.mass)*(sprite.position.x<Object.position.x?1:-1);
                        Object.velocity.x -= (Object.velocity.x / sprite.mass - Object.mass)*(sprite.position.x<Object.position.x?1:-1);
                    } else
                        Object.velocity.x -= Object.velocity.x;
                }
            }
            Object.velocity.x -= Object.velocity.x*deltaTime;
        }
    }
    @Override
    public void fixedupdate(float deltaTime) {

    }
    @Override
    public void render(Graphics g) {
        if(isDebug)
        {
            int realX = (int) Object.position.x - Object.Width/2;
            int realY = (int) Object.position.y - Object.Height/2;
            g.setColor(Color.GREEN);
            g.drawRect(realX,realY, Object.Width, Object.Height);

            g.setColor(Color.ORANGE);
            g.drawRect((int) (Object.position.x - Object.Width / 2),
                       (int) (Object.position.y + Object.velocity.y * 0.2f), Object.Width, Object.Height/2);
            g.setColor(Color.red);
            g.drawRect((int) (Object.position.x+ Object.velocity.x * 0.2f - Object.Width / 2),
                       (int) (Object.position.y - Object.Height / 2), Object.Width, Object.Height);
        }
    }
}
