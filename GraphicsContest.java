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
			double x = rgen.nextDouble(getWidth() - r/2, getHeight() - r/2);
			double y = rgen.nextDouble(getWidth() - r/2, getHeight() - r/2);
			GOval ball = new GOval (x - r/2, y - r/2, r*2, r*2);
			ball.setFilled(true);
			ball.setColor(rgen.nextColor());
			add(ball);
		}
	}

	private void drawBackGround() {

	}

}