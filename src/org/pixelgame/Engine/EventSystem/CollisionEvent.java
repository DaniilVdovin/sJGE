package org.pixelgame.Engine.EventSystem;

// TODO: 17.05.2023  
public class CollisionEvent extends Event<IOnCollisionListener> implements IOnCollisionListener {
    @Override
    public final void CollisionEnter(){
        for (IOnCollisionListener c:_listeners) c.CollisionEnter();
    }
    @Override
    public final void CollisionExit() {
        for (IOnCollisionListener c:_listeners) c.CollisionExit();
    }

}
