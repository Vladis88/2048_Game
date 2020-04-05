package com.epam.menu.game.Models;

import com.epam.menu.game.MyTile;

public class KeyEventResolvedModel {

    private boolean condition;
    private MyTile tile;
    private int col;
    private int row;

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
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
