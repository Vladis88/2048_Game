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
    private JButton buttonAgain;
    private JFrame window;
    public GamePanel gamePanel;
    private ScoreBoard scoreBoard;
    private final ScoreManager scores = new ScoreManager(null);

    public JFrame BuildGameFrame() {
        scoreBoard = new ScoreBoard(scores.getCurrentScore(), scores.getCurrentTopScore());
        buttonBackMenu = new JButton();
        buttonAgain = new JButton();
        ImageIcon icon = new ImageIcon("res/2048_ico.png");
        window = new JFrame();
        gamePanel = new GamePanel(scoreBoard);
        window.setIconImage(icon.getImage());
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
        window.add(gamePanel);
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        drawButton();

        //button back to menu
        buttonBackMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to menu");
                window.dispose();
                JFrame backMenu = new Menu().BuildMenu();
                backMenu.setVisible(true);
                scores.setNewGame(false);
                scoreBoard.setBestRes(scores.getCurrentTopScore());
            }
        });

        //button again
        buttonAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Again");
                gamePanel.reset();
                window.dispose();
                JFrame game = new Game().BuildGameFrame();
                game.setVisible(true);
                scores.setNewGame(true);
                scoreBoard.setBestRes(scores.getCurrentTopScore());
            }
        });

        return window;
    }


    public void drawButton() {
        ImageIcon image2048 = new ImageIcon("res/2048_image.png");
        JLabel label = new JLabel(image2048);
        label.setSize(new Dimension(220, 75));
        label.setLocation(10, 60);
        window.add(label);

        //Button "game again"
        buttonAgain.setSize(new Dimension( 64, 64));
        buttonAgain.setLocation(435,220);
        buttonAgain.setBackground(new Color(0xEFEFEF));
        buttonAgain.setBorderPainted(false);
        buttonAgain.setIcon(new ImageIcon("res/again.png"));
        window.add(buttonAgain);

        //Button "back to menu"
        buttonBackMenu.setSize(new Dimension( 72, 72));
        buttonBackMenu.setLocation(35, 215);
        buttonBackMenu.setBackground(new Color(0xEFEFEF));
        buttonBackMenu.setBorderPainted(false);
        buttonBackMenu.setIcon(new ImageIcon("res/backMenu3.png"));
        window.add(buttonBackMenu);

        // draws a panel with a current score and a panel with a record
        window.add(scoreBoard);
    }
}
