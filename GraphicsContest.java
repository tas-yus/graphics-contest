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
	private static final int WIDTH = 700;
    private static final int HEIGHT = 700;
    private static final int PAUSE = 10;
    
	public void run() {
		this.resize(WIDTH,HEIGHT);
        pause(PAUSE);
		setUpBall();

	}

	private void setUpBall() {
		int s = 10;
		while(true) {
			int x = rgen.nextInt(0, getWidth()/2 - s);
			int y = rgen.nextInt(0, getHeight()/2 - s);
			addAll(s, x, y);
			pause(10);
		}
	}

	private void addAll(int s, int x, int y) {
		Color newColor = new Color (255,rgen.nextInt(128,255),0);
		GOval pixel1 = new GOval (getWidth()/2 - x - s/2, getHeight()/2 - y - s/2, s, s);
		pixel1.setFilled(true);
		pixel1.setColor(newColor);
		GOval pixel2 = new GOval (getWidth()/2 - y - s/2, getHeight()/2 - x - s/2, s, s);
		pixel2.setFilled(true);
		pixel2.setColor(newColor);
		GOval pixel3 = new GOval (getWidth()/2 + x - s/2, getHeight()/2 + y - s/2, s, s);
		pixel3.setFilled(true);
		pixel3.setColor(newColor);
		GOval pixel4 = new GOval (getWidth()/2 + y - s/2, getHeight()/2 + x - s/2, s, s);
		pixel4.setFilled(true);
		pixel4.setColor(newColor);
		GOval pixel5 = new GOval (getWidth()/2 - x - s/2, getHeight()/2 + y - s/2, s, s);
		pixel5.setFilled(true);
		pixel5.setColor(newColor);
		GOval pixel6 = new GOval (getWidth()/2 - y - s/2, getHeight()/2 + x - s/2, s, s);
		pixel6.setFilled(true);
		pixel6.setColor(newColor);
		GOval pixel7 = new GOval (getWidth()/2 + x - s/2, getHeight()/2 - y - s/2, s, s);
		pixel7.setFilled(true);
		pixel7.setColor(newColor);
		GOval pixel8 = new GOval (getWidth()/2 + y - s/2, getHeight()/2 - x - s/2, s, s);
		pixel8.setFilled(true);
		pixel8.setColor(newColor);
		add(pixel1);
		add(pixel2);
		add(pixel3);
		add(pixel4);
		add(pixel5);
		add(pixel6);
		add(pixel7);
		add(pixel8);
	}
	

}