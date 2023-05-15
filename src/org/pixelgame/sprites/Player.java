package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.object.Mob;
import org.pixelgame.Engine.object.Text;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Gravity;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.pixelgame.Engine.graphics.Renderer.MainCamera;

public class Player extends Mob {
    public Player(int id, int posX, int posY) {
        super(id, posX, posY);
        mass = 100;
        SetSize(10,20);
        SetColor(Color.WHITE);
        //SetImage("/image/player.png");

        components.add(new Gravity(this));
        components.add(new Collision(this,true));
    }
    @Override
    public void update(float deltaTime) {
        MainCamera.position = Vector2.Lerp(MainCamera.position,position,deltaTime);
        //Walk Left
        if(Input.getKey(KeyEvent.VK_A)){
            velocity.x-=runSpeed;
        }
        //Walk Right
        if(Input.getKey(KeyEvent.VK_D)){
            velocity.x+=runSpeed;
        }
        //Jump
        if(_isGround & Input.getKey(KeyEvent.VK_SPACE)){
            velocity.y = (float)-(Math.sqrt(50*mass));
        }
        //Fly
        if(Input.getKey(KeyEvent.VK_X)){
            velocity.y = (float)-(Math.sqrt(50*mass));
        }
        //Mine
        if(Input.getKey(KeyEvent.VK_E)){

        }
        //DIE in Darkness
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
        debug.Text.append("(").append(position.x).append(",").append(position.y).append(")");
        debug.render(g);
    }
}