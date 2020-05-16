package com.epam.game2048.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LeaderBoards {

    private static LeaderBoards lBoard;
    private final String filePath;
    private final String highScores;

    private final ArrayList<Integer> topScores;
    private final ArrayList<Integer> topTiles;

    private LeaderBoards() {
        filePath = new File("").getAbsolutePath().concat("\\INFO");
        highScores = "Scores.json";

        topScores = new ArrayList<Integer>();
        topTiles = new ArrayList<Integer>();
    }

    public static LeaderBoards getInstance() {
        if (lBoard == null) {
            lBoard = new LeaderBoards();
        }
        return lBoard;
    }

    public void addScore(int score) {
        for (int i = 0; i < topScores.size(); i++) {
            if (score >= topScores.get(i)) {
                topScores.add(i, score);
                topScores.remove(topScores.size() - 1);
                return;
            }
        }
    }

    public void addTile(int tileValue) {
        for (int i = 0; i < topTiles.size(); i++) {
            if (tileValue >= topTiles.get(i)) {
                topTiles.add(i, tileValue);
                topTiles.remove(topTiles.size() - 1);
                return;
            }
        }
    }

    public void loadScores() {
        try {
            File f = new File(filePath, highScores);
            if (!f.isFile()) {
                createSaveDate();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

            topScores.clear();
            topTiles.clear();

            String[] scores = reader.readLine().split("-");
            String[] tiles = reader.readLine().split("-");

            for (String score : scores) {
                this.topScores.add(Integer.parseInt(score));
            }

            for (String tile : tiles) {
                this.topTiles.add(Integer.parseInt(tile));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveScores() {
        FileWriter output = null;

        try {
            File f = new File(filePath, highScores);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write(topScores.get(0) + "-" + topScores.get(1) + "-" + topScores.get(2) + "-" + topScores.get(3) + "-" + topScores.get(4));
            writer.newLine();
            writer.write(topTiles.get(0) + "-" + topTiles.get(1) + "-" + topTiles.get(2) + "-" + topTiles.get(3) + "-" + topTiles.get(4));
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createSaveDate() {
        FileWriter output = null;

        try {
            File f = new File(filePath, highScores);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write("0-0-0-0-0");
            writer.newLine();
            writer.write("0-0-0-0-0");
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getHighScore() {
        return topScores.get(0);
    }

}
