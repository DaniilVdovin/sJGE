package org.pixelgame;

import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.Particles.ParticleSystem;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.World;
import org.pixelgame.sprites.Fire;
import org.pixelgame.sprites.Grass;
import org.pixelgame.sprites.Player;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) {
        Renderer.init();
        World.curentWorld = new World();

        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        for (int i=0;i<25;i++)
            for (int j=0;j<3;j++)
                sprites.add(new Grass(i+j, 100+i*64,150+j*64));
        for (int i = 0; i < 25; i++) {
            sprites.add(new Grass(i, 100+i*64,400+(int)(Math.sin(i)*64)));
        }

        sprites.add(new Player(-1,200,40));

        Fire fire = new Fire(20,500,500);
        fire.Create(10,20);
        sprites.add(fire);

        World.curentWorld.sprites.addAll(sprites);
    }
    public static void quit(){
        System.exit(0);
    }

}
