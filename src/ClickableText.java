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

	public ClickableText(PApplet p, String text, int textSize, int textPosY, int textPosX, boolean isCenter, int[] textColour) {
		this.p = p;
		this.text = text;
		this.textSize = textSize;
		this.textPosY = textPosY;
		this.textPosX = textPosX;
		this.isCenter = isCenter;
		this.textColour = textColour;
	}

	public boolean isPressed() {
		if (isCenter) {
			if (p.mouseX >= textPosX - ((text.length()) * textSize / 2) / 2
					&& p.mouseX <= textPosX + ((text.length()) * textSize / 2) / 2 && p.mouseY >= textPosY - textSize
					&& p.mouseY <= textPosY) {
				return true;
			}
			return false;
		} else {
			if (p.mouseX >= textPosX && p.mouseX <= (text.length()) * textSize / 2 && p.mouseY >= textPosY - textSize
					&& p.mouseY <= textPosY) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void drawText() {
		if (isCenter) {
			p.textAlign(PConstants.CENTER);
		} else {
			p.textAlign(PConstants.LEFT);
		}
		p.fill(textColour[0], textColour[1], textColour[2]);
		p.text(text, textPosX, textPosY);
	}

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