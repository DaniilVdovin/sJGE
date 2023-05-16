package org.pixelgame.Engine.world;

import java.awt.*;

public interface IUpdatable {
    void update(float deltaTime);
    void fixedupdate(float deltaTime);
    void render(Graphics g);
}
