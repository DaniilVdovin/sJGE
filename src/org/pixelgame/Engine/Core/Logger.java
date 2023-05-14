package org.pixelgame.Engine.Core;

import java.awt.*;
import java.util.Date;

public class Logger{
    private final StringBuilder log;
    private final Vector2Int pos;
    public Logger(Vector2Int pos){
        this.pos = pos;
        this.log = new StringBuilder();
    }
    public void Log(String tag,String message){
        log.append("[").append(new Date().getTime()).append("]--[").append(tag).append("]:  ").append(message).append("\n");
    }
    public void render(Graphics g){
        g.setColor(Color.GREEN);
        g.drawString(log.toString(), pos.x, pos.y);
    }
}
