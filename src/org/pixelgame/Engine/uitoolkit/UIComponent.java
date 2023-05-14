package org.pixelgame.Engine.uitoolkit;
import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.object.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIComponent implements Component {
    public int   Layer;
    public int Width,Height;
    public Vector2Int position;
    public List<UIComponent> Child = new ArrayList<>();

    public UIComponent(int layer, int width, int height, Vector2Int position) {
        Layer = layer;
        Width = width;
        Height = height;
        this.position = position;
    }
    @Override
    public void update(float deltaTime){for (UIComponent uc:Child)uc.update(deltaTime);}
    @Override
    public void fixedupdate(float deltaTime) {for (UIComponent uc:Child)uc.fixedupdate(deltaTime);}
    @Override
    public void render(Graphics g) { for (UIComponent uc:Child)uc.render(g);}
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
