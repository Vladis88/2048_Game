package com.epam.menu.game;

import com.epam.menu.Menu;

import javax.swing.JFrame;

public class StartMain {

    public static void main(String[] args) {

        //Interface
        JFrame menu = new Menu().BuildMenu();
        menu.setVisible(true);
    }
}

