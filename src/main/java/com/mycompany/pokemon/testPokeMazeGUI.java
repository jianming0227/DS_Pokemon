/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

/**
 *
 * @author User
 */
import java.awt.FontFormatException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

public class testPokeMazeGUI extends javax.swing.JFrame {

    public testPokeMazeGUI() {
        Player p = new Player();
        initComponents();
    }

    private void initComponents() {
        JLabel label = new JLabel();
        MazeTile mazeTile = new MazeTile(label,' ');
        
        // Declare the components
        JButton right_button = new javax.swing.JButton();
        JButton up_button = new javax.swing.JButton();
        JButton down_button = new javax.swing.JButton();
        JButton left_button = new javax.swing.JButton();
        JLabel Pokemaze_logo = new javax.swing.JLabel();
        JLabel background = new javax.swing.JLabel();
        JLabel maze = new javax.swing.JLabel();
        JLabel jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(815, 615));
        setPreferredSize(new java.awt.Dimension(815, 615));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        right_button.setBackground(new java.awt.Color(246, 210, 3));
        right_button.setFont(new java.awt.Font("SimSun-ExtB", 1, 18));
        right_button.setForeground(new java.awt.Color(42, 90, 186));
        right_button.setText("RIGHT");
        right_button.addActionListener(e -> mazeTile.movePlayer('R'));
        getContentPane().add(right_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 490, 100, 30));

        up_button.setBackground(new java.awt.Color(246, 210, 3));
        up_button.setFont(new java.awt.Font("SimSun-ExtB", 1, 18));
        up_button.setForeground(new java.awt.Color(42, 90, 186));
        up_button.setText("UP");
        up_button.addActionListener(e -> mazeTile.movePlayer('U'));
        getContentPane().add(up_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 450, 100, 30));

        down_button.setBackground(new java.awt.Color(246, 210, 3));
        down_button.setFont(new java.awt.Font("SimSun-ExtB", 1, 18));
        down_button.setForeground(new java.awt.Color(42, 90, 186));
        down_button.setText("DOWN");
        down_button.addActionListener(e -> mazeTile.movePlayer('D'));
        getContentPane().add(down_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 530, 100, 30));

        left_button.setBackground(new java.awt.Color(246, 210, 3));
        left_button.setFont(new java.awt.Font("SimSun-ExtB", 1, 18));
        left_button.setForeground(new java.awt.Color(42, 90, 186));
        left_button.setText("LEFT");
        left_button.addActionListener(e -> mazeTile.movePlayer('L'));
        getContentPane().add(left_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 490, 100, 30));

        jLabel1.setFont(new java.awt.Font("SimSun-ExtB", 0, 12));
        jLabel1.setForeground(new java.awt.Color(255, 250, 0));
        jLabel1.setText("HINT : Unlock Maze with Your First Move as DOWN");        
        background.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\PokeMaze\\PokeMaze\\bg.png"));
        Pokemaze_logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\User\\Downloads\\pokemaze.png"));
        getContentPane().add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 52, 800, 580));
        getContentPane().add(Pokemaze_logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 570, 170));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, -1, -1));
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 580));

        setLocationRelativeTo(null);
        pack();
    }
    
    private static Stack<String> path = new Stack<>();

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testPokeMazeGUI().setVisible(true);
                
            }
        });
    }
}
