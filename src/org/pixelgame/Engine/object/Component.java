package org.pixelgame.Engine.object;

import jdk.jfr.Enabled;

import java.awt.*;

public interface Component {


    boolean isEnable = true;
    /**
     * Update object per frame
     * @param deltaTime
     */
    void update(float deltaTime);
    /**
     * Update object per deltatime
     * @param deltaTime
     */
    void fixedupdate(float deltaTime);
    /**
     * Render object
     * @param g
     */
    void render(Graphics g);
}
