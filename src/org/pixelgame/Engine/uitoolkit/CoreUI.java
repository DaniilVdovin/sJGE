package org.pixelgame.Engine.uitoolkit;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CoreUI implements IComponent, IUpdatable {
    public static List<UIComponent> uiComponents = new ArrayList<>();
    @Override
    public void update(float deltatime){
        for (UIComponent uc:uiComponents) uc.update(deltatime);
    }
    @Override
    public void fixedupdate(float deltatime) {for (UIComponent uc:uiComponents) uc.fixedupdate(deltatime);}
    @Override
    public void render(Graphics g){
        for (UIComponent uc:uiComponents) uc.render(g);
        Vector2<Integer> mouse = Input.getMousePosition();
        g.setColor(Color.YELLOW);
        g.drawOval(mouse.x-15, mouse.y-15,30,30);
        g.setColor(Color.GREEN);
        g.drawString(mouse.toString(), mouse.x-30, mouse.y+35);
    }

    @Override
    public Sprite GetParent() {
        return null;
    }
}
