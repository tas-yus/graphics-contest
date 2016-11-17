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
	private static final int BRUSH_SIZE = 5;
	private Color newColor = new Color (255, 128, 0);
	private boolean draw = true;
	private int s = BRUSH_SIZE;
	private GRect icon1;
	private GRect icon2;
	private GRect icon3;
	private GRect icon4;
	private GRect icon5;
	private GLabel brushStatus;
	private GLabel brushSizeStatus;
	

	public void run() {
		this.resize(WIDTH,HEIGHT);
		pause(PAUSE);
		addMouseListeners();
		setUpIcons();
	}

	private void setUpIcons() {
		icon1 = new GRect (0, 0, WIDTH/5, ICON_HEIGHT);
		add(icon1);
		icon2 = new GRect (WIDTH*1/5, 0, WIDTH/5, ICON_HEIGHT);
		add(icon2);
		icon3 = new GRect (WIDTH*2/5, 0, WIDTH/5, ICON_HEIGHT);
		add(icon3);
		icon4 = new GRect (WIDTH*3/5, 0, WIDTH/5, ICON_HEIGHT);
		add(icon4);
		icon5 = new GRect (WIDTH*4/5, 0, WIDTH/5, ICON_HEIGHT);
		add(icon5);
		brushStatus = new GLabel ("Brush: On", WIDTH/10, ICON_HEIGHT/2);
		brushStatus.move(-brushStatus.getWidth()/2, +brushStatus.getAscent()/2);
		add(brushStatus);
		brushSizeStatus = new GLabel ("Size = x" + s, WIDTH/10, ICON_HEIGHT/2);
		brushSizeStatus.move(-brushSizeStatus.getWidth()/2, +brushSizeStatus.getAscent()/2);
		add(brushSizeStatus);
	}
	
	private void updateIcons() {
		String status = "";
		if (draw == true) {
			status = "On";
		} else {
			status = "Off";
		}
		brushStatus = new GLabel ("Brush: " + status, WIDTH/10, ICON_HEIGHT/2);
		if (getElementAt(WIDTH/10,ICON_HEIGHT/2) != null){
            remove(getElementAt(WIDTH/10,ICON_HEIGHT/2));
		}
		brushStatus.move(-brushStatus.getWidth()/2, +brushStatus.getAscent()/2);
		add(brushStatus);
		add(brushSizeStatus);
		add(icon2);
		add(icon3);
		add(icon4);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (clickIcon1(e) == true) {
			if (draw == false)  {
				draw = true;
				updateIcons();
			} else if (draw == true) {
				draw = false;
				updateIcons();
			}
		}
		if (clickIcon3(e) == true) {
			s++;
			updateIcons();
		}
		if (clickIcon4(e) == true) {
			s--;
			updateIcons();
		}
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
	
	private boolean clickIcon1(MouseEvent e) {
		if (clickIcon3(e) && clickIcon4(e)) return false;
		else return true;
	}
	
	private boolean clickIcon3(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*2/5 && x < WIDTH*3/5 && y <= 50) {
			return true;
		} else return false;
	}
	
	private boolean clickIcon4(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*3/5 && x < WIDTH*4/5 && y <= 50) {
			return true;
		} else return false;
	}
}