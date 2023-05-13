package org.pixelgame.Engine.object;

import org.pixelgame.Engine.Core.Vector2;
import org.pixelgame.Engine.graphics.Renderer;
import org.pixelgame.Engine.physics.Physics;
import org.pixelgame.Engine.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite extends Physics {
    public int id = 0;
    public Vector2 position = Vector2.Zero();
    public float moveX = 0;
    public int Width = 0,Height = 0;
    public BufferedImage image = null;
    public Color DefaultColor = Color.GREEN;
    public Sprite(int id,float posX,float posY){
        this.id = id;
        this.position.x = posX;
        this.position.y = posY;
    }
    public Sprite(int id,Vector2 pos){
        this.id = id;
        this.position = pos;
    }
    public void SetGravity(boolean value){
        _isGravity = value;
    }
    public void update (float deltaTime){
        if(_isGravity && !_isGround)
            velocity.y += mass*deltaTime;
        if (Collision) {
            for (Sprite sprite : World.curentWorld.sprites) {
                if (sprite == this) continue;
                Rectangle rect = new Rectangle((int) (position.x - Width/ 2),
                        (int) (position.y + velocity.y * deltaTime - Height / 2), Width, Height);

                Rectangle otherRect = new Rectangle((int) (sprite.position.x - sprite.Width / 2)
                        , (int) (sprite.position.y - sprite.Height / 2), sprite.Width, sprite.Height);

                if (rect.intersects(otherRect)) {
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
    public void render(Graphics g){
        if(image == null){
            g.setColor(DefaultColor);
            g.fillRect((int)position.x,(int)position.y, Width, Height);
        }else{
            int realX = (int) position.x - Width/2;
            int realY = (int) position.y - Height/2;
            g.drawImage(image,realX,realY,Width,Height,null);
        }
    }
    public void SetSize(int width, int height){
        Width = width; Height = height;
        image = null;
    }
    public void SetSize(int size){
        SetSize(size,size);
    }
    public void SetColor(Color color){
        DefaultColor = color;
    }
    public void SetImage(String path){
        try {
            image = Renderer.loadImage(path);
            Height =  image.getHeight();
            Width = image.getWidth();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
