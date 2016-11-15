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
		if (ball != null) {
			if ( e.getKeyCode() == KeyEvent.VK_UP ) {
			    ball.move(0,-10);
			}
			if ( e.getKeyCode() == KeyEvent.VK_DOWN ) {
			    ball.move(0,+10);
			}
			if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
			    obstacle.move(+10,0);
			}
			if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			    obstacle.move(-10,0);
			}
		}
	}
	
}
