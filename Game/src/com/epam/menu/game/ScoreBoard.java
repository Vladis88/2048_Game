package com.epam.menu.game;

import com.epam.menu.Menu;

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
        board.fillRoundRect(0, 0, 65, 65, 5, 5);
        board.setColor(new Color(0x45CC5F));
        board.fillRoundRect(300, 60, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
        board.setFont(new Font("Arial", Font.BOLD, 22));
        board.setColor(Color.WHITE);
        board.drawString("SCORE", 310, 85);
        board.setColor(Color.BLACK);
        board.setFont(font);
        board.drawString("" + currentRes, 310, 115);
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
        board.drawString("" + bestRes, 425, 115);
    }

    public void setBestRes(int bestRes) {
        this.bestRes = bestRes;
    }

    public void setCurrentRes(int currentRes) {
        this.currentRes = currentRes;
    }

}
