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
			double s = 1;
			double x = rgen.nextDouble(0 + 2*s, getWidth() - 2*s);
			double y = rgen.nextDouble(0 + 2*s, getHeight() - 2*s);
			GRect pixel = new GRect (x - s, y - s, s*2, s*2);
			pixel.setFilled(true);
			pixel.setColor(rgen.nextColor());
			add(pixel);
			pause(20);
		}
	}

	private void drawBackGround() {

	}

}