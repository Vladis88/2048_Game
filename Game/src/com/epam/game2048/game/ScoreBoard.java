package com.epam.game2048.game;

import com.epam.game2048.menu.Menu;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreBoard extends JPanel {
    /**
     * This class draws a panel with a current score and a panel with a record (maximum score)
     */
    public static final int WIDTH = 100;
    public static final int HEIGHT = 70;
    public static final int ARC_WIDTH = 10;
    public static final int ARC_HEIGHT = 10;
    private final Font font;
    private int countFromWon = 0;
    private boolean gameOver = false;
    private boolean won = false;
    private int currentRes;
    private int bestRes;


    public ScoreBoard(int currentRes, int bestRes) {
        this.currentRes = currentRes;
        this.bestRes = bestRes;
        font = Menu.main.deriveFont(20f);
        this.repaint();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics board) {
        //currentScoreBoard
        board.setColor(new Color(0xEFEFEF));
        board.fillRect(0, 0, 60, 60);
        board.setColor(new Color(0x45CC5F));
        board.fillRoundRect(300, 60, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        board.setFont(new Font("Arial", Font.BOLD, 22));
        board.setColor(Color.WHITE);
        board.drawString("SCORE", 310, 85);
        board.setColor(Color.BLACK);
        board.setFont(font);
        board.drawString("" + currentRes, 312, 115);
        //bestScoreBoard
        board.setColor(new Color(0xEFEFEF));
        board.fillRoundRect(0, 0, 65, 65, 5, 5);
        board.setColor(new Color(0x45CC5F));
        board.fillRoundRect(410, 60, WIDTH + 20, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        board.setFont(new Font("Arial", Font.BOLD, 22));
        board.setColor(Color.WHITE);
        board.drawString("BEST", 440, 85);
        board.setColor(Color.BLACK);
        board.setFont(font);
        board.drawString("" + bestRes, 432, 115);

        if (gameOver) {
            board.setColor(Color.red);
            board.setFont(Menu.main.deriveFont(70f));
            board.drawString("Game Over!", 75, 200);
            board.setFont(Menu.main.deriveFont(20f));
            board.drawString("Press one of these buttons", 143, 260);
        }
        if(won){
            board.setColor(Color.red);
            board.setFont(Menu.main.deriveFont(40f));
            board.drawString("CONGRATULATIONS!", 65, 200);
            if(countFromWon++ > 2){
                board.setColor(new Color(0xEFEFEF));
                board.fillRect(0, 160, 550, 50);
            }
        }
    }

    public void setBestRes(int bestRes) {
        this.bestRes = bestRes;
    }

    public void setCurrentRes(int currentRes) {
        this.currentRes = currentRes;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setWon(boolean won) {
        this.won = won;
    }
}
