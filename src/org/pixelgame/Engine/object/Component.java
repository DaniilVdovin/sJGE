package org.pixelgame.Engine.object;

import java.awt.*;

public interface Component {
    void update(float deltaTime);
    void fixedupdate(float deltaTime);
    void render(Graphics g);
}
