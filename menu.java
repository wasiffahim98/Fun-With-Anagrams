package AnagramGameV3;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;

public class menu extends JFrame{
	private int playerTotal;
	private int currentPlayer = 0;
	private ArrayList<Question> database;
	ArrayList<Player> players = new ArrayList<Player>();
	private JButton play;
	private JButton exit;
	private JLabel lblNewLabel;
	public menu(int playerTotal, ArrayList<Question> database){
		super("The title bar");
		this.playerTotal = playerTotal;
		this.database = database;
		//remember to add() to add to the window
		setTitle("Anagram Generator");
		play = new JButton("PLAY");   //play button
		play.setFont(new Font("AR DARLING", Font.BOLD, 100)); //font
		play.setPreferredSize(new Dimension(60, 120));
		play.setToolTipText("Start the game");
		
		exit = new JButton("Exit"); //exit button
		exit.setFont(new Font("Times New Roman", Font.BOLD, 17)); //font size and type
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Fun With Anagrams");
		lblNewLabel.setFont(new Font("MV Boli", Font.PLAIN, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		getContentPane().add(play, BorderLayout.CENTER);
		getContentPane().add(exit, BorderLayout.SOUTH);
		
		//give each item a handler
		thehandler handler = new thehandler();
		exit.addActionListener(handler);
		play.addActionListener(handler);
	}
	//need action listener to handle events.
	private class thehandler implements ActionListener{
	
		//issued automatically it's built in
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == exit){
				//getActionCommand gets the text from that item
				System.exit(1);
			} else if(event.getSource() == play){
				currentPlayer++;
				if(currentPlayer > playerTotal){
					JOptionPane.showMessageDialog(null, sortTopFive(players));
					System.exit(1);
				}
				Collections.shuffle(database);
				//play player-x turn.
				questionMenu qmain;
				try {
					qmain = new questionMenu(currentPlayer, database, players);
					qmain.setSize(480,320);
					qmain.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public String sortTopFive(ArrayList<Player> playerList){
		String result = "";
		Collections.sort(playerList, (o1,o2) -> o2.getScore().compareTo(o1.getScore()));
		Iterator<Player> e = playerList.iterator();
		while(e.hasNext()){
			Player q = e.next();
			result += q.name + " score: " + q.score + "\n";
			String r = q.name + " : " + q.score;
			saveScore("out.txt",r,true);
		}
	    return result;
	}
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
	}
}
