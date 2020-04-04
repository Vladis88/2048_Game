package com.epam.game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game implements KeyListener, Runnable {
    public GamePanel game;

    public Game() {
        ImageIcon icon = new ImageIcon("res/2048_ico.png");
        JFrame window = new JFrame();
        game = new GamePanel(window);
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
        window.add(game);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.addKeyListener(this);
    }

    public void update() {
        game.update();
    }

    public void draw() {
        game.repaint();
    }

    @Override
    public void run() {

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
