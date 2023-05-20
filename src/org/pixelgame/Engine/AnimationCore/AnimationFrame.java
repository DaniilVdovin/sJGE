package org.pixelgame.Engine.AnimationCore;

import org.pixelgame.Engine.Core.Vector2;

import java.awt.image.BufferedImage;

public class AnimationFrame {
    public BufferedImage ImageFrame;
    public Vector2<Integer> point;
    public Vector2<Integer> Size;
    public int Frame;

    public AnimationFrame(int i, BufferedImage finalImage, Vector2<Integer> integerVector2, Vector2<Integer> frameSize) {
        Frame = i;
        ImageFrame = finalImage;
        point = integerVector2;
        Size = frameSize;
    }
}
