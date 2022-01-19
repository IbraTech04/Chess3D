import processing.core.PApplet;

public class Avatar {
	private String name;
	private int hairColourChoice;
	private int skinColor;
	private int eyeChoice;
	private int eyeColourChoice;
	private int eyeBrowChoice;
	private int mouthChoice;
	private PApplet p;

	/**
	 * @author Fardeen Kasmani
	 * @param name
	 * @param hairColourChoice
	 * @param skinColor
	 * @param eyeChoice
	 * @param eyeColourChoice
	 * @param eyeBrowChoice
	 * @param mouthChoice
	 * @param p
	 */
	public Avatar(String name, int hairColourChoice, int skinColor, int eyeChoice, int eyeColourChoice,
			int eyeBrowChoice, int mouthChoice, PApplet p) {
		this.name = name;
		this.hairColourChoice = hairColourChoice;
		this.skinColor = skinColor;
		this.eyeChoice = eyeChoice;
		this.eyeColourChoice = eyeColourChoice;
		this.eyeBrowChoice = eyeBrowChoice;
		this.mouthChoice = mouthChoice;
		this.p = p;
	}

	/**
	 * Gets name
	 * 
	 * @author Fardeen Kasmani
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name
	 * 
	 * @author Fardeen Kasmani
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @author
	 * @param
	 */
	public void render() {

	}
}
