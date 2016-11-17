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
	private static final int RED = 0;
	private static final int ORANGE = 1;
	private static final int YELLOW = 2;
	private static final int GREEN = 3;
	private static final int BLUE = 4;
	private static final int CYAN = 5;
	private static final int PURPLE = 6;
	
	
	private Color newColor = new Color (255, 128, 0);
	private boolean draw = false;
	private boolean pure = true;
	private boolean plain = false;
	private boolean mixed = false;
	private int s = BRUSH_SIZE;
	private GRect icon1;
	private GRect icon2;
	private GRect icon3;
	private GRect icon4;
	private GRect icon5;
	private GRect colorIcon1;
	private GRect colorIcon2;
	private GRect colorIcon3;
	private GRect colorIcon4;
	private GRect colorIcon5;
	private GRect colorIcon6;
	private GRect colorIcon7;
	private String status = "off";
	private String mode = "Pure";
	private GLabel brushStatus;
	private GLabel brushSizeStatus;
	private GLabel plusSize;
	private GLabel minusSize;
	private GLabel colorMode;
	
	private Color[][] plainColor;
	private Color[] chosenColor;
	private Color chosenPureColor;

	public void run() {
		this.resize(WIDTH,HEIGHT);
		pause(PAUSE);
		addMouseListeners();
		setUpColors();
		setUpIcons();
		setUpColorChoice();
	}

	private void setUpColors() {
		plainColor = new Color[7][5];
		plainColor[RED][0] = new Color(255,0,0); 
		plainColor[RED][1] = new Color(255,51,51); 
		plainColor[RED][2] = new Color(255,102,102); 
		plainColor[RED][3] = new Color(255,153,153);
		plainColor[RED][4] = new Color(255,204,204);
		plainColor[ORANGE][0] = new Color(255,128,0); 
		plainColor[ORANGE][1] = new Color(255,153,51); 
		plainColor[ORANGE][2] = new Color(255,178,102); 
		plainColor[ORANGE][3] = new Color(255,204,153);
		plainColor[ORANGE][4] = new Color(255,229,204);
		plainColor[YELLOW][0] = new Color(255,255,0); 
		plainColor[YELLOW][1] = new Color(255,255,51); 
		plainColor[YELLOW][2] = new Color(255,255,102); 
		plainColor[YELLOW][3] = new Color(255,255,153);
		plainColor[YELLOW][4] = new Color(255,255,204);
		plainColor[GREEN][0] = new Color(0,255,0); 
		plainColor[GREEN][1] = new Color(51,255,51); 
		plainColor[GREEN][2] = new Color(102,255,102); 
		plainColor[GREEN][3] = new Color(153,255,153);
		plainColor[GREEN][4] = new Color(204,255,204);
		plainColor[BLUE][0] = new Color(0,255,255); 
		plainColor[BLUE][1] = new Color(51,255,255); 
		plainColor[BLUE][2] = new Color(102,255,255); 
		plainColor[BLUE][3] = new Color(153,255,255);
		plainColor[BLUE][4] = new Color(204,255,255);
		plainColor[CYAN][0] = new Color(0,0,255); 
		plainColor[CYAN][1] = new Color(51,51,255); 
		plainColor[CYAN][2] = new Color(102,102,255); 
		plainColor[CYAN][3] = new Color(153,153,255);
		plainColor[CYAN][4] = new Color(204,204,255);
		plainColor[PURPLE][0] = new Color(127,0,255); 
		plainColor[PURPLE][1] = new Color(153,51,255); 
		plainColor[PURPLE][2] = new Color(178,102,255); 
		plainColor[PURPLE][3] = new Color(204,153,255);
		plainColor[PURPLE][4] = new Color(229,204,255);
	}
	
	private void setUpIcons() {
		icon1 = new GRect (0, 0, WIDTH/5, ICON_HEIGHT);
		add(icon1);
		icon2 = new GRect (WIDTH*1/5, 0, WIDTH/5, ICON_HEIGHT);
		add(icon2);
		icon3 = new GRect (WIDTH*2/5, 0, WIDTH/20, ICON_HEIGHT);
		add(icon3);
		icon4 = new GRect (WIDTH*9/20, 0, WIDTH/20, ICON_HEIGHT);
		add(icon4);
		icon5 = new GRect (WIDTH*1/2, 0, WIDTH/10, ICON_HEIGHT);
		add(icon5);
		brushStatus = new GLabel ("Brush: " + status, WIDTH/10, ICON_HEIGHT/2);
		brushStatus.move(-brushStatus.getWidth()/2, +brushStatus.getAscent()/2);
		add(brushStatus);
		brushSizeStatus = new GLabel ("Size = x" + s, WIDTH*3/10, ICON_HEIGHT/2);
		brushSizeStatus.move(-brushSizeStatus.getWidth()/2, +brushSizeStatus.getAscent()/2);
		add(brushSizeStatus);
		plusSize = new GLabel ("+", WIDTH*17/40, ICON_HEIGHT/2);
		plusSize.move(-plusSize.getWidth()/2, +plusSize.getAscent()/2);
		add(plusSize);
		minusSize = new GLabel ("-", WIDTH*19/40, ICON_HEIGHT/2);
		minusSize.move(-minusSize.getWidth()/2, +minusSize.getAscent()/2);
		add(minusSize);
		colorMode = new GLabel (mode, WIDTH*11/20, ICON_HEIGHT/2);
		colorMode.move(-colorMode.getWidth()/2, +colorMode.getAscent()/2);
		add(colorMode);
		colorIcon1 = new GRect (WIDTH*3/5, 0, WIDTH*2/35, ICON_HEIGHT);
		add(colorIcon1);
		colorIcon2 = new GRect (WIDTH*23/35, 0, WIDTH*2/35, ICON_HEIGHT);
		add(colorIcon2);
		colorIcon3 = new GRect (WIDTH*25/35, 0, WIDTH*2/35, ICON_HEIGHT);
		add(colorIcon3);
		colorIcon4 = new GRect (WIDTH*27/35, 0, WIDTH*2/35, ICON_HEIGHT);
		add(colorIcon4);
		colorIcon5 = new GRect (WIDTH*29/35, 0, WIDTH*2/35, ICON_HEIGHT);
		add(colorIcon5);
		colorIcon6 = new GRect (WIDTH*31/35, 0, WIDTH*2/35, ICON_HEIGHT);
		add(colorIcon6);
		colorIcon7 = new GRect (WIDTH*33/35, 0, WIDTH*2/35, ICON_HEIGHT);
		add(colorIcon7);
	}
	
	private void updateIcons() {
		remove(brushStatus);
		remove(brushSizeStatus);
		remove(colorMode);
		brushStatus = new GLabel ("Brush: " + status, WIDTH/10, ICON_HEIGHT/2);
		brushStatus.move(-brushStatus.getWidth()/2, +brushStatus.getAscent()/2);
		brushSizeStatus = new GLabel ("Size = x" + s, WIDTH*3/10, ICON_HEIGHT/2);
		brushSizeStatus.move(-brushSizeStatus.getWidth()/2, +brushSizeStatus.getAscent()/2);
		colorMode = new GLabel (mode, WIDTH*11/20, ICON_HEIGHT/2);
		colorMode.move(-colorMode.getWidth()/2, +colorMode.getAscent()/2);
		add(brushStatus);
		add(brushSizeStatus);
		add(colorMode);
	}
	
	private void setUpColorChoice() {
		colorIcon1.setFilled(true);
		colorIcon1.setColor(plainColor[RED][0]);
		colorIcon2.setFilled(true);
		colorIcon2.setColor(plainColor[ORANGE][0]);
		colorIcon3.setFilled(true);
		colorIcon3.setColor(plainColor[YELLOW][0]);
		colorIcon4.setFilled(true);
		colorIcon4.setColor(plainColor[GREEN][0]);
		colorIcon5.setFilled(true);
		colorIcon5.setColor(plainColor[BLUE][0]);
		colorIcon6.setFilled(true);
		colorIcon6.setColor(plainColor[CYAN][0]);
		colorIcon7.setFilled(true);
		colorIcon7.setColor(plainColor[PURPLE][0]);
	}
	
	public void mouseClicked(MouseEvent e) {
		if (clickIcon1(e) == true) {
			if (draw == false)  {
				status = "On";
				draw = true;
				updateIcons();
			} else if (draw == true) {
				status = "Off";
				draw = false;
				updateIcons();
			}
		}
		if (clickIcon3(e) == true) {
			s++;
			updateIcons();
		}
		if (clickIcon4(e) == true) {
			if(s != 0) {
				s--;
			}
			updateIcons();
		}
		if (clickIcon5(e) == true) {
			if(pure == true) {
				pure = false;
				plain = true;
				mode = "Plain";
				updateIcons();
			} else if (plain == true) {
				plain = false;
				mixed = true;
				mode = "Mixed";
				updateIcons();
			} else if (mixed == true) {
				mixed = false;
				pure = true;
				mode = "Pure";
				updateIcons();
			}
		}
		if (pure == true) {
			if(clickColorIcon1(e) == true) {
				chosenPureColor = plainColor[RED][0]; 
			}
			if(clickColorIcon2(e) == true) {
				chosenPureColor = plainColor[ORANGE][0]; 
			}
			if(clickColorIcon3(e) == true) {
				chosenPureColor = plainColor[YELLOW][0]; 
			}
			if(clickColorIcon4(e) == true) {
				chosenPureColor = plainColor[GREEN][0]; 
			}
			if(clickColorIcon5(e) == true) {
				chosenPureColor = plainColor[BLUE][0]; 
			}
			if(clickColorIcon6(e) == true) {
				chosenPureColor = plainColor[CYAN][0]; 
			}
			if(clickColorIcon7(e) == true) {
				chosenPureColor = plainColor[PURPLE][0]; 
			}
		}
		if (plain == true) {
			if(clickColorIcon1(e) == true) {
				chosenColor = plainColor[RED]; 
			}
			if(clickColorIcon2(e) == true) {
				chosenColor = plainColor[ORANGE]; 
			}
			if(clickColorIcon3(e) == true) {
				chosenColor = plainColor[YELLOW]; 
			}
			if(clickColorIcon4(e) == true) {
				chosenColor = plainColor[GREEN]; 
			}
			if(clickColorIcon5(e) == true) {
				chosenColor = plainColor[BLUE]; 
			}
			if(clickColorIcon6(e) == true) {
				chosenColor = plainColor[CYAN]; 
			}
			if(clickColorIcon7(e) == true) {
				chosenColor = plainColor[PURPLE]; 
			}
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
		if (pure == true) {
			newColor = chosenPureColor; 
		}
		if (plain == true) {
			newColor = randomizeColor(chosenColor);
		}
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
	
	private Color randomizeColor(Color[] chosenColor) {
		Color color = chosenColor[rgen.nextInt(0,chosenColor.length - 1)];
		return color;
	}
	
	private boolean clickIcon1(MouseEvent e) {
		if (clickIcon3(e) || clickIcon4(e) || clickIcon5(e) ||
				clickColorIcon1(e) || clickColorIcon2(e) || clickColorIcon3(e) ||
				clickColorIcon4(e) || clickColorIcon5(e) || clickColorIcon6(e) ||
				clickColorIcon7(e)) return false;
		else return true;
	}
	
	private boolean clickIcon3(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*2/5 && x < WIDTH*9/20 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	
	private boolean clickIcon4(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*9/20 && x < WIDTH*1/2 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	
	private boolean clickIcon5(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*1/2 && x < WIDTH*3/5 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	
	private boolean clickColorIcon1(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*3/5 && x < WIDTH*23/35 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	
	private boolean clickColorIcon2(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*23/35 && x < WIDTH*25/35 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	
	private boolean clickColorIcon3(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*25/35 && x < WIDTH*27/35 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	private boolean clickColorIcon4(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*27/35 && x < WIDTH*29/35 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	private boolean clickColorIcon5(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*29/35 && x < WIDTH*31/35 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	private boolean clickColorIcon6(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*31/35 && x < WIDTH*33/35 && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
	private boolean clickColorIcon7(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		if (x > WIDTH*33/35 && x < WIDTH && y <= ICON_HEIGHT) {
			return true;
		} else return false;
	}
}