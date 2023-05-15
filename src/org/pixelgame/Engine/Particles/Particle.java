package org.pixelgame.Engine.Particles;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Gravity;

import java.awt.*;

public class Particle extends Sprite {
    public float lifeTime=0;
    public Particle(int id, Vector2 pos) {
        this(id,pos,false,false,Color.RED,5,10);
    }
    public Particle(int id, Vector2 pos, boolean Gravity, boolean Collision, Color color, int Size, float lifeTime) {
        super(id, pos);
        SetColor(color);
        SetSize(Size);
        this.lifeTime = lifeTime;

        if(Gravity) components.add(new Gravity(this));
        if(Collision) components.add(new Collision(this));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(isAlive()){
            lifeTime-=deltaTime;
            SetSize(Width-(int)(Width/lifeTime));
        }
    }

    public boolean isAlive(){
        return lifeTime<=0;
    }
}
