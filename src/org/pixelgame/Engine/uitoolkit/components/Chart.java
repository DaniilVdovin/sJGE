package org.pixelgame.Engine.uitoolkit.components;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.uitoolkit.UIComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Chart extends UIComponent {
    private List<Series> ListSeries;
    public Chart(int width, int height, Vector2<Integer> position) {
        super(1, width, height, position);
        ListSeries = new ArrayList<>(1);
    }
    public void addSeries(Series series){
        series.data = new ArrayList<>();
        ListSeries.add(series);
    }
    public void removeSeries(Series series){
        ListSeries.remove(series);
    }
    @Override
    public void update(float deltaTime) {
        for (Series series:ListSeries) {
            if(series.component == null) continue;
            series.component.update(deltaTime);
        }
    }
    @Override
    public void fixedupdate(float deltaTime)
    {
        for (Series series:ListSeries) {
            if(series.component == null) continue;
            series.component.fixedupdate(deltaTime);
        }
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(position.x, position.y,Width,Height);
        int c = (Width/5)-2;
        for (Series series:ListSeries) {
            g.setColor(series.color);
            float facH = (float) (Height/2)/ (float)series.Max;
            for (int i = series.data.size()-(series.data.size()>c?c:series.data.size()-1); i < series.data.size(); i++) {
                g.drawLine(
                        (int) (position.x+Width+(i-series.data.size()-1)*5),
                        (int) (position.y+(series.data.get(i-1))*facH),

                        (int) (position.x+Width+(i-series.data.size())*5),
                        (int) (position.y+(series.data.get(i))*facH));
            }
            if(series.component == null) continue;
                series.component.render(g);
        }
       //for (int i = data.size()-(data.size()>c?c:data.size()-1); i < data.size(); i++) {
       //    g.drawLine(
       //            (int) (position.x+Width+(i-data.size()-1)*5),
       //            (int) (position.y+(data.get(i-1))*facH),

       //            (int) (position.x+Width+(i-data.size())*5),
       //            (int) (position.y+(data.get(i))*facH));
       //}
       //component.render(g);
    }
}
