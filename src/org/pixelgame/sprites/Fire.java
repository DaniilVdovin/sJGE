package org.pixelgame.sprites;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.Particles.IParticleAction;
import org.pixelgame.Engine.Particles.ParticleSystem;

public class Fire extends ParticleSystem {
    public Fire(int id, Vector2Int pos, int duration) {
        super(id, pos, duration);
        isDebug = true;
        SetAction(new IParticleAction() {
            @Override
            public Vector2 Action(Vector2 position, float deltaTime) {
                return position.plusEquals(new Vector2((int)Math.sin(deltaTime),-(10*deltaTime)));
            }
            @Override
            public void Destroy(boolean result) {

            }
        });
    }
}
