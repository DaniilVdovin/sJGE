package org.pixelgame.sprites;

import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.object.Sprite;

import java.io.IOException;

public class Grass extends Sprite {
    public Grass(int id, int posX, int posY) {
        super(id, posX, posY);
        SetImage("/image/grass.jpg");
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
