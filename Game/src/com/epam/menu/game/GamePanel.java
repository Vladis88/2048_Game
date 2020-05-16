package com.epam.menu.game;

import com.epam.menu.Menu;
import com.epam.menu.player.RunPlayer;
import com.epam.menu.player.SaveProcess;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    public MyTile[][] tileBoard;
    private final BufferedImage boardImg;
    public final ScoreBoard scoreBoard;
    private boolean won = false;
    private boolean dead = false;
    private int numberCheckWon = 0;
    private final boolean flag;

    private ScoreManager scores;
    private LeaderBoards lBoard;
    private final SaveProcess saveProcessGame;
    public RunPlayer player;

    //Construction
    public GamePanel(ScoreBoard score, boolean save) {
        this.flag = save;
        scoreBoard = score;
        setVisible(true);
        setFocusable(true);
        addKeyListener(this);
        boardImg = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        tileBoard = new MyTile[ROWS][COLS];

        drawBoard();
        saveProcessGame = new SaveProcess(this);
        if (!flag) {
            startPlayer();
        } else {
            lBoard = LeaderBoards.getInstance();
            lBoard.loadScores();
            scores = new ScoreManager(this);
            scores.loadGame();
            scores.setCurrentTopScore(lBoard.getHighScore());
            scoreBoard.setBestRes(scores.getCurrentTopScore());
            scoreBoard.setCurrentRes(scores.getCurrentScore());
            if (scores.newGame()) {
                start();
                scores.saveGame();
            } else {
                for (int i = 0; i < scores.getBoard().length; i++) {
                    if (scores.getBoard()[i] == 0) continue;
                    spawn(i / ROWS, i % COLS, scores.getBoard()[i]);
                }
                dead = checkDead();
                won = checkWon();
            }
            saveProcessGame.createFile();
            saveProcessGame.setScore(scores.getCurrentScore());
            saveProcessGame.setTopScore(scores.getCurrentTopScore());
            saveProcessGame.saveAddProcess();
            saveProcessGame.saveNumberOfProcess();
        }
    }

    private synchronized void startPlayer(){
        player = new RunPlayer(this, saveProcessGame);
        player.start();
    }

    public void spawn(int row, int col, int value) {
        tileBoard[row][col] = new MyTile(value, getTileX(col), getTileY(row));
    }

    public void reset() {
        tileBoard = new MyTile[ROWS][COLS];
        start();
        scores.saveGame();
        scores.reset();
        dead = false;
        won = false;
    }

    private void start() {
        if (flag) {
            for (int i = 0; i < startTail; i++) {
                createRandom();
            }
        }
    }

    private void createRandom() {
        Random random = new Random();
        int location = random.nextInt(ROWS * COLS);
        int row = location / ROWS;
        int col = location % COLS;

        MyTile current = tileBoard[row][col];
        if (current == null) {
            int value = random.nextInt(10) < 9 ? 2 : 4; //С вероятностью 90% - 2, а 10% - 4
            MyTile tile = new MyTile(value, getTileX(col), getTileY(row));
            tileBoard[row][col] = tile;
        } else if (checkCreateRandomAgain()) {
            createRandom();
        }
    }

    private boolean checkCreateRandomAgain() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (tileBoard[row][col] == null) return true;
            }
        }
        return false;
    }

    public int getTileX(int col) {
        return POSITION_X + SPACING + SPACING * col + MyTile.WIDTH * col;
    }

    public int getTileY(int row) {
        return POSITION_Y + SPACING + SPACING * row + MyTile.HEIGHT * row;
    }

    public void update() {
        if (flag) {
            scores.saveGame();
            this.repaint();
            if (scores.getCurrentScore() >= scores.getCurrentTopScore()) {
                scores.setCurrentTopScore(scores.getCurrentScore());
                scoreBoard.setBestRes(scores.getCurrentTopScore());
                lBoard.addTile(getHighestTileValue());
                lBoard.addScore(scores.getCurrentScore());
                lBoard.saveScores();
            }

            scoreBoard.repaint();

            saveProcessGame.setScore(scores.getCurrentScore());
            saveProcessGame.setTopScore(scores.getCurrentTopScore());
            saveProcessGame.saveAddProcess();
            saveProcessGame.saveNumberOfProcess();
        }
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
        } else {
            return;
        }
        tileBoard = goTo.getTileBoard();
        int score = scores.getCurrentScore() + goTo.getCountScore();
        scores.setCurrentScore(score);
        scoreBoard.setCurrentRes(score);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                MyTile currentTile = tileBoard[row][col];
                if (currentTile == null) continue;
                resetPosition(currentTile, row, col);
            }
        }
        if (checkWon()) {
            scoreBoard.setWon(true);
            System.out.println("CONGRATULATIONS!!!");
            scoreBoard.setGameOver(false);
        }

        if (!checkDead()) {
            createRandom();
        } else {
            scoreBoard.setGameOver(true);
            System.out.println("GAME OVER!!!");
            scoreBoard.setWon(false);
        }
        update();
    }

    private void resetPosition(MyTile current, int row, int col) {
        if (current == null) return;

        int x = getTileX(col);
        int y = getTileY(row);

        current.setX(x);
        current.setY(y);
    }

    public void drawBoard() {
        //draw board
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
        //System.out.println(Thread.currentThread().getName());
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

    private boolean checkWon() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (tileBoard[row][col] == null) continue;
                if (tileBoard[row][col].getValue() == 2048 && numberCheckWon < 1) {
                    numberCheckWon++;
                    setWon(true);
                    return true;
                }
            }
        }
        setWon(false);
        return false;
    }

    private boolean checkDead() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (tileBoard[row][col] == null) continue;
                if (checkSurroundingTiles(row, col, tileBoard[row][col])) {
                    setWon(false);
                    return false;
                }
            }
        }
        setDead(true);
        return true;
    }

    private boolean checkSurroundingTiles(int row, int col, MyTile current) {
        if (row > 0) {
            MyTile check = tileBoard[row - 1][col];
            if (check == null) return true;
            if (current.getValue() == check.getValue()) return true;
        }
        if (row < ROWS - 1) {
            MyTile check = tileBoard[row + 1][col];
            if (check == null) return true;
            if (current.getValue() == check.getValue()) return true;
        }
        if (col > 0) {
            MyTile check = tileBoard[row][col - 1];
            if (check == null) return true;
            if (current.getValue() == check.getValue()) return true;
        }
        if (col < COLS - 1) {
            MyTile check = tileBoard[row][col + 1];
            if (check == null) return true;
            if (current.getValue() == check.getValue()) return true;
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (flag) {
            this.MoveTile(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public MyTile[][] getTileBoard() {
        return tileBoard;
    }

    public int getHighestTileValue() {
        int value = 2;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (tileBoard[row][col] == null) continue;
                if (tileBoard[row][col].getValue() > value) value = tileBoard[row][col].getValue();
            }
        }
        return value;
    }

    public void setDead(boolean dead) {
        if (!this.dead && dead) {
            lBoard.addTile(getHighestTileValue());
            lBoard.addScore(scores.getCurrentScore());
            lBoard.saveScores();
        }
        this.dead = dead;
    }

    public void setWon(boolean won) {
        if (!this.won && won) {
            lBoard.saveScores();
        }
        this.won = won;
    }
}