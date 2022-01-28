
/**
 * Class used to make valid keys for the program
 * @author Fardeen Kasmani
 *
 */
import java.util.Random;

public class KeyGen {
	static Random random;

	/**
	 * Method which prints out key
	 * @author Fardeen Kasmani
	 * @param args
	 */
	public static void main(String[] args) {
		String segOne = makeKeySeg(7);
		String segTwo = makeKeySeg(17);
		String segThree = makeKeySeg(3);
		String segFour = makeKeySeg(2);

		System.out.println(segOne + "-" + segTwo + "-" + segThree + "-" + segFour);
	}

	/**
	 * Given a key size, this method will make a segment of a key
	 * @param mul Size of key segment
	 * @return The key segment
	 * @author Fardeen Kasmani
	 */
	static String makeKeySeg(int mul) {
		while (true) {
			String temp = makeString(mul);

			if (addUpString(temp) % mul == 0) {
				return temp;
			}
		}
	}

	/**
	 * Creates random string combinations
	 * @param len Length of string to make
	 * @return the random string
	 * @author Fardeen Kasmani
	 */
	static String makeString(int len) {
		random = new Random();
		String toReturn = "";
		for (int i = 0; i < len; i++) {
			char a = (char) (random.nextInt(90 - 48) + 48);
			toReturn += a;
		}
		return toReturn;
	}

	/**
	 * Method which adds up a string given its ASCII values
	 * @param key The key to add
	 * @return The sum of the ASCII values
	 * @author Ibrahim Chehab
	 */
	static int addUpString(String key) {
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
}
