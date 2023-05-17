package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;
import java.util.ArrayList;

public class Physics implements IComponent, IUpdatable {
    public boolean _isStatic = false;
    public boolean _isGround = false;
    public int mass;
    public Vector2<Float> velocity = new Vector2<>(0f,0f);
    public float speed = 10.0f;
    public float max_speed = 70.0f;
    private final Sprite _parent;
    private ArrayList<Collider> _colliders = new ArrayList<>();
    private boolean _hasCollision;
    public Physics(Sprite parent){
        this(parent,false);
    }
    public Physics(Sprite parent,boolean hasCollision) {
        _parent = parent;
        _colliders = _parent.GetComponents(Collider.class);
        if (_colliders.size() == 0){
            _colliders.add(new Collider(parent));
            _parent.AddComponent(this._colliders.get(0));
        }
        _hasCollision = hasCollision;
        if(_hasCollision==false)
            _hasCollision = _parent.GetComponent(Collision.class) != null;
    }
    public ArrayList<Collider> GetCollider(){
        return _colliders;
    }
    @Override
    public Sprite GetParent() {
        return _parent;
    }

    // FIXME: 17.05.2023 
    @Override
    public void update(float deltaTime) {
        if(isEnable & !_isStatic) {
            if (!_hasCollision) {
                if (!_isGround)
                    velocity.y += mass * deltaTime;
                velocity.x -= velocity.x * deltaTime * (_isGround ? 10 : 1);
                velocity.x = Math.abs(velocity.x) > max_speed ? max_speed * (velocity.x > 0 ? 1 : -1) : velocity.x;
                _parent.position.y += velocity.y * deltaTime;
                _parent.position.x += velocity.x * deltaTime;
            }
        }
    }

    @Override
    public void fixedupdate(float deltaTime) {}
    @Override
    public void render(Graphics g) {}
}
