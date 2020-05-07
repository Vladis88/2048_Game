package com.epam.menu.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class ScoreManager {

    //Current scores
    private int currentScore = 0;
    private int currentTopScore = 0;
    private final int[] board = new int[GamePanel.ROWS * GamePanel.COLS];

    //File
    private final String filePath;
    private final String temp = "TMP.json";
    private final GamePanel gBoard;

    //Boolean
    private boolean newGame;

    public ScoreManager(GamePanel gBoard) {
        this.gBoard = gBoard;
        filePath = new File("").getAbsolutePath().concat("\\INFO");
    }

    public void reset() {
        File f = new File(filePath, temp);
        if (f.isFile()) {
            f.delete();
        }
        newGame = true;
        currentScore = 0;
    }

    private void createFile() {
        FileWriter output = null;
        newGame = true;

        try {
            File f = new File(filePath, temp);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write("" + 0);   //currentScore
            writer.newLine();
            writer.write("" + 0);  //currentTopScore
            writer.newLine();

            for (int row = 0; row < GamePanel.ROWS; row++) {
                for (int col = 0; col < GamePanel.COLS; col++) {
                    if (row == GamePanel.ROWS - 1 && col == GamePanel.COLS - 1) {
                        writer.write("" + 0);
                    } else {
                        writer.write(0 + "-");
                    }
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveGame() {
        FileWriter output = null;
        if (newGame) newGame = false;

        try {
            File f = new File(filePath, temp);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write("" + currentScore);   //currentScore
            writer.newLine();
            writer.write("" + currentTopScore);  //currentTopScore
            writer.newLine();

            for (int row = 0; row < GamePanel.ROWS; row++) {
                for (int col = 0; col < GamePanel.COLS; col++) {
                    int location = row * GamePanel.COLS + col;
                    MyTile tile = gBoard.getTileBoard()[row][col];
                    this.board[location] = tile != null ? tile.getValue() : 0;

                    if(row == GamePanel.ROWS - 1 && col == GamePanel.COLS - 1){
                        writer.write("" + board[location]);
                    } else {
                        writer.write(board[location] + "-");
                    }
                }
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try{
            File f = new File(filePath, temp);

            if(!f.isFile()){
                createFile();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            currentScore = Integer.parseInt(reader.readLine());
            currentTopScore = Integer.parseInt(reader.readLine());

            String[] board = reader.readLine().split("-");
            for(int i = 0; i < board.length; i++) {
                this.board[i] = Integer.parseInt(board[i]);
            }

            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getCurrentTopScore() {
        return currentTopScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentTopScore(int currentTopScore) {
        this.currentTopScore = currentTopScore;
    }

    public int[] getBoard() {
        return board;
    }

    public boolean newGame() {
        return newGame;
    }

    public void setNewGame(boolean newGame) {
        this.newGame = newGame;
    }
}
