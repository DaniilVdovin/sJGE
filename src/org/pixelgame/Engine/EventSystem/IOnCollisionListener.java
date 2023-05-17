package org.pixelgame.Engine.EventSystem;

import org.pixelgame.Engine.physics.Collider;
import org.pixelgame.Engine.physics.Physics;

public interface IOnCollisionListener {
    void CollisionEnter(Physics sender);
    void CollisionExit(Physics sender);
}
