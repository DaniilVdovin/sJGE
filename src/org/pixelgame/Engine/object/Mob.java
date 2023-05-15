package org.pixelgame.Engine.object;

public class Mob extends Sprite {
    protected float runSpeed = 10.0f;
    protected float max_speed = 70.0f;
    public Mob(int id, int posX, int posY) {
        super(id, posX, posY);
    }
    @Override
    public void update(float deltaTime) {
        velocity.x -= velocity.x * deltaTime * (_isGround?10:1);
        velocity.x  = Math.abs(velocity.x)>max_speed?max_speed*(velocity.x>0?1:-1):velocity.x;
        super.update(deltaTime);
    }
}
