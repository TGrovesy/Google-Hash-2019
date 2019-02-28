package hash.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public Main() {

	}

	int numOfPhotosInCollection = 0;
	int photoPointer = 0;

	HashMap<String, ArrayList<Integer>> locationOfTags = new HashMap<String, ArrayList<Integer>>();

	private void fillMostCommonTagMap() {
		for (int i = 0; i < tags.size(); i++) {
			for (int j = 0; j < tags.get(i).size(); j++) {
				String tag = tags.get(i).get(j);
				if (locationOfTags.containsKey(tag)) {
					locationOfTags.get(tag).add(i);
				} else {
					ArrayList<Integer> firstImage = new ArrayList<Integer>();
					firstImage.add(i);
					locationOfTags.put(tag, firstImage);
				}
			}
		}
	}

	ArrayList<ArrayList<String>> tags = new ArrayList<ArrayList<String>>();
	char[] photoOreintation;

	private void readInFile() {
		try {
			Scanner sc = new Scanner(new File("e_shiny_selfies.txt"));
			String firstLine = sc.nextLine();
			this.numOfPhotosInCollection = Integer.parseInt(firstLine);

			String[] photos = new String[this.numOfPhotosInCollection];

			photoOreintation = new char[this.numOfPhotosInCollection];

			int tempTagLength = 0;

			for (int i = 0; i < this.numOfPhotosInCollection; i++) {
				photos[i] = sc.nextLine();
				String[] photo = photos[i].split(" ");

				int numOfTags = Integer.parseInt(photo[1]);
				for (int j = 0; j < photo.length; j++) {
					photoOreintation[i] = photo[0].toCharArray()[0];
					tags.add(new ArrayList<String>());
					// System.out.println("length of tags[j]: " +
					// tags.get(j).size());

				}
				for (int k = 0; k < numOfTags; k++) {
					tags.get(i).add(photo[k + 2]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Main main = new Main();
		main.readInFile();
		int photoID = 79979;
		/*
		 * System.out.print("Orientation: " + main.photoOreintation[photoID] +
		 * " "); for (int i = 0; i < main.tags.get(photoID).size(); i++) {
		 * System.out.print(main.tags.get(photoID).get(i) + " "); }
		 */

		main.fillMostCommonTagMap();
		Object[] keyArray = new String[main.locationOfTags.size()];
		keyArray = main.locationOfTags.keySet().toArray();

		System.out.println(keyArray.length);
		try (PrintWriter out = new PrintWriter("output.txt")) {

			for (int i = 0; i < keyArray.length; i++) {
				out.append((CharSequence) keyArray[i] + "\n");
				for (int j = 0; j < main.locationOfTags.get(keyArray[i]).size(); j++) {
					out.append(main.locationOfTags.get(keyArray[i]).get(j) + ", ");
				}

				System.out.println(i);
				out.append( "\n\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
