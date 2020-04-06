package com.epam.menu.game.service;

import com.epam.menu.game.service.Models.KeyEventResolvedModel;
import com.epam.menu.game.GamePanel;
import com.epam.menu.game.MyTile;
import java.awt.event.KeyEvent;


public class KeyEventResolver implements KeyEventResolvable {

    private MyTile tile;
    private int col;
    private int row;
    private int event;

    private int dist;
    private boolean horizontal = false;
    private boolean vertical = false;

    public KeyEventResolver(MyTile tile, int col, int row, int event) {
        this.tile = tile;
        this.col = col;
        this.row = row;
        this.event = event;
    }

    @Override
    public KeyEventResolvedModel resolve() {
        KeyEventResolvedModel model = new KeyEventResolvedModel();
        model.setRow(this.row);
        model.setCol(this.col);
        switch (this.event) {
            case KeyEvent.VK_LEFT:
                horizontal = true;
                dist = 1;
                model.setCondition(this.col == 0);
                this.tile.setX(GamePanel.getTileX(0));//надо передавать не 0(для того, чтобы плитки не залазили друг на друга)
                model.setTile(this.tile);
                model.setCol(0);//надо передавать не 0(для того, чтобы плитки не залазили друг на друга)
                break;
            case KeyEvent.VK_RIGHT:
                horizontal = true;
                dist = -1;
                model.setCondition(this.col == GamePanel.COLS - 1);
                this.tile.setX(GamePanel.getTileX(GamePanel.COLS - 1));
                model.setTile(this.tile);
                model.setCol(GamePanel.COLS - 1);
                break;
            case KeyEvent.VK_UP:
                vertical = true;
                dist = -1;
                model.setCondition(this.row == 0);
                this.tile.setY(GamePanel.getTileY(0 ));
                model.setTile(this.tile);
                model.setRow(0);
                break;
            case KeyEvent.VK_DOWN:
                vertical = true;
                dist = 1;
                model.setCondition(this.row == GamePanel.ROWS - 1);
                this.tile.setY(GamePanel.getTileY(GamePanel.ROWS - 1));
                model.setTile(this.tile);
                model.setRow(GamePanel.ROWS - 1);
                break;
        }
        return model;
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

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
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
}
