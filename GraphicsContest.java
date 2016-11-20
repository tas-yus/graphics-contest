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

	/*removeall, auto, rgb, mergecolor*/
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private static final int ICON_HEIGHT = 30;
	private static final int DELAY = 200;
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
	private static final int WHITE = 7;
	private static final int BLACK = 8;


	private Color newColor;
	private boolean draw = false;
	private boolean pure = false;
	private boolean plain = false;
	private boolean mixed = true;
	private boolean auto = false;
	private boolean line = false;
	private boolean rotation = false;
	private boolean reflection = true;
	private int s = BRUSH_SIZE;
	private int speed = DELAY;
	private int speedLevel = 1;
	private int symmetry = 8;
	private int plane = 4;
	private GRect colorTray;
	private GRect icon1;
	private GRect icon2;
	private GRect icon3;
	private GRect icon4;
	private GRect icon5;
	private GRect icon6;
	private GRect colorIcon1;
	private GRect colorIcon2;
	private GRect colorIcon3;
	private GRect colorIcon4;
	private GRect colorIcon5;
	private GRect colorIcon6;
	private GRect colorIcon7;
	private GRect colorIcon8;
	private GRect colorIcon9;
	private String status = "off";
	private String mode = "Mixed";
	private GLabel brushStatus;
	private GLabel brushSizeStatus;
	private GLabel speedStatus;
	private GLabel plusSize;
	private GLabel minusSize;
	private GLabel colorMode;
	private GLine line1;
	private GLine line2;
	private GLine line3;
	private GLine line4;
	private double[] slope = new double[plane];
	double[][] coordinate;
	
	private Color[][] plainColor;
	private Color[] chosenColor;
	private Color chosenPureColor;
	private int chosenMixedColor;

	public void run() {
		this.resize(WIDTH,HEIGHT);
		pause(PAUSE);
		addMouseListeners();
		addKeyListeners();
		setUpColors();
		setUpIcons();
		setUpColorChoice();
		autoDraw();
	}

	private void autoDraw() {
		double x = getWidth()/2 - s;
		double y = getHeight()/2 + ICON_HEIGHT/2;
		x = x - (getWidth()/2 - s);
		y = y - (getHeight()/2 + ICON_HEIGHT/2 - s);
		int c = 0;
		int n = 1;
		while(true) {
			if(auto == true) {
				double dx = s;
				double dy = s;
				if (c == 0) {
					for (int i = 0; i < n; i++) {
						x = x + dx;
						setUpBall(x, y);
					}
				}
				if (c == 1) {
					for (int i = 0; i < n; i++) {
						x = x + dx;
						y = y + dy;
						setUpBall(x, y);
					}
				}
				if (c == 2) {
					for (int i = 0; i < n; i++) {
						y = y + dy;
						setUpBall(x, y);
					}
				}
				if (c == 3) {
					for (int i = 0; i < n+1; i++) {
						x = x - dx;
						y = y + dy;
						setUpBall(x, y);
					}
				}
				if (c == 4) {
					for (int i = 0; i < n+1; i++) {
						x = x - dx;
						setUpBall(x, y);
					}
				}
				if (c == 5) {
					for (int i = 0; i < n+1; i++) {
						x = x - dx;
						y = y - dy;
						setUpBall(x, y);
					}
				}
				if (c == 6) {
					for (int i = 0; i < n; i++) {
						y = y - dy;
						setUpBall(x, y);
					}
				}
				if (c == 7) {
					for (int i = 0; i < n+1; i++) {
						x = x + dx;
						y = y - dy;
						setUpBall(x, y);
					}
					n++;
				}
				c = (c+1)%8;
			}
			pause(speed);
			if (n == 20) {
				removeAll();
				setUpColors();
				setUpIcons();
				setUpColorChoice();
				colorTray.setColor(plainColor[chosenMixedColor][0]);
				n = 1;
				x = getWidth()/2 - s;
				y = getHeight()/2 + ICON_HEIGHT/2;
				x = x - (getWidth()/2 - s);
				y = y - (getHeight()/2 + ICON_HEIGHT/2 - s);
			}
		}
	}

	private void setUpColors() {
		plainColor = new Color[9][5];
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
		plainColor[WHITE][0] = Color.WHITE;
		plainColor[WHITE][1] = Color.WHITE;
		plainColor[WHITE][2] = Color.WHITE; 
		plainColor[WHITE][3] = Color.WHITE;
		plainColor[WHITE][4] = Color.WHITE;;
		plainColor[BLACK][0] = new Color(0,0,0);
		plainColor[BLACK][1] = new Color(51,51,51);
		plainColor[BLACK][2] = new Color(102,102,102);
		plainColor[BLACK][3] = new Color(153,153,153);
		plainColor[BLACK][4] = new Color(204,204,204);
	}

	private void setUpIcons() {
		colorTray = new GRect (0, 0, getWidth()/20, ICON_HEIGHT);
		add(colorTray);
		colorTray.setFilled(true);
		colorTray.setColor(plainColor[RED][0]);
		icon1 = new GRect (getWidth()/20, 0, getWidth()*3/20, ICON_HEIGHT);
		add(icon1);
		icon2 = new GRect (getWidth()*1/5, 0, getWidth()*3/20, ICON_HEIGHT);
		add(icon2);
		icon3 = new GRect (getWidth()*7/20, 0, getWidth()/20, ICON_HEIGHT);
		add(icon3);
		icon4 = new GRect (getWidth()*8/20, 0, getWidth()/20, ICON_HEIGHT);
		add(icon4);
		icon5 = new GRect (getWidth()*9/20, 0, getWidth()/20, ICON_HEIGHT);
		add(icon5);
		icon6 = new GRect (getWidth()*1/2, 0, getWidth()/10, ICON_HEIGHT);
		add(icon6);
		brushStatus = new GLabel ("Brush: " + status, getWidth()/8, ICON_HEIGHT/2);
		brushStatus.move(-brushStatus.getWidth()/2, +brushStatus.getAscent()/2);
		add(brushStatus);
		brushSizeStatus = new GLabel ("Size x" + s, getWidth()*11/40, ICON_HEIGHT/2);
		brushSizeStatus.move(-brushSizeStatus.getWidth()/2, +brushSizeStatus.getAscent()/2);
		add(brushSizeStatus);
		speedStatus = new GLabel ("", getWidth()*11/40, ICON_HEIGHT/2);
		speedStatus.move(-speedStatus.getWidth()/2, +speedStatus.getAscent()/2);
		add(speedStatus);
		plusSize = new GLabel ("+", getWidth()*15/40, ICON_HEIGHT/2);
		plusSize.move(-plusSize.getWidth()/2, +plusSize.getAscent()/2);
		add(plusSize);
		minusSize = new GLabel ("-", getWidth()*17/40, ICON_HEIGHT/2);
		minusSize.move(-minusSize.getWidth()/2, +minusSize.getAscent()/2);
		add(minusSize);
		colorMode = new GLabel (mode, getWidth()*11/20, ICON_HEIGHT/2);
		colorMode.move(-colorMode.getWidth()/2, +colorMode.getAscent()/2);
		add(colorMode);
		colorIcon1 = new GRect (getWidth()*3/5, 0, getWidth()*2/45, ICON_HEIGHT);
		add(colorIcon1);
		colorIcon2 = new GRect (getWidth()*29/45, 0, getWidth()*31/45, ICON_HEIGHT);
		add(colorIcon2);
		colorIcon3 = new GRect (getWidth()*31/45, 0, getWidth()*33/45, ICON_HEIGHT);
		add(colorIcon3);
		colorIcon4 = new GRect (getWidth()*33/45, 0, getWidth()*35/45, ICON_HEIGHT);
		add(colorIcon4);
		colorIcon5 = new GRect (getWidth()*35/45, 0, getWidth()*37/45, ICON_HEIGHT);
		add(colorIcon5);
		colorIcon6 = new GRect (getWidth()*37/45, 0, getWidth()*39/45, ICON_HEIGHT);
		add(colorIcon6);
		colorIcon7 = new GRect (getWidth()*39/45, 0, getWidth()*41/45, ICON_HEIGHT);
		add(colorIcon7);
		colorIcon8 = new GRect (getWidth()*41/45, 0, getWidth()*43/35, ICON_HEIGHT);
		add(colorIcon8);
		colorIcon9 = new GRect (getWidth()*43/45, 0, getWidth(), ICON_HEIGHT);
		add(colorIcon9);
		setUpLines(plane);
		coordinate = new double[2*plane + 1][2*plane + 1];
	}

	private void setUpLines (int fold) {
		double A = Math.cos(2*Math.PI/2/fold);
		double B = Math.sin(2*Math.PI/2/fold);
		double[][] rotationalArray = new double[2][2];
		rotationalArray[0][0] = A;
		rotationalArray[0][1] = B;
		rotationalArray[1][0] = -B;
		rotationalArray[1][1] = A;
		double y = getHeight() - (getHeight()/2 + ICON_HEIGHT/2);
		for (int n = 0; n < fold; n++) {
			GLine reflectionLine = new GLine (getWidth()/2 + y*(powMatrix(rotationalArray, n)[0][1]), getHeight()/2 + ICON_HEIGHT/2 + y*(powMatrix(rotationalArray, n)[1][1]),
					getWidth()/2 - y*(powMatrix(rotationalArray, n)[0][1]), getHeight()/2 + ICON_HEIGHT/2 - y*(powMatrix(rotationalArray, n)[1][1]));
			add(reflectionLine);
			slope[n] = getSlope(reflectionLine);
		}
		
	}
	
	private void updateIcons() {
		remove(brushStatus);
		remove(brushSizeStatus);
		remove(colorMode);
		remove(speedStatus);
		brushStatus = new GLabel ("Brush: " + status, getWidth()/8, ICON_HEIGHT/2);
		brushStatus.move(-brushStatus.getWidth()/2, +brushStatus.getAscent()/2);
		brushSizeStatus = new GLabel ("Size x" + s, getWidth()*11/40, ICON_HEIGHT/2);
		brushSizeStatus.move(-brushSizeStatus.getWidth()/2, +brushSizeStatus.getAscent()/2);
		colorMode = new GLabel (mode, getWidth()*11/20, ICON_HEIGHT/2);
		colorMode.move(-colorMode.getWidth()/2, +colorMode.getAscent()/2);
		speedStatus = new GLabel ("Speed x" + speedLevel, getWidth()*11/40, ICON_HEIGHT/2);
		speedStatus.move(-speedStatus.getWidth()/2, +speedStatus.getAscent()/2);
		add(brushStatus);
		add(colorMode);
		if(auto == true) {
			add(speedStatus);
		} else {
			add(brushSizeStatus);
		}
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
		colorIcon8.setFilled(true);
		colorIcon8.setColor(plainColor[WHITE][0]);
		colorIcon9.setFilled(true);
		colorIcon9.setColor(plainColor[BLACK][0]);
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
			if (auto == true && speed > 0) {
				speed -= 5;
				speedLevel++;
			}
			else {
				s++;
			}
			updateIcons();
		}
		if (clickIcon4(e) == true) {
			if (auto == true) {
				speed += 5;
				speedLevel--;
			} else if (s != 0){
				s--;
			}
			updateIcons();
		}
		if (clickIcon5(e) == true) {
			if (line == false) {
				add(line1);
				add(line2);
				add(line3);
				add(line4);
				line = true;
			} else {
				remove(line1);
				remove(line2);
				remove(line3);
				remove(line4);
				line = false;
			}
		}
		if (clickIcon6(e) == true) {
			if (mixed == true) {
				mixed = false;
				pure = true;
				mode = "Pure";
				updateIcons();
				updateIcons();
			} else if(pure == true) {
				pure = false;
				plain = true;
				mode = "Plain";
				updateIcons();
			} else if (plain == true) {
				plain = false;
				mixed = true;
				mode = "Mixed";
				updateIcons();
			} else if (auto == true) {
				auto = false;
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
			if(clickColorIcon8(e) == true) {
				chosenPureColor = plainColor[WHITE][0]; 
			}
			if(clickColorIcon9(e) == true) {
				chosenPureColor = plainColor[BLACK][0]; 
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
			if(clickColorIcon8(e) == true) {
				chosenColor = plainColor[WHITE]; 
			}
			if(clickColorIcon9(e) == true) {
				chosenColor = plainColor[BLACK]; 
			}
		}
		if (mixed == true || auto == true) {
			if(clickColorIcon1(e) == true) {
				chosenMixedColor = RED;
			}
			if(clickColorIcon2(e) == true) {
				chosenMixedColor = ORANGE;
			}
			if(clickColorIcon3(e) == true) {
				chosenMixedColor = YELLOW;
			}
			if(clickColorIcon4(e) == true) {
				chosenMixedColor = GREEN;
			}
			if(clickColorIcon5(e) == true) {
				chosenMixedColor = BLUE;
			}
			if(clickColorIcon6(e) == true) {
				chosenMixedColor = CYAN;
			}
			if(clickColorIcon7(e) == true) {
				chosenMixedColor = PURPLE; 
			}
			if(clickColorIcon8(e) == true) {
				chosenMixedColor = WHITE; 
			}
			if(clickColorIcon9(e) == true) {
				chosenMixedColor = BLACK; 
			}
		}
		if(clickColorIcon1(e) == true) {
			colorTray.setColor(plainColor[RED][0]);
		}
		if(clickColorIcon2(e) == true) {
			colorTray.setColor(plainColor[ORANGE][0]);
		}
		if(clickColorIcon3(e) == true) {
			colorTray.setColor(plainColor[YELLOW][0]);
		}
		if(clickColorIcon4(e) == true) {
			colorTray.setColor(plainColor[GREEN][0]);
		}
		if(clickColorIcon5(e) == true) {
			colorTray.setColor(plainColor[BLUE][0]);
		}
		if(clickColorIcon6(e) == true) {
			colorTray.setColor(plainColor[CYAN][0]);
		}
		if(clickColorIcon7(e) == true) {
			colorTray.setColor(plainColor[PURPLE][0]);
		}
		if(clickColorIcon8(e) == true) {
			colorTray.setColor(plainColor[WHITE][0]);
		}
		if(clickColorIcon9(e) == true) {
			colorTray.setColor(plainColor[BLACK][0]);
		}
	}

	public void mouseMoved(MouseEvent e) {
		if (draw == true) {
			double x = e.getX() - s;
			double y = e.getY() - s;
			if (y > ICON_HEIGHT + s) {
				x = (x - getWidth()/2);
				y = (y - (getHeight()/2 + ICON_HEIGHT/2));
			}
			coordinate[1][0] = x;
			coordinate[0][1] = y;
			setUpBall(x, y);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
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
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
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
			} else if (auto == true) {
				auto = false;
				pure = true;
				mode = "Pure";
				updateIcons();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			auto = true;
			plain = false;
			pure = false;
			mixed = false;
			draw = false;
			status = "Off";
			mode = "Auto";
			updateIcons();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (pure == true) {
				int newColor = colorToInt(colorTray.getColor()) + 1;
				if (newColor == 9) {
					newColor = 0;
				}
				chosenPureColor = plainColor[newColor][0];
				colorTray.setColor(plainColor[newColor][0]);
			}
			if (plain == true) {
				int newColor = colorToInt(colorTray.getColor()) + 1;
				if (newColor == 9) {
					newColor = 0;
				}
				chosenColor = plainColor[newColor];
				colorTray.setColor(plainColor[newColor][0]);
			}
			if (mixed == true || auto == true) {
				int newColor = colorToInt(colorTray.getColor()) + 1;
				if (newColor == 9) {
					newColor = 0;
				}
				chosenMixedColor = newColor;
				colorTray.setColor(plainColor[newColor][0]);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (pure == true) {
				int newColor = colorToInt(colorTray.getColor()) - 1;
				if (newColor == -1) {
					newColor = 8;
				}
				chosenPureColor = plainColor[newColor][0];
				colorTray.setColor(plainColor[newColor][0]);
			}
			if (plain == true) {
				int newColor = colorToInt(colorTray.getColor()) - 1;
				if (newColor == -1) {
					newColor = 8;
				}
				chosenColor = plainColor[newColor];
				colorTray.setColor(plainColor[newColor][0]);
			}
			if (mixed == true || auto == true) {
				int newColor = colorToInt(colorTray.getColor()) - 1;
				if (newColor == -1) {
					newColor = 8;
				}
				chosenMixedColor = newColor;
				colorTray.setColor(plainColor[newColor][0]);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			if (pure == true) {
				int r = (colorTray.getColor()).getRed();
				int g = (colorTray.getColor()).getGreen();
				int b = (colorTray.getColor()).getBlue();
				r = (r + 10)%255;
				chosenPureColor = new Color (r,g,b);
				colorTray.setColor(chosenPureColor);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_G) {
			if (pure == true) {
				int r = (colorTray.getColor()).getRed();
				int g = (colorTray.getColor()).getGreen();
				int b = (colorTray.getColor()).getBlue();
				g = (g + 10)%255;
				chosenPureColor = new Color (r,g,b);
				colorTray.setColor(chosenPureColor);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_B) {
			if (pure == true) {
				int r = (colorTray.getColor()).getRed();
				int g = (colorTray.getColor()).getGreen();
				int b = (colorTray.getColor()).getBlue();
				b = (b + 10)%255;
				chosenPureColor = new Color (r,g,b);
				colorTray.setColor(chosenPureColor);
			}
		}
		if (auto == true) {
			if (e.getKeyCode() == KeyEvent.VK_UP && speed > 5) {
				speed -= 5;
				speedLevel++;
				updateIcons();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && speed < 200) {
				speed += 5;
				speedLevel--;
				updateIcons();
			}
		}
		if (auto == false) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				s++;
				updateIcons();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && s > 0) {
				s--;
				updateIcons();
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
		if (mixed == true) {
			newColor = mixColor(chosenMixedColor);
		}
		if (auto == true) {
			newColor = mixColor(chosenMixedColor);
		}
		if (rotation == true) {
			addPixel(x,y,symmetry);
		}
		if (reflection == true) {
			addPixel(x,y,plane);
		}
	}

	private void addPixel(double x, double y, int fold) {
		if (rotation == true) {
			double A = Math.cos(2*Math.PI/fold);
			double B = Math.sin(2*Math.PI/fold);
			double[][] rotationalArray = new double[2][2];
			rotationalArray[0][0] = A;
			rotationalArray[0][1] = B;
			rotationalArray[1][0] = -B;
			rotationalArray[1][1] = A;
			for (int n = 0; n < fold; n++) {
				GOval pixel = new GOval (getWidth()/2 + x*(powMatrix(rotationalArray, n)[0][0]) + y*(powMatrix(rotationalArray, n)[0][1]) - s/2, getHeight()/2 + ICON_HEIGHT/2 + x*(powMatrix(rotationalArray, n)[1][0]) + y*(powMatrix(rotationalArray, n)[1][1]) - s/2, s, s);
				pixel.setFilled(true);
				pixel.setColor(newColor);
				add(pixel);
			}
		}
		if (reflection == true) {
			for (int n = 0; n < fold; n++) {
				double[][] reflectionArray = new double[2][2];
				double m = slope[n];
				double A = (1 - m*m)/(1 + m*m);
				double B = 2*m/(1 + m*m);
				reflectionArray[0][0] = A;
				reflectionArray[0][1] = B;
				reflectionArray[1][0] = B;
				reflectionArray[1][1] = -A;
				for (int i = 1; i <= Math.pow(2, n-1); i++) {
					coordinate[(int) (Math.pow(2, n) + i)][0] = coordinate[i][0]*(powMatrix(reflectionArray, i)[0][0]) + coordinate[0][i]*(powMatrix(reflectionArray, i)[0][1]);
					coordinate[0][(int) (Math.pow(2, n) + i)] = coordinate[i][0]*(powMatrix(reflectionArray, i)[1][0]) + coordinate[0][i]*(powMatrix(reflectionArray, i)[1][1]);
				}
			}
			for (int j = 1; j < coordinate.length - 1; j++) {
				double X = coordinate[j][0];
				double Y = coordinate[0][j];
				GOval pixel = new GOval (getWidth()/2 + X - s/2, getHeight()/2 + ICON_HEIGHT/2 + Y - s/2, s, s);
				pixel.setFilled(true);
				pixel.setColor(newColor);
				add(pixel);
			}
		}
	}

	private Color mixColor(int chosenMixedColor) {
		if (chosenMixedColor == RED) {
			Color color = new Color (255,rgen.nextInt(25,220),rgen.nextInt(25,255));
			return color;
		} else if (chosenMixedColor == ORANGE) {
			Color color = new Color (255, rgen.nextInt(128,255),0);
			return color;
		} else if (chosenMixedColor == YELLOW) {
			Color color = new Color (rgen.nextInt(200,255), rgen.nextInt(230,255),0);
			return color;
		} else if (chosenMixedColor == GREEN) {
			Color color = new Color (rgen.nextInt(0,102), rgen.nextInt(102,255), 0);
			return color;
		} else if (chosenMixedColor == BLUE) {
			Color color = new Color (rgen.nextInt(0,102), rgen.nextInt(128,255),rgen.nextInt(102,255));
			return color;
		} else if (chosenMixedColor == CYAN) {
			Color color = new Color (rgen.nextInt(0,128), rgen.nextInt(0,255),255);
			return color;
		} else if (chosenMixedColor == PURPLE) {
			Color color = new Color (rgen.nextInt(0,255), 0,rgen.nextInt(127,255));
			return color;
		} else if (chosenMixedColor == WHITE) {
			Color color = Color.WHITE;
			return color;
		} else if (chosenMixedColor == BLACK) {
			int x = rgen.nextInt(0,255);
			Color color = new Color (x, x, x);
			return color;
		} else return null;
	}
	private Color randomizeColor(Color[] chosenColor) {
		Color color = chosenColor[rgen.nextInt(0,chosenColor.length - 1)];
		return color;
	}

	private boolean clickIcon1(MouseEvent e) {
		if (clickIcon3(e) || clickIcon4(e) || clickIcon5(e) || clickIcon6(e) ||
				clickColorIcon1(e) || clickColorIcon2(e) || clickColorIcon3(e) ||
				clickColorIcon4(e) || clickColorIcon5(e) || clickColorIcon6(e) ||
				clickColorIcon7(e) || clickColorIcon8(e) || clickColorIcon9(e)) {
			return false;
		}
		else return true;
	}

	private boolean clickIcon3(MouseEvent e) {
		if (icon3.contains(e.getX(),e.getY())) return true;
		else return false;
	}

	private boolean clickIcon4(MouseEvent e) {
		if (icon4.contains(e.getX(),e.getY())) return true;
		else return false;
	}

	private boolean clickIcon5(MouseEvent e) {
		if (icon5.contains(e.getX(),e.getY())) return true;
		else return false;
	}

	private boolean clickIcon6(MouseEvent e) {
		if (icon6.contains(e.getX(),e.getY())) return true;
		else return false;
	}

	private boolean clickColorIcon1(MouseEvent e) {
		if (colorIcon1.contains(e.getX(),e.getY())) return true;
		else return false;
	}

	private boolean clickColorIcon2(MouseEvent e) {
		if (colorIcon2.contains(e.getX(),e.getY())) return true;
		else return false;
	}

	private boolean clickColorIcon3(MouseEvent e) {
		if (colorIcon3.contains(e.getX(),e.getY())) return true;
		else return false;
	}
	private boolean clickColorIcon4(MouseEvent e) {
		if (colorIcon4.contains(e.getX(),e.getY())) return true;
		else return false;
	}
	private boolean clickColorIcon5(MouseEvent e) {
		if (colorIcon5.contains(e.getX(),e.getY())) return true;
		else return false;
	}
	private boolean clickColorIcon6(MouseEvent e) {
		if (colorIcon6.contains(e.getX(),e.getY())) return true;
		else return false;
	}
	private boolean clickColorIcon7(MouseEvent e) {
		if (colorIcon7.contains(e.getX(),e.getY())) return true;
		else return false;
	}
	private boolean clickColorIcon8(MouseEvent e) {
		if (colorIcon8.contains(e.getX(),e.getY())) return true;
		else return false;
	}
	private boolean clickColorIcon9(MouseEvent e) {
		if (colorIcon9.contains(e.getX(),e.getY())) return true;
		else return false;
	}

	private int colorToInt(Color color) {
		if (color == plainColor[RED][0]) return 0;
		else if (color == plainColor[ORANGE][0]) return 1;
		else if (color == plainColor[YELLOW][0]) return 2;
		else if (color == plainColor[GREEN][0]) return 3;
		else if (color == plainColor[BLUE][0]) return 4;
		else if (color == plainColor[CYAN][0]) return 5;
		else if (color == plainColor[PURPLE][0]) return 6;
		else if (color == plainColor[WHITE][0]) return 7;
		else if (color == plainColor[BLACK][0]) return 8;
		else return 0;
	}

	private double[][] powMatrix(double[][] matrix, int n) {
		double [][] result = new double[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			result[i][i] = 1;
		}
		for (int power = 0; power < n; power++) {
			double [][] temp = new double[matrix.length][matrix[0].length];
			for (int i = 0; i < matrix.length; i++) { 
				for (int j = 0; j < result[0].length; j++) { 
					for (int k = 0; k < matrix[0].length; k++) { 
						temp[i][j] += matrix[i][k] * result[k][j];
					}
				}
			}
			result = temp;
		}
		return result;
	}
	
	private double getSlope(GLine line) {
		double x2 = (line.getEndPoint()).getX();
		double y2 = (line.getEndPoint()).getY();
		double x1 =  (line.getStartPoint()).getX();
		double y1 = (line.getStartPoint()).getY();
		double slope = (y2 - y1)/(x2 - x1);
		return slope;
	}
}