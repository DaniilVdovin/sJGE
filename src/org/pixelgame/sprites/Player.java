package org.pixelgame.sprites;

import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.object.Mob;

import java.awt.event.KeyEvent;

public class Player extends Mob {
    public Player(int id, float posX, float posY) {
        super(id, posX, posY);
        Collision = true;
        _isGravity = true;
        mass = 50;
        SetImage("/image/player.png");
    }
    @Override
    public void update(float deltaTime) {
        if(Input.getKey(KeyEvent.VK_A)){
            moveX-=runSpeed;
        }
        if(Input.getKey(KeyEvent.VK_D)){
            moveX+=runSpeed;
        }
        if(Input.getKey(KeyEvent.VK_SPACE) & _isGround){
            velocity.y = (float)-(Math.sqrt(50*mass));
        }
        if(Input.getMouse()){
            Collision = !Collision;
        }
        super.update(deltaTime);
    }
}