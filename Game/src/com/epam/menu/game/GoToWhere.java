package com.epam.menu.game;

import java.awt.event.KeyEvent;

public class GoToWhere {
    private final MyTile[][] tileBoard;
    private final int event;

    private int horizontalDirection = 0;
    private int verticalDirection = 0;
    private boolean canMove = false;
    private int countScore = 0;

    public MyTile[][] getTileBoard() {
        return tileBoard;
    }

    public GoToWhere(MyTile[][] tileBoard, int event) {
        this.tileBoard = tileBoard;
        this.event = event;
    }

    private boolean move(int row, int col, int horizontalDirection, int verticalDirection, int event) {
        boolean canMove = false;
        MyTile current = tileBoard[row][col];
        if(current == null) return false;
        boolean move = true;
        int newCol = col;
        int newRow = row;
        while(move){
            newCol += horizontalDirection;
            newRow += verticalDirection;
            if(checkOutOfBounds(event, newRow,newCol)) break;
            if(tileBoard[newRow][newCol] == null){
                tileBoard[newRow][newCol] = current;
                tileBoard[newRow - verticalDirection][newCol - horizontalDirection] = null;
            } else if (tileBoard[newRow][newCol].getValue() == current.getValue()){
                tileBoard[newRow][newCol].setValue(tileBoard[newRow][newCol].getValue() + current.getValue());
                canMove = true;
                tileBoard[newRow - verticalDirection][newCol - horizontalDirection] = null;
                countScore += tileBoard[newRow][newCol].getValue();
            } else {
                move = false;
            }
        }
        return canMove;
    }

    private boolean checkOutOfBounds(int event, int row, int col) {
        if(event == KeyEvent.VK_LEFT){
            return col < 0;
        } else if(event == KeyEvent.VK_RIGHT){
            return col > GamePanel.COLS - 1;
        }  else if(event == KeyEvent.VK_UP){
            return row < 0;
        } else if(event == KeyEvent.VK_DOWN){
            return  row > GamePanel.ROWS - 1;
        }
        return  false;
    }

    public void goToLeft() {
        horizontalDirection = -1;
        for (int row = 0; row < GamePanel.ROWS; row++) {
            for (int col = 0; col < GamePanel.COLS; col++) {
                if (!canMove) {
                    canMove = move(row, col, horizontalDirection, verticalDirection, event);
                } else move(row, col, horizontalDirection, verticalDirection, event);
            }
        }
    }


    public void goToRight() {
        horizontalDirection = 1;
        for (int row = 0; row < GamePanel.ROWS; row++) {
            for (int col = GamePanel.COLS - 1; col >= 0; col--) {
                if (!canMove) {
                    canMove = move(row, col, horizontalDirection, verticalDirection, event);
                } else move(row, col, horizontalDirection, verticalDirection, event);
            }
        }
    }

    public void goToUp() {
        verticalDirection = -1;
        for (int row = 0; row < GamePanel.ROWS; row++) {
            for (int col = 0; col < GamePanel.COLS; col++) {
                if (!canMove) {
                    canMove = move(row, col, horizontalDirection, verticalDirection, event);
                } else move(row, col, horizontalDirection, verticalDirection, event);
            }
        }
    }

    public void goToDown() {
        verticalDirection = 1;
        for (int row = GamePanel.ROWS - 1; row >= 0; row--) {
            for (int col = 0; col < GamePanel.COLS; col++) {
                if (!canMove) {
                    canMove = move(row, col, horizontalDirection, verticalDirection, event);
                } else move(row, col, horizontalDirection, verticalDirection, event);
            }
        }
    }

    public int getCountScore() {
        return countScore;
    }
}
