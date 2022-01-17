
public class GameUtils {

	/**
	 * Runs DXDiag in XML mode. This will save an XML file to the users computer
	 * with all their information This will be used in conjunction with Fardeen's
	 * function
	 * 
	 * @author Ibrahim Chehab
	 */
	static void getDXDiag() {
		String filePath = "./dxdiag.xml";
		String command = "dxdiag.exe /x " + filePath;
		try {
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes string input of a CPU name and returns a score based on the CPU
	 * performance
	 * 
	 * @param CPUName The name of the CPU to check
	 * @return CPU's score
	 * @author Ibrahim Chehab
	 */
	static int getCPUScore(String CPUName) {
		if (CPUName.toLowerCase().contains("threadripper")) {
			return 10;
		}
		if (CPUName.toLowerCase().contains("xeon")) {
			return 10;
		}
		if (CPUName.toLowerCase().contains("ryzen")) {
			return 7;
		}
		if (CPUName.toLowerCase().contains("core")) {
			return 7;
		}
		if (CPUName.toLowerCase().contains("athlon")) {
			return 5;
		}
		if (CPUName.toLowerCase().contains("celeron")) {
			return 3;
		}
		return 0;
	}

	static int getGPUScore(String GPUName) {
		if (GPUName.toLowerCase().contains("super")) {
			return 8;
		}
		if (GPUName.toLowerCase().contains("rtx")) {
			return 9;
		}

		if (GPUName.toLowerCase().contains("GTX")) {
			return 7;
		}

		return 0;
	}
}
