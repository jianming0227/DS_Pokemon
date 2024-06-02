/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

/**
 *
 * @author User
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author ljmwa
 */
class MyFrame extends JFrame implements KeyListener, MouseListener, ActionListener {

    JLabel overlayLabel;
    private JLabel congratsLabel;
    private JLabel backgroundLabel;
    int destinationX;
    int destinationY;
    JButton myButton = new JButton();
    static int saveNumber;

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame(saveNumber);
        myFrame.destination(-168, 265);
        myFrame.printMap();

    }

    MyFrame(int saveNumber) {
        this.saveNumber = saveNumber;
        // basic setup
        setTitle("Rival Race");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make sure exit out of the application
        setResizable(false); //cannot resize the window
        setSize(604, 658); // set the x and y dimension
        setLayout(null);
        this.addKeyListener(this);
        this.addMouseListener(this);
        setVisible(true); // make frame visible  

        getContentPane().setBackground(Color.white);

        myButton.setBounds(400, 525, 150, 40);
        myButton.setOpaque(false);
        myButton.setContentAreaFilled(false);
        myButton.setBorderPainted(false);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        add(myButton);

        //set the icon logo
        ImageIcon image = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\RivalRace\\RivalRace\\picture\\poke.jpg"); // create an ImageIcon
        setIconImage(image.getImage()); // change icon of this
    }

    public void printMap() {
        //adjust the image size

        Border border = BorderFactory.createLineBorder(Color.GREEN, 3);

        ImageIcon originalIcon = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\RivalRace\\RivalRace\\picture\\pokemapFinal.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(550, 500, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        //write text in it
        JLabel label = new JLabel();

        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\RivalRace\\RivalRace\\Abhaya_Libre,Press_Start_2P\\Press_Start_2P\\PressStart2P-Regular.ttf")).deriveFont(Font.PLAIN, 30);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        label.setIcon(resizedIcon);// show the image
        label.setText("RIVAL's RACE");
        label.setFont(customFont);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);//make it on top of the image
        label.setForeground(Color.black);
        label.setIconTextGap(7);
        label.setOpaque(true);
        label.setBounds(0, 0, 590, 620);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(border);

        add(label);
        setupCharacterOverlay();

    }

    private void setupCharacterOverlay() {
        ImageIcon overlayIcon = new ImageIcon("C:\\Users\\ljmwa\\OneDrive - Universiti Malaya\\OneDrive\\Documents\\NetBeansProjects\\PokemonYF\\POKEMON\\src\\main\\java\\com\\mycompany\\pokemon\\Pictures\\DS Assignment\\RivalRace\\RivalRace\\picture\\redFInal.png");
        Image overlayImage = overlayIcon.getImage();
        Image resizedOverlayImage = overlayImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon resizedOverlayIcon = new ImageIcon(resizedOverlayImage);

        overlayLabel = new JLabel(resizedOverlayIcon);

        overlayLabel.setBounds(0, 0, 40, 40); // Fixed size, adjust as needed
        add(overlayLabel);
        setComponentZOrder(overlayLabel, 0);
        this.revalidate();
        this.repaint();

    }

// *************DUN DELETE THE COMMENT*****************
//  //keyListener
    public void destination(int destinationX, int destinationY) {
        this.destinationX = destinationX;
        this.destinationY = destinationY;
    }

    public void keyTyped(KeyEvent e) {
        int x = overlayLabel.getX();
        int y = overlayLabel.getY();

        switch (e.getKeyChar()) {
            case 'a':
                overlayLabel.setLocation(x - 5, y);
                break;
            case 'd':
                overlayLabel.setLocation(x + 5, y);
                break;
            case 'w':
                overlayLabel.setLocation(x, y - 5);
                break;
            case 's':
                overlayLabel.setLocation(x, y + 5);
                break;
        }

//    System.out.println("Overlay Label Coordinates: (" + x + ", " + y + ")");
//    System.out.println("Destination Coordinates: (" + destinationX + ", " + destinationY + ")");
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

//mouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        // Get the absolute screen coordinates of the mouse click
        int screenX = e.getXOnScreen();
        int screenY = e.getYOnScreen();

        // Get the absolute screen coordinates of the top-left corner of the frame
        Point frameLocationOnScreen = this.getLocationOnScreen();
        int frameX = (int) frameLocationOnScreen.getX();
        int frameY = (int) frameLocationOnScreen.getY();

        // Calculate the relative coordinates within the frame
        int relativeX = screenX - frameX;
        int relativeY = screenY - frameY;

        System.out.println("Mouse pressed at frame coordinates: (" + relativeX + ", " + relativeY + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Define a tolerance level for how close the label needs to be to the destination
        int tolerance = 10;  // 10 pixels of leeway in any direction

        if (e.getSource() == myButton) {
            if (Math.abs(overlayLabel.getX() - destinationX) <= tolerance && Math.abs(overlayLabel.getY() - destinationY) <= tolerance) {
                // Display Congrats message
                congratsWindow myWindow = new congratsWindow(saveNumber);
            } else {
                WrongDestination wrongWindow = new WrongDestination(saveNumber);
            }
        }
    }

}
