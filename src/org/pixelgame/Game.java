package org.pixelgame;

import org.pixelgame.EditorV2.Core.GenerateObjectClass;
import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.object.Sprite;
import org.pixelgame.Engine.physics.Collider;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Physics;

import java.io.IOException;

public class Game{
    public static void main(String[] args) {
        //javax.swing.SwingUtilities.invokeLater(MainWindow::new);
        //new MainWindow();
        //new GameApp().Init();

        new GenerateObjectClass()
                .setName("TestObject")
                .setPath("org.pixelgame.sprites")
                .addComponent(Collider.class)
                .addComponent(Physics.class)
                .addComponent(Collision.class,true)
                .addInits(int.class, "HP",true,100)
                .addInits(int.class, "Wanish")
                .addInits(float.class, "Speed",true,1.5f)
                .addInits(float.class, "Temp_Speed")
                .addInits(String.class, "Name",true,"TTT")
                .addInits(String.class, "tempValue")
                .addSpriteImage("chest_open","/image/Chest_open.png")
                .addSpriteImage("chest_close","/image/chest.png",true)
                .addInitLogic(new StringBuilder()
                        .append("physics.mass = 50;\n")
                        .append("SetSize(25);\n")
                )
                .Generate()
                .SaveFile();
    }
}
