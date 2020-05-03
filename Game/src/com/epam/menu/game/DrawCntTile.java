package com.epam.menu.game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class DrawCntTile {
    /**
     * This class is for calculating the position in the center of the tile.
     */
    private DrawCntTile() {}
    public static int getWidth(String str, Font f, Graphics2D g) {
        g.setFont(f);
        Rectangle2D bounds = g.getFontMetrics().getStringBounds(str, g);//получаем размер цифры (ширина), которая будет на плитке
        return (int) bounds.getWidth();
    }
    public static int getHeight(String str, Font f, Graphics2D g) {
        g.setFont(f);
        if(str.length() == 0) return 0;
        TextLayout tl = new TextLayout(str, f, g.getFontRenderContext()); //получаем размер цифры (высота), которая будет на плитке
        return (int) tl.getBounds().getHeight();
    }
}
