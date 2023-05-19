package org.pixelgame.Engine.AnimationCore;

import org.pixelgame.Engine.EventSystem.AnimationEvent;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;

public class Animation extends AnimationEvent implements IUpdatable {
    public float Duration;
    public float Speed;
    public float Delay;
    public boolean isRepeatable;
    public boolean isStopped;
    public boolean isComplete;
    public AnimationState State;
    public SpriteImage Source;
    protected void Play(){

    }
    protected void Pause(){

    }
    protected void Stop(){

    }
    protected void setSpriteImage(SpriteImage source){

    }
    @Override
    public void update(float deltaTime) {

    }
    @Override
    public void fixedupdate(float deltaTime) {

    }
    @Override
    public void render(Graphics g) {}
}
