package org.pixelgame.Engine.object;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;
import java.util.*;

/**
 * Sprite is parent class for custom objects
 * @see org.pixelgame.Engine.physics.Physics
 */
public class Sprite implements IComplementable, IUpdatable {
    public int id = 0;
    public Vector2<Float> position = new Vector2<>(0f,0f);
    public int Width = 0,Height = 0;
    private SpriteImage _image = null;
    private Color DefaultColor = Color.GREEN;
    public ArrayList<IUpdatable> components = new ArrayList<>();
    public ArrayList<Sprite> Child = new ArrayList<>();
    /**
     * Constructor
     * @param id
     * @param pos
     */
    public Sprite(int id,Vector2<Integer> pos){
        this.id = id;
        this.position = pos.toFloat();
    }
    public void update(float deltaTime){
        for (IUpdatable c:components) c.update(deltaTime);
        for (IUpdatable c:Child) c.update(deltaTime);
    }
    public void fixedupdate(float deltaTime) {
        for (IUpdatable c:components) c.fixedupdate(deltaTime);
        for (IUpdatable c:Child) c.fixedupdate(deltaTime);
    }
    public void render(Graphics g){
        int realX = position.x.intValue() - Width/2;
        int realY = position.y.intValue() - Height/2;
        if(_image == null){
            g.setColor(DefaultColor);
            g.fillRect(realX,realY, Width, Height);
        }else{
            g.drawImage(_image.Get(), realX, realY,Width,Height,null);
        }
        for (IUpdatable c:components) c.render(g);
        for (IUpdatable c:Child) c.render(g);
    }
    /**
     * set Width and Height of this object
     * @param width
     * @param height
     * @return changed object
     */
    public Sprite SetSize(int width, int height){
        Width = width; Height = height;
        return this;
    }
    /**
     * set Size
     * @param size
     * @return squre
     */
    public Sprite SetSize(int size){
       return SetSize(size,size);
    }
    /**
     * @param color Base color
     * @return changed object
     */
    public Sprite SetColor(Color color){
        DefaultColor = color;
        _image = null;
        return this;
    }
    /**
     * @param image SpriteImage
     * @return changed object
     */
    public Sprite SetImage(SpriteImage image){
        this._image = image;
        return this;
    }

    @Override
    public IComponent GetComponent(Class component) {
        for (IUpdatable c:components)if(c.getClass().getCanonicalName().equals(component.getCanonicalName())) return (IComponent) c;
        return null;
    }

    @Override
    public ArrayList GetComponents(Class component) {
        var result = new ArrayList<>();
        for (IUpdatable c:components) {
            if(c.getClass().getCanonicalName().equals(component.getCanonicalName()))
                result.add(component.cast(c));
        }
        return result;
    }

    @Override
    public IComponent AddComponent(IComponent component) {
        if(component instanceof IUpdatable) components.add((IUpdatable) component);
        return component;
    }
}
