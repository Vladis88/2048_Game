package com.epam.game2048.player;

import com.epam.game2048.game.GamePanel;
import com.epam.game2048.game.MyTile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class SaveProcess {

    //Current scores
    private int score;
    private int topScore;
    private final int[] board = new int[GamePanel.ROWS * GamePanel.COLS];

    //File
    private final String filePath;
    private final String temp = "Process.json";
    private final String tempNumberProcess = "NumberOfProcess.json";

    private final GamePanel gBoard;
    private int numberOfProcess = 0;

    public SaveProcess(GamePanel gBoard) {
        this.gBoard = gBoard;
        filePath = new File("").getAbsolutePath().concat("\\INFO\\Player");
    }

    public void createFile() {
        FileWriter output = null;

        try {
            File f = new File(filePath, temp);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write("" + 0);//score
            writer.newLine();
            writer.write("" + 0);//BestScore
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
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        numberOfProcess = 1;
    }

    public void saveAddProcess() {
        FileWriter output = null;

        try {
            File f = new File(filePath, temp);

            if(!f.isFile()){
                createFile();
            }

            output = new FileWriter(f, true);
            BufferedWriter writer = new BufferedWriter(output);
            writer.append(String.valueOf(score));
            writer.newLine();
            writer.append(String.valueOf(topScore));
            writer.newLine();

            for (int row = 0; row < GamePanel.ROWS; row++) {
                for (int col = 0; col < GamePanel.COLS; col++) {
                    int location = row * GamePanel.COLS + col;
                    MyTile tile = gBoard.getTileBoard()[row][col];
                    this.board[location] = tile != null ? tile.getValue() : 0;
                    if (row == GamePanel.ROWS - 1 && col == GamePanel.COLS - 1) {
                        writer.write("" + board[location]);
                    } else {
                        writer.write(board[location] + "-");
                    }
                }
            }
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        numberOfProcess++;
    }

    public void saveNumberOfProcess() {
        FileWriter output = null;
        try {
            File f = new File(filePath, tempNumberProcess);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write("" + numberOfProcess);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadNumberOfProcess() {
        //System.out.println(Thread.currentThread().getName());
        try {
            File f = new File(filePath, tempNumberProcess);
            if(!f.isFile())return;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            numberOfProcess = Integer.parseInt(reader.readLine());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadProcess(int numStr) {
        //System.out.println(Thread.currentThread().getName());
        try {
            File f = new File(filePath, temp);

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            for(int n = 1; n <= numStr; n++){
                if(n == numStr){
                    score = Integer.parseInt(reader.readLine());
                    topScore = Integer.parseInt(reader.readLine());
                    String[] board = reader.readLine().split("-");
                    for (int i = 0; i < board.length; i++) {
                        this.board[i] = Integer.parseInt(board[i]);
                    }
                } else {
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTopScore() {
        return topScore;
    }


    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }


    public int getNumberOfProcess() {
        return numberOfProcess;
    }

    public int[] getBoard() {
        return board;
    }
}
