package org.pixelgame.Engine.Core;
import java.util.function.BinaryOperator;
public class Vector2<T extends Number>{
    public T x,y;
    private static final BinaryOperator<Number> c = (number, number2) -> number.floatValue() + number2.floatValue();
    private static final BinaryOperator<Number> m = (number, number2) -> number.floatValue()-number2.floatValue();
    public Vector2(Vector2<T> value) {
        this(value.x, value.y);
    }
    public Vector2(T x, T y) {
        this.x = x;this.y = y;
    }
    /**
     *
     * @return Vector2Int(x,y)
     */
    public Vector2<Integer> toInt(){
        return new Vector2<>(x.intValue(),y.intValue());
    }
    public Vector2<Float> toFloat(){
        return new Vector2<>(x.floatValue(),y.floatValue());
    }

    /**
     * @return Vector2()
     */
    public static Vector2<Number> Zero(){
        return new Vector2<>( 0, 0);
    }
    //PLUS
    /**
     *
     * @param b
     * @return changed object
     */
    public Vector2<T> plusEquals(Vector2<T> b){
        x = (T) c.apply(x,b.x);
        y = (T) c.apply(y,b.y);
        return this;
    }

    /**
     * @param b
     * @return changed object
     */
    public Vector2<T> plusEquals(int b){
        x = (T) c.apply(x,b);
        y = (T) c.apply(y,b);
        return this;
    }
    public Vector2<T> plus(Vector2<T> b){
       return new Vector2<>((T) c.apply(x,b.x),(T) c.apply(y,b.y));
    }
    public Vector2<T> plus(int b){
         return new Vector2(c.apply(x,b),c.apply(y,b));
    }
    public Vector2<T> plusX(int b){
        return new Vector2<>((T) c.apply(x,b),y);
    }
    public Vector2<T> plusY(int b){
        return new Vector2<>(x,(T) c.apply(y,b));
    }
    //MINUS
    public Vector2<T> minusEquals(Vector2<T> b){
        x = (T) m.apply(x,b.x);
        y = (T) m.apply(y,b.y);
        return this;
    }
    public Vector2<T> minusEquals(int b){
        x = (T) m.apply(x,b);
        y = (T) m.apply(y,b);
        return this;
    }
    public Vector2<T> minus(Vector2 b){
        return new Vector2<>((T) m.apply(x,b.x), (T) m.apply(y,b.y));
    }
    public Vector2<T> minus(int b){
        return new Vector2<>((T) m.apply(x,b), (T) m.apply(y,b));
    }
    public Vector2<T> minusX(int b){
        return new Vector2<>((T) m.apply(x,b),y);
    }
    public Vector2<T> minusY(int b){
        return new Vector2<>(x,(T) m.apply(y,b));
    }
    public static Vector2<Float> Lerp(Vector2<Float> firstVector, Vector2<Float> secondVector, float by)
    {
        return new Vector2<>(firstVector.x*(1 - by) + secondVector.x * by, firstVector.y*(1 - by) + secondVector.y * by);
    }
    /**
     * clone vector
     * @return Vector2(this);
     */
    public Vector2<T> clone(){
        return new Vector2<>(this);
    }

    @Override
    public String toString() {
        return "("+ x.intValue() +", " + y.intValue() +')';
    }
}
