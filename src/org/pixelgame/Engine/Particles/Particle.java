package org.pixelgame.Engine.Particles;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collision;

import java.awt.*;

// FIXME: 16.05.2023 
public class Particle extends Sprite {
    public float lifeTime=0;
    public Particle(int id, Vector2<Integer> pos) {
        this(id,pos,false,false,Color.RED,5,10);
    }
    public Particle(int id, Vector2<Integer> pos, boolean Gravity, boolean Collision, Color color, int Size, float lifeTime) {
        super(id, pos);
        SetColor(color);
        SetSize(Size);
        this.lifeTime = lifeTime;
        if(Collision) components.add(new Collision(this));
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(isAlive()){
            lifeTime-=deltaTime;
            SetSize(Width-(int)((Width/lifeTime)*deltaTime));
        }
    }

    public boolean isAlive(){
        return lifeTime<=0;
    }
}
