package org.pixelgame.Engine.uitoolkit;
import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.EventSystem.ClickEvent;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class UIComponent extends ClickEvent implements IComponent, IUpdatable, MouseListener {
    public int Layer;
    public int Width,Height;
    public Vector2<Integer> position;
    public Rectangle rect;
    public List<UIComponent> Child = new ArrayList<>();

    public UIComponent(int layer, int width, int height, Vector2<Integer> position) {
        Layer = layer;
        Width = width;
        Height = height;
        this.position = position;
        rect = new Rectangle(position.x,position.y,width,height);
        Renderer.canvas.addMouseListener(this);
    }
    @Override
    public void update(float deltaTime){
        for (UIComponent uc:Child)uc.update(deltaTime);
        rect.x  = position.x;
        rect.y = position.y;
        rect.width = Width;
        rect.height = Height;
    }
    @Override
    public void fixedupdate(float deltaTime) {for (UIComponent uc:Child)uc.fixedupdate(deltaTime);}
    @Override
    public void render(Graphics g) { for (UIComponent uc:Child)uc.render(g);}
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    public void addChild(UIComponent ui){
        Child.add(ui);
    }
    public void addChild(List<UIComponent> ui){
        Child.addAll(ui);
    }
    @Override
    public Sprite GetParent() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(rect.contains(e.getPoint().getLocation())) {
            OnClick();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
