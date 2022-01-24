import processing.core.PApplet;
import processing.core.PConstants;

public class ClickableText {
	String text;
	int textPosX;
	int textPosY;
	int textSize;
	boolean isCenter = false;
	PApplet p;
	int[] textColour;

	/**
	 * Constructor for the ClickableText class
	 * 
	 * @author Fardeen Kasmani
	 * @param p          PApplet reference
	 * @param text       text to be displayed
	 * @param textSize   text size
	 * @param textPosX   text X position
	 * @param textPosY   text y position
	 * @param isCenter   whether to left align or center align text
	 * @param textColour int array with text fill
	 */
	public ClickableText(PApplet p, String text, int textSize, int textPosX, int textPosY, boolean isCenter,
			int[] textColour) {
		this.p = p;
		this.text = text;
		this.textSize = textSize;
		this.textPosY = textPosY;
		this.textPosX = textPosX;
		this.isCenter = isCenter;
		this.textColour = textColour;
	}

	/**
	 * @author Ibrahim Chehab
	 * @return true/false if the mouse is pressed
	 */
	public boolean isPressed() {
		if (isCenter) {
			if (p.mousePressed && p.mouseX >= textPosX - ((text.length()) * textSize / 2) / 2
					&& p.mouseX <= textPosX + ((text.length()) * textSize / 2) / 2 && p.mouseY >= textPosY - textSize
					&& p.mouseY <= textPosY) {
				return true;
			}
			return false;
		} else {
			if (p.mousePressed && p.mouseX >= textPosX && p.mouseX <= (text.length()) * textSize / 2
					&& p.mouseY >= textPosY - textSize && p.mouseY <= textPosY) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Draws the Clickable Text to the Screen
	 * 
	 * @author Ibrahim Chehab
	 */
	public void drawText() {
		if (isCenter) {
			p.textAlign(PConstants.CENTER);
		} else {
			p.textAlign(PConstants.LEFT);
		}
		p.fill(textColour[0], textColour[1], textColour[2]);
		p.textSize(textSize);
		p.text(text, textPosX, textPosY);
	}

	/**
	 * Sets the Text, Size, Mode, and Position of the Clickable Text
	 * 
	 * @author Fardeen Kasmani
	 */
	public void setText(String tempText) {
		text = tempText;
	}

	/**
	 * Sets the Size
	 * 
	 * @author Fardeen Kasmani
	 * @param size Size to set text
	 */
	public void setSize(int size) {
		textSize = size;
	}

	/**
	 * Sets the X Position and Y Position
	 * 
	 * @author Fardeen Kasmani
	 * @param x x coord
	 * @param y y coord
	 */
	public void setPos(int x, int y) {
		textPosX = x;
		textPosY = y;
	}

	/**
	 * Left or Centre Aligned for Text
	 * 
	 * @author Ibrahim Chehab
	 * @param mode Mode to set
	 */
	public void setMode(String mode) {
		if (mode.toUpperCase().equals("CENTER")) {
			isCenter = true;
		} else {
			isCenter = false;
		}
	}

	/**
	 * Gets the Size, X Position, Y position and Text
	 * 
	 * @author Ibrahim Chehab
	 * @return textSize Text size
	 */
	public int getTextSize() {
		return textSize;
	}

	/**
	 * Gets the X Position of the Text
	 * 
	 * @author Fardeen Kasmani
	 * @return textPosX text x position
	 */
	public int getTextX() {
		return textPosX;
	}

	/**
	 * Gets the Y Position of the Text
	 * 
	 * @author Fardeen Kasmani
	 * @return textPosY text y position
	 */
	public int getTextY() {
		return textPosY;
	}

	/**
	 * Gets the Text
	 * 
	 * @author Fardeen Kasmani
	 * @return text text to display
	 */
	public String getText() {
		return text;
	}
}