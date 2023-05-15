package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Component;

public abstract class Physics implements Component {
    public boolean _isBounds = true;
    public boolean _isStatic = false;
    protected boolean _isGravity = false;
    public boolean _isGround = false;
    protected boolean Collision = false;
    public int mass = 0;
    public Vector2 velocity = Vector2.Zero();
}
