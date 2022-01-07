import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GameUtils {
	/**
	 * Method which checks whether the users computer is capable of running our
	 * game. If not, it will alert the user
	 * 
	 * @author Fardeen Kasmani
	 * @return
	 */
	public static int checkGameRequriements() {
		System.getProperties().list(System.out);
		if (System.getProperty("os.name").contains("Windows")) {
			String[] osInfo = getDxDiag();
			int memory = Integer.parseInt(osInfo[12].split(":")[1].split("")[0]);
			if (memory < 4000) {
				return 1;
			}

		} else {

		}
		return 10;
	}

	/**
	 * Returns the output of running dxdiag. Used to get information on CPU and GPU
	 * 
	 * @author Ibrahim Chehab
	 * @return String[][] osInfo
	 */
	private static String[] getDxDiag() {
		try {

			String filePath = "./foo.txt";
			// Use "dxdiag /t" variant to redirect output to a given file
			ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "dxdiag", "/t", filePath);
			System.out.println("-- Executing dxdiag command --");
			Process p = pb.start();
			p.waitFor();

			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			ArrayList<String> temps = new ArrayList<String>();

			while ((line = br.readLine()) != null) {
				temps.add(line);
			}
			return temps.toArray(new String[0]);
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
