package org.pixelgame.Engine.object;

import java.util.ArrayList;

public interface IComplementable<T extends IComponent>{
    T GetComponent(Class component);
    ArrayList<T> GetComponents(Class component);
    T AddComponent(T component);
}
