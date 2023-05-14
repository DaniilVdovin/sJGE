package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Component;

public abstract class Physics implements Component {
    protected boolean _isGravity = false;
    protected boolean _isGround = false;
    protected boolean Collision = false;
    protected int mass = 0;
    protected Vector2 velocity = Vector2.Zero();
}
