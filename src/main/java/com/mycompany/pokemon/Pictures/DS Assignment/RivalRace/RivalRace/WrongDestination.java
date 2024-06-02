/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon1;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author ljmwa
 */
public class WrongDestination {
    
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    
WrongDestination() {
        ImageIcon congratsPage = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\Pokemon1\\src\\main\\java\\com\\mycompany\\pokemon1\\picture\\GameOver !!.png");
        Image congratsImage = congratsPage.getImage();
        Image resizedCongratsImage = congratsImage.getScaledInstance(600, 400, Image.SCALE_SMOOTH);  // Resize to match the frame size
        ImageIcon resizedCongratsIcon = new ImageIcon(resizedCongratsImage);

        label.setIcon(resizedCongratsIcon);
        label.setBounds(0, 0, 550, 350); // Set bounds to cover the entire frame
        frame.add(label);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(null); // Keep using null layout
        frame.setVisible(true);
    }
}

