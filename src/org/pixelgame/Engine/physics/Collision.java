package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.EventSystem.CollisionEvent;
import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.object.Text;
import org.pixelgame.Engine.world.IUpdatable;
import org.pixelgame.Engine.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Collision extends CollisionEvent implements IComponent, IUpdatable {
    private final Sprite _parent;
    private Physics _physics;
    public boolean isEnable;
    public boolean isDebug;
    private ArrayList<PhysicsState> _states;
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
        _states = new ArrayList<>();
        if (this._physics == null){
            _physics = new Physics(_parent,true);
            for (Collider c:_physics.GetCollider()) {
                _states.add(new PhysicsState(c,this._physics,PState.Non));
            }
            _parent.AddComponent(_physics);
        }else {
            for (Collider c:_physics.GetCollider()) {
                _states.add(new PhysicsState(c,this._physics,PState.Non));
            }
        }
    }
    // FIXME: 17.05.2023 
    @Override
    public void update(float deltaTime) {
        if (isEnable) {
            _physics._isGround = false;
            for (Sprite sprite : World.curentWorld.sprites) {
                if (sprite == _parent) continue;
                Collision sc = ((Collision) sprite.GetComponent(Collision.class));
                Physics sp = ((Physics) sprite.GetComponent(Physics.class));
                if (sp == null) continue;
                for (Collider collider : _physics.GetCollider()) {
                    for (Collider othercollider : sp.GetCollider()) {
                        if (collider.isTrigger) {
                            if (collider.GetPlus(_physics.velocity.x * deltaTime, PlusType.X)
                                    .intersects(othercollider.Get()))
                                 collisionenter(othercollider,sc);
                            else collisionexit(othercollider,sc);
                        } else {
                            if (!othercollider.isTrigger) {
                                if (collider.GetPlus(_physics.velocity.y * deltaTime, PlusType.Y)
                                        .intersects(othercollider.Get())) {
                                    _physics._isGround = true;
                                    if (!sp._isStatic & sp.mass <= _physics.mass)
                                        sp.velocity.y += (_physics.velocity.y) * deltaTime * (sprite.position.y < _parent.position.y ? 1 : -1);
                                    _physics.velocity.y -= _physics.velocity.y;
                                }
                                if (collider.GetPlus(_physics.velocity.x * deltaTime, PlusType.X)
                                        .intersects(othercollider.Get())) {
                                    collisionenter(othercollider, sc);
                                    if (!sp._isStatic & sp.mass <= _physics.mass)
                                        sp.velocity.x += (_physics.velocity.x) * deltaTime * (sprite.position.x < _parent.position.x ? 1 : -1);
                                    _physics.velocity.x -= _physics.velocity.x;
                                } else collisionexit(othercollider, sc);
                            }
                        }
                    }
                }
            }
        }
        if (!_physics._isGround)
            _physics.velocity.y += _physics.mass * deltaTime;
        _physics.velocity.x -= _physics.velocity.x * deltaTime * (_physics._isGround ? 10 : 1);
        _physics.velocity.x = Math.abs(_physics.velocity.x) > _physics.max_speed ?
                _physics.max_speed * (_physics.velocity.x > 0 ? 1 : -1) : _physics.velocity.x;
        _parent.position.y += _physics.velocity.y * deltaTime;
        _parent.position.x += _physics.velocity.x * deltaTime;
    }

    // FIXME: 17.05.2023 
    private void collisionenter(Collider c,Collision sp){
        if(sp == null) return;
        for (PhysicsState s:sp._states) {
            if(s.collider == c & !collisions.contains(s)){
                collisions.add(s);
                CollisionEnter(sp._physics);
            }
        }
    }

    // FIXME: 17.05.2023
    private void collisionexit(Collider c,Collision sp){
        if(sp == null) return;
        for (PhysicsState s:sp._states) {
            if(s.collider == c & collisions.contains(s)){
                collisions.remove(s);
                CollisionExit(sp._physics);
            }
        }
    }

    @Override
    public void fixedupdate(float deltaTime) {

    }
    @Override
    public void render(Graphics g) {
        if(isDebug) {
            for (Collider collider : _physics.GetCollider()) {
                if(collider.isTrigger){
                    Rectangle t = collider.Get();
                    g.setColor(Color.white);
                    g.drawRect(t.x, t.y, t.width, t.height);
                }else {
                    Rectangle rS = collider.GetPlus(_physics.velocity.x * 0.2f, PlusType.X);
                    Rectangle rU = collider.GetPlus(_physics.velocity.y * 0.2f, PlusType.Y);
                    g.setColor(Color.ORANGE);
                    g.drawRect(rU.x, rU.y, rU.width, rU.height);
                    g.setColor(Color.BLUE);
                    g.drawRect(rS.x, rS.y, rS.width, rS.height);
                }
            }
            Text debug = new Text(_parent.position.toInt().minusY(30).minusX(40));
            debug.Text.append("S:").append(_states.size()).append(" ").append("C:").append(collisions.size());
            debug.render(g);
        }
    }

    @Override
    public Sprite GetParent() {
        return _parent;
    }
}
