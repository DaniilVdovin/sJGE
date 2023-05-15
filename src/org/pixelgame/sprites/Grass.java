package org.pixelgame.sprites;

import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.input.MouseOver;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Gravity;

import java.awt.*;
import java.io.IOException;

public class Grass extends Sprite {
    MouseOver mouseOver = new MouseOver();
    public Grass(int id, int posX, int posY) {
        super(id, posX, posY);
        SetImage("/image/grass.jpg");
        mass = 100;
        components.add(mouseOver);
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
    @Override
    public void render(Graphics g){
        super.render(g);
        if(mouseOver.isHover){
            g.setColor(Color.WHITE);
            g.drawRect((int) position.x, (int) position.y,Width,Height);
        }
    }
}
