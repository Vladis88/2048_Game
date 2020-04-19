package com.epam.menu.game.service;

import com.epam.menu.game.MyTile;

import java.awt.event.KeyEvent;


public class KeyEventResolver implements KeyEventResolvable {

    private MyTile tile;
    private MyTile[][] tileBoard;
    private int col;
    private int row;
    private int event;
    private KeyEventResolvedModel model;
    private GoToWhere goTo;

    private int dist;
    private boolean horizontal = false;
    private boolean vertical = false;

    public KeyEventResolver(MyTile[][] tileBoard, MyTile tile, int col, int row, int event) {
        this.tileBoard = tileBoard;
        this.tile = tile;
        this.col = col;
        this.row = row;
        this.event = event;
        model = new KeyEventResolvedModel();
        model.setRow(this.row);
        model.setCol(this.col);
        goTo = new GoToWhere(tileBoard, tile, col, row, model);
    }

    @Override
    public KeyEventResolvedModel resolve() {
        switch (this.event) {
            case KeyEvent.VK_LEFT:
                model = goTo.goToLeft();
                break;
            case KeyEvent.VK_RIGHT:
                model = goTo.goToRight();
                break;
            case KeyEvent.VK_UP:
                model = goTo.goToUp();
                break;
            case KeyEvent.VK_DOWN:
                model = goTo.goToDown();
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
