package org.pixelgame.Engine.graphics;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.uitoolkit.CoreUI;
import org.pixelgame.Game;
import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;

public class Renderer {
    private static boolean DRAW_FPS_COUNTER = true;
    public static Camera MainCamera;
    private static CoreUI ui;
    private static Frame frame;
    public static Canvas canvas;

    private static int canvasWidth = 0;
    private static int canvasHeigth = 0;

    private static final int GAME_WIDTH = 1024;
    private static final int GAME_HEIGHT = 720;

    private static int GameWidth = 0;
    private static int GameHeight = 0;

    private static int FrameWidth = 0;
    private static int FrameHeight = 0;

    private static long lastFpsCheck = 0;
    private static int currentFPS = 0;
    private static int totalFrames = 0;

    private static void getBestSize(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        //Dimension screenSize = frame.getSize();

        boolean done = false;

        while (!done){
            canvasHeigth += GAME_HEIGHT;
            canvasWidth += GAME_WIDTH;

            if(canvasWidth > screenSize.width || canvasHeigth > screenSize.height){
                canvasHeigth -= GAME_HEIGHT;
                canvasWidth -= GAME_WIDTH;
                done = true;
            }
        }

        int xDiff = screenSize.width - canvasWidth;
        int yDiff = screenSize.height - canvasHeigth;
        int factor = (canvasWidth / GAME_WIDTH );
        factor = factor==0?1:factor;

        GameWidth = canvasWidth / factor + xDiff /factor;
        GameHeight = canvasHeigth / factor + yDiff / factor;

        canvasHeigth = GameHeight * factor;
        canvasWidth = GameWidth * factor;

        FrameHeight = frame.getHeight();
        FrameWidth = frame.getWidth();

        MainCamera.offSet = new Vector2(FrameWidth/2,FrameHeight/2);
    }

    private static void makeFullScreen(){
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = environment.getDefaultScreenDevice();

        if(graphicsDevice.isFullScreenSupported()){
            frame.setUndecorated(true);
            graphicsDevice.setFullScreenWindow(frame);
        }
    }

    public static void init(){
        frame = new Frame();
        canvas = new Canvas();
        MainCamera = new Camera();

        getBestSize();
        canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeigth));

        frame.add(canvas,0);
        //makeFullScreen();
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Game.quit();
            }
        });
        frame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                getBestSize();
                canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeigth));
            }
        });

        frame.setVisible(true);
        Input InputSystem = new Input();
        canvas.addKeyListener(InputSystem);
        canvas.addMouseMotionListener(InputSystem);
        startRenderingGame();
    }

    private static void startRenderingGame(){
        Thread thread = new Thread(){
            public void run(){
                GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
                VolatileImage vImage = gc.createCompatibleVolatileImage(GameWidth,GameHeight);
                while (true){
                    //FPS counter
                    {//SYSTEM
                        totalFrames++;
                        if (System.nanoTime() > lastFpsCheck + 1000000000) {
                            lastFpsCheck = System.nanoTime();
                            currentFPS = totalFrames;
                            totalFrames = 0;
                            World.fixedupdate();
                        }
                        if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
                            vImage = gc.createCompatibleVolatileImage(canvasWidth,FrameHeight);
                        }
                    }
                    {//GAME RENDER
                        Graphics g = vImage.getGraphics();
                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, canvasWidth, canvasHeigth);
                        Vector2Int trans = MainCamera.GetTranslate().toInt();

                        g.translate(-trans.x, -trans.y);
                        //RENDER STUFF
                        World.update();
                        World.render(g);

                        g.dispose();
                    }
                    {//UI RENDER
                        Graphics g = vImage.getGraphics();
                        //RENDER STUFF
                        World.renderUI(g);
                        //Draw FPS counter
                        if (DRAW_FPS_COUNTER) {
                            g.setColor(Color.lightGray);
                            g.drawString(String.valueOf(currentFPS), 2, 10);
                        }
                    }
                    {//SYSTEM END DRAW
                        Graphics g = canvas.getGraphics();
                        g.drawImage(vImage, 0, 0, canvasWidth, canvasHeigth, null);
                        g.dispose();
                    }
                    if (currentFPS>60) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        thread.setName("Rendering Thread");
        thread.start();
    }

    public static BufferedImage loadImage(String path) throws IOException {
        BufferedImage rawImage = ImageIO.read(Renderer.class.getResource(path));
        BufferedImage finalImage = canvas.getGraphicsConfiguration().createCompatibleImage(rawImage.getWidth(),rawImage.getHeight(),rawImage.getTransparency());

        finalImage.getGraphics().drawImage(rawImage,0,0,rawImage.getWidth(),rawImage.getHeight(),null);

        return finalImage;
    }
}
