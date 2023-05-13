package org.pixelgame.Engine.Core;
public class Vector2 {
    public float x,y;
    public Vector2() {
        this.x = 0;this.y = 0;
    }
    public Vector2(float x, float y) {
        this.x = x;this.y = y;
    }
    public static Vector2 Zero(){
        return new Vector2();
    }
    public static Vector2 One(){
        return new Vector2(1,1);
    }
}
