package org.pixelgame.Engine.graphics;

import org.pixelgame.Game;
import org.pixelgame.Engine.input.Input;
import org.pixelgame.Engine.world.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;

public class Renderer {

    private static boolean DRAW_FPS_COUNTER = true;


    private static Frame frame;
    private static Canvas canvas;

    private static int canvasWidth = 0;
    private static int canvasHeigth = 0;

    private static final int GAME_WIDTH = 1024;
    private static final int GAME_HEIGHT = 720;

    private static int GameWidth = 0;
    private static int GameHeight = 0;


    private static long lastFpsCheck = 0;
    private static int currentFPS = 0;
    private static int totalFrames = 0;

    private static void getBestSize(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

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
        int factor = canvasWidth / GAME_WIDTH;

        GameWidth = canvasWidth / factor + xDiff /factor;
        GameHeight = canvasHeigth / factor + yDiff / factor;

        canvasHeigth = GameHeight * factor;
        canvasWidth = GameWidth * factor;
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

        getBestSize();

        canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeigth));

        frame.add(canvas);
        //makeFullScreen();
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);



        frame. addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Game.quit();
            }
        });

        frame.setVisible(true);
        canvas.addKeyListener(new Input());
        startRendering();
    }

    private static void startRendering(){
        Thread thread = new Thread(){
            public void run(){

                GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
                VolatileImage vImage = gc.createCompatibleVolatileImage(GameWidth,GameHeight);

                while (true){
                    //FPS counter
                    totalFrames++;
                    if(System.nanoTime() > lastFpsCheck + 1000000000){
                        lastFpsCheck = System.nanoTime();
                        currentFPS = totalFrames;
                        totalFrames = 0;
                    }

                    if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE){
                        vImage = gc.createCompatibleVolatileImage(GameWidth,GameHeight);
                    }

                    Graphics g = vImage.getGraphics();

                    g.setColor(Color.BLACK);
                    g.fillRect(0,0,GameWidth,GameHeight);

                    //RENDER STUFF
                    World.update();
                    World.render(g);

                    //Draw FPS counter
                    if(DRAW_FPS_COUNTER){
                        g.setColor(Color.lightGray);
                        g.drawString(String.valueOf(currentFPS),2,10);
                    }

                    g.dispose();

                    g = canvas.getGraphics();
                    g.drawImage(vImage,0,0,canvasWidth,canvasHeigth,null);

                    g.dispose();
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
