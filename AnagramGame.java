package AnagramGameV3;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AnagramGame {
	public static void main(String args[]) throws FileNotFoundException, ArrayIndexOutOfBoundsException{
		if(args.length != 1){
			JOptionPane.showMessageDialog(null, "Please enter a valid amount of players, only 1 arguement please");
			System.exit(1);
		} else if(Integer.parseInt(args[0]) < 0){
			JOptionPane.showMessageDialog(null, "Please enter a valid amount of players");
			System.exit(1);
		}
		for(int i = 0; i < Integer.parseInt(args[0]); i++){
		}
		Scanner scan = new Scanner(new File("inputs.txt"));
		ArrayList<Question> database = new ArrayList<Question>();
		loadQuestions(scan, database);
		
		
		menu main = new menu(Integer.parseInt(args[0]),database);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setSize(480,320);
		main.setVisible(true);
	}
	
	static void loadQuestions(Scanner scan, ArrayList<Question> database){
		String quest = "";    //Loads question
		String anag = "";
		while(scan.hasNextLine()){
			quest = scan.nextLine();    //Takes user input
			anag = 	generateAnagram(quest);
			Question q = new Question(quest, anag, quest);
			database.add(q);  //Adds into the array list. 
		}
		Collections.shuffle(database);
	}

	private static String generateAnagram(String quest) {
		// TODO Auto-generated method stub
		Random rndword = new Random();
		StringBuffer anagram = new StringBuffer(quest);
		int WordLength = anagram.length();
		while (WordLength > 0) {
			int index = rndword.nextInt(anagram.length()-1);
			char temp = anagram.charAt(index);  //saves letter
			anagram.deleteCharAt(index);// deletes a letter
			anagram.append(temp); //moves letter to back of the word
			WordLength--;
		}
		return anagram.toString();
	}
	/*
	public static void saveScore(String file, String text, boolean append){
		try{
		File scores = new File(file);
		FileWriter writing = new FileWriter(scores,append);
		PrintWriter printing = new PrintWriter(writing);
		printing.println(text);
		printing.close();
		} catch(IOException e){
			System.out.println("Error: saveScore");
		}	
	} */
}
