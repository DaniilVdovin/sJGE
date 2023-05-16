package org.pixelgame.Engine.object;

public interface IComplementable<T extends IComponent>{
    T GetComponent(Class component);
    T AddComponent(T component);
}
