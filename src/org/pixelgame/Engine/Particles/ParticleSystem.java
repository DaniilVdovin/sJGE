package org.pixelgame.Engine.Particles;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.object.Text;

import java.awt.*;
import java.util.*;
import java.util.List;


public class ParticleSystem extends Sprite {
    private List<Particle> Particles = new ArrayList<>();
    private boolean Enable = false;
    private boolean isCreated = false;
    private IParticleAction action;
    private final float duration;
    private float temp_duration;
    public  boolean isDebug = false;
    public ParticleSystem(){
        this(0,0,0,10);
    }
    public ParticleSystem(int id, int posX, int posY) {
        this(id,posX,posY,10);
    }
    public ParticleSystem(int id, int posX, int posY,float duration) {
        super(id, posX, posY);
        _isGravity = false;
        mass = 0;
        SetSize(5);
        SetColor(Color.WHITE);
        this.duration = duration;
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (Enable) {
            for (Iterator<Particle> particleIterator = Particles.iterator();particleIterator.hasNext();){
                Particle p = particleIterator.next();
                p.position = action.Action(p.position,deltaTime);
                if(p.isAlive()){
                    action.Destroy(true);
                    particleIterator.remove();
                    continue;
                }
                p.update(deltaTime);
            }
            temp_duration -= deltaTime;
            if(temp_duration<=0) {
                Emit(10, 10);
                temp_duration = duration;
            }
        }
    }
    @Override
    public void render(Graphics g) {
        super.render(g);
        if(isDebug){
            Text stats = new Text(position.toInt().plusY(10).minusX(10));
            stats.Text.append("Particles: ").append(Particles.size()).append("\n")
                      .append("Duration: ").append(duration).append("\n")
                      .append("TDuration: ").append((int)temp_duration).append("\n");
            stats.render(g);
        }
        if(Enable) for (Particle s:Particles) s.render(g);
    }
    public void SetAction(IParticleAction Action){
        this.action = Action;
    }
    public void Stop(){
        if(!isCreated) throw new RuntimeException("First need Create ParticleSystem");
        Enable = false;
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
        Emit(count,lifeTime);
        isCreated = true;
    }
    public void Emit(int count,float lt){
        List<Particle> temp = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            rand.setSeed(new Date().getTime());
            Particle p =
                    new Particle(i
                            ,position.plusX(rand.nextInt(-10,10))
                            ,false
                            ,false
                            ,Color.RED
                            ,rand.nextInt(5,20),
                            lt);
            temp.add(p);
        }
        Particles.addAll(temp);
    }

}
