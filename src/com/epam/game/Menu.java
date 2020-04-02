package com.epam.game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Menu extends JPanel {
    public static final int WIDTH = 550;
    public static final int HEIGHT = 850;
    public static final Font main = new Font("Bebas Neue Regular", Font.BOLD, 30);
    private JFrame window;
    private JButton buttonPlay;
    private JButton buttonExit;
    private GamePanel game;
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

    public Menu() {
        this.window = new JFrame();
        ImageIcon icon = new ImageIcon("res/backMenu2.png");
        this.window.setIconImage(icon.getImage());
        buttonPlay = new JButton();
        buttonExit = new JButton();
        this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.window.pack();
        this.window.setResizable(false);
        this.window.setLocationRelativeTo(null);
        drawMenu();
        this.window.setVisible(true);

        buttonCall();
    }

    public void newWindow() {
        ImageIcon icon = new ImageIcon("res/2048_ico.png");
        JFrame newWindow = new JFrame();
        game = new GamePanel(newWindow);
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
        label.setBounds(70, 50, 400, 383);
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
