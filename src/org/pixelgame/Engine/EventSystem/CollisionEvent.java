package org.pixelgame.Engine.EventSystem;

import org.pixelgame.Engine.physics.Physics;

// TODO: 17.05.2023
public class CollisionEvent extends Event<IOnCollisionListener> implements IOnCollisionListener{
    @Override
    public final void CollisionEnter(Physics sender){for (IOnCollisionListener c:_listeners) c.CollisionEnter(sender);}
    @Override
    public final void CollisionExit(Physics sender) {
        for (IOnCollisionListener c:_listeners) c.CollisionExit(sender);
    }

}
