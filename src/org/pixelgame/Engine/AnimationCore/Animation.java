package org.pixelgame.Engine.AnimationCore;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.EventSystem.AnimationEvent;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.object.IComplementable;
import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.pixelgame.Engine.graphics.Renderer.canvas;

public class Animation extends AnimationEvent implements IComponent, IUpdatable {

    private Sprite _parent;

    public float Speed;
    public float Delay;
    public boolean isRepeatable;
    public boolean isStopped = true;
    public boolean isComplete;
    public AnimationState State;

    public SpriteImage AssetSource;
    public Vector2<Integer> FrameSize = new Vector2<>(90,70);
    public Vector2<Integer> Offset = new Vector2<>(39,290);
    public ArrayList<AnimationFrame> Frames = new ArrayList<>();
    public AnimationFrame CurrentFrame;
    public int CurrentFrameIndex;
    public int FrameCount=5;

    public Animation(Sprite parent) {
        _parent = parent;
    }
    public Animation setAssetSource(SpriteImage source){
        AssetSource = source;
        return this;
    }
    public Animation setParameters(float delay,float speed,boolean isRepeatable){
        Delay =delay;
        Speed = speed;
        this.isRepeatable = isRepeatable;
        return this;
    }
    public Animation preload(){
        for (int i = 0; i < FrameCount; i++) {
            BufferedImage finalImage = canvas.getGraphicsConfiguration()
                    .createCompatibleImage(AssetSource.Get().getWidth(),AssetSource.Get().getHeight(),AssetSource.Get().getTransparency());
            finalImage.getGraphics().drawImage(AssetSource.Get(),0,0,AssetSource.Get().getWidth(),AssetSource.Get().getHeight(),null);
            Frames.add(new AnimationFrame(i,finalImage.getSubimage(Offset.x+(i*FrameSize.x),Offset.y,FrameSize.x,FrameSize.y)
                    ,new Vector2<>(Offset.x+i*FrameSize.x,Offset.y),FrameSize));
        }
        return this;
    }
    public void Play(){
        isStopped = false;
        State = AnimationState.Play;
        OnStateChange(State);
    }
    protected void Pause(){
        isStopped = true;
        State = AnimationState.Pause;
        OnStateChange(State);
    }
    protected void Stop(){
        isStopped = false;
        CurrentFrameIndex = 0;
        State = AnimationState.Stopped;
        OnStateChange(State);
    }

    @Override
    public void update(float deltaTime) {

    }
    @Override
    public void fixedupdate(float deltaTime) {
        if(!isStopped) {
            if (CurrentFrameIndex < FrameCount) {
                CurrentFrame = Frames.get(CurrentFrameIndex);
                _parent._image.Change(CurrentFrame.ImageFrame);
                CurrentFrameIndex++;
            } else if (FrameCount == CurrentFrameIndex) {
                OnComplete();
                if (isRepeatable)
                {
                    CurrentFrameIndex = 0;
                }else
                {
                    State = AnimationState.Complete;
                    OnStateChange(State);
                    OnComplete();
                    CurrentFrameIndex = 0;
                    isStopped = true;
                }
            }
        }
    }
    @Override
    public void render(Graphics g) {}

    @Override
    public Sprite GetParent() {
        return _parent;
    }
}
