package cryptographyTask;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class CreativeEncryption {

	public static void main(String[] args) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		ArrayList<Integer> list = new ArrayList<>();

		int[][] vSquare = new int[26][26];
		Scanner sc = new Scanner(System.in);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("VigenereTable.txt", "UTF-8");
			for (int i = 0; i < vSquare.length; i++) {
				for (int j = 0; j < 26; j++) {
					list.add(128512 + j);
				}
				Collections.shuffle(list);
				for (int j = 0; j < 26; j++) {
					vSquare[i][j] = list.get(j);
					writer.println(vSquare[i][j]);
//					System.out.println(vSquare[i][j]);
				}
				list.clear();
//				System.out.println();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println("Please enter your plain text:");
		String input = sc.nextLine();
		System.out.println("Please enter your encryption key:");
		String cryptoKey = sc.nextLine();

		String output = "";

		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < input.length(); i++) {
			String letter = String.valueOf(alphabet.charAt(i));

			int a = cryptoKey.charAt(i) - 97;
			int b = input.charAt(i) - 97;

//			System.out.println(a);

			char mysteryEmoji[] = { Character.highSurrogate(vSquare[a][b]), Character.lowSurrogate(vSquare[a][b]) };

			map.put(letter, String.valueOf(mysteryEmoji));
			output = output + map.get(letter);

		}
		System.out.println(output);

		sc.close();
	}
}