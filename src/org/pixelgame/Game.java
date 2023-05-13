package org.pixelgame;

import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.object.ParticleSystem;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.World;
import org.pixelgame.sprites.Grass;
import org.pixelgame.sprites.Player;

import java.util.ArrayList;

public class Game {

    public static void main(String[] args) {
        Renderer.init();
        World.curentWorld = new World();

        ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        for (int i=0;i<25;i++)
            for (int j=0;j<1;j++)
                sprites.add(new Grass(i+j, 100+i*64,150+j*64));
        sprites.add(new Player(-1,200,40));

        ParticleSystem flame = new ParticleSystem(20,500,500);
        flame.Create(1,20);
        sprites.add(flame);

        World.curentWorld.sprites.addAll(sprites);
    }
    public static void quit(){
        System.exit(0);
    }

}
