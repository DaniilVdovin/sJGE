package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.object.Text;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Physics;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.pixelgame.Engine.graphics.Renderer.MainCamera;

/**
 * Class extends from Mob
 */
public class Player extends Sprite {
    /**
     * @param id
     * @param posX
     * @param posY
     * @exception null
     * Create a Player object
     */
    private Physics physics;
    public Player(int id, Vector2<Integer> pos) {
        super(id, pos);
        SetSize(10,20);
        SetColor(Color.WHITE);
        physics = (Physics) AddComponent(new Physics(this));
        AddComponent(new Collision(this));
        physics.mass = 100;
    }
    /**
     * @param deltaTime
     * update objcet per frame
     * set control keys
     */
    @Override
    public void update(float deltaTime) {
        MainCamera.position = Vector2.Lerp(MainCamera.position.toFloat(),position.toFloat(),deltaTime);
        //Walk Left
        if(Input.getKey(KeyEvent.VK_A)){
            physics.velocity.x-= physics.speed;
        }
        //Walk Right
        if(Input.getKey(KeyEvent.VK_D)){
            physics.velocity.x+= physics.speed;
        }
        //Jump
        if(physics._isGround & Input.getKey(KeyEvent.VK_SPACE)){
            physics.velocity.y = (float)-(Math.sqrt(50*physics.mass));
        }
        //Fly
        if(Input.getKey(KeyEvent.VK_X)){
            physics.velocity.y = (float)-(Math.sqrt(50*physics.mass));
        }
        //DIE in Darkness
        if(position.y>1000)
        {
            position = new Vector2<>(100f,100f);
        }
        super.update(deltaTime);
    }
    /**
     * @param g
     * render g
     * set debug text
     */
    @Override
    public void render(Graphics g) {
        super.render(g);
        Text debug = new Text(position.toInt().minusY(50).minusX(20));
        debug.Text.append("(").append(position.x).append(",").append(position.y).append(")");
        debug.render(g);
    }
}