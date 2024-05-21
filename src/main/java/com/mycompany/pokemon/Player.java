/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemon;

/**
 *
 * @author User
 */
import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    
	private int x;
	private int y;
	private int xPrev;
	private int yPrev;
        private boolean isLegal;
	private Image playerUp;
	private Image playerDown;
	private Image playerLeft;
	private Image playerRight;
	private Image player;
	
	public Player() {
		this.x = 1;
		this.y = 0;
		ImageIcon imgD = new ImageIcon("C:\\Users\\User\\Downloads\\PokeMaze\\PokeMaze\\playerD.png");  //player facing down
		ImageIcon imgU = new ImageIcon("C:\\Users\\User\\Downloads\\PokeMaze\\PokeMaze\\playerU.png");  //player facing up
		ImageIcon imgL = new ImageIcon("C:\\Users\\User\\Downloads\\PokeMaze\\PokeMaze\\playerL.png");  //player facing left
		ImageIcon imgR = new ImageIcon("C:\\Users\\User\\Downloads\\PokeMaze\\PokeMaze\\playerR.png");  //player facing right
		this.playerDown = imgD.getImage();
		this.playerUp = imgU.getImage();
		this.playerLeft = imgL.getImage();
		this.playerRight = imgR.getImage();
		this.player = playerDown;
		this.xPrev = 1;
		this.yPrev = 0;
	}
       
	public Image getPlayer() {		
		return this.player;
	}
	
	public void processMove(char move, boolean legal) {
		yPrev = y;
		xPrev = x;
		
		if (move == 'U') {
			player = playerUp;
			if (legal) y--;
		} else if (move == 'L') {
			player = playerLeft;
			if (legal) x--;
		} else if (move == 'R') {
			player = playerRight;
			if (legal) x++;
		} else if (move == 'D') {
			player = playerDown;
			if (legal) y++;
		} 
	}
        
	
	public int xCoord() {
		return this.x;
	}
	
	public int yCoord() {
		return this.y;
	}
	
	public void updateXCoord(int newX) {
		this.xPrev = x;
		this.x = newX;
	}
	
	public void updateYCoord(int newY) {
		this.yPrev = y;
		this.y = newY;
	}

	public int xCoordPrev() {
		return xPrev;
	}
	
	public int yCoordPrev() {
		return yPrev;
	}
}
