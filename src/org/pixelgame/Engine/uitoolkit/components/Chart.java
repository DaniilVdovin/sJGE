package org.pixelgame.Engine.uitoolkit.components;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.uitoolkit.UIComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Chart extends UIComponent {
    private int index = 0;
    private final List<Vector2Int> data;
    private int Max,Min;
    public Chart(int width, int height, Vector2Int position) {
        super(1, width, height, position);
        data = new ArrayList<>();
    }
    public void addData(Vector2Int value){
        data.add(value);
        Max = Math.max(value.y, Max);
        Min = Math.min(value.y, Min);
        index++;
    }
    @Override
    public void fixedupdate(float deltaTime)
    {
        addData(new Vector2Int(index,Renderer.currentFPS));
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(position.x, position.y,Width,Height);
        g.setColor(Color.BLUE);
        int c = (Width/5)-2;
        float facH = (float) Height/2 / (float) Max;
        for (int i = data.size()-(data.size()>c?c:data.size()-1); i < data.size(); i++) {
            g.drawLine(
                    (int) (position.x+Width+(i-data.size()-1)*5),
                    (int) (position.y+(data.get(i-1).y)*facH),

                    (int) (position.x+Width+(i-data.size())*5),
                    (int) (position.y+(data.get(i).y)*facH));
        }
    }
}
