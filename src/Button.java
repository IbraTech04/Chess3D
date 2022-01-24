import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Class which contains all the necessary functions for buttons
 * 
 * @author Ibrahim Chehab
 *
 */
public class Button {
	private int x; // ButtonX
	private int y; // ButtonY
	private int sizeX; // Button length
	private int sizeY; // Button Width
	private PApplet p; // Reference to PApplet to draw to screen
	private String text; // Text to be displayed
	private int[] textFill; // Text Color
	private int[] buttonFill; // Button Color

	/**
	 * 
	 * @param x
	 * @param y
	 * @param sizeX
	 * @param sizeY
	 * @param text
	 * @param p
	 * @param buttonFill
	 * @param textFill
	 * @author Ibrahim Chehab
	 */
	public Button(int x, int y, int sizeX, int sizeY, String text, PApplet p, int[] buttonFill, int[] textFill) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.text = text;
		this.p = p;
		this.buttonFill = buttonFill;
		this.textFill = textFill;
	}

	/**
	 * Function which draws the button to the screen
	 * 
	 * @author Ibrahim Chehab
	 */
	public void drawButton() {
		p.noStroke();
		p.rectMode(PConstants.CENTER);
		p.fill(buttonFill[0], buttonFill[1], buttonFill[2]);
		p.rect(x, y, sizeX, sizeY, 15, 15, 15, 15);
		p.textAlign(PConstants.CENTER);
		p.fill(textFill[0], textFill[1], textFill[2]);
		p.text(text, x, y + sizeY / 4);
	}

	/**
	 * Function which returns whether the button is pressed
	 * 
	 * @author Ibrahim Chehab
	 * @return Button.isPressed
	 */
	public boolean isPressed() {
		return (p.mousePressed && p.mouseX >= x - sizeX / 2 && p.mouseX <= x + sizeX / 2 && p.mouseY >= y - sizeY / 2
				&& p.mouseY <= y + sizeY / 2);
	}

	/** Sets the Text
	 * @author Ibrahim Chehab
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
}