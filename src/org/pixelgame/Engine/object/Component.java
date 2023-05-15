package org.pixelgame.Engine.object;

import jdk.jfr.Enabled;

import java.awt.*;

public interface Component {
    boolean isEnable = true;
    void update(float deltaTime);
    void fixedupdate(float deltaTime);
    void render(Graphics g);
}
