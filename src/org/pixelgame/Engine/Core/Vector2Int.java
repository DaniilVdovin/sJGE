package org.pixelgame.Engine.Core;

public class Vector2Int extends Vector2{
    public int x,y;
    public Vector2Int() {
        this.x = 0; this.y = 0;
    }
    public Vector2Int(int x, int y) {
        this.x =x; this.y = y;
    }
    public Vector2Int(float x, float y) {
        this.x = (int) x; this.y = (int) y;
    }
    public static Vector2Int Zero(){
        return new Vector2Int();
    }

    @Override
    public Vector2Int plusEquals(int b) {
        x+=b;y+=b;
        return this;
    }

    @Override
    public Vector2Int plusEquals(Vector2 b) {
        x+=b.x;y+=b.y;
        return this;
    }
    @Override
    public Vector2Int plusY(int b) {
        return new Vector2Int(x,y+b);
    }

    @Override
    public Vector2Int plusX(int b) {
        return new Vector2Int(x+b,y);
    }
    @Override
    public Vector2Int minus(int b) {
        return new Vector2Int(x-b,y-b);
    }
    @Override
    public Vector2Int minusX(int b) {
        return new Vector2Int(x-b,y);
    }
    public Vector2Int minusY(int b){
        return new Vector2Int(x,y-b);
    }
    @Override
    public String toString() {
        return "("+ x +", " + y +')';
    }
}
