package com.epam.game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel {
    //Field
    public static final int ROWS = 4;
    public static final int COLS = 4;
    public static final int POSITION_X = 50;
    public static final int POSITION_Y = 325;
    private static int SPACING = 10;
    public static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * MyTile.WIDTH;     //440
    public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * MyTile.HEIGHT;  //440
    private static final int startTail = 2;
    private JButton buttonBackMenu;
    private JButton buttonBackOne;
    private JButton buttonAgain;
    private JFrame window;
    private ScoreBoard scoreBoard;
    private MyTile[][] tileBoard;

    //Construction
    public GamePanel(JFrame window) {
        tileBoard = new MyTile[ROWS][COLS];
        this.scoreBoard = new ScoreBoard(0, 0);//max value = 3932100
        this.window = window;
        this.buttonBackMenu = new JButton();
        this.buttonBackOne = new JButton();
        this.buttonAgain = new JButton();
        drawButton();
        start();
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

    private void start() {
        for (int i = 0; i < startTail; i++) {
            createRandom();
        }
    }

    private void createRandom() {
        Random random = new Random();
        boolean notValid = true;

        while (notValid) {
            int location = random.nextInt(ROWS * COLS);
            int row = location / ROWS;
            int col = location % COLS;
            MyTile current = tileBoard[row][col];
            if (current == null) {
                int value = random.nextInt(10) < 9 ? 2 : 4; //С вероятностью 90% - 2, а 10% - 4
                MyTile tile = new MyTile(value, getTileX(col), getTileY(row));
                tileBoard[row][col] = tile;
                notValid = false;
            }
        }
    }

    public int getTileX(int col) {
        return POSITION_X + SPACING + SPACING * col + MyTile.WIDTH * col;
    }

    public int getTileY(int row) {
        return POSITION_Y + SPACING + SPACING * row + MyTile.HEIGHT * row;
    }

    public void drawBoard(Graphics2D board) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = SPACING + SPACING * col + MyTile.WIDTH * col;
                int y = SPACING + SPACING * row + MyTile.HEIGHT * row;
                board.fillRoundRect(x + POSITION_X, y + POSITION_Y, MyTile.WIDTH, MyTile.HEIGHT, MyTile.ARC_WIDTH, MyTile.ARC_HEIGHT);
            }
        }
    }

    public void update(){
        checkKey();

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                MyTile current = tileBoard[row][col];
                if (current == null) continue;
                current.update();
                //reset position

                //win
            }
        }

    }

    private void checkKey() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D board = (Graphics2D) g;
        scoreBoard.drawScores(board);
        //draw boarder
        board.setColor(new Color(0xB2CAC1));
        board.fillRect(POSITION_X, POSITION_Y, BOARD_WIDTH, BOARD_HEIGHT);
        board.setColor(new Color(0xDFEBE6));
        drawBoard(board);
        //draw tiles
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                MyTile current = tileBoard[row][col];
                if (current == null) continue;
                current.render(board);
            }
        }
    }

}
