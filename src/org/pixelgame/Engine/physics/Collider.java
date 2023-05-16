package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;

// TODO: 17.05.2023  
public class Collider implements IComponent, IUpdatable {
    private final Sprite _parent;
    private Rectangle _rect;
    private boolean _isDebug;
    public Collider(Sprite parent){
        this(parent,new Rectangle(
                parent.position.x.intValue() - parent.Width/2,
                parent.position.y.intValue() - parent.Height/2,
                parent.Width,parent.Height),false);
    }
    public Collider(Sprite parent,Rectangle rect,boolean isDebug)
    {
        _isDebug = isDebug;
        _rect = rect;
        _parent = parent;
    }
    public Sprite GetParent(){
        return _parent;
    }
    @Override
    public void update(float deltaTime) {}
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
