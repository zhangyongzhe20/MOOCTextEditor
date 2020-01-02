package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 * @author Zhang Yong Zhe
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		String[] words = sourceText.split("[ ]+");
		// System.out.println(words[1]);
		starter = words[0];
		ListNode prevWord = new ListNode(starter);
		ListNode w;
		wordList.add(prevWord);
		for (int n = 1; n < words.length; n++) {
			w = new ListNode(words[n]);
			for(ListNode node : wordList) {
				if(node.getWord().equals(words[n-1])) {
					prevWord = node;
				}
			}
			if(wordList.contains(prevWord)){
				prevWord.addNextWord(words[n]);
			} else {
				wordList.add(prevWord);
				prevWord.addNextWord(words[n]);
			}
			prevWord = w;
		}
		//System.out.println(prevWord.getWord());
		wordList.add(prevWord);
		prevWord.addNextWord(starter);
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String out = new String();
		if(wordList.size() ==0 || numWords ==0) {
			return out;
		}
		List<String> outputs = new ArrayList<String>();
		ListNode currWord = wordList.get(0);
	    outputs.add(currWord.getWord());
		for(int n =1; n<numWords; n++) {
			String next = currWord.getRandomNextWord(rnGenerator);
			outputs.add(next);
			for(ListNode node : wordList) {
				if(node.getWord().equals(next)) {
					currWord = node;
				}
			}
		}
		for(String word : outputs) {
			out = out + word + " ";
		}
		return out;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
	
//		ListNode s = new ListNode("test");
//		List<ListNode> wordList = new ArrayList<ListNode>(); 
//		wordList.add(s);
//		ListNode s1 = new ListNode("test");
//		if(wordList.contains(s1)) {
//			System.out.print("hello");
//		}
		// feed the generator a fixed random value for repeatable behavior
//		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
//		String textStringq = "hi there hi Leo.";
//		gen.train(textStringq);
//		System.out.println(gen);
//		for(int n=0; n< 100; n++) {
//		System.out.println(gen.generateText(4));
//		}
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		// [0.....nexWords.size()-1]
	    return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


