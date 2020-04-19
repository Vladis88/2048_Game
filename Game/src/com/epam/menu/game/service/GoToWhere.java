package com.epam.menu.game.service;

import com.epam.menu.game.GamePanel;
import com.epam.menu.game.MyTile;
import com.epam.menu.game.ScoreBoard;

public class GoToWhere {

    private final MyTile tile;
    private MyTile[][] tileBoard;
    private final KeyEventResolvedModel model;
    private final int col;
    private final int row;
    private int dist;

    public GoToWhere(MyTile[][] tileBoard, MyTile tile, int col, int row, KeyEventResolvedModel model) {
        this.tile = tile;
        this.tileBoard = tileBoard;
        this.col = col;
        this.row = row;
        this.model = model;
    }

    public KeyEventResolvedModel goToLeft() {
        dist = 1;
        model.setHorizontal(true);
        model.setOutOfBounds(col < 0);
        tile.setX(GamePanel.getTileX(0));
        model.setTile(this.tile);
        model.setCol(0);
        return model;
    }

    public KeyEventResolvedModel goToRight() {
        dist = -1;
        model.setHorizontal(true);
        model.setOutOfBounds(col > GamePanel.COLS - 1);
        tile.setX(GamePanel.getTileX(GamePanel.COLS - 1));
        model.setTile(this.tile);
        model.setCol(GamePanel.COLS - 1);
        return model;
    }

    public KeyEventResolvedModel goToUp() {
        dist = -1;
        model.setVertical(true);
        model.setOutOfBounds(row < 0);
        tile.setY(GamePanel.getTileY(0));
        model.setTile(this.tile);
        model.setRow(0);
        return model;
    }

    public KeyEventResolvedModel goToDown() {
        dist = 1;
        model.setVertical(true);
        model.setOutOfBounds(row > GamePanel.ROWS - 1);
        tile.setY(GamePanel.getTileY(GamePanel.ROWS - 1));
        model.setTile(this.tile);
        model.setRow(GamePanel.ROWS - 1);
        return model;
    }

    public MyTile[][] checkMoving() {
        int countScore = 0;
        if (model.isOutOfBounds()) return tileBoard;
        tileBoard[row][col] = null;
        if (tileBoard[model.getRow()][model.getCol()] != null) {
            MyTile existTile = tileBoard[model.getRow()][model.getCol()];
            if (existTile.getValue() == model.getTile().getValue()) {
                existTile.setValue(existTile.getValue() + model.getTile().getValue());
                countScore += model.getScoreCount() + existTile.getValue();
                model.setScoreCount(countScore);
                model.setTile(existTile);
            }
        }

        return tileBoard;
    }

}
