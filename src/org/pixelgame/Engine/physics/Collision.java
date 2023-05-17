package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.EventSystem.CollisionEvent;
import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.object.Text;
import org.pixelgame.Engine.world.IUpdatable;
import org.pixelgame.Engine.world.World;

import java.awt.*;
import java.util.ArrayList;

public class Collision extends CollisionEvent implements IComponent, IUpdatable {
    private final Sprite _parent;
    private Physics _physics;
    public boolean isEnable;
    public boolean isDebug;
    private PhysicsState _state;
    private final ArrayList<PhysicsState> collisions = new ArrayList<>();
    public Collision(Sprite parent) {
        this(true, parent,false);
    }
    public Collision(Sprite parent,boolean isDebug) {
        this(true, parent,isDebug);
    }
    public Collision(boolean isEnable, Sprite parent,boolean isDebug) {
        this.isEnable = isEnable;
        this.isDebug = isDebug;
        _parent = parent;
        _physics = (Physics) _parent.GetComponent(Physics.class);
        if (this._physics == null){
            _physics = new Physics(_parent);
            _parent.AddComponent(_physics);
        }
        _state = new PhysicsState(this._physics,PState.Non);
    }
    // FIXME: 17.05.2023 
    @Override
    public void update(float deltaTime) {
        if (isEnable) {

            _physics._isGround = false;
            for (Sprite sprite : World.curentWorld.sprites) {
                if (sprite == _parent) continue;
                Collision spriteCollision = ((Collision)sprite.GetComponent(Collision.class));
                Physics spritePhysics = ((Physics)sprite.GetComponent(Physics.class));
                if(spritePhysics == null) continue;
                if (_physics.GetCollider().GetPlus(_physics.velocity.y*deltaTime,PlusType.Y)
                        .intersects(spritePhysics.GetCollider().Get())) {
                    _physics._isGround = true;
                    if (!spritePhysics._isStatic & spritePhysics.mass <= _physics.mass)
                        spritePhysics.velocity.y += (_physics.velocity.y)*deltaTime*(sprite.position.y< _parent.position.y?1:-1);
                    _physics.velocity.y -= _physics.velocity.y;
                }
                if (_physics.GetCollider().GetPlus(_physics.velocity.x*deltaTime,PlusType.X)
                        .intersects(spritePhysics.GetCollider().Get())) {
                    collisionenter(spriteCollision);
                    if (!spritePhysics._isStatic & spritePhysics.mass <= _physics.mass)
                        spritePhysics.velocity.x += (_physics.velocity.x)*deltaTime*(sprite.position.x< _parent.position.x?1:-1);
                    _physics.velocity.x -= _physics.velocity.x;
                }else collisionexit(spriteCollision);
            }
        }
    }
    private void collisionenter(Collision sp){
        if(sp == null) return;
        if(!collisions.contains(sp._state)){
            collisions.add(sp._state);
            CollisionEnter(sp._physics);
        }
    }
    private void collisionexit(Collision sp){
        if(sp == null) return;
        if(collisions.contains(sp._state)){
            collisions.remove(sp._state);
            CollisionExit(sp._physics);
        }
    }

    @Override
    public void fixedupdate(float deltaTime) {

    }
    @Override
    public void render(Graphics g) {
        if(isDebug) {
            Rectangle rS =  _physics.GetCollider().GetPlus(_physics.velocity.x*0.2f,PlusType.X);
            Rectangle rU =  _physics.GetCollider().GetPlus(_physics.velocity.y*0.2f,PlusType.Y);
            g.setColor(Color.ORANGE);
            g.drawRect(rU.x,rU.y,rU.width,rU.height);
            g.setColor(Color.BLUE);
            g.drawRect(rS.x,rS.y,rS.width,rS.height);

            Text debug = new Text(_parent.position.toInt());
            debug.Text.append("C:").append(collisions.size());
            debug.render(g);
        }
    }

    @Override
    public Sprite GetParent() {
        return _parent;
    }
}
