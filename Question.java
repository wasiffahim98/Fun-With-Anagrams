package AnagramGameV3;


public class Question {
	String word;   //Stores the words
	String anagram;    //Stores the anagram
	String answer;     //Stores the answer
	//well i want an image file to correspond to the question
	String file;    //Stores the name of the file
	public Question(){};   
	public Question(String word, String anagram, String file){   //Constructor method
		this.word = word;
		this.anagram = anagram;
		this.file = file;
		this.answer = word;
	}
	public String getWord(){
		return this.word;   //Returns the word variable
	}
	public String getAnagram(){  
		return this.anagram;   //Returns the anagram variable
	}
	public String getAnswer(){
		return this.answer;    //Returns the answer variable
	}
	public String getJPEG(){
		return this.file + ".jpg";   //For every line, adds in the .jpg to find the designated image
	}
	public boolean isCorrect(){
		if(answer.equals(word)){    //Checks if the word entered is correct
			return true;    //If its correct
		} else{
			return false;   //Its its wrong
		}
	}
}
