package org.pixelgame.Engine.object;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.Core.Vector2Int;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.physics.Physics;
import org.pixelgame.Engine.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite extends Physics implements Component{
    public int id = 0;
    public Vector2 position = Vector2.Zero();
    public float moveX = 0;
    public int Width = 0,Height = 0;
    public BufferedImage image = null;
    public Color DefaultColor = Color.GREEN;
    public boolean isDebug = true;
    public Sprite(int id,int posX,int posY){
        this.id = id;
        this.position.x = posX;
        this.position.y = posY;
    }
    public Sprite(int id,Vector2Int pos){
        this.id = id;
        this.position = pos;
    }
    public Sprite(int id,Vector2 pos){
        this.id = id;
        this.position = pos;
    }
    public Sprite SetGravity(boolean value){
        _isGravity = value;
        return this;
    }

    @Override
    public void update(float deltaTime){
        if(_isGravity && !_isGround)
            velocity.y += mass*deltaTime;
        if (Collision) {
            Rectangle rect = new Rectangle((int) (position.x - Width/ 2),
                    (int) (position.y + velocity.y * deltaTime - Height / 2), Width, Height);
            for (Sprite sprite : World.curentWorld.sprites) {
                if (sprite == this) continue;
                Rectangle otherRect = new Rectangle((int) (sprite.position.x - sprite.Width / 2)
                        , (int) (sprite.position.y - sprite.Height / 2), sprite.Width, sprite.Height);
                if (!_isGround & rect.intersects(otherRect)) {
                    velocity.y -= velocity.y;
                    _isGround = true;
                } else {
                    _isGround = false;
                }
                rect = new Rectangle((int) (position.x - Width / 2),
                        (int) (position.y + velocity.y * deltaTime - Height / 2), Width, Height);
                if (rect.intersects(otherRect)) {
                    moveX -= moveX;
                }
            }
        }
        position.y+=velocity.y*deltaTime;
        position.x+=moveX*deltaTime;
        moveX=0;
    }

    @Override
    public void fixedupdate(float deltaTime) {
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
        if(isDebug)
        {
            g.setColor(Collision?Color.GREEN:Color.RED);
            g.drawRect(realX,realY, Width, Height);
        }
    }
    public Sprite SetSize(int width, int height){
        Width = width; Height = height;
        return this;
    }
    public Sprite SetSize(int size){
       return SetSize(size,size);
    }
    public Sprite SetColor(Color color){
        DefaultColor = color;
        image = null;
        return this;
    }
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
