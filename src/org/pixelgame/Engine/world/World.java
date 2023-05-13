package org.pixelgame.Engine.world;

import org.pixelgame.Engine.object.Sprite;

import java.awt.*;
import java.util.ArrayList;


public class World {
    public static World curentWorld = null;

    private static long lastTime = 0;

    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    public static void update(){
        float deltaTime = (System.nanoTime() - lastTime)/1000000000.0f;
        lastTime = System.nanoTime();
        if(curentWorld!=null) {
            for (Sprite sprite : curentWorld.sprites) {
                sprite.update(deltaTime);
            }
        }
    }
    public static void render(Graphics g){
        try {
            for(Sprite sprite : curentWorld.sprites){
                sprite.render(g);
            }
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }

    }
}
