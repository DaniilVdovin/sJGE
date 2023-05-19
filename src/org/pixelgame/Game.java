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

        try {
            GenerateObjectClass.addComponent(Collider.class);
            GenerateObjectClass.addComponent(Physics.class);
            GenerateObjectClass.addComponent(Collision.class);
            GenerateObjectClass.addComponent(Sprite.class);
            GenerateObjectClass.SaveFile("TestObject.java","org.pixelgame.sprites",GenerateObjectClass.Generate());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
