//Main class which loads the game

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File saveDir = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "jChess");
		File saveFile = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "jChess"
				+ System.getProperty("file.separator") + "save.txt");

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Set the Java Swing UI style to
																					// Windows
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		try { // If the save directory exists, the user has already played the game. Skip
				// requirements check

			String[] data = loadFile(saveFile);

			if (!isValidKey(data[1])) { // Check if licence file has been tampered with
				JOptionPane.showMessageDialog(null,
						"Your licence file appears to have been tampered with. Please reneter your product key",
						"Activation Unsuccessful", JOptionPane.ERROR_MESSAGE);
				throw new Exception();
			}

			int mode = Integer.parseInt(data[0]); // Get default mode

			if (mode == 0) { // Load the game
				Chess3D c = new Chess3D();
				c.main(new String[] {});
			} else {
				Chess2D c = new Chess2D();
				c.main(new String[] {});
			}

		}

		catch (Exception e)

		{ // Otherwise run requirements check
			saveDir.mkdir(); // Make the save directory

			try {
				saveFile.createNewFile(); // Create new file
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			String pKey;

			while (true) { // Continue asking user for key until a valid key is entered
				pKey = JOptionPane
						.showInputDialog("Thank you for purchasing jChess! Please enter your product key now");

				if (pKey == null || pKey.isEmpty() || pKey.isBlank()) {
					JOptionPane.showMessageDialog(null, "Empty keys aren't keys ( ͡• ͜ʖ ͡• )", "Invalid Key",
							JOptionPane.ERROR_MESSAGE);
				} else if (!isValidKey(pKey)) {
					JOptionPane.showMessageDialog(null, "Invalid key enetered. Please try again", "Invalid Key",
							JOptionPane.ERROR_MESSAGE);
				} else {
					break; // Break out of loop
				}
			}

			saveFile(saveFile, new String[] { "0", pKey, "79,197,247", "255,255,255" }); // Save file

			JOptionPane.showMessageDialog(null,
					"In order to proceed, we must check your system to ensure it meets our system requirements. This process may take some time; Please wait",
					"System Requirements Check", JOptionPane.INFORMATION_MESSAGE); // Inform
																					// the
																					// user
																					// that
																					// their
																					// system
																					// will
																					// be
																					// assessed
			int state = checkSystemRequirements(); // Check system requirements

			if (state == 0) { // If the users PC cannot run 3D, load 2D
				saveFile(saveFile, new String[] { "1", pKey, "79,197,247", "255,255,255" }); // Save file
				Chess2D twoD = new Chess2D();
				twoD.main(new String[] {});
			} else if (state == 1) { // Otherwise load 3D
				saveFile(saveFile, new String[] { "0", pKey, "79,197,247", "255,255,255" }); // Save file
				Chess3D threeD = new Chess3D();
				threeD.main(new String[] {});
			}
		}
	}

	/**
	 * Method which checks system requirements. Uses DXDiag on windows
	 * 
	 * @author Ibrahim Chehab
	 */
	public static int checkSystemRequirements() {
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
					return 1;
				}

			} catch (Exception e) { // If we hit an error, the users computer is likely too slow to run our game.
									// Give them the option to either run the game in 2D mode, exit, or run in 3D
									// mode regardless
				Object[] options = { "Continue in 2D Mode (Recommended)", "Continue in 3D (Not recommended)", "Exit" };
				int n = JOptionPane.showOptionDialog(null,
						"Unfortunately your system does not meet our minimum system requirements for 3D mode. Please select from the options below",
						"Thank you for downloading jChess!", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[2]);

				return n;
			}
		}
		return 1;
	}

	/**
	 * Returns a score based on which GPU the User has
	 * 
	 * @author Fardeen Kasmani
	 * @param name GPU name
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

	/**
	 * Method which determines whether a key is valid
	 * 
	 * @author Ibrahim Chehab
	 * @param key Key to check
	 * @return Whether key is valid
	 */
	static boolean isValidKey(String key) {
		if (key == null || key.isEmpty() || key.isBlank()) {
			return false;
		} else {
			try {
				String[] splitKey = key.split("-");

				int setOne = addUpString(splitKey[0]);
				int setTwo = addUpString(splitKey[1]);
				int setThree = addUpString(splitKey[2]);
				int setFour = addUpString(splitKey[3]);

				if (setOne % 7 == 0 && setTwo % 17 == 0 && setThree % 3 == 0 && setFour % 2 == 0) {
					return true;
				} else {
					return false;
				}

			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * Method which adds up all the ASCII values in a string and returns that value
	 * 
	 * @author Ibrhaim Chehab
	 * @param key
	 * @return
	 */
	public static int addUpString(String key) {
		int sum = 0;
		for (int i = 0; i < key.length(); i++) {
			if (Character.isDigit(key.charAt(i))) {
				sum += Character.getNumericValue(key.charAt(i));
			} else {
				sum += key.charAt(i);
			}
		}
		return sum;
	}

	/**
	 * Method which loads a text files data, given a file object reference
	 * 
	 * @param file File object reference
	 * @return File data
	 * @author Fardeen Kasmani
	 */
	public static String[] loadFile(File file) {
		ArrayList<String> strings = new ArrayList<String>();

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				strings.add(scanner.nextLine());

			}
			scanner.close();
			return makeStringArray(strings);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @author Ibrahim Chehab
	 * @param file File object reference to save to
	 * @param data Data to save to
	 */
	public static void saveFile(File file, String[] data) {
		try {
			FileWriter writer = new FileWriter(file);
			for (String s : data) {
				writer.write(s);
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method which converts an arraylist to a string array
	 * 
	 * @param s Arraylist to convert
	 * @return toReturn Converted Array
	 * @author Fardeen Kasmani
	 */
	public static String[] makeStringArray(ArrayList<String> s) {
		String[] toReturn = new String[s.size()];

		for (int i = 0; i < s.size(); i++) {
			toReturn[i] = s.get(i);
		}

		return toReturn;
	}
}
