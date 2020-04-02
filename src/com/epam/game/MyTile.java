package com.epam.game;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MyTile {
    //*
    //Size, speed and form of tile
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    public static final int MOVE_SPEED = 20;
    public static final int ARC_WIDTH = 15;
    public static final int ARC_HEIGHT = 15;
    //*
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
            background = new Color(0xffffff);
            text = new Color(0x373737);
        } else if (value == 4) {
            background = new Color(0xF3FFF4);
            text = new Color(0x373737);
        } else if (value == 8) {
            background = new Color(0xffffff);
            text = new Color(0x373737);
        } else if (value == 16) {
            background = new Color(0xffffff);
            text = new Color(0x373737);
        } else if (value == 32) {
            background = new Color(0xffffff);
            text = new Color(0x373737);
        } else if (value == 64) {
            background = new Color(0xffffff);
            text = new Color(0x373737);
        } else if (value == 128) {
            background = new Color(0xffffff);
            text = new Color(0x373737);
        } else if (value == 256) {
            background = new Color(0xffffff);
            text = new Color(0x373737);
        } else if (value == 512) {
            background = new Color(0xffffff);
            text = new Color(0x373737);
        } else if (value == 1024) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 2048) {
            background = new Color(0xffffff);
            text = new Color(0x373737);
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

        int drawX = WIDTH / 2 - DrawCntTile.getWidth("" + value, font, g) / 2;
        int drawY = HEIGHT / 2 + DrawCntTile.getHeight("" + value, font, g) / 2;

        g.drawString("" + value, drawX, drawY);
        g.dispose();
    }

    public void update() {

    }

    public void render(Graphics2D g) {
        g.drawImage(tileImg, x, y, null);
    }

    public int getValue() {
        return value;
    }
}
