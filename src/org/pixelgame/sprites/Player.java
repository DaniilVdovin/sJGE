package org.pixelgame.sprites;

import org.pixelgame.Engine.AnimationCore.Animation;
import org.pixelgame.Engine.AnimationCore.AnimationState;
import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.EventSystem.IOnAnimationListener;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Physics;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.pixelgame.Engine.graphics.Renderer.MainCamera;


/**
 * Class extends from Mob
 */
public class Player extends Sprite {
    private final Physics physics;
    Animation animation;
    public Player(int id, Vector2<Integer> pos) {
        super(id, pos);
        SetImage(new SpriteImage("/image/char_atlas.png"));
        SetSize(25,40);
        physics   =     (Physics) AddComponent(new Physics(this,true));
        AddComponent(new Collision(this));
        physics.mass = 50;
        animation = (Animation) AddComponent(
                new Animation(this)
                        .setAssetSource(new SpriteImage("/image/char_atlas.png"))
                        .setParameters(0,1,true)
                        .preload()
        );
        animation.addListener(new IOnAnimationListener() {
            @Override
            public void OnStateChange(AnimationState state) {
                System.out.println("State: "+state.name());
            }

            @Override
            public void OnComplete() {
                System.out.println("Animation Complete");
            }
        });
        animation.Play();
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
            animation.setOffset(new Vector2<>(20,195));
            physics.velocity.x-= physics.speed;
        }
        //Walk Right
        if(Input.getKey(KeyEvent.VK_D)){
            animation.setOffset(new Vector2<>(20,295));
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
    }
}