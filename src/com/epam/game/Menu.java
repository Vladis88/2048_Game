package com.epam.game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public static final int WIDTH = 550;
    public static final int HEIGHT = 850;
    private JFrame window;
    private JButton buttonPlay;
    private JButton buttonExit;

    public Menu() {
        window = new JFrame("MENU");
        ImageIcon icon = new ImageIcon("res/backMenu2.png");
        window.setIconImage(icon.getImage());
        buttonPlay = new JButton();
        buttonExit = new JButton();
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.pack();
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        drawMenu();
        window.setVisible(true);

        buttonCall();
    }

    public void newWindow() {
        ImageIcon icon = new ImageIcon("res/2048_ico.png");
        JFrame newWindow = new JFrame("2048");
        GamePanel game = new GamePanel(newWindow);
        newWindow.setIconImage(icon.getImage());
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        newWindow.add(game);
        newWindow.pack();
        newWindow.setResizable(false);
        newWindow.setLocationRelativeTo(null);
        newWindow.setVisible(true);
    }

    public void buttonCall() {
        //button play
        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                newWindow();
            }
        });

        //button exit
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
                System.exit(0);
            }
        });
    }

    public void drawMenu() {
        ImageIcon mainImage = new ImageIcon("res/2048_ico_Main2.png");
        JLabel label = new JLabel(mainImage);
        label.setBounds(70,50,400,383);
        window.add(label);

        buttonPlay.setBounds(50, 500, 450, 88);
        ImageIcon iconPlay = new ImageIcon("res/Play.png");
        buttonPlay.setIcon(iconPlay);
        window.setLayout(null);

        buttonExit.setBounds(50, 650, 450, 88);
        ImageIcon iconExit = new ImageIcon("res/Exit.png");
        buttonExit.setIcon(iconExit);
        window.setLayout(null);

        window.add(buttonPlay);
        window.add(buttonExit);
    }
}
