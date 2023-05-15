package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.object.Component;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.World;

import java.awt.*;

public class Gravity implements Component {
    public boolean isEnable  = true;
    public Sprite Object;
    public boolean isDebug = false;

    public Gravity(Sprite object) {
        this(true,object,false);
    }
    public Gravity(boolean isEnable, Sprite object, boolean isDebug) {
        this.isEnable = isEnable;
        Object = object;
        this.isDebug = isDebug;
    }

    @Override
    public void update(float deltaTime) {
        if(isEnable) {
            if(!Object._isGround & !Object._isStatic)
                Object.velocity.y += Object.mass*deltaTime;
        }
    }
    @Override
    public void fixedupdate(float deltaTime) {}
    @Override
    public void render(Graphics g) { }
}
