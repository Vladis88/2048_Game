package com.epam.game2048.menu;

import javax.swing.JFrame;

public class StartMain {

    public static void main(String[] args) {
        //Interface
        JFrame menu = new Menu().BuildMenu();
        menu.setVisible(true);
    }
}

