package org.pixelgame.Engine.graphics;

import org.pixelgame.Engine.Core.Vector2;

public class Camera {
    public Vector2 offSet = Vector2.Zero();
    public Vector2 position = Vector2.Zero();
    public Vector2 GetTranslate(){
        return position.minus(offSet);
    }
}