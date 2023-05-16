package org.pixelgame.Engine.object;

public class Mob extends Sprite {
    protected float runSpeed = 10.0f;
    protected float max_speed = 70.0f;

    /**
     * @param id
     * @param posX
     * @param posY
     * Constructor
     */
    public Mob(int id, int posX, int posY) {
        super(id, posX, posY);
    }

    /**
     * @param deltaTime
     * set velocity.x
     * update object per frame
     */
    @Override
    public void update(float deltaTime) {
        velocity.x -= velocity.x * deltaTime * (_isGround?10:1);
        velocity.x  = Math.abs(velocity.x)>max_speed?max_speed*(velocity.x>0?1:-1):velocity.x;
        super.update(deltaTime);
    }
}
