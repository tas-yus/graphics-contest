/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {
	
	/** Stores all the words from HangmanLexicon text file */
	private ArrayList <String> wordList = new ArrayList <String> (); 
	
	/** Returns the number of words in the lexicon. */
	public HangmanLexicon () {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true) {
				String word = rd.readLine();
				if(word == null) break;
				wordList.add(word);
			}
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return wordList.get(index);
	}
	
	public int getWordCount() {
		return wordList.size();
	}	
}
