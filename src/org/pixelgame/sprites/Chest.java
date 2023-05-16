package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collision;

import java.awt.*;

public class Chest extends Sprite {
    SpriteImage OpenChest = new SpriteImage("/image/Chest_open.png");
    SpriteImage CloseChest = new SpriteImage("/image/chest.png");
    public Chest(int id, Vector2<Integer> pos) {
        super(id, pos);
        SetImage(CloseChest);
        SetSize(25);
    }
    @Override
    public void update(float deltaTime) {super.update(deltaTime);}

    @Override
    public void fixedupdate(float deltaTime) {}

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
