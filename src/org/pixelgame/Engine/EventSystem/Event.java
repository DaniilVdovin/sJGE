package org.pixelgame.Engine.EventSystem;

import java.util.ArrayList;

public class Event<T>{
    protected final ArrayList<T> _listeners = new ArrayList<>();
    public final void addListener(T toAdd) {
        _listeners.add(toAdd);
    }
    public final void removeListener(T toAdd) {_listeners.remove(toAdd);}
}
