package org.pixelgame.Engine.graphics;

import org.pixelgame.Engine.Core.Vector2;

public class Camera {
    public Vector2<Float>  offSet = new Vector2<>(0f,0f);
    public Vector2<Float>  position = new Vector2<>(0f,0f);
    public Vector2<Integer> GetTranslate(){
        return position.minus(offSet).toInt();
    }
}