package org.pixelgame.Engine.uitoolkit.components;

import org.pixelgame.Engine.object.Component;

import java.awt.*;
import java.util.List;
public class Series {
    public int index = 0;
    int Max,Min;
    public Component component;
    List<Integer> data;
    public Color color;
    public void addData(int value){
        data.add(value);
        Max = Math.max(value, Max);
        Min = Math.min(value, Min);
        index++;
    }
}