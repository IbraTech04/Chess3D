//Main class which loads the game

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Main {

	public static void main(String[] args) {
		File saveDir = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "jChess");

		if (saveDir.exists()) { // If the save directory exists, the user has already played the game. Skip
								// requirements check
			Chess3D c = new Chess3D();
			c.main(new String[] {});
		}

		else { // Otherwise run requirements check
			saveDir.mkdir(); // Make the save directory
			File avatarDir = new File(saveDir + System.getProperty("file.separator") + "Avatars");
			avatarDir.mkdir(); // Make avatar save dir
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Set the Java Swing UI style to
																						// Windows
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,
					"In order to proceed, we must check your system to ensure it meets our system requirements. This process may take some time; Please wait",
					"System Requirements Check", JOptionPane.INFORMATION_MESSAGE); // Inform the user that their system
																					// will be assessed
			checkSystemRequirements(); // Check system requirements

			System.out.println("Requirements check completed");
		}
	}

	/**
	 * Method which checks system requirements
	 * 
	 * @author Ibrahim Chehab
	 */
	public static void checkSystemRequirements() {
		if (System.getProperty("os.name").toLowerCase().contains("windows")) { // If the OS is windows
			int cpuCores = Runtime.getRuntime().availableProcessors();
			String filePath = "./dxdiag.xml";
			String command = "dxdiag.exe /x " + filePath;
			try {
				Process p = Runtime.getRuntime().exec(command);
				p.waitFor();
				p.destroy();

				SAXBuilder sax = new SAXBuilder();
				Document doc = sax.build(new File("dxdiag.xml"));

				String data = doc.getRootElement().getChild("SystemInformation").getChild("Memory").getValue();

				int RAM = Integer.parseInt(data.substring(0, data.length() - 6));

				String GPU = doc.getRootElement().getChild("DisplayDevices").getChild("DisplayDevice")
						.getChild("CardName").getValue();

				double gpuScore = getGPUScore(GPU);

				if (gpuScore < 4 && cpuCores <= 2 && RAM <= 4096) {
					throw new Exception(); // Since our code is encapsulated in try/catch, we can break out of it by
											// throwing an exception
				} else {
					Chess3D c = new Chess3D();
					c.main(new String[] {});
				}

			} catch (Exception e) { // If we hit an error, the users computer is likely too slow to run our game.
									// Give them the option to either run the game in 2D mode, exit, or run in 3D
									// mode regardless
				Object[] options = { "Continue in 2D Mode (Recommended)", "Continue in 3D (Not recommended)", "Exit" };
				int n = JOptionPane.showOptionDialog(null,
						"Unfortunately your system does not meet our minimum system requirements for 3D mode. Please select from the options below",
						"Thank you for downloading jChess!", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[2]);

				if (n == 0) {
					Chess2D c = new Chess2D();
					c.main(new String[] {});
				} else if (n == 1) {
					Chess3D c = new Chess3D();
					c.main(new String[] {});
				}
			}
		}
	}
	/** Returns a score based on which GPU the User has
	 * @author Fardeen Kasmani
	 * @param name
	 * @return GPU Score
	 */
	static double getGPUScore(String name) {
		name = name.toLowerCase();

		if (name.contains("super")) {
			return 7;
		}
		if (name.contains("rtx")) {
			return 8;
		}

		String cardNumber = name.replaceAll("[^0-9]", "");
		int total = 0;
		for (int i = 0; i < cardNumber.length(); i++) {
			total += Character.getNumericValue(cardNumber.charAt(i));
		}

		return (total + 1) / 2;

	}
}
