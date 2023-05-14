package org.pixelgame.Engine.Particles;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;

public interface IParticleAction{
    //Action call in update for move particle
    Vector2 Action(Vector2 position, float deltaTime);
    //Active and return true when particle in die
    void Destroy(boolean result);
}
