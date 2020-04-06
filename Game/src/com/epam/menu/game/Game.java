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

public class Game {
    /**
     * This class creates a game window and starts it.
     */
    private JButton buttonBackMenu;
    private JButton buttonBackOne;
    private JButton buttonAgain;
    private JFrame window;
    public GamePanel gamePanel;
    private ScoreBoard scoreBoard;

    public JFrame BuildGameFrame() {
        ImageIcon icon = new ImageIcon("res/2048_ico.png");
        window = new JFrame();
        gamePanel = new GamePanel();
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
        window.add(gamePanel);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        //window.setVisible(true);
        this.scoreBoard = new ScoreBoard(0, 0);
        this.buttonBackMenu = new JButton();
        this.buttonBackOne = new JButton();
        this.buttonAgain = new JButton();
        drawButton();

        //button back to menu
        buttonBackMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to menu");
                window.dispose();
                JFrame backMenu = new Menu().BuildMenu();
                backMenu.setVisible(true);
            }
        });

        //button again
        buttonAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Again");
            }
        });

        //button back one
        buttonBackOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BackOne");
            }
        });

        update();
        return window;
    }

    public void update() {
        gamePanel.update();
    }


    public void drawButton() {
        ImageIcon image2048 = new ImageIcon("res/2048_image.png");
        JLabel label = new JLabel(image2048);
        label.setBounds(10, 40, 220, 75);
        window.add(label);

        // draws a panel with a current score and a panel with a record
        scoreBoard.setLocation(270, 40);
        scoreBoard.setPreferredSize(new Dimension(ScoreBoard.WIDTH + ScoreBoard.WIDTH_NEXT, ScoreBoard.HEIGHT));
        window.add(scoreBoard);
        window.setVisible(true);

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

        //Button "game again"
        buttonAgain.setBounds(460, 200, 64, 64);
        buttonAgain.setBackground(new Color(0xEFEFEF));
        buttonAgain.setBorderPainted(false);
        buttonBackMenu.setIcon(iconMenu);
        ImageIcon iconAgain = new ImageIcon("res/again.png");
        buttonAgain.setIcon(iconAgain);
        window.setLayout(null);
        window.add(buttonAgain);
    }
}
