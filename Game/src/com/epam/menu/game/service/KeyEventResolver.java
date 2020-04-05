package com.epam.menu.game.service;

import com.epam.menu.game.Models.KeyEventResolvedModel;
import com.epam.menu.game.GamePanel;
import com.epam.menu.game.MyTile;
import java.awt.event.KeyEvent;


public class KeyEventResolver implements  KeyEventResolverInterface {

    private MyTile tile;
    private int col;
    private int row;
    private int event;

    public KeyEventResolver(MyTile tile, int col, int row, int event) {
        this.tile = tile;
        this.col = col;
        this.row = row;
        this.event = event;
    }
    private int checkPosition(int col, int row){
        int resultCol = col;
        int resultRow = row;
        if(col == 0){

        }

        return resultCol;
    }
    @Override
    public KeyEventResolvedModel resolve() {
        KeyEventResolvedModel model = new KeyEventResolvedModel();
        model.setRow(this.row);
        model.setCol(this.col);
        switch (this.event) {
            case KeyEvent.VK_LEFT:
                model.setCondition(this.col == 0);
                this.tile.setX(GamePanel.getTileX(0));//надо передавать не 0(для того, чтобы плитки не залазили друг на друга)
                model.setTile(this.tile);
                model.setCol(0);//надо передавать не 0(для того, чтобы плитки не залазили друг на друга)
                break;
            case KeyEvent.VK_RIGHT:
                model.setCondition(this.col == GamePanel.COLS - 1);
                this.tile.setX(GamePanel.getTileX(GamePanel.COLS - 1));
                model.setTile(this.tile);
                model.setCol(GamePanel.COLS - 1);
                break;
            case KeyEvent.VK_UP:
                model.setCondition(this.row == 0);
                this.tile.setY(GamePanel.getTileY(0 ));
                model.setTile(this.tile);
                model.setRow(0);
                break;
            case KeyEvent.VK_DOWN:
                model.setCondition(this.row == GamePanel.ROWS - 1);
                this.tile.setY(GamePanel.getTileY(GamePanel.ROWS - 1));
                model.setTile(this.tile);
                model.setRow(GamePanel.ROWS - 1);
                break;
        }
        return model;
    }
}
