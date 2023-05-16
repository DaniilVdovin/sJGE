package org.pixelgame.Engine.physics;

import org.pixelgame.Engine.object.IComponent;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.world.IUpdatable;
import org.pixelgame.Engine.world.World;

import java.awt.*;

public class Collision implements IComponent, IUpdatable {
    private final Sprite _parent;
    private final Physics _physics;
    public boolean isEnable;
    public Collision(Sprite parent) {
        this(true, parent);
    }
    public Collision(boolean isEnable, Sprite parent) {
        this.isEnable = isEnable;
        _parent = parent;
        this._physics = (Physics) _parent.GetComponent(Physics.class);
        if (this._physics == null) throw new RuntimeException("Need Add Physics");
    }

    // FIXME: 17.05.2023 
    @Override
    public void update(float deltaTime) {
        if (isEnable) {
            _physics._isGround = false;
            for (Sprite sprite : World.curentWorld.sprites) {
                Physics sp = ((Physics)sprite.GetComponent(Physics.class));
                if(sp == null) continue;
                if (sprite == _parent) continue;
                if (!_physics._isBounds) continue;
                Rectangle rect = new Rectangle((int) (_parent.position.x - _parent.Width / 2),
                        (int) (_parent.position.y + _physics.velocity.y * deltaTime - _parent.Height / 2), _parent.Width, _parent.Height);
                Rectangle otherRect = new Rectangle((int) (sprite.position.x - sprite.Width / 2)
                        , (int) (sprite.position.y - sprite.Height / 2), sprite.Width, sprite.Height);
                if (rect.intersects(otherRect)) {
                    _physics._isGround = true;

                    if (!sp._isStatic & sp.mass <= _physics.mass) {
                        sp.velocity.y += (_physics.velocity.y)*deltaTime*(sprite.position.y< _parent.position.y?1:-1);
                    }
                    _physics.velocity.y -= _physics.velocity.y;
                }
                rect = new Rectangle((int) (_parent.position.x + _physics.velocity.x * deltaTime - _parent.Width / 2),
                        (int) (_parent.position.y - _parent.Height / 2), _parent.Width, _parent.Height);
                if (rect.intersects(otherRect)) {
                    if (!sp._isStatic & sp.mass <= _physics.mass) {
                        sp.velocity.x += ((_physics.velocity.x/2)/((float) (sp.mass + _physics.mass) /50));
                    }
                    _physics.velocity.x -= _physics.velocity.x;
                }
            }
            _physics.velocity.x -= _physics.velocity.x*deltaTime;
        }
    }
    @Override
    public void fixedupdate(float deltaTime) {

    }
    @Override
    public void render(Graphics g) { }

    @Override
    public Sprite GetParent() {
        return _parent;
    }
}
