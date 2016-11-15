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
		double ballCenterY = getHeight()/8;
		ball = new GOval (ballCenterX - BALL_RADIUS, ballCenterY - BALL_RADIUS, BALL_RADIUS*2, BALL_RADIUS *2);
		add(ball);
	}
	
	private void drawBackGround() {
		double obCenterX = getWidth()*2;
		double obCenterY = getHeight()/8;
		obstacle = new GRect (obCenterX - OBSTACLE_SIZE/2, obCenterY - OBSTACLE_SIZE/2, OBSTACLE_SIZE, OBSTACLE_SIZE);
		add(obstacle);
	}
	
	public void keyPressed(KeyEvent e) {
		if (ball != null) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP: ball.move(0, -1); break;
			case KeyEvent.VK_DOWN: ball.move(0, +1); break;
			case KeyEvent.VK_LEFT: ball.move(-1, 0); break;
			case KeyEvent.VK_RIGHT: ball.move(0, -1); break;
			}
		}
	}
	
}
