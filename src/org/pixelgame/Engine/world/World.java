package org.pixelgame.Engine.world;

import org.pixelgame.Engine.Core.GameApplication;
import org.pixelgame.Engine.Core.Logger;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.uitoolkit.CoreUI;

import java.awt.*;
import java.util.ArrayList;


public class World {
    public static World curentWorld = null;
    public static GameApplication App;
    private static long lastTime = 0;

    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    public static CoreUI UI = new CoreUI();
    public static void update(){
        float deltaTime = (System.nanoTime() - lastTime)/1000000000.0f;
        lastTime = System.nanoTime();
        if(curentWorld!=null) {
            for (Sprite sprite : curentWorld.sprites) {
                sprite.update(deltaTime);
            }
            UI.update(deltaTime);
            App.update(deltaTime);
        }
    }
    public static void render(Graphics g){
        try {
            for(Sprite sprite : curentWorld.sprites){
                sprite.render(g);
            }
            App.render(g);
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }
    public static void renderUI(Graphics g){
        UI.render(g);
    }
    public static void fixedupdate(){
        if(curentWorld!=null) {
            for (Sprite sprite : curentWorld.sprites) {
                sprite.fixedupdate(1f);
            }
            UI.fixedupdate(1f);
            App.fixedupdate(1f);
        }
    }
}
