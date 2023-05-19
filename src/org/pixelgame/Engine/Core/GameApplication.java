package org.pixelgame.Engine.Core;

import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.uitoolkit.CoreUI;
import org.pixelgame.Engine.uitoolkit.UIComponent;
import org.pixelgame.Engine.world.IUpdatable;
import org.pixelgame.Engine.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameApplication implements IUpdatable {
    public static boolean isPreview =false;

    private ArrayList<Sprite> temp_objects = new ArrayList<>();
    public void Init(){
        Renderer.init();
        World.curentWorld = new World();
        World.App = this;
    }
    public Sprite addObject(Sprite object){
        temp_objects.add(object);
        return object;
    }

    public void addObject(List<Sprite>  object){
        temp_objects.addAll(object);
    }
    public void addDone(){
        if(temp_objects.size()==0) return;
        World.curentWorld.sprites.addAll(temp_objects);
        temp_objects.clear();
    }
    public UIComponent addUIElement(UIComponent ui){
        CoreUI.uiComponents.add(ui);
        return ui;
    }
    public void addUIElements(List<UIComponent> ui){
        CoreUI.uiComponents.addAll(ui);
    }
    @Override
    public void update(float deltaTime) { }

    @Override
    public void fixedupdate(float deltaTime) {}

    @Override
    public void render(Graphics g) {}
    public static void quit(){
        if(isPreview) Renderer.frame.setVisible(false);
        else System.exit(0);
    }
}
