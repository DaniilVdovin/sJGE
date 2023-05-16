package org.pixelgame.Engine.graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteImage{
    private final String _path;
    private final BufferedImage _image;
    public SpriteImage(String path){
        this._path = path;
        try {
            _image = Renderer.loadImage(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedImage Get(){
        return _image;
    }
    public String GetPath(){
        return _path;
    }
}
