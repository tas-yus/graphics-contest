/*
 * File: GraphicsContest.java
 * --------------------------
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class GraphicsContest extends GraphicsProgram {
	
	private static final double BALL_RADIUS = 10;
	private static final double OBSTACLE_SIZE = 20;
	private GRect obstacle;
	private GOval ball;
	private boolean[]keys = new boolean[256];

	public void run() {
		setUp();
		addKeyListeners();
	}
	
	private void setUp() {
		drawCharacter();
		drawBackGround();
	}
	
	private void drawCharacter() {
		double ballCenterX = getWidth()/4;
		double ballCenterY = getHeight()*7/8;
		ball = new GOval (ballCenterX - BALL_RADIUS, ballCenterY - BALL_RADIUS, BALL_RADIUS*2, BALL_RADIUS *2);
		add(ball);
		ball.setFilled(true);
	}
	
	private void drawBackGround() {
		double obCenterX = getWidth();
		double obCenterY = getHeight()*7/8;
		obstacle = new GRect (obCenterX - OBSTACLE_SIZE/2, obCenterY - OBSTACLE_SIZE/2, OBSTACLE_SIZE, OBSTACLE_SIZE);
		add(obstacle);
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]=true;
		move();
	}
	
	public void keyReleased(KeyEvent e) {
	    keys[e.getKeyCode()]=false;
	    move();
	}

	private void move() {
		if(keys[KeyEvent.VK_UP]) {
			moveUp(ball);
		}
		if(keys[KeyEvent.VK_RIGHT]) {
			moveLeft(obstacle);
		}
		if(keys[KeyEvent.VK_SPACE]) {
			jump(ball);
		}
	}
	
	private void moveUp(GObject obj) {
		obj.move(0, -10);
	}
	
	private void moveRight(GObject obj) {
		obj.move(+10, 0);
	}
	
	private void moveLeft(GObject obj) {
		obj.move(-10, 0);
	}
	
	private void jump(GObject obj) {
		while(true) {
			obj.move(0, -5);
			pause(5);
			if (obj.getX() <= getHeight()*3/4) break;
		}
		while(true) {
			obj.move(0, +5);
			pause(5);
			if (obj.getX() >= getHeight()*7/8 - OBSTACLE_SIZE/2) break;
		}
	}
	
}
