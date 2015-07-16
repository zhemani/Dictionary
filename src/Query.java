import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//Class to work functions of a dictionary
public class Query {
//Main method within class
	public static void main(String[] args) throws IOException {
		//Declaring variables to be used throughout the main method 
		BufferedReader br;
		String line;
		OrderedDictionary dict = new OrderedDictionary();
	//Buffer reader to read files from arguments 
		br = new BufferedReader(new FileReader(args[0]));
		while ((line = br.readLine()) != null) {
			String word; //Read line by line to separate into word and definition
			String definition;
			int type;
			word = line;
			definition = br.readLine(); //Assign the appropriate lines to variables 
			//To get the type, check if type is 3 by checking the extensions of files
			if (definition.contains(".jpg") || definition.contains(".gif")) {
				type = 3;
			}//To get the type, check if type is 2 by checking the extensions of files
			else if (definition.contains(".wav") || definition.contains(".mid")) {
				type = 2;
			} //Else the type will be 1; a normal definition
			else {
				type = 1;
			}
			
			try { //Convert the word to lower case and then insert it into the dictionary
				word = word.toLowerCase();
				dict.insert(word, definition, type);
			} catch (DictionaryException e) { //Catch the exception if the word already exists
				System.out.println("Word already exists");
			}
		}
        br.close(); //Close the buffer reader
        
		boolean yes = true;
		while(yes) { //While loop to keep asking for next command until the program is terminated 
			StringReader keyboard = new StringReader();
			String line2 = keyboard.read("Enter next command: "); //String reader to read the users input 
			String temp [] = line2.split(" ", 3);
			
			boolean check = false;
			if(temp.length == 2) { //Ensuring the length of the input is appropriate 
				check = true;
				temp[0]=temp[0].toLowerCase();
				temp[1]=temp[1].toLowerCase(); //Convert to lower case
			}
			/////////////////Start with the various functions/////////////////////
			if (temp[0].equals("define") && check == true) { //Check define
				if (dict.findWord(temp[1]).equals("")) {
					System.out.println("This word is not in the dictionary"); //If the word isn't in the dictionary
				}
				else {
					int type = dict.findType(temp[1]); //If the type is 1 and a normal definition then just output the def
					if (type == 1) {
					System.out.println(dict.findWord(temp[1]));
					}
					
					else if (type == 2) { //Check if type is 2; play the sound through the player
						SoundPlayer player = new SoundPlayer();
						try {
							player.play(dict.findWord(temp[1]));
						} catch (MultimediaException e) { //Catch the exception if the sound cannot be played 
							System.out.println("This sound cannot be played");
						}	
					}
					else if (type == 3) { //Check if type is 3; show the picture on the screen
						PictureViewer viewer = new PictureViewer();
						try {
							System.out.println(dict.findWord(temp[1]));
							viewer.show(dict.findWord(temp[1]));
						} catch (MultimediaException e) { //Catch the exception if the picture cannot be shown 
							System.out.println("This picture cannot be shown");
						}
					}	
				}
			}
			/////////////////////////////////////////////////////////////////////
			else if (temp[0].equals("delete") && check == true) {
				try {
					dict.remove(temp[1]); //Remove the word from the dictionary 
				} catch (DictionaryException e) {
					System.out.print(temp[1]); //Catch exception if the word is not in the dictionary 
					System.out.println(" is not in the dictionary");
				}
			}
			/////////////////////////////////////////////////////////////////////
			else if (temp[0].equals("next") && check == true) {
				if ((dict.findWord(temp[1])).equals("")) { //Check next word; print if the word is not in the dictionary
					System.out.println("This word is not in the dictionary");
				}
				else if (dict.successor(temp[1]).equals("")) { //Check if the word is the last word in the dictionary
					System.out.print(temp[1]);
					System.out.println(" is the last word in the dictionary"); //Print message 
				}
				else {
				System.out.println(dict.successor(temp[1])); //Else print the successor for the next word
				}
			}
			//////////////////////////////////////////////////////////////////////
			else if (temp[0].equals("previous") && check == true) { //Output the previous word in the dictionary
				if ((dict.findWord(temp[1])).equals("")) { //Ensure the word is in the dictionary 
					System.out.println("This word is not in the dictionary");
				}
				else if (dict.predecessor(temp[1]).equals("")) { //Check if there is a predecessor, if not then first word
					System.out.print(temp[1]);
					System.out.println(" is the first word in the dictionary");
				}
				else {
					System.out.println(dict.predecessor(temp[1])); //Print the predecessor of the word inputted 
				}
			}
			///////////////////////////////////////////////////////////////////////
			else if (temp[0].equals("end")) {  //end input which terminates the program
				System.exit(0);
			}
			
			else if (temp[0].equals("list") && check == true) { //List option, which would list the words with starting prefix inputted 
				if (!dict.successor(temp[1]).contains(temp[1])) { //Ensure there are words matching the prefix input 
					System.out.println("No words matching this input");
				}
				else {
					if (dict.successor(temp[1]).startsWith(temp[1])) { //Use startsWith for Strings to ensure it starts with the prefix 
						String word = dict.successor(temp[1]);
						System.out.println(word); //Print the successor if it exists
						while (word.startsWith(temp[1])) {
							word = dict.successor(word); //Find the successor of the word and print 
							if (word.startsWith(temp[1])) {
								System.out.println(word);// Print only if it start with the prefix 
							}
						}
					}
					else {
						System.out.println("No words matching this input"); //Otherwise there is no word matching the prefix 
					}
				}
			}
			////////////////////////////////////////////////////////////////////////
			else if (temp.length == 3) { //If the length is 3 of temp, then add the word 
				if (temp[0].equals("add")) {  //Checks if input is add
					String word = temp[1];
					String definition = temp[2]; //Get all dictentry requirements to insert
					int type;
					if (definition.contains(".jpg") || definition.contains(".gif")) {
						type = 3;
					}//To get the type, check if type is 2 by checking the extensions of files
					else if (definition.contains(".wav") || definition.contains(".mid")) {
						type = 2;
					} //Else the type will be 1; a normal definition
					else {
						type = 1;
					}
					try {
						dict.insert(word, definition, type); //Insert the word into the dictionary with definition
					} catch (DictionaryException e) {
						System.out.println("Word already exists; cannot define a word that exists"); //Check if the word already exists
					}
				}
				else {
					System.out.println("Enter a valid input"); //Ensure the input is valid
				}
			}
			
			else {
				System.out.println("Enter a valid input"); //Input entered isn't a function of the dictionary
			}
		}	
	}//End of class
}
