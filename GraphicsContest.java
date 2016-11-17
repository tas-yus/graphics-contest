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
			int s = 1;
			int x = rgen.nextInt(0 + 2*s, getWidth() - 2*s);
			int y = rgen.nextInt(0 + 2*s, getHeight() - 2*s);
			GRect pixel = new GRect (x - s, y - s, s*2, s*2);
			pixel.setFilled(true);
			pixel.setColor(rgen.nextColor());
			if (getElementAt(x,y) != null){
	            remove(getElementAt(x,y));
			}
			add(pixel);
			pause(1);
		}
	}

	private void drawBackGround() {

	}

}