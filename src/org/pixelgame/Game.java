package org.pixelgame;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.Particles.ParticleSystem;
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
import org.pixelgame.sprites.Fire;
import org.pixelgame.sprites.Grass;
import org.pixelgame.sprites.Player;
import org.pixelgame.sprites.RBox;

import java.awt.*;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Renderer.init();
        World.curentWorld = new World();

        ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        for (int i = -20; i < 100; i++) {
            if(i%10==1){
                Sprite s = new Sprite(i, 100+i*25,350).SetSize(25);
                s.mass = 20;
                s.components.add(new Gravity(s));
                s.components.add(new Collision(s));
                sprites.add(s);
            }

        }
        for (int i = -20; i < 100; i++) {
            sprites.add(new Grass(i, 100+i*25,400).SetSize(25));
            for (int j = 0; j < 30; j++) {
                sprites.add(new Sprite(i, 100+i*25,400+(j*25)+25).SetSize(25).SetColor(Color.darkGray));
            }
        }

        sprites.add(new Player(-1,1200,40));

        Fire fire = new Fire(20,new Vector2Int(900,200),1);
        fire.Create(10,10);
        sprites.add(fire);

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

        sprites.add(new RBox(100,new Vector2(900,300)));

        World.curentWorld.sprites.addAll(sprites);
    }
    public static void quit(){
        System.exit(0);
    }

}
