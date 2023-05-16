package org.pixelgame.Engine.Core;
public class Vector2{
    public float x,y;
    public Vector2() {
        this(0,0);
    }
    public Vector2(Vector2 value) {
        this(value.x, value.y);
    }
    public Vector2(int x, int y) {
        this((float) x,(float) y);
    }
    public Vector2(float x, float y) {
        this.x = x;this.y = y;
    }

    /**
     *
     * @return Vector2Int(x,y)
     */
    public Vector2Int toInt(){
        return new Vector2Int(x,y);
    }

    /**
     * @return Vector2()
     */
    public static Vector2 Zero(){
        return new Vector2();
    }

    /**
     *
     * @return Vector2(1,1)
     */
    public static Vector2 One(){
        return new Vector2(1,1);
    }
    //PLUS

    /**
     *
     * @param b
     * @return changed object
     */
    public Vector2 plusEquals(Vector2 b){
        x+=b.x;y+=b.y; return this;
    }

    /**
     * @param b
     * @return changed object
     */
    public Vector2 plusEquals(int b){
        x+=b;y+=b; return this;
    }
    public Vector2 plus(Vector2 b){
       return new Vector2(x+b.x,y+b.y);
    }
    public Vector2 plus(int b){
         return new Vector2(x+b,y+b);
    }
    public Vector2 plusX(int b){
        return new Vector2(x+b,y);
    }
    public Vector2 plusY(int b){
        return new Vector2(x,y+b);
    }
    //MINUS
    public Vector2 minusEquals(Vector2 b){
        x-=b.x;y-=b.y; return this;
    }
    public Vector2 minusEquals(int b){
        x-=b;y-=b; return this;
    }
    public Vector2 minus(Vector2 b){
        return new Vector2(x-b.x,y-b.y);
    }
    public Vector2 minus(int b){
        return new Vector2(x-b,y-b);
    }
    public Vector2 minusX(int b){
        return new Vector2(x-b,y);
    }
    public Vector2 minusY(int b){
        return new Vector2(x,y-b);
    }
    public static Vector2 Lerp(Vector2 firstVector, Vector2 secondVector, float by)
    {
        float retX = firstVector.x * (1 - by) + secondVector.x * by;
        float retY = firstVector.y * (1 - by) + secondVector.y * by;
        return new Vector2(retX, retY);
    }

    /**
     * clone vector
     * @return Vector2(this);
     */
    public Vector2 clone(){
        return new Vector2(this);
    }
    @Override
    public String toString() {
        return "("+ x +", " + y +')';
    }
}
