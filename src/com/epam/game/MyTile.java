package com.epam.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class MyTile {
    //*
    //Size, speed and form of tile
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    public static final int MOVE_SPEED = 28;
    public static final int ARC_WIDTH = 15;
    public static final int ARC_HEIGHT = 15;
    //*
    //Color, digit and position
    private int value;
    private Color background;
    private Color text;
    private int x, y;

    public MyTile(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {
        if (value == 2) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 4) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 8) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 16) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 32) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 64) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 128) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 256) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 512) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 1024) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else if (value == 2048) {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        } else {
            background = new Color(0xffffff);
            text = new Color(0x00000);
        }
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(background);
        g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);

        g.setColor(text);
    }

    public void update() {

    }

    public int getValue() {
        return value;
    }
}
