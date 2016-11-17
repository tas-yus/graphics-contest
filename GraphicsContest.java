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
	private static final int WIDTH = 900;
    private static final int HEIGHT = 900;
    private static final int PAUSE = 10;
    
	public void run() {
		this.resize(WIDTH,HEIGHT);
        pause(PAUSE);
		setUpBall();

	}

	private void setUpBall() {
		int s = 10;
		int x = rgen.nextInt(0 + 2*s, getWidth() - 2*s);
		int y = rgen.nextInt(0 + 2*s, getHeight() - 2*s);
		GRect pixel = new GRect (x - s, y - s, s*2, s*2);
		pixel.setFilled(true);
		pixel.setColor(rgen.nextColor());
		if (getElementAt(x,y) != null){
			remove(getElementAt(x,y));
		}
	}

	private void drawBackGround() {

	}

}