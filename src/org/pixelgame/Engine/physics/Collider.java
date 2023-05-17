package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;

enum PlusType{
    X,Y
}
// TODO: 17.05.2023  
public class Collider implements IComponent, IUpdatable {
    public boolean isTrigger;
    private final Sprite _parent;
    private Rectangle _rect;
    private boolean _isDebug;
    public Collider(Sprite parent){
        this(parent,new Rectangle(
                parent.position.x.intValue() - parent.Width/2,
                parent.position.y.intValue() - parent.Height/2,
                parent.Width,parent.Height),false);
    }
    public Collider(Sprite parent,boolean isDebug){
        this(parent,new Rectangle(
                parent.position.x.intValue() - parent.Width/2,
                parent.position.y.intValue() - parent.Height/2,
                parent.Width,parent.Height),isDebug);
    }
    public Collider(Sprite parent,Rectangle rect,boolean isDebug)
    {
        _isDebug = isDebug;
        _rect = rect;
        _parent = parent;
        isTrigger = false;
    }
    public Rectangle Get(){
        return _rect;
    }
    public Rectangle GetPlus(float Delta,PlusType p){
         switch (p) {
             case X:
             return new Rectangle((int) (_rect.x + Delta), (int) (_rect.y - Math.abs(_rect.height * .1f)), (_rect.width), (int) (_rect.height * .9f));
             case Y:
             return new Rectangle((int) (_rect.x + Math.abs(_rect.width * .3f)), (int) (_rect.y + Delta + _rect.height * .8f), (int) (_rect.width * .5f), (int) (_rect.height * .2f));
         }
         return _rect;
    }
    public void setDebugMode(boolean value){
        _isDebug = value;
    }
    public Sprite GetParent(){
        return _parent;
    }
    @Override
    public void update(float deltaTime) {
        _rect.x = _parent.position.x.intValue()-_parent.Width/2;
        _rect.y = _parent.position.y.intValue()-_parent.Height/2;
    }
    @Override
    public void fixedupdate(float deltaTime) {}
    @Override
    public void render(Graphics g) {
        if(_isDebug)
        {
            g.setColor(Color.GREEN);
            g.drawRect(_rect.x,_rect.y, _rect.width, _rect.height);
        }
    }
}
