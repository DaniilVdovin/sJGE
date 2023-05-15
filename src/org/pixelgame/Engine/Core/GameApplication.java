package org.pixelgame.Engine.Core;

import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.object.Component;
import org.pixelgame.Engine.world.World;

import java.awt.*;

public abstract class GameApplication implements Component {
    public void Init(){
        Renderer.init();
        World.curentWorld = new World();
        World.App = this;
    }
    @Override
    public void update(float deltaTime) { }

    @Override
    public void fixedupdate(float deltaTime) {}

    @Override
    public void render(Graphics g) {}
    public static void quit(){
        System.exit(0);
    }
}
