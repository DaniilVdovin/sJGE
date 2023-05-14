package org.pixelgame.Engine.Particles;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.object.Sprite;
import java.awt.*;

public class Particle extends Sprite {
    public float lifeTime=0;
    public Particle(int id, Vector2 pos) {
        this(id,pos,false,false,Color.RED,5,10);
    }
    public Particle(int id, Vector2 pos, boolean Gravity, boolean Collision, Color color, int Size, float lifeTime) {
        super(id, pos);
        this.Collision = Collision;
        SetGravity(Gravity);
        SetColor(color);
        SetSize(Size);
        this.lifeTime = lifeTime;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(isAlive()) lifeTime-=deltaTime;
    }

    public boolean isAlive(){
        return lifeTime>0;
    }
}
