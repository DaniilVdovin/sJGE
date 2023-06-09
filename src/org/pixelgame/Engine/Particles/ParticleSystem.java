package org.pixelgame.Engine.Particles;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.object.Text;

import java.awt.*;
import java.util.*;
import java.util.List;

// FIXME: 16.05.2023
public class ParticleSystem extends Sprite {
    private List<Particle> Particles = new ArrayList<>();
    private boolean Enable = false;
    private boolean isCreated = false;
    private IParticleAction action;
    private final float duration;
    private int emut_count;
    private float temp_duration;
    public  boolean isDebug = false;
    public ParticleSystem(int id, Vector2<Integer> pos,float duration) {
        super(id, pos);
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
                if(action!=null) p.position = action.Action(p.position,deltaTime);
                if(p.isAlive()){
                    if(action!=null) action.Destroy(true);
                    particleIterator.remove();
                    Particles.remove(p);
                    continue;
                }
                p.update(deltaTime);
            }
            temp_duration -= deltaTime;
            if(temp_duration<=0) {
                Emit(emut_count, duration);
                temp_duration = duration;
            }
        }
    }
    @Override
    public void render(Graphics g) {
        super.render(g);
        if(isDebug){
            Text stats = new Text(position.plusY(10).minusX(10).toInt());
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
        emut_count = count;
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
                            ,position.plusX(rand.nextInt(20)-10).toInt()
                            ,true
                            ,false
                            ,Color.RED
                            ,rand.nextInt(25)-5,
                            lt);
            temp.add(p);
        }
        Particles.addAll(temp);
    }

}
