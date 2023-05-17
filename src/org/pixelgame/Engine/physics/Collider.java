package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;

enum PlusType{
    X,Y
}
// TODO: 17.05.2023  
public class Collider implements IComponent, IUpdatable {
    public boolean isTrigger = false;
    private final Sprite _parent;
    private Rectangle _rect;
    private boolean _isDebug;
    private Vector2<Integer> _offset = new Vector2<>(0,0);
    public Collider(Sprite parent){
        this(parent,new Rectangle(
                parent.position.x.intValue() - parent.Width/2,
                parent.position.y.intValue() - parent.Height/2,
                parent.Width,parent.Height),false,false);
    }
    public Collider(Sprite parent,boolean isDebug){
        this(parent,new Rectangle(
                parent.position.x.intValue() - parent.Width/2,
                parent.position.y.intValue() - parent.Height/2,
                parent.Width,parent.Height),false,isDebug);
    }
    public Collider(Sprite parent,Rectangle rect,boolean isTrigger,boolean isDebug)
    {
        _isDebug = isDebug;
        _rect = rect;
        _parent = parent;
        this.isTrigger = isTrigger;
    }
    public Collider setOffset(Vector2<Integer> value){
        _offset = value;
        return this;
    }
    public Rectangle Get(){
        return _rect;
    }
    public Rectangle GetPlus(float Delta,PlusType p){
         switch (p) {
             case X:
             return new Rectangle((int) (_rect.x + Delta - Math.abs(_rect.width * .1f)),
                     (int) (_rect.y - Math.abs(_rect.height * .1f)), (int) (_rect.width + Math.abs(_rect.width * .2f)), (int) (_rect.height * .9f));
             case Y:
             return new Rectangle((int) (_rect.x + Math.abs(_rect.width * .3f)),
                     (int) (_rect.y + Delta + _rect.height * .8f), (int) (_rect.width * .5f), (int) (_rect.height * .2f));
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
        _rect.x = (_parent.position.x.intValue()-_parent.Width/2)  - _offset.x;
        _rect.y = (_parent.position.y.intValue()-_parent.Height/2) - _offset.y;
    }
    @Override
    public void fixedupdate(float deltaTime) {}
    @Override
    public void render(Graphics g) {
        if(_isDebug)
        {
            g.setColor(Color.RED);
            g.drawRect(_rect.x,_rect.y, _rect.width, _rect.height);
        }
    }
}
