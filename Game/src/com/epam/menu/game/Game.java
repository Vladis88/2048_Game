package com.epam.menu.game;

import com.epam.menu.Menu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements Runnable {
    /**
     * This class creates a game window and starts it.
     */
    private JButton buttonBackMenu;
    private JButton buttonBackOne;
    private JButton buttonAgain;
    private JFrame window;
    public GamePanel game;
    private boolean running;
    private Thread gameThread;

    public Game() {
        ImageIcon icon = new ImageIcon("res/2048_ico.png");
        window = new JFrame();
        game = new GamePanel();
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
        window.add(game);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.buttonBackMenu = new JButton();
        this.buttonBackOne = new JButton();
        this.buttonAgain = new JButton();
        drawButton();
    }

    public void update() {
        game.update();
    }

    @Override
    public void run() {
        update();
    }

    public synchronized void start() {
        if(running) return;
        running = true;
        gameThread = new Thread(this,"game");
        gameThread.start();
    }

    public synchronized void stop(){
        if(!running) return;
        running = false;
        window.dispose();
    }

    public void drawButton() {
        ImageIcon image2048 = new ImageIcon("res/2048_image.png");
        JLabel label = new JLabel(image2048);
        label.setBounds(10, 40, 220, 75);
        window.add(label);

        //Button "back to menu"
        buttonBackMenu.setBounds(10, 195, 72, 72);
        buttonBackMenu.setBackground(new Color(0xEFEFEF));
        buttonBackMenu.setBorderPainted(false);
        ImageIcon iconMenu = new ImageIcon("res/backMenu3.png");
        buttonBackMenu.setIcon(iconMenu);
        window.setLayout(null);
        window.add(buttonBackMenu);

        //Button "back one step"
        buttonBackOne.setBounds(385, 200, 64, 64);
        buttonBackOne.setBackground(new Color(0xEFEFEF));
        buttonBackOne.setBorderPainted(false);
        ImageIcon iconBack = new ImageIcon("res/backOne.png");
        buttonBackOne.setIcon(iconBack);
        window.setLayout(null);
        window.add(buttonBackOne);
        buttonAgain.setBounds(460, 200, 64, 64);
        buttonAgain.setBackground(new Color(0xEFEFEF));
        buttonAgain.setBorderPainted(false);
        buttonBackMenu.setIcon(iconMenu);

        //Button "game again"
        ImageIcon iconAgain = new ImageIcon("res/again.png");
        buttonAgain.setIcon(iconAgain);
        window.setLayout(null);
        window.add(buttonAgain);

        //button back to menu
        buttonBackMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to menu");
                stop();

                new Menu();
            }
        });
        buttonAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Again");
            }
        });
        buttonBackOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BackOne");
            }
        });
    }
}
