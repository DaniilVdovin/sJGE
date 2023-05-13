package org.pixelgame.Engine.object;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticleSystem extends Sprite{
    private List<Sprite> Particles = new ArrayList<>();
    private boolean Enable = false;
    private boolean isCreated = false;
    private int Count = 0;
    private float LifeTime = 0;
    public ParticleSystem(int id, float posX, float posY) {
        super(id, posX, posY);
        SetSize(2);
        SetColor(Color.WHITE);
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (Enable) {
            Random rand = new Random();
            rand.setSeed(this.hashCode());
            for (Sprite s : Particles) {
                s.SetSize(10);
                s.update(deltaTime);
            }
            if(LifeTime<=0) Stop();
            LifeTime -= deltaTime;
        }
    }
    @Override
    public void render(Graphics g) {
        if(Enable) for (Sprite s:Particles) s.render(g);
        //super.render(g);
    }
    public void Stop(){
        if(!isCreated) throw new RuntimeException("First need Create ParticleSystem");
        Enable = false;
        LifeTime = 0;
        Count = 0;
        Particles = new ArrayList<>();
        isCreated = false;
    }
    public void Pause(){
        if(isCreated) Enable = false;
        else throw new RuntimeException("First need Create ParticleSystem");
    }
    public void Run(){
       if(isCreated) Enable = true;
       else throw new RuntimeException("First need Create ParticleSystem");
    }
    public void Create(int count,float lifeTime){
        if(isCreated) Stop();
        Enable = true;
        LifeTime = lifeTime;
        Count = count;
        Particles = new ArrayList<>();
        for (int i = 0; i < Count; i++) {
            Sprite temp = new Sprite(i,position);
            temp.SetGravity(true);
            temp.SetSize(10);
            temp.SetColor(Color.ORANGE);
            Particles.add(temp);
        }
        isCreated = true;
    }
}
