/*
 * File: GraphicsContest.java
 * --------------------------
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;

public class GraphicsContest extends GraphicsProgram {

	/*removeall, auto, rgb, mergecolor*/
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private static final int ICON_HEIGHT = 30;
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700 + ICON_HEIGHT;
	private static final int PAUSE = 10;
	private static final double S_ICON_WIDTH = 0.8*WIDTH/20;
	private static final double M_ICON_WIDTH = WIDTH/10;
	private static final double L_ICON_WIDTH = 2.7*WIDTH/20;
	private static final int DEFAULT_PLANE_NUM = 4;
	private static final int DEFAULT_SYMMETRY_NUM = 8;
	private static final int DEFAULT_BLOCK_NUM = 2;
	private static final int MAX_PLANE = 18;
	private static final int MAX_SYMMETRY = 18;
	private static final int MAX_BLOCK = 10;
	private static final int MIN_PLANE = 0;
	private static final int MIN_SYMMETRY = 0;
	private static final int MIN_BLOCK = 0;
	private static final int MAX_BRUSH_SIZE = 50;
	private static final int MIN_BRUSH_SIZE = 0;
	private static final int DEFAULT_BRUSH_SIZE = 5;
	private static final int MAX_AUTO_SPEED_LEVEL = 25;
	private static final int MIN_AUTO_SPEED_LEVEL = 0;
	private static final int DEFAULT_AUTO_SPEED_DELAY = 200;
	private static final int DEFAULT_AUTO_SPEED_LEVEL = 1;
	private static final int DEFAULT_SPEED_INTERVAL = 5;
	private static final int RED = 0;
	private static final int ORANGE = 1;
	private static final int YELLOW = 2;
	private static final int GREEN = 3;
	private static final int BLUE = 4;
	private static final int CYAN = 5;
	private static final int PURPLE = 6;
	private static final int WHITE = 7;
	private static final int BLACK = 8;
	private static final int DEFAULT_COLOR = ORANGE;
	private static final int N_COLORS = 9;
	private static final int MIXED = 0;
	private static final int PURE = 1;
	private static final int PLAIN = 2;
	private static final int AUTO = 3;
	private static final int DEFAULT_COLOR_MODE = MIXED;
	private static final int ROT = 0;
	private static final int REF = 1;
	private static final int TRANS = 2;
	private static final int DEFAULT_SYM_MODE = REF;
	private static final int AXIS = 0;
	private static final int PLANE = 1;
	private static final int BLOCK = 2;
	private static final int SIZE = 3;
	private static final int SPEED = 4;
	private static final int DEFAULT_ADJUST = DEFAULT_SYM_MODE;
	
	private Color newColor;
	private boolean draw = false;
	private boolean line = true;
	private int ColorMode = DEFAULT_COLOR_MODE;
	private int SymMode = DEFAULT_SYM_MODE;
	private int Adjust = DEFAULT_ADJUST;
	private int s = DEFAULT_BRUSH_SIZE;
	private int speed = DEFAULT_AUTO_SPEED_DELAY;
	private int speedLevel = DEFAULT_AUTO_SPEED_LEVEL;
	private int symmetry = DEFAULT_SYMMETRY_NUM;
	private int plane = DEFAULT_PLANE_NUM;
	private int block = DEFAULT_BLOCK_NUM;
	private GRect colorTray;
	private GRect icon1;
	private GRect icon2;
	private GRect icon3;
	private GRect icon4;
	private GRect icon5;
	private GRect icon6;
	private GRect icon7;
	private GRect icon8;
	private GRect colorIcon1;
	private GRect colorIcon2;
	private GRect colorIcon3;
	private GRect colorIcon4;
	private GRect colorIcon5;
	private GRect colorIcon6;
	private GRect colorIcon7;
	private GRect colorIcon8;
	private GRect colorIcon9;
	private String status = "Off";
	private String colorModeStatus;
	private String symmetryModeStatus;
	private GLabel brushStatus;
	private GLabel brushSizeStatus;
	private GLabel speedStatus;
	private GLabel plusSize;
	private GLabel minusSize;
	private GLabel colorMode;
	private GLabel symmetryMode;
	private GLabel axisNum;
	private GLabel planeNum;
	private GLabel blockNum;
	private double[] slope;
	private double[][] coordinate;
	private GLine[] symLine;

	private Color[][] plainColor;
	private Color[] chosenColor;
	private Color chosenPureColor;
	private int chosenMixedColor;

	public void run() {
		setUp();
		autoDraw();
	}

	/* Method: setUp */
	/**
	 * Sets up the canvas before the user draws.
	 */

	private void setUp() {
		this.resize(WIDTH,HEIGHT);
		pause(PAUSE);
		addMouseListeners();
		addKeyListeners();
		setUpColors();
		setUpIcons();
		setUpLines();
		setUpColorChoice();
	}
	
	/* Method: setUpColors */
	/**
	 * Add the colors to the array "plainColor" that will be used throughout the program.
	 */
	
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
	
	/* Method: setUpIcons */
	/**
	 * Creates all the over-head icons including the color tray on the top left corner.
	 */
	
	private void setUpIcons() {
		colorTray = new GRect (0, 0,S_ICON_WIDTH, ICON_HEIGHT);
		add(colorTray);
		colorTray.setFilled(true);
		colorTray.setColor(plainColor[DEFAULT_COLOR][0]);
		icon1 = new GRect (colorTray.getX() + colorTray.getWidth(), 0, L_ICON_WIDTH, ICON_HEIGHT);
		add(icon1);
		icon2 = new GRect (icon1.getX() + icon1.getWidth(), 0, L_ICON_WIDTH, ICON_HEIGHT);
		add(icon2);
		icon3 = new GRect (icon2.getX() + icon2.getWidth(), 0, S_ICON_WIDTH, ICON_HEIGHT);
		add(icon3);
		icon4 = new GRect (icon3.getX() + icon3.getWidth(), 0, S_ICON_WIDTH, ICON_HEIGHT);
		add(icon4);
		icon5 = new GRect (icon4.getX() + icon4.getWidth(), 0, S_ICON_WIDTH, ICON_HEIGHT);
		add(icon5);
		icon6 = new GRect (icon5.getX() + icon5.getWidth(), 0, S_ICON_WIDTH, ICON_HEIGHT);
		add(icon6);
		icon7 = new GRect (icon6.getX() + icon6.getWidth(), 0, M_ICON_WIDTH, ICON_HEIGHT);
		add(icon7);
		icon8 = new GRect (icon7.getX() + icon7.getWidth(), 0, M_ICON_WIDTH, ICON_HEIGHT);
		add(icon8);
		brushStatus = new GLabel ("Brush: " + status, icon1.getX() + icon1.getWidth()/2, ICON_HEIGHT/2);
		brushStatus.move(-brushStatus.getWidth()/2, +brushStatus.getAscent()/2);
		add(brushStatus);
		brushSizeStatus = new GLabel ("Size x" + s, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
		brushSizeStatus.move(-brushSizeStatus.getWidth()/2, +brushSizeStatus.getAscent()/2);
		if (Adjust == SIZE) add(brushSizeStatus);
		axisNum = new GLabel ("Axis x" + symmetry, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
		axisNum.move(-axisNum.getWidth()/2, +axisNum.getAscent()/2);
		if (Adjust == AXIS) add(axisNum);
		planeNum = new GLabel ("Plane x" + plane, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
		planeNum.move(-planeNum.getWidth()/2, +planeNum.getAscent()/2);
		if (Adjust == PLANE) add(planeNum);
		blockNum = new GLabel ("Block: " + block + " x " + block, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
		blockNum.move(-blockNum.getWidth()/2, +blockNum.getAscent()/2);
		speedStatus = new GLabel ("Speed x" + speedLevel, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
		speedStatus.move(-speedStatus.getWidth()/2, +speedStatus.getAscent()/2);
		if (ColorMode == AUTO) add(speedStatus);
		plusSize = new GLabel ("+", icon3.getX() + icon3.getWidth()/2, ICON_HEIGHT/2);
		plusSize.move(-plusSize.getWidth()/2, +plusSize.getAscent()/2);
		add(plusSize);
		minusSize = new GLabel ("-", icon4.getX() + icon4.getWidth()/2, ICON_HEIGHT/2);
		minusSize.move(-minusSize.getWidth()/2, +minusSize.getAscent()/2);
		add(minusSize);
		GLabel iconSign = new GLabel ("H", icon5.getX() + icon5.getWidth()/2, ICON_HEIGHT/2);
		iconSign.move(-iconSign.getWidth()/2, +iconSign.getAscent()/2);
		add(iconSign);
		GLabel iconSign2 = new GLabel ("C", icon6.getX() + icon6.getWidth()/2, ICON_HEIGHT/2);
		iconSign2.move(-iconSign2.getWidth()/2, +iconSign2.getAscent()/2);
		add(iconSign2);
		colorModeStatus = printColorMode(ColorMode);
		colorMode = new GLabel (colorModeStatus, icon7.getX() + icon7.getWidth()/2, ICON_HEIGHT/2);
		colorMode.move(-colorMode.getWidth()/2, +colorMode.getAscent()/2);
		add(colorMode);
		symmetryModeStatus = printSymMode(SymMode);
		symmetryMode = new GLabel (symmetryModeStatus, icon8.getX() + icon8.getWidth()/2, ICON_HEIGHT/2);
		symmetryMode.move(-symmetryMode.getWidth()/2, +symmetryMode.getAscent()/2);
		add(symmetryMode);
		double width = (getWidth() - (icon8.getX() + icon8.getWidth()))/N_COLORS;
		colorIcon1 = new GRect (icon8.getX() + icon8.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon1);
		colorIcon2 = new GRect (colorIcon1.getX() + colorIcon1.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon2);
		colorIcon3 = new GRect (colorIcon2.getX() + colorIcon2.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon3);
		colorIcon4 = new GRect (colorIcon3.getX() + colorIcon3.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon4);
		colorIcon5 = new GRect (colorIcon4.getX() + colorIcon4.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon5);
		colorIcon6 = new GRect (colorIcon5.getX() + colorIcon5.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon6);
		colorIcon7 = new GRect (colorIcon6.getX() + colorIcon6.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon7);
		colorIcon8 = new GRect (colorIcon7.getX() + colorIcon7.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon8);
		colorIcon9 = new GRect (colorIcon8.getX() + colorIcon8.getWidth(), 0, width, ICON_HEIGHT);
		add(colorIcon9);
		if (ColorMode == PURE) {
			chosenPureColor = plainColor[DEFAULT_COLOR][0]; 
		}
		if (ColorMode == PLAIN) {
			chosenColor = plainColor[DEFAULT_COLOR]; 
		}
		if (ColorMode == MIXED) {
			chosenMixedColor = DEFAULT_COLOR;
		} 
	}

	/* Method: printColorMode && printSymMode */
	/**
	 * Takes in the number of the mode to print the corresponding status.
	 */
	private String printColorMode(int mode) {
		String result = "";
		if (mode == MIXED) result += "Mixed";
		else if (mode == PURE) result += "Pure";
		else if (mode == PLAIN) result += "Plain";
		else if (mode == AUTO) result += "Auto";
		return result;
	}
	
	private String printSymMode(int mode) {
		String result = "";
		if (mode == ROT) result += "Rotation";
		else if (mode == REF) result += "Reflection";
		else if (mode == TRANS) result += "Translation";
		return result;
	}

	/* Method: setUpLines */
	/**
	 * Creates the division lines depending on the symmetry mode chosen by the user.
	 */
	private void setUpLines() {
		if (SymMode == ROT) setUpAxes(symmetry);
		if (SymMode == REF) setUpPlanes(plane);
		if (SymMode == TRANS) setUpBlocks(block);
		addSymLine(symLine);
		coordinate = new double[2*plane + 1][2*plane + 1];
	}
	
	/* Method: setUpAxes */
	/**
	 * Creates axes for the rotation mode + add those line onto the array containing lines.
	 */
	private void setUpAxes (int fold) {
		double A = Math.cos(2*Math.PI/fold);
		double B = Math.sin(2*Math.PI/fold);
		double[][] rotationalArray = new double[2][2];
		rotationalArray[0][0] = A;
		rotationalArray[0][1] = B;
		rotationalArray[1][0] = -B;
		rotationalArray[1][1] = A;
		symLine = new GLine[fold];
		double y = getHeight() - (getHeight()/2 + ICON_HEIGHT/2);
		for (int n = 0; n < fold; n++) {
			GLine symmetryLine = new GLine (getWidth()/2, getHeight()/2 + ICON_HEIGHT/2, getWidth()/2 + y*(powMatrix(rotationalArray, n)[0][1]), getHeight()/2 + ICON_HEIGHT/2 + y*(powMatrix(rotationalArray, n)[1][1]));
			symLine[n] = symmetryLine;
		}
	}
	
	/* Method: setUpPlanes */
	/**
	 * Creates planes + add those line onto the array containing lines. 
	 * It also calculate the slope of those planes for the reflection mode.
	 */
	private void setUpPlanes (int fold) {
		double A = Math.cos(2*Math.PI/2/fold);
		double B = Math.sin(2*Math.PI/2/fold);
		double[][] rotationalArray = new double[2][2];
		rotationalArray[0][0] = A;
		rotationalArray[0][1] = B;
		rotationalArray[1][0] = -B;
		rotationalArray[1][1] = A;
		slope = new double[fold];
		symLine = new GLine[fold];
		double y = getHeight() - (getHeight()/2 + ICON_HEIGHT/2);
		for (int n = 0; n < fold; n++) {
			GLine symmetryLine = new GLine (getWidth()/2 + y*(powMatrix(rotationalArray, n)[0][1]), getHeight()/2 + ICON_HEIGHT/2 + y*(powMatrix(rotationalArray, n)[1][1]),
					getWidth()/2 - y*(powMatrix(rotationalArray, n)[0][1]), getHeight()/2 + ICON_HEIGHT/2 - y*(powMatrix(rotationalArray, n)[1][1]));
			slope[n] = getSlope(symmetryLine);
			symLine[n] = symmetryLine;
		}
	}
	
	/* Method: setUpBlocks */
	/**
	 * Creates n x n blocks for the translation mode + add those line onto 
	 * the array containing lines.
	 */
	private void setUpBlocks (int fold) {
		symLine = new GLine[fold*2];
		for (int i = 0; i < fold; i++) {
			GLine symmetryLine = new GLine (getWidth()*i/fold, ICON_HEIGHT, getWidth()*i/fold, getHeight());
			symLine[i] = symmetryLine;
		}
		for (int j = 0; j < fold; j++) {
			GLine symmetryLine = new GLine (0, ICON_HEIGHT + (getHeight() - ICON_HEIGHT)*j/fold, getWidth(), ICON_HEIGHT + (getHeight() - ICON_HEIGHT)*j/fold);
			symLine[fold + j] = symmetryLine;
		}
	}
	/* Method: addSymLine & removeSymLine */
	/**
	 * add/remove all the division lines onto the screen.
	 */
	private void addSymLine(GLine[] symLine) {
		for(int i = 0; i < symLine.length; i++) {
			add(symLine[i]);
		}
	}
	private void removeSymLine(GLine[] symLine) {
		for(int i = 0; i < symLine.length; i++) {
			remove(symLine[i]);
		}
	}
	
	/* Method: setUpColorChoices */
	/**
	 * Fills color icons with colors so the user knows which one to choose.
	 */
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

	/* Method: updateIcons */
	/**
	 * Update how each icon appears upon clicking (For example, the color of the tray is 
	 * changed according to color chosen). 
	 */
	private void updateIcons() {
		remove(brushStatus);
		remove(brushSizeStatus);
		remove(colorMode);
		remove(symmetryMode);
		remove(speedStatus);
		remove(axisNum);
		remove(planeNum);
		remove(blockNum);
		removeSymLine(symLine);
		coordinate = new double[2*plane + 1][2*plane + 1];
		brushStatus = new GLabel ("Brush: " + status, icon1.getX() + icon1.getWidth()/2, ICON_HEIGHT/2);
		brushStatus.move(-brushStatus.getWidth()/2, +brushStatus.getAscent()/2);
		brushSizeStatus = new GLabel ("Size x" + s, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
		brushSizeStatus.move(-brushSizeStatus.getWidth()/2, +brushSizeStatus.getAscent()/2);
		colorModeStatus = printColorMode(ColorMode);
		colorMode = new GLabel (colorModeStatus, icon7.getX() + icon7.getWidth()/2, ICON_HEIGHT/2);
		colorMode.move(-colorMode.getWidth()/2, +colorMode.getAscent()/2);
		symmetryModeStatus = printSymMode(SymMode);
		symmetryMode = new GLabel (printSymMode(SymMode), icon8.getX() + icon8.getWidth()/2, ICON_HEIGHT/2);
		symmetryMode.move(-symmetryMode.getWidth()/2, +symmetryMode.getAscent()/2);
		speedStatus = new GLabel ("Speed x" + speedLevel, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
		speedStatus.move(-speedStatus.getWidth()/2, +speedStatus.getAscent()/2);
		add(brushStatus);
		add(colorMode);
		add(symmetryMode);
		if (SymMode == ROT) setUpAxes(symmetry);
		if (SymMode == REF) setUpPlanes(plane);
		if (SymMode == TRANS) setUpBlocks(block);
		if (Adjust == SIZE) {
			add(brushSizeStatus);
		}
		if (Adjust == AXIS) {
			axisNum = new GLabel ("Axis x" + symmetry, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
			axisNum.move(-axisNum.getWidth()/2, +axisNum.getAscent()/2);
			add(axisNum);
		}
		if (Adjust == PLANE) {
			planeNum = new GLabel ("Plane x" + plane, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
			planeNum.move(-planeNum.getWidth()/2, +planeNum.getAscent()/2);
			add(planeNum);
		}
		if (Adjust == BLOCK) {
			blockNum = new GLabel ("Block: " + block + " x " + block, icon2.getX() + icon2.getWidth()/2, ICON_HEIGHT/2);
			blockNum.move(-blockNum.getWidth()/2, +blockNum.getAscent()/2);
			add(blockNum);
		}
		if (line == true) {
			addSymLine(symLine);
		}
		if (Adjust == SPEED) {
			remove(brushSizeStatus);
			remove(axisNum);
			remove(planeNum);
			remove(blockNum);
			add(speedStatus);
		}
	}
	
	/* Method: mouseClicked */
	/**
	 * Contains important information of how the clicking of each icon influences the change.
	 *  - Clicking icon 1 when the brush is off will turn on the brush, and vice versa.
	 *  - Icon 2 allows the user to adjust the number of planes, axes, blocks. 
	 *  However, clicking it again will allow the user to adjust brush size.
	 *  - Icon 3 increases the value of what is current shown in icon 2.
	 *  - Icon 4 decreases the value of what is current shown in icon 2.
	 *  - Icon 5 hides all the division lines.
	 *  - Icon 6 clears screen to a blank.
	 *  - Icon 7 allows the user to change color modes from Mixed to Pure to Plain.
	 *  - Icon 8 allows the user to change symmetry modes from Reflection to Rotation to
	 *  Translation.
	 *  - ColorIcon 1-9 changes colors of the brush. 
	 */
	public void mouseClicked(MouseEvent e) {
		if (clickIcon1(e) == true) {
			if (draw == false)  {
				status = "On";
				draw = true;
			} else if (draw == true) {
				status = "Off";
				draw = false;
			}
			updateIcons();
		}
		if (clickIcon2(e) == true) {
			if (ColorMode != AUTO) {
				if (Adjust == SIZE) {
					if (SymMode == ROT) {
						Adjust = AXIS;
					} else if (SymMode == REF) {
						Adjust = PLANE;
					} else if (SymMode == TRANS) {
						Adjust = BLOCK;
					}
				} else if (Adjust != SIZE) {
					Adjust = SIZE;
				}
			} else {
				if (Adjust == SIZE) {
					if (SymMode == ROT) {
						Adjust = AXIS;
					} else if (SymMode == REF) {
						Adjust = PLANE;
					} else if (SymMode == TRANS) {
						Adjust = BLOCK;
					}
				} else if (Adjust != SIZE) {
					Adjust = SIZE;
				}
			}
			updateIcons();
		}
		if (clickIcon3(e) == true) {
			if (ColorMode == AUTO && Adjust == SPEED && speedLevel < MAX_AUTO_SPEED_LEVEL) {
				speed -= DEFAULT_SPEED_INTERVAL;
				speedLevel++;
			} else if (Adjust == SIZE && s < MAX_BRUSH_SIZE) {
				s++;
			} else if (Adjust == AXIS && symmetry < MAX_SYMMETRY) {
				symmetry++;
			} else if (Adjust == PLANE && plane < MAX_PLANE) {
				plane++;
			} else if (Adjust == BLOCK && block < MAX_BLOCK) {
				block++;
			}
			updateIcons();
		}
		if (clickIcon4(e) == true) {
			if (ColorMode == AUTO && Adjust == SPEED && speedLevel > MIN_AUTO_SPEED_LEVEL) {
				speed += DEFAULT_SPEED_INTERVAL;
				speedLevel--;
			} else if (Adjust == SIZE && (s != MIN_BRUSH_SIZE)) {
				s--;
			} else if (Adjust == AXIS && symmetry > MIN_SYMMETRY + 1) {
				symmetry--;
			} else if (Adjust == PLANE && plane > MIN_PLANE + 1) {
				plane--;
			} else if (Adjust == BLOCK && block > MIN_BLOCK + 1) {
				block--;
			}
			updateIcons();
		}
		if (clickIcon5(e) == true) {
			if (line == false) {
				addSymLine(symLine);
				line = true;
			} else {
				removeSymLine(symLine);
				line = false;
			}
		}
		if (clickIcon6(e) == true) {
			removeAll();
			setUpColors();
			setUpIcons();
			setUpColorChoice();
			updateIcons();
		}
		if (clickIcon7(e) == true) {
			if (ColorMode == MIXED) {
				ColorMode = PURE;
				chosenPureColor = plainColor[DEFAULT_COLOR][0]; 
			} else if(ColorMode == PURE) {
				ColorMode = PLAIN;
				chosenColor = plainColor[DEFAULT_COLOR];
			} else if (ColorMode == PLAIN) {
				ColorMode = MIXED;
				chosenMixedColor = DEFAULT_COLOR;
			} else if (ColorMode == AUTO) {
				ColorMode = DEFAULT_COLOR_MODE;
				Adjust = DEFAULT_ADJUST;
			}
			updateIcons();
			colorTray.setColor(plainColor[DEFAULT_COLOR][0]);
		}
		if (clickIcon8(e) == true) {
			if (SymMode == ROT) {
				SymMode = TRANS;
				Adjust = BLOCK;
			} else if (SymMode == REF) {
				SymMode = ROT;
				Adjust = AXIS;
			} else if (SymMode == TRANS) {
				SymMode = REF;
				Adjust = PLANE;
			} 
			updateIcons();
		}
		if (ColorMode == PURE) {
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
		if (ColorMode == PLAIN) {
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
		if (ColorMode == MIXED || ColorMode == AUTO) {
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

	/* Method: mouseMoved */
	/**
	 * Enables the user to draw circles anywhere the mouse is moved to. It first 
	 * references the x y coordinate of the mouse to the center of the screen
	 * for further calculation purposes.
	 */
	
	public void mouseMoved(MouseEvent e) {
		if (draw == true) {
			double x = e.getX() - s/2;
			double y = e.getY() - s/2;
			if (y > ICON_HEIGHT) {
				x = (x - getWidth()/2);
				y = (y - (getHeight()/2 + ICON_HEIGHT/2));
				draw(x, y);
			}
		}
	}
	
	/* Method: draw */
	/**
	 * First, choose colors depending on the color mode. Then, add pixels depending 
	 * on the current symmetry mode. 
	 */
	private void draw(double x, double y) {
		if (ColorMode == PURE) {
			newColor = chosenPureColor; 
		}
		if (ColorMode == PLAIN) {
			newColor = randomizeColor(chosenColor);
		}
		if (ColorMode == MIXED) {
			newColor = mixColor(chosenMixedColor);
		}
		if (ColorMode == AUTO) {
			newColor = mixColor(chosenMixedColor);
		}
		if (SymMode == ROT) {
			addPixels(x,y,symmetry);
		}
		if (SymMode == REF) {
			addPixels(x,y,plane);
		}
		if (SymMode == TRANS) {
			addPixels(x,y,block);
		}
	}
	
	/* Method: randomizeColor */
	/**
	 * Takes in the array containing color and randomly assign the color within that
	 * array to the drawn pixel one at a time
	 */
	private Color randomizeColor(Color[] chosenColor) {
		Color color = chosenColor[rgen.nextInt(0,chosenColor.length - 1)];
		return color;
	}
	
	/* Method: mixColor */
	/**
	 * Takes in the color index and mix that color with colors of similar shades
	 */
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

	/* Method: addPixels */
	/**
	 * Depending on the symmetry mode, this methods adds multiple circles on the screen. 
	 * The idea of each mode has to do with matrix transformation of vectors.  
	 */
	private void addPixels(double x, double y, int fold) {
		if (SymMode == ROT) {
			double A = Math.cos(2*Math.PI/fold);
			double B = Math.sin(2*Math.PI/fold);
			double[][] rotationalArray = new double[2][2];
			rotationalArray[0][0] = A;
			rotationalArray[0][1] = B;
			rotationalArray[1][0] = -B;
			rotationalArray[1][1] = A;
			for (int n = 0; n < fold; n++) {
				double X = x*(powMatrix(rotationalArray, n)[0][0]) + y*(powMatrix(rotationalArray, n)[0][1]);
				double Y = x*(powMatrix(rotationalArray, n)[1][0]) + y*(powMatrix(rotationalArray, n)[1][1]);
				if (getHeight()/2 + ICON_HEIGHT/2 + Y - s/2 > ICON_HEIGHT) {
					GOval pixel = new GOval (getWidth()/2 + X - s/2, getHeight()/2 + ICON_HEIGHT/2 + Y - s/2, s, s);
					pixel.setFilled(true);
					pixel.setColor(newColor);
					add(pixel);
				}
			}
		}
		if (SymMode == REF) {
			coordinate[1][0] = x;
			coordinate[0][1] = y;
			coordinate[fold + 1][0] = -x;
			coordinate[0][fold + 1] = y;
			for (int n = 1; n < fold; n++) {
				double[][] reflectionArray = new double[2][2];
				double m = slope[n];
				double A = (1 - m*m)/(1 + m*m);
				double B = 2*m/(1 + m*m);
				reflectionArray[0][0] = A;
				reflectionArray[0][1] = B;
				reflectionArray[1][0] = B;
				reflectionArray[1][1] = -A;
				coordinate[n + 1][0] = x*(reflectionArray[0][0]) + y*(reflectionArray[0][1]);
				coordinate[0][n + 1] = x*(reflectionArray[1][0]) + y*(reflectionArray[1][1]);
				coordinate[n + fold + 1][0] = -x*(reflectionArray[0][0]) + y*(reflectionArray[0][1]);
				coordinate[0][n + fold + 1] = -x*(reflectionArray[1][0]) + y*(reflectionArray[1][1]);
			}
			for (int j = 1; j < coordinate.length ; j++) {
				double X = coordinate[j][0];
				double Y = coordinate[0][j];
				if (getHeight()/2 + ICON_HEIGHT/2 + Y - s/2 > ICON_HEIGHT) {
					GOval pixel = new GOval (getWidth()/2 + X - s/2, getHeight()/2 + ICON_HEIGHT/2 + Y - s/2, s, s);
					pixel.setFilled(true);
					pixel.setColor(newColor);
					add(pixel);
				}
			}
		}
		if (SymMode == TRANS) {
			x += getWidth()/2;
			y += getHeight()/2 - ICON_HEIGHT/2;
			double width = getWidth()/fold;
			double height = (getHeight() - ICON_HEIGHT)/fold;
			while (true) {
				x -= width;
				if (x < 0) {
					x += width;
					break;
				}
			}
			while (true) {
				y -= height;
				if (y < 0) {
					y += height;
					break;
				}
			}
			double X = 0;
			double Y = 0;
			for (int i = 0; i < fold; i++) {
				for (int j = 0; j < fold; j++) {
					if (i % 2 == 0 && j % 2 == 0) {
						X = width*i + x;
						Y = ICON_HEIGHT + height*(j) + y;
					} else if (i % 2 != 0 && j % 2 == 0) {
						X = width*(i+1) - x;
						Y = ICON_HEIGHT + height*(j) + y;
					} else if (i % 2 == 0 && j % 2 != 0) {
						X = width*i + x;
						Y = ICON_HEIGHT + height*(j+1) - y;
					} else if (i % 2 != 0 && j % 2 != 0) {
						X = width*(i+1) - x;
						Y = ICON_HEIGHT + height*(j+1) - y;
					}
					if (Y - s/2 > ICON_HEIGHT) {
						GOval pixel = new GOval (X - s/2, Y - s/2, s, s);
						pixel.setFilled(true);
						pixel.setColor(newColor);
						add(pixel);
					}
				}
			}
		}
	}
	
	/* Method: keyPressed */
	/**
	 * Contains important information of how the entering of each key influences the change.
	 *  - Space bar = icon 1 (turn brush on/off) 
	 *  - ALT = icon 2 (switch adjust symmetry to brush size)
	 *  - Up = icon 3 (+) 
	 *  - Down = icon 4 (-) 
	 *  - H = icon 5 (hide)
	 *  - C = icon 6 (clear)
	 * 	- Shift = icon 7 (color mode)
	 * 	- CTRL = icon 8 (symmetry mode)
	 *  - Left = go from current color to the one on the left
	 *  - Right = go from current color to the one on the right
	 *  - R/G/B = increase the value of red green blue of the color (under PURE mode)
	 *  - ENTER = enable/disable the auto draw mode
	 */
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
		if (e.getKeyCode() == KeyEvent.VK_ALT) {
			if (ColorMode != AUTO) {
				if (Adjust == SIZE) {
					if (SymMode == ROT) {
						Adjust = AXIS;
					} else if (SymMode == REF) {
						Adjust = PLANE;
					} else if (SymMode == TRANS) {
						Adjust = BLOCK;
					}
				} else if (Adjust != SIZE) {
					Adjust = SIZE;
				}
				updateIcons();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (Adjust == SPEED && speedLevel < MAX_AUTO_SPEED_LEVEL) {
				speed -= DEFAULT_SPEED_INTERVAL;
				speedLevel++;
			} else if (Adjust == SIZE && s < MAX_BRUSH_SIZE) {
				s++;
			} else if (Adjust == AXIS && symmetry < MAX_SYMMETRY) {
				symmetry++;
			} else if (Adjust == PLANE && plane < MAX_PLANE) {
				plane++;
			} else if (Adjust == BLOCK && block < MAX_BLOCK) {
				block++;
			}
			updateIcons();

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (ColorMode == AUTO && Adjust == SPEED && speedLevel > MIN_AUTO_SPEED_LEVEL) {
				speed += DEFAULT_SPEED_INTERVAL;
				speedLevel--;
			} else if (Adjust == SIZE && (s != MIN_BRUSH_SIZE)) {
				s--;
			} else if (Adjust == AXIS && symmetry > MIN_SYMMETRY + 1) {
				symmetry--;
			} else if (Adjust == PLANE && plane > MIN_PLANE + 1) {
				plane--;
			} else if (Adjust == BLOCK && block > MIN_BLOCK + 1) {
				block--;
			}
			updateIcons();
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			removeAll();
			setUpColors();
			setUpIcons();
			setUpColorChoice();
			updateIcons();
		}
		if (e.getKeyCode() == KeyEvent.VK_H) {
			if (line == false) {
				addSymLine(symLine);
				line = true;
			} else {
				removeSymLine(symLine);
				line = false;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			if(ColorMode == PURE) {
				ColorMode = PLAIN;
				chosenColor = plainColor[DEFAULT_COLOR];
			} else if (ColorMode == PLAIN) {
				ColorMode = MIXED;
				chosenMixedColor = DEFAULT_COLOR;
			} else if (ColorMode == MIXED) {
				ColorMode = PURE;
				chosenPureColor = plainColor[DEFAULT_COLOR][0]; 
			} else if (ColorMode == AUTO) {
				ColorMode = DEFAULT_COLOR_MODE;
				Adjust = DEFAULT_ADJUST;
			}
			updateIcons();
			colorTray.setColor(plainColor[DEFAULT_COLOR][0]);
		}
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			if (SymMode == ROT) {
				SymMode = TRANS;
				Adjust = BLOCK;
			} else if (SymMode == REF) {
				SymMode = ROT;
				Adjust = AXIS;
			} else if (SymMode == TRANS) {
				SymMode = REF;
				Adjust = PLANE;
			}
			updateIcons();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (ColorMode == PURE) {
				int newColor = colorToInt(colorTray.getColor()) + 1;
				if (newColor == 9) {
					newColor = 0;
				}
				chosenPureColor = plainColor[newColor][0];
				colorTray.setColor(plainColor[newColor][0]);
			}
			if (ColorMode == PLAIN) {
				int newColor = colorToInt(colorTray.getColor()) + 1;
				if (newColor == 9) {
					newColor = 0;
				}
				chosenColor = plainColor[newColor];
				colorTray.setColor(plainColor[newColor][0]);
			}
			if (ColorMode == MIXED || ColorMode == AUTO) {
				int newColor = colorToInt(colorTray.getColor()) + 1;
				if (newColor == 9) {
					newColor = 0;
				}
				chosenMixedColor = newColor;
				colorTray.setColor(plainColor[newColor][0]);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (ColorMode == PURE) {
				int newColor = colorToInt(colorTray.getColor()) - 1;
				if (newColor == -1) {
					newColor = 8;
				}
				chosenPureColor = plainColor[newColor][0];
				colorTray.setColor(plainColor[newColor][0]);
			}
			if (ColorMode == PLAIN) {
				int newColor = colorToInt(colorTray.getColor()) - 1;
				if (newColor == -1) {
					newColor = 8;
				}
				chosenColor = plainColor[newColor];
				colorTray.setColor(plainColor[newColor][0]);
			}
			if (ColorMode == MIXED || ColorMode == AUTO) {
				int newColor = colorToInt(colorTray.getColor()) - 1;
				if (newColor == -1) {
					newColor = 8;
				}
				chosenMixedColor = newColor;
				colorTray.setColor(plainColor[newColor][0]);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			if (ColorMode == PURE) {
				int r = (colorTray.getColor()).getRed();
				int g = (colorTray.getColor()).getGreen();
				int b = (colorTray.getColor()).getBlue();
				r = (r + 10)%255;
				chosenPureColor = new Color (r,g,b);
				colorTray.setColor(chosenPureColor);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_G) {
			if (ColorMode == PURE) {
				int r = (colorTray.getColor()).getRed();
				int g = (colorTray.getColor()).getGreen();
				int b = (colorTray.getColor()).getBlue();
				g = (g + 10)%255;
				chosenPureColor = new Color (r,g,b);
				colorTray.setColor(chosenPureColor);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_B) {
			if (ColorMode == PURE) {
				int r = (colorTray.getColor()).getRed();
				int g = (colorTray.getColor()).getGreen();
				int b = (colorTray.getColor()).getBlue();
				b = (b + 10)%255;
				chosenPureColor = new Color (r,g,b);
				colorTray.setColor(chosenPureColor);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (ColorMode != AUTO) {
				ColorMode = AUTO;
				draw = false;
				Adjust = SPEED;
				status = "Off";
			} else {
				ColorMode = DEFAULT_COLOR_MODE;
				Adjust = DEFAULT_ADJUST; 
			}
			updateIcons();
		}
	}

	/* Method: clickIcon1-8 & clickColorIcon1-9 */
	/**
	 * returns true when clicking on that particular region else return false.
	 */
	private boolean clickIcon1(MouseEvent e) {
		if (clickIcon2(e) || clickIcon3(e) || clickIcon4(e) || clickIcon5(e) || clickIcon6(e) || 
				clickIcon7(e) || clickIcon8(e) || clickColorIcon1(e) || clickColorIcon2(e) || clickColorIcon3(e) ||
				clickColorIcon4(e) || clickColorIcon5(e) || clickColorIcon6(e) ||
				clickColorIcon7(e) || clickColorIcon8(e) || clickColorIcon9(e)) {
			return false;
		}
		else return true;
	}
	private boolean clickIcon2(MouseEvent e) {
		if (icon2.contains(e.getX(),e.getY())) return true;
		else return false;
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
	private boolean clickIcon7(MouseEvent e) {
		if (icon7.contains(e.getX(),e.getY())) return true;
		else return false;
	}
	private boolean clickIcon8(MouseEvent e) {
		if (icon8.contains(e.getX(),e.getY())) return true;
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

	/* Method: colorToInt */
	/**
	 * takes in current color of the tray and returns color index.
	 */
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

	/* Method: powMatrix */
	/**
	 * raises matrix to the power of n.
	 */
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

	/* Method: getSlope */
	/**
	 * returns the slope of a line.
	 */
	private double getSlope(GLine line) {
		double x2 = (line.getEndPoint()).getX();
		double y2 = (line.getEndPoint()).getY();
		double x1 =  (line.getStartPoint()).getX();
		double y1 = (line.getStartPoint()).getY();
		double slope = -(y2 - y1)/(x2 - x1);
		return slope;
	}

	/* Method: autoDraw */
	/**
	 * draws pictures on the screen automatically and resets the screen to blank and start over
	 * once the picture is too large
	 */
	private void autoDraw() {
		double x = getWidth()/2 - s;
		double y = getHeight()/2 + ICON_HEIGHT/2;
		x = x - (getWidth()/2 - s);
		y = y - (getHeight()/2 + ICON_HEIGHT/2 - s);
		int c = 0;
		int n = 1;
		while(true) {
			if(ColorMode == AUTO) {
				double dx = s;
				double dy = s;
				if (c == 0) {
					for (int i = 0; i < n; i++) {
						x = x + dx;
						draw(x, y);
					}
				}
				if (c == 1) {
					for (int i = 0; i < n; i++) {
						x = x + dx;
						y = y + dy;
						draw(x, y);
					}
				}
				if (c == 2) {
					for (int i = 0; i < n; i++) {
						y = y + dy;
						draw(x, y);
					}
				}
				if (c == 3) {
					for (int i = 0; i < n+1; i++) {
						x = x - dx;
						y = y + dy;
						draw(x, y);
					}
				}
				if (c == 4) {
					for (int i = 0; i < n+1; i++) {
						x = x - dx;
						draw(x, y);
					}
				}
				if (c == 5) {
					for (int i = 0; i < n+1; i++) {
						x = x - dx;
						y = y - dy;
						draw(x, y);
					}
				}
				if (c == 6) {
					for (int i = 0; i < n; i++) {
						y = y - dy;
						draw(x, y);
					}
				}
				if (c == 7) {
					for (int i = 0; i < n+1; i++) {
						x = x + dx;
						y = y - dy;
						draw(x, y);
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
}
