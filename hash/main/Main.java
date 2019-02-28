package hash.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public Main() {

	}

	int numOfPhotosInCollection = 0;
	int photoPointer = 0;
	

	ArrayList<ArrayList<String>> tags = new ArrayList<ArrayList<String>>();
	char[] photoOreintation;

	private void readInFile() {
		try {
			Scanner sc = new Scanner(new File("a_example.txt"));
			String firstLine = sc.nextLine();
			this.numOfPhotosInCollection = Integer.parseInt(firstLine);

			String[] photos = new String[this.numOfPhotosInCollection];
			
			photoOreintation = new char[this.numOfPhotosInCollection];
			
			int tempTagLength = 0;
			
			
			for (int i = 0; i < this.numOfPhotosInCollection; i++) {
				photos[i] = sc.nextLine();
				String[] photo = photos[i].split(" "); 

				int numOfTags = Integer.parseInt(photo[1]);
				for(int j = 0; j < photo.length; j++){
					photoOreintation[i] = photo[0].toCharArray()[0];
					tags.add(new ArrayList<String>());
					//System.out.println("length of tags[j]: " + tags.get(j).size());
					
				}
				for(int k = 0; k < numOfTags; k++){
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
		int photoID = 0 ;
		System.out.print("Orientation: " + main.photoOreintation[photoID] + " ");
		for(int i = 0; i < main.tags.get(photoID).size(); i++){
			System.out.print(main.tags.get(photoID).get(i) + " ");
		}
	}

}
