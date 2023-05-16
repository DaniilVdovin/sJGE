package org.pixelgame.sprites;

import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Gravity;

import java.awt.*;

public class Chest extends Sprite {
    Collision curColis = new Collision(this);
    String OpenChest = "/image/Chest_open.png";
    String curentSkin = "/image/chest.png";
    String CloseChest = "/image/chest.png";
    public Chest(int id, int posX, int posY) {
        super(id, posX, posY);
        components.add(new Gravity(this));
        components.add(curColis);
        SetImage(curentSkin);
        SetSize(25);
    }
    public void ChangeSkin(String Skin){

        SetImage(Skin);
        SetSize(25);
    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(curColis.isInterset == true && curentSkin == CloseChest)
        {
            ChangeSkin(OpenChest);

        } else if ( curentSkin == OpenChest ) {;
            ChangeSkin(CloseChest);
        }

    }

    @Override
    public void fixedupdate(float deltaTime) {
        super.fixedupdate(deltaTime);

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    public Sprite SetSize(int width, int height) {
        return super.SetSize(width, height);
    }

    @Override
    public Sprite SetImage(String path) {
        return super.SetImage(path);
    }
}
