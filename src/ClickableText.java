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

	/** Constructor for the ClickableText class
	 * @author Fardeen Kasmani
	 * @param p
	 * @param text
	 * @param textSize
	 * @param textPosX
	 * @param textPosY
	 * @param isCenter
	 * @param textColour
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
			if (p.mousePressed && p.mouseX >= textPosX && p.mouseX <= (text.length()) * textSize / 2 && p.mouseY >= textPosY - textSize
					&& p.mouseY <= textPosY) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/** Draws the Clickable Text to the Screen
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

	/** Sets the Text, Size, Mode, and Position of the Clickable Text
	 * @author Fardeen Kasmani
	 */
	public void setText(String tempText) {
		text = tempText;
	}

	public void setSize(int size) {
		textSize = size;
	}

	public void setPos(int x, int y) {
		textPosX = x;
		textPosY = y;
	}

	public void setMode(String mode) {
		if (mode.toUpperCase().equals("CENTER")) {
			isCenter = true;
		} else {
			isCenter = false;
		}
	}
	
	/** Gets the Size, X Position, Y position and Text
	 * @author Ibrahim Chehab
	 */
	public int getTextSize() {
		return textSize;
	}

	public int getTextX() {
		return textPosX;
	}

	public int getTextY() {
		return textPosY;
	}

	public String getText() {
		return text;
	}
}