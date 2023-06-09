package org.pixelgame.Engine.graphics;
import org.pixelgame.EditorV2.Windows.MainWindow;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.pixelgame.Engine.graphics.Renderer.loadImage;

public class SpriteImage{
    private final String _path;
    private BufferedImage _image;
    public SpriteImage(String path){
        this._path = path;
        try {
            _image = loadImage(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedImage Get(){
        return _image;
    }
    public void Change(BufferedImage source){
        _image = source;
    }
    public String GetPath(){
        return _path;
    }
}
