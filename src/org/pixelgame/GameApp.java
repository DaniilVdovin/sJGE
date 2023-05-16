package org.pixelgame;

import org.pixelgame.Engine.Core.GameApplication;

import java.awt.*;
import java.util.ArrayList;

import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.object.Component;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Gravity;
import org.pixelgame.Engine.uitoolkit.CoreUI;
import org.pixelgame.Engine.uitoolkit.components.Button;
import org.pixelgame.Engine.uitoolkit.components.Chart;
import org.pixelgame.Engine.uitoolkit.components.Grid;
import org.pixelgame.Engine.uitoolkit.components.Series;
import org.pixelgame.Engine.world.World;
import org.pixelgame.sprites.Chest;
import org.pixelgame.sprites.Grass;
import org.pixelgame.sprites.Player;
import org.pixelgame.sprites.Rock;

public class GameApp extends GameApplication {
    @Override
    public void Init() {
        super.Init();
        ArrayList<Sprite> objects = new ArrayList<>();

        Renderer.FIX_FRAME_RATE = false;

        for (int i = -20; i < 100; i++) {
            if(i%10==1){
                objects.add(new Chest(i, 100+i*25,350));
            }

        }
        for (int i = -20; i < 100; i++) {
            objects.add(new Grass(i, 100+i*25,400).SetSize(25));
            for (int j = 0; j < 30; j++) {
                objects.add(new Rock( 100+i*25,400+(j*25)+25).SetSize(25).SetColor(Color.darkGray));
            }
        }

        objects.add(new Player(-1,1200,40));

        Grid grid = new Grid(200,150,new Vector2Int(20,20));
        Button button = new Button(new Vector2Int(25,25));
        button.Text = "Hello!";
        grid.Child.add(button);

        Chart chart = new Chart(190,80,new Vector2Int(25,50));
        Series seriesRED = new Series();
        seriesRED.color = Color.RED;
        seriesRED.component = new Component() {
            @Override
            public void update(float deltaTime) { }
            @Override
            public void fixedupdate(float deltaTime) {
                seriesRED.addData(Renderer.currentFPS);
            }
            @Override
            public void render(Graphics g) {}
        };
        Series seriesGREEN = new Series();
        seriesGREEN.color = Color.BLACK;
        seriesGREEN.component = new Component() {
            @Override
            public void update(float deltaTime) {
                seriesGREEN.addData((int)Math.abs(Math.sin(seriesGREEN.index)*10));
            }
            @Override
            public void fixedupdate(float deltaTime) {
            }
            @Override
            public void render(Graphics g) {}
        };

        Series SeriesSome = new Series();
        SeriesSome.color = Color.orange;
        SeriesSome.component = new Component() {
            @Override
            public void update(float deltaTime) {
                SeriesSome.addData(44);
            }
            @Override
            public void fixedupdate(float deltaTime) {
            }
            @Override
            public void render(Graphics g) {}
        };

        chart.addSeries(seriesRED);
        chart.addSeries(seriesGREEN);
        chart.addSeries(SeriesSome);


        grid.Child.add(button);
        grid.Child.add(chart);
        CoreUI.uiComponents.add(grid);
        World.curentWorld.sprites.addAll(objects);

    }
    public void AddObject() {


    }
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void fixedupdate(float deltaTime) {
        super.fixedupdate(deltaTime);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }
}
