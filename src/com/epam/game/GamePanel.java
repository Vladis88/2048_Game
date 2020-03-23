package com.epam.game;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    //Field
    public static final int ROWS = 4;
    public static final int COLS = 4;
    public static final int POSITION_X = 50;
    public static final int POSITION_Y = 325;
    private static int SPACING = 8;
    public static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * MyTile.WIDTH;     //440
    public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * MyTile.HEIGHT;  //440
    private JButton buttonBackMenu;
    private JButton buttonBackOne;
    private JButton buttonAgain;
    private boolean dead;
    private boolean won;

    private long lastTime;
    private long delta;



    private JFrame window;

    //Construction
    public GamePanel(JFrame window) {
        this.window = window;
        this.buttonBackMenu = new JButton();
        this.buttonBackOne = new JButton();
        this.buttonAgain = new JButton();
        drawButton();
    }

    public void drawButton() {
        ImageIcon image2048 = new ImageIcon("res/2048_image.png");
        JLabel label = new JLabel(image2048);
        label.setBounds(10, 40, 220, 75);
        add(label);

        //Button "back to menu"
        buttonBackMenu.setBounds(10, 195, 72, 72);
        buttonBackMenu.setBackground(new Color(0xEFEFEF));
        buttonBackMenu.setBorderPainted(false);
        ImageIcon iconMenu = new ImageIcon("res/backMenu3.png");
        buttonBackMenu.setIcon(iconMenu);
        setLayout(null);
        add(buttonBackMenu);

        //Button "back one step"
        buttonBackOne.setBounds(385, 200, 64, 64);
        buttonBackOne.setBackground(new Color(0xEFEFEF));
        buttonBackOne.setBorderPainted(false);
        ImageIcon iconBack = new ImageIcon("res/backOne.png");
        buttonBackOne.setIcon(iconBack);
        setLayout(null);
        add(buttonBackOne);
        buttonAgain.setBounds(460, 200, 64, 64);
        buttonAgain.setBackground(new Color(0xEFEFEF));
        buttonAgain.setBorderPainted(false);
        buttonBackMenu.setIcon(iconMenu);

        //Button "game again"
        ImageIcon iconAgain = new ImageIcon("res/again.png");
        buttonAgain.setIcon(iconAgain);
        setLayout(null);
        add(buttonAgain);

        //button back to menu
        buttonBackMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Back to menu");
                window.dispose();

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

    @Override
    public void paintComponent(Graphics board) {
        //draw boarder
        board.setColor(new Color(0xB2CAC1));
        board.fillRect(POSITION_X, POSITION_Y, BOARD_WIDTH, BOARD_HEIGHT);
        board.setColor(new Color(0xDFEBE6));
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int x = SPACING + SPACING * j + MyTile.WIDTH * j;
                int y = SPACING + SPACING * i + MyTile.HEIGHT * i;
                board.fillRoundRect(x + POSITION_X, y + POSITION_Y, MyTile.WIDTH, MyTile.HEIGHT, MyTile.ARC_WIDTH, MyTile.ARC_HEIGHT);
            }
        }
    }
}