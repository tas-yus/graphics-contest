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

	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	public void run() {
		setUpBall();

	}

	private void setUpBall() {
		while (true) {
			double r = rgen.nextDouble(20,50);
			double vx = rgen.nextDouble(1,5);
			double vy = rgen.nextDouble(1,5);
			double x = rgen.nextDouble(0 + 2*r, getWidth() - 2*r);
			double y = rgen.nextDouble(0 + 2*r, getHeight() - 2*r);
			GOval ball = new GOval (x - r, y - r, r*2, r*2);
			ball.setFilled(true);
			ball.setColor(rgen.nextColor());
			add(ball);
			pause(20);
		}
	}

	private void drawBackGround() {

	}

}