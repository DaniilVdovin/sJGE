package org.pixelgame.Engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import static org.pixelgame.Engine.graphics.Renderer.loadImage;

public class AtlasSpriteImage {
    private final String _path;
    private BufferedImage _image;
    private ArrayList<BufferedImage>  _images;
    private int _frame;
    public AtlasSpriteImage(String path){
        this._path = path;
        try {
            _image = loadImage(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedImage Get(){
        return Get(0);
    }
    public BufferedImage Get(int frame){
        return _images.get(frame);
    }
    public String GetPath(){
        return _path;
    }
}
