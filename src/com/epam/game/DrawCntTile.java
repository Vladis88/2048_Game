package com.epam.game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class DrawCntTile {
    private DrawCntTile() {}

    public static int getWidth(String str, Font f, Graphics2D g) {
        g.setFont(f);
        Rectangle2D bounds = g.getFontMetrics().getStringBounds(str, g);
        return (int) bounds.getWidth();
    }
    public static int getHeight(String str, Font f, Graphics2D g) {
        g.setFont(f);
        if(str.length() == 0) return 0;
        TextLayout tl = new TextLayout(str, f, g.getFontRenderContext());
        return (int) tl.getBounds().getHeight();
    }
}
