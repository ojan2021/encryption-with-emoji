package cryptographyTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CreativeDecryption {

	public static void main(String[] args) {
		Integer[][] vSquare = new Integer[26][26];
		Scanner sc = new Scanner(System.in);

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("VigenereTable.txt"));

			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < 26; j++) {
					String line = br.readLine();
					if (line == null)
						break; // check if we reached the EOF (end of file)
					vSquare[i][j] = Integer.parseInt(line);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("The file you specified does not exist.");
		} catch (IOException e) {
			System.err.println("Some other IO exception occured. Message: " + e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Please enter your encrypted text:");
		String input = sc.nextLine();
		System.out.println("Please enter your encryption key:");
		String cryptoKey = sc.nextLine();

		String s = input;

		StringBuilder sb = new StringBuilder();
		Integer[] temp = new Integer[input.length()];
		Integer[] uniEmoji = new Integer[temp.length];
		Integer res = 0;

		for (int i = 0; i < s.length(); i++) {
			if (Character.isSurrogate(s.charAt(i))) {
				res = Character.codePointAt(s, i);
				i++;
				sb.append(res);
			} else {
				sb.append(s.charAt(i));
			}
		}

		String str = sb.toString();
//		System.out.println(str.length());
		int g = 0;
		for (int i = 0; i < (str.length() / 6); i++) {
			uniEmoji[i] = Integer.parseInt(str.substring(g, g + 6));
			g += 6;
		}

		int deneme = 0;
		for (int i = 0; i < cryptoKey.length(); i++) {
			int a = cryptoKey.charAt(i) - 97;
			for (int j = 0; j < 26; j++) {
				deneme = vSquare[a][j];
				if (uniEmoji[i].equals(deneme)) {
					System.out.print((char) (j + 97));
				}
			}

		}

		sc.close();
	}
}