package com.epam.game2048.player;

import com.epam.game2048.game.GamePanel;

public class RunPlayer extends Thread {

    private final GamePanel gamePanel;
    private final SaveProcess saveProcessGame;
    public boolean running;

    public RunPlayer(GamePanel gBoard, SaveProcess ProcessGame) {
        this.gamePanel = gBoard;
        saveProcessGame = ProcessGame;
        running = true;
    }

    @Override
    public void run() {
        saveProcessGame.loadNumberOfProcess();
        int countOfProcess = saveProcessGame.getNumberOfProcess();
        int tmpStr = 1;
        while (countOfProcess > 0) {
            System.out.println("Run..");
            if(!running) break;
            zeroBoard();
            saveProcessGame.loadProcess(tmpStr);
            gamePanel.scoreBoard.setCurrentRes(saveProcessGame.getScore());
            gamePanel.scoreBoard.setBestRes(saveProcessGame.getTopScore());
            for (int i = 0; i < saveProcessGame.getBoard().length; i++) {
                if (saveProcessGame.getBoard()[i] == 0) continue;
                gamePanel.spawn(i / GamePanel.ROWS, i % GamePanel.COLS, saveProcessGame.getBoard()[i]);
            }
            countOfProcess--;
            tmpStr++;
            gamePanel.repaint();
            gamePanel.scoreBoard.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void zeroBoard() {
        for (int row = 0; row < GamePanel.ROWS; row++) {
            for (int col = 0; col < GamePanel.COLS; col++) {
                gamePanel.tileBoard[row][col] = null;
            }
        }
    }

}
