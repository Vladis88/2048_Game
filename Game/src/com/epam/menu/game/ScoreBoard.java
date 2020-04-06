package com.epam.menu.game;

import com.epam.menu.Menu;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;

public class ScoreBoard extends JPanel {
    /**
     * This class draws a panel with a current score and a panel with a record (maximum score)
     */

    public static final int WIDTH = 90;
    public static final int WIDTH_NEXT = 145;
    public static final int HEIGHT = 70;
    public static final int ARC_WIDTH = 10;
    public static final int ARC_HEIGHT = 10;
    private Font font;
    private int bestRes;
    private int currentRes;

    public ScoreBoard(int bestRes, int currentRes) {
        this.bestRes = bestRes;
        this.currentRes = currentRes;
        font = Menu.main.deriveFont(20f);
        this.repaint();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics board) {
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
        board.drawString("" + bestRes, 415, 95);
        board.dispose();
    }


    public int getBestRes() {
        return bestRes;
    }

    public void setBestRes(int bestRes) {
        this.bestRes = bestRes;
    }

    public int getCurrentRes() {
        return currentRes;
    }

    public void setCurrentRes(int currentRes) {
        this.currentRes = currentRes;
    }

}
