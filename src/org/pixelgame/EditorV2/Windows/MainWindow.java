package org.pixelgame.EditorV2.Windows;

import org.pixelgame.EditorV2.Components.FileTree;
import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.graphics.SpriteImage;
import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.input.MouseOver;
import org.pixelgame.Engine.world.World;
import org.pixelgame.GameApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class MainWindow{
    private JPanel frame;
    private JPanel object_tree_frame;
    private JTabbedPane tabbled_frame;
    private JPanel main_frame;
    private JPanel tabble_editor_mode;
    private JTable table1;
    private JTree tree1;
    private JButton startGame;

    public MainWindow() {
        JFrame root = new JFrame("sJGE");
        root.setExtendedState(MAXIMIZED_BOTH);
        root.setContentPane(frame);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        root.setSize(dim);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        object_tree_frame.setLayout(new BoxLayout(object_tree_frame, BoxLayout.X_AXIS));
        String[] av = new String[]{"C:\\Users\\stels\\IdeaProjects\\Terarion\\src\\org\\pixelgame\\resources","C:\\Users\\stels\\IdeaProjects\\Terarion\\src\\org\\pixelgame\\resources"};
        for (int i = 0; i < av.length; i++)
            object_tree_frame.add(new FileTree(new File(av[i])));
        root.setVisible(true);
        startGame.addActionListener(e -> {
            new GameApp().Init();
        });
        InitScene();
    }
    public void InitScene(){
        Canvas canvas = new Canvas();
        Input InputSystem = new Input();
        canvas.addKeyListener(InputSystem);
        canvas.addMouseMotionListener(InputSystem);
        tabble_editor_mode.add(canvas);
        Thread scene = new Thread(() -> {
            GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
            VolatileImage vImage = gc.createCompatibleVolatileImage(tabble_editor_mode.getWidth(),tabble_editor_mode.getHeight());
            Vector2<Integer> cursore = new Vector2<>(0,0);
            ArrayList<Vector2<Integer>> blocks = new ArrayList<>();
            MouseListener mouseListener = new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }
                @Override
                public void mousePressed(MouseEvent e) {
                    Vector2<Integer> mouse = Input.getMousePosition().clone();
                    int mix = mouse.x;
                    int miy = mouse.y;
                    if(!Input.getKey(KeyEvent.VK_ALT)){
                        mix = ((int) (mouse.x/25))*25;
                        miy = ((int) (mouse.y/25))*25;
                    }

                    blocks.add(new Vector2<Integer>(mix,miy));

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            };
            canvas.addMouseListener(mouseListener);
            BufferedImage grass = null;
            try {
                grass = loadImage("/Tileset/png/Tiles/2.png",canvas);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            while (true){
                {//SYSTEM
                    if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
                        vImage = gc.createCompatibleVolatileImage(tabble_editor_mode.getWidth(),tabble_editor_mode.getHeight());
                    }
                }
                {//BEGIB SCENE
                    Graphics g = vImage.getGraphics();
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(0, 0, tabble_editor_mode.getWidth(), tabble_editor_mode.getHeight());
                    g.setColor(Color.gray);
                    for (int i = 0; i <= tabble_editor_mode.getWidth(); i+=25) {
                        g.drawLine(i,0,i,tabble_editor_mode.getHeight());
                    }
                    for (int i = 0; i <= tabble_editor_mode.getWidth(); i+=25) {
                        g.drawLine(0,i,tabble_editor_mode.getWidth(),i);
                    }
                    g.dispose();
                }
                {//SCENE
                    Graphics g = vImage.getGraphics();
                    Vector2<Integer> mouse = Input.getMousePosition().clone();
                    int mix = ((int) (mouse.x/25))*25;
                    int miy = ((int) (mouse.y/25))*25;
                    g.setColor(Color.ORANGE);
                    g.drawRect((mix),(miy),25,25);
                    for (Vector2<Integer> b:blocks) {
                        //g.setColor(Color.GREEN);
                        //g.fillRect((b.x),(b.y),25,25);
                        g.drawImage(grass,b.x,b.y,25,25,null);
                    }
                    g.dispose();
                }
                {//DONE
                    Graphics g = canvas.getGraphics();
                    g.drawImage(vImage, 0, 0, tabble_editor_mode.getWidth(), tabble_editor_mode.getHeight(), null);
                    g.dispose();
                }
            }
        });
        scene.setName("Scene Renderer");
        scene.start();
    }
    public BufferedImage loadImage(String path,Canvas canvas) throws IOException {
        BufferedImage rawImage = ImageIO.read(Objects.requireNonNull(Renderer.class.getResource(path)));
        BufferedImage finalImage = canvas.getGraphicsConfiguration().createCompatibleImage(rawImage.getWidth(),rawImage.getHeight(),rawImage.getTransparency());
        finalImage.getGraphics().drawImage(rawImage,0,0,rawImage.getWidth(),rawImage.getHeight(),null);
        return finalImage;
    }
}
