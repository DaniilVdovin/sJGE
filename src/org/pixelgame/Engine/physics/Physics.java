package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;

public class Physics implements IComponent, IUpdatable {
    public boolean _isStatic = false;
    public boolean _isGround = false;
    public int mass;
    public Vector2<Float> velocity = new Vector2<>(0f,0f);
    public float speed = 10.0f;
    public float max_speed = 70.0f;
    private final Sprite _parent;
    private Collider _collider;
    public Physics(Sprite parent) {
        _parent = parent;
        _collider = (Collider) _parent.GetComponent(Collider.class);
        if (_collider == null){
            _collider = new Collider(parent);
            _parent.AddComponent(this._collider);
        };
    }
    public Collider GetCollider(){
        return _collider;
    }
    @Override
    public Sprite GetParent() {
        return _parent;
    }

    // FIXME: 17.05.2023 
    @Override
    public void update(float deltaTime) {
        if(isEnable & !_isStatic) {
            if (!_isGround)
                velocity.y += mass * deltaTime;
            velocity.x -= velocity.x * deltaTime * (_isGround ? 10 : 1);
            velocity.x = Math.abs(velocity.x) > max_speed ? max_speed * (velocity.x > 0 ? 1 : -1) : velocity.x;
            _parent.position.y += velocity.y * deltaTime;
            _parent.position.x += velocity.x * deltaTime;
        }
    }

    @Override
    public void fixedupdate(float deltaTime) {}
    @Override
    public void render(Graphics g) {}
}
