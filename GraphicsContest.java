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
	private static final int ICON_HEIGHT = 30;
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700 + ICON_HEIGHT;
	private static final int PAUSE = 10;
	private static final int s = 5;
	private Color newColor = new Color (255, 128, 0);
	private boolean draw = true;

	public void run() {
		this.resize(WIDTH,HEIGHT);
		pause(PAUSE);
		addMouseListeners();
	}

	private void updateIcons() {
		GRect brushOnOff = new GRect (0, 0, WIDTH/3, ICON_HEIGHT);
		String status = "";
		if (draw == true) {
			status = "On";
		} else {
			status = "Off";
		}
		GLabel OnOff = new GLabel ("Brush:" + status, 50, 50);
		add(brushOnOff);
		add(OnOff);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (draw == false) {
			draw = true;
		} else if (draw == true) {
			draw = false;
		}
		updateIcons();
	}

	public void mouseMoved(MouseEvent e) {
		if (draw == true) {
			double x = e.getX();
			double y = e.getY();
			if (y > ICON_HEIGHT) {
				x = x - (getWidth()/2 - s/2);
				y = y - (getHeight()/2 + ICON_HEIGHT - s/2);
				setUpBall(x, y);
			}
		}
	}

	private void setUpBall(double x, double y) {
		addAll(s, x, y);
	}
	private void addAll(int s, double x, double y) {
		newColor = new Color (255,rgen.nextInt(128,255),0);
		GOval pixel1 = new GOval (getWidth()/2 - x - s/2, getHeight()/2 + ICON_HEIGHT - y - s/2, s, s);
		pixel1.setFilled(true);
		pixel1.setColor(newColor);
		GOval pixel2 = new GOval (getWidth()/2 - y - s/2, getHeight()/2 + ICON_HEIGHT- x - s/2, s, s);
		pixel2.setFilled(true);
		pixel2.setColor(newColor);
		GOval pixel3 = new GOval (getWidth()/2 + x - s/2, getHeight()/2 + ICON_HEIGHT + y - s/2, s, s);
		pixel3.setFilled(true);
		pixel3.setColor(newColor);
		GOval pixel4 = new GOval (getWidth()/2 + y - s/2, getHeight()/2 + ICON_HEIGHT + x - s/2, s, s);
		pixel4.setFilled(true);
		pixel4.setColor(newColor);
		GOval pixel5 = new GOval (getWidth()/2 - x - s/2, getHeight()/2 + ICON_HEIGHT + y - s/2, s, s);
		pixel5.setFilled(true);
		pixel5.setColor(newColor);
		GOval pixel6 = new GOval (getWidth()/2 - y - s/2, getHeight()/2 + ICON_HEIGHT + x - s/2, s, s);
		pixel6.setFilled(true);
		pixel6.setColor(newColor);
		GOval pixel7 = new GOval (getWidth()/2 + x - s/2, getHeight()/2 + ICON_HEIGHT - y - s/2, s, s);
		pixel7.setFilled(true);
		pixel7.setColor(newColor);
		GOval pixel8 = new GOval (getWidth()/2 + y - s/2, getHeight()/2 + ICON_HEIGHT - x - s/2, s, s);
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