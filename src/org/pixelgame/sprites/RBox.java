package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.object.Text;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Gravity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RBox extends Sprite {
    public RBox(int id, Vector2 pos) {
        super(id, pos);
        SetColor(Color.ORANGE);
        SetSize(10);
        _isBounds = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void fixedupdate(float deltaTime) {
        var temp = new Sprite(0,position.clone());
        temp.SetSize(10);
        temp.SetColor(Color.GREEN);
        temp.mass = 20;
        temp.components.add(new Collision(temp,true));
        temp.components.add(new Gravity(temp));
        Child.add(temp);
        super.fixedupdate(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Text debug = new Text(position.plusY(20).minusX(10).toInt());
        debug.Text.append(Child.size());
        debug.render(g);
    }
}
