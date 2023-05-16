package org.pixelgame.Engine.object;

import org.pixelgame.Engine.Core.ComponentList;
import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.physics.Collision;
import org.pixelgame.Engine.physics.Gravity;
import org.pixelgame.Engine.physics.Physics;
import org.pixelgame.Engine.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Proxy;
import java.util.*;
import java.util.List;

/**
 * Sprite is parent class for custom objects
 * @see org.pixelgame.Engine.physics.Physics
 */
public class Sprite extends Physics {
    public int id = 0;
    public Vector2 position = Vector2.Zero();
    public int Width = 0,Height = 0;
    public BufferedImage image = null;
    public Color DefaultColor = Color.GREEN;
    public boolean isDebug = false;
    public ComponentList components = new ComponentList();
    public ArrayList<Sprite> Child = new ArrayList<>();

    /**
     * Constructor
     * @param id
     * @param posX
     * @param posY
     */
    public Sprite(int id,int posX,int posY){
        this.id = id;
        this.position.x = posX;
        this.position.y = posY;
    }

    /**
     * Constructor
     * @param id
     * @param pos
     */
    public Sprite(int id,Vector2Int pos){
        this.id = id;
        this.position = pos;
    }

    /**
     * Constructor
     * @param id
     * @param pos
     */
    public Sprite(int id,Vector2 pos){
        this.id = id;
        this.position = pos;
    }

    @Override
    public void update(float deltaTime){
        for (Component c:components) c.update(deltaTime);
        for (Component c:Child) c.update(deltaTime);
        position.y+=velocity.y*deltaTime;
        position.x+=velocity.x*deltaTime;
    }

    @Override
    public void fixedupdate(float deltaTime) {
        for (Component c:components) c.fixedupdate(deltaTime);
        for (Component c:Child) c.fixedupdate(deltaTime);
    }

    @Override
    public void render(Graphics g){
        int realX = (int) position.x - Width/2;
        int realY = (int) position.y - Height/2;
        if(image == null){
            g.setColor(DefaultColor);
            g.fillRect(realX,realY, Width, Height);
        }else{
            g.drawImage(image, realX, realY,Width,Height,null);
        }
        for (Component c:components) c.render(g);
        for (Component c:Child) c.render(g);

        if(isDebug)
        {
            Text debug = new Text(position.clone().minusY(Height*2).minusX(Width).toInt());
            debug.Text.append(velocity.toString());
            debug.render(g);
        }
    }

    /**
     * set Width and Height of this object
     * @param width
     * @param height
     * @return changed object
     */
    public Sprite SetSize(int width, int height){
        Width = width; Height = height;
        return this;
    }

    /**
     * set Size
     * @param size
     * @return squre
     */
    public Sprite SetSize(int size){
       return SetSize(size,size);
    }

    /**
     * @param color
     * @return changed object
     */
    public Sprite SetColor(Color color){
        DefaultColor = color;
        image = null;
        return this;
    }

    /**
     * @param path
     * @return changed object
     */
    public Sprite SetImage(String path){
        try {
            image = Renderer.loadImage(path);
            Height =  image.getHeight();
            Width = image.getWidth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
