package org.pixelgame.sprites;

import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.object.Mob;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Mob {
    public Player(int id, int posX, int posY) {
        super(id, posX, posY);
        Collision = true;
        _isGravity = true;
        mass = 50;
        SetSize(10,20);
        SetColor(Color.WHITE);
        //SetImage("/image/player.png");
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
            velocity.y = (float)-(Math.sqrt(100*mass));
        }
        if(Input.getMouse()){
            Collision = !Collision;
        }
        super.update(deltaTime);
    }
}