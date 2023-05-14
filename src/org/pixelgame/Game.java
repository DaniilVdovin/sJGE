package org.pixelgame;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.Particles.ParticleSystem;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.uitoolkit.CoreUI;
import org.pixelgame.Engine.uitoolkit.components.Button;
import org.pixelgame.Engine.uitoolkit.components.Chart;
import org.pixelgame.Engine.uitoolkit.components.Grid;
import org.pixelgame.Engine.world.World;
import org.pixelgame.sprites.Fire;
import org.pixelgame.sprites.Grass;
import org.pixelgame.sprites.Player;

import java.awt.*;
import java.util.ArrayList;

public class Game {

    public static void main(String[] args) {
        Renderer.init();
        World.curentWorld = new World();

        ArrayList<Sprite> sprites = new ArrayList<Sprite>();


        for (int i = 0; i < 100; i++) {
            sprites.add(new Grass(i, 100+i*25,400+(int)(Math.sin(i)*25)).SetSize(25).SetColor(Color.GREEN));
            for (int j = 0; j < 10+(int)Math.sin(i+1); j++) {
                sprites.add(new Grass(i, 100+i*25,400+(int)(Math.sin(i)*25)+(j*25)+25).SetSize(25).SetColor(Color.darkGray));
            }
        }

        sprites.add(new Player(-1,200,40));

        Fire fire = new Fire(20,300,200);
        fire.Create(10,20);
        sprites.add(fire);


        Grid grid = new Grid(100,200,new Vector2Int(20,20));
        Button button = new Button(new Vector2Int(25,25));
        button.Text = "Hello!";
        grid.Child.add(button);
        Chart chart = new Chart(95,40,new Vector2Int(25,50));
        grid.Child.add(button);
        grid.Child.add(chart);
        CoreUI.uiComponents.add(grid);


        World.curentWorld.sprites.addAll(sprites);
    }
    public static void quit(){
        System.exit(0);
    }

}
