package com.epam.menu.game.service;

import com.epam.menu.game.MyTile;
import com.epam.menu.game.ScoreBoard;

public class KeyEventResolvedModel {

    private boolean outOfBounds;
    private boolean horizontal = false;
    private boolean vertical = false;
    private MyTile tile;
    private MyTile[][] tileBoard;
    private int col;
    private int row;
    private int scoreCount;

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
    }

    public MyTile[][] getTileBoard() {
        return tileBoard;
    }

    public void setTileBoard(MyTile[][] tileBoard) {
        this.tileBoard = tileBoard;
    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

    public void setOutOfBounds(boolean condition) {
        this.outOfBounds = condition;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public MyTile getTile() {
        return tile;
    }

    public void setTile(MyTile tile) {
        this.tile = tile;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
