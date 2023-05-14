package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.object.Mob;
import org.pixelgame.Engine.object.Text;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.pixelgame.Engine.graphics.Renderer.MainCamera;

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
        MainCamera.position = Vector2.Lerp(MainCamera.position,position,deltaTime);
        if(Input.getKey(KeyEvent.VK_A)){
            moveX-=runSpeed;
        }
        if(Input.getKey(KeyEvent.VK_D)){
            moveX+=runSpeed;
        }
        if(Input.getKey(KeyEvent.VK_SPACE)){
            velocity.y = (float)-(Math.sqrt(50*mass));
        }
        if(Input.getKey(KeyEvent.VK_C)){
            Collision = !Collision;
        }

        //DIE
        if(position.y>1000)
        {
            position = new Vector2(100,100);
        }
        super.update(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Text debug = new Text(position.toInt().minusY(50).minusX(20));
        debug.Text.append("(").append(velocity.x).append(",").append(velocity.y).append(")");
        debug.render(g);
    }
}