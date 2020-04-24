package com.epam.menu.game;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {
    /**
     * This class draws a field in the game window and implements the basic logic of the game.
     */
    public static final int ROWS = 4;
    public static final int COLS = 4;
    public static final int POSITION_X = 50;
    public static final int POSITION_Y = 325;
    private static final int SPACING = 10;
    public static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * MyTile.WIDTH;     //440
    public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * MyTile.HEIGHT;  //440
    private static final int startTail = 2;
    private MyTile[][] tileBoard;
    private final BufferedImage boardImg;
    private final ScoreBoard scoreBoard;
    private int countScore = 0;
    private boolean gameOver = false;

    //Construction
    public GamePanel(ScoreBoard score) {
        scoreBoard = score;
        setVisible(true);
        setFocusable(true);
        addKeyListener(this);
        boardImg = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        tileBoard = new MyTile[ROWS][COLS];
        drawBoard();
        start();
    }

    private void start() {
        for (int i = 0; i < startTail; i++) {
            createRandom();
        }
    }

    private void createRandom() {
        Random random = new Random();
        int location = random.nextInt(ROWS * COLS);
        int row = location / ROWS;
        int col = location % COLS;
        if (tileBoard[row][col] != null) {
            this.createRandom();
        }
        MyTile current = tileBoard[row][col];
        if (current == null) {
            int value = random.nextInt(10) < 9 ? 2 : 4; //С вероятностью 90% - 2, а 10% - 4
            MyTile tile = new MyTile(value, getTileX(col), getTileY(row));
            tileBoard[row][col] = tile;
        }
    }

    public static int getTileX(int col) {
        return POSITION_X + SPACING + SPACING * col + MyTile.WIDTH * col;
    }

    public static int getTileY(int row) {
        return POSITION_Y + SPACING + SPACING * row + MyTile.HEIGHT * row;
    }

    public void update() {
        this.repaint();
        scoreBoard.repaint();
    }

    public void MoveTile(int event) {
        GoToWhere goTo = new GoToWhere(tileBoard, event);
        if (event == KeyEvent.VK_LEFT) {
            goTo.goToLeft();
        } else if (event == KeyEvent.VK_RIGHT) {
            goTo.goToRight();
        } else if (event == KeyEvent.VK_UP) {
            goTo.goToUp();
        } else if (event == KeyEvent.VK_DOWN) {
            goTo.goToDown();
        }
        tileBoard = goTo.getTileBoard();
        countScore += goTo.getCountScore();
        scoreBoard.setCurrentRes(countScore);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                MyTile currentTile = tileBoard[row][col];
                if(currentTile == null) continue;
                resetPosition(currentTile,row,col);
                update();
            }
        }
        if(checkNewTile()){
            createRandom();
        } else {
            gameOver = true;
            System.out.println("Game over");
        }
    }

    private void resetPosition(MyTile current, int row, int col) {
        if(current == null) return;

        int x = getTileX(col);
        int y = getTileY(row);

        current.setX(x);
        current.setY(y);
    }

    public void drawBoard() {
        //draw boar
        Graphics2D board = (Graphics2D) boardImg.getGraphics();
        board.setColor(new Color(0xB2CAC1));
        board.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        board.setColor(new Color(0xDFEBE6));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = SPACING + SPACING * col + MyTile.WIDTH * col;
                int y = SPACING + SPACING * row + MyTile.HEIGHT * row;
                board.fillRoundRect(x, y, MyTile.WIDTH, MyTile.HEIGHT, MyTile.ARC_WIDTH, MyTile.ARC_HEIGHT);
            }
        }
        board.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(boardImg, POSITION_X, POSITION_Y, null);
        //draw tiles
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                MyTile current = tileBoard[row][col];
                if (current == null) continue;
                current.render(g);
            }
        }
        g.dispose();
    }

    private boolean checkNewTile() {
        int number = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tileBoard[i][j] != null) {
                    number++;
                }
            }
        }
        return number < 16;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.MoveTile(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public boolean isGameOver() {
        return gameOver;
    }
}
