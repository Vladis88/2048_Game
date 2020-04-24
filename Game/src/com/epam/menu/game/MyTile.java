package com.epam.menu.game;

import com.epam.menu.Menu;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MyTile extends JPanel{
    /**
     * This class draws tiles.
     */

    //Size, speed and form of tile
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    public static final int MOVE_SPEED = 20;
    public static final int ARC_WIDTH = 15;
    public static final int ARC_HEIGHT = 15;
    //Color, digit and position
    private int value;
    private BufferedImage tileImg;
    private Color background;
    private Color text;
    private Font font;
    private int x;
    private int y;

    public MyTile(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        tileImg = new BufferedImage(GamePanel.BOARD_WIDTH, GamePanel.BOARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        drawTail();
    }


    public void drawTail() {
        Graphics2D g = (Graphics2D) tileImg.getGraphics();
        if (value == 2) {
            background = new Color(0xF4F5F3);
            text = new Color(0x373737);
        } else if (value == 4) {
            background = new Color(0xDDF5D8);
            text = new Color(0x373737);
        } else if (value == 8) {
            background = new Color(0x1EC062);
            text = new Color(0xffffff);
        } else if (value == 16) {
            background = new Color(0xC2E85D);
            text = new Color(0xffffff);
        } else if (value == 32) {
            background = new Color(0xEEB600);
            text = new Color(0xffffff);
        } else if (value == 64) {
            background = new Color(0xEF612F);
            text = new Color(0xffffff);
        } else if (value == 128) {
            background = new Color(0xE00325);
            text = new Color(0xffffff);
        } else if (value == 256) {
            background = new Color(0xFF50FC);
            text = new Color(0xffffff);
        } else if (value == 512) {
            background = new Color(0xD90CC9);
            text = new Color(0xffffff);
        } else if (value == 1024) {
            background = new Color(0x7B10C2);
            text = new Color(0xffffff);
        } else if (value == 2048) {
            background = new Color(0x2845A7);
            text = new Color(0xffffff);
        } else {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        }
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(background);
        g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        g.setColor(text);

        if (value <= 64) {
            font = Menu.main.deriveFont(36f);
        } else {
            font = Menu.main;
        }
        g.setFont(font);
        //высчитывание позиции ровно по центру плитки, исходя из размера цифры
        int drawX = WIDTH / 2 - DrawCntTile.getWidth("" + value, font, g) / 2;//позиция на плитки X
        int drawY = HEIGHT / 2 + DrawCntTile.getHeight("" + value, font, g) / 2;//позиция на плитки Y

        g.drawString("" + value, drawX, drawY);
        g.dispose();
    }


    public void render(Graphics g) {
        g.drawImage(tileImg, x, y, null);
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MyTile setX(int x) {
        this.x = x;
        return this;
    }

    public MyTile setY(int y) {
        this.y = y;
        return this;
    }

    public MyTile setValue(int value) {
        this.value = value;
        drawTail();
        return this;
    }

}
