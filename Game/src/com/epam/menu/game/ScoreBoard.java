package com.epam.menu.game;

import com.epam.menu.Menu;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ScoreBoard extends JPanel {
    /**
     * This class draws a panel with a current score and a panel with a record (maximum score)
     */

    private static final int WIDTH = 90;
    private static final int WIDTH_NEXT = 145;
    private static final int HEIGHT = 70;
    private static final int ARC_WIDTH = 15;
    private static final int ARC_HEIGHT = 15;
    private Font font;
    private int bestRes;
    private int currentRes;

    public ScoreBoard(int bestRes, int currentRes) {
        this.bestRes = bestRes;
        this.currentRes = currentRes;
        font = Menu.main.deriveFont(20f);
        drawScores();
    }


    protected void drawScores() {
        Graphics2D board = (Graphics2D) getGraphics();
        //currentScoreBoard
        board.setColor(new Color(0x45CC5F));
        board.fillRoundRect(270, 40, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        board.setFont(new Font("Arial", Font.BOLD, 22));
        board.setColor(Color.WHITE);
        board.drawString("SCORE", 275, 65);
        board.setColor(Color.BLACK);
        board.setFont(font);
        board.drawString("" + currentRes, 275, 95);
        //maxScoreBoard
        board.setColor(new Color(0x45CC5F));
        board.fillRoundRect(380, 40, WIDTH_NEXT, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        board.setFont(new Font("Arial", Font.BOLD, 22));
        board.setColor(Color.WHITE);
        board.drawString("BEST", 425, 65);
        board.setColor(Color.BLACK);
        board.setFont(font);
        board.drawString("" + bestRes, 383, 95);
        board.dispose();
    }

}
