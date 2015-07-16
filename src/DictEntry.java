//Class DictEntry to make an object
public class DictEntry {
//Declaring private variables to use for DictEntry object
	private String word;
	private String definition;
	private int type;
	//DictEntry constructor
	public DictEntry (String word, String definition, int type) {
		this.word = word;
		this.definition = definition;
		this.type = type;
	}
	//Getter method for word
	public String word () {
		return word;
	}
	//Getter method for definition
	public String definition () {
		return definition;
	}
	//Getter method for type
	public int type () {
		return type;
	}
}//End of class
