package org.pixelgame.Engine.uitoolkit.components;

import org.pixelgame.Engine.world.IUpdatable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class Series {
    public int index = 0;
    int Max,Min;
    public IUpdatable component;
    List<Integer> data = new ArrayList<>();
    public Color color;
    public void addData(int value){
        data.add(value);
        Max = Math.max(value, Max);
        Min = Math.min(value, Min);
        index++;
    }
}
