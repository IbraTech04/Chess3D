import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Class which contains all the necessary functions for buttons
 * 
 * @author Ibrahim Chehab
 *
 */
public class Button {
	private int x;
	private int y;
	private int sizeX;
	private int sizeY;
	private PApplet p;
	private String text;
	private int[] textFill;
	private int[] buttonFill;

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
}