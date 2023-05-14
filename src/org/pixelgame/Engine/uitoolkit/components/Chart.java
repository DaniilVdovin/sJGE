package org.pixelgame.Engine.uitoolkit.components;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
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
        for (int i = 0; i < 30; i++) {
            addData(new Vector2Int(i,(int)(Math.sin(i)*10)));
        }
    }
    public void addData(Vector2Int value){
        data.add(value);
        Max = Math.max(value.y, Max);
        Min = Math.min(value.y, Min);
        index++;
    }
    @Override
    public void update(float deltaTime) {
        addData(new Vector2Int(index,(int)(Math.sin(index)*10)));
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(position.x, position.y,Width,Height);
        g.setColor(Color.BLUE);
        float facH = (float) Height / (float) Max;
        float facW = (float) Width  / (float) Max;
        for (int i = data.size()-(data.size()>20?20:0); i < data.size(); i++) {
            g.drawLine(
                    (int) (position.x+(data.size()-i-1)*5),
                    (int) (position.y+(data.get(i-1).y)),

                    (int) (position.x+(data.size()-i)*5),
                    (int) (position.y+(data.get(i).y)));
        }
    }
}
