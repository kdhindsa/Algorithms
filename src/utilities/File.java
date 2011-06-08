package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author kdhindsa
 * 
 * Collection of file input output utilities primarily for
 * reading, writing and making a large random file to test
 * sorting, searching, etc., algorithms on.
 *
 */
public class File {
        
    /**
     * Read a file line by line into an ArrayList and return it
     * 
     * @param fileName      full path including the name of the file to read from
     * 
     * @return      ArrayList containing all the lines read from the file
     */
    public static ArrayList readFile(String fileName) {
            String line = "";
            ArrayList<String> data = new ArrayList<String> ();
            try {
                    BufferedReader br = new BufferedReader(new FileReader(fileName));
                    while ((line = br.readLine()) != null){
                            data.add(line);
                    }       
            }
            catch (FileNotFoundException fN){
                    fN.printStackTrace();
            }
            catch (IOException e){
                    System.out.println(e);
            }
            return data;
    }
    
    /**
     * Write line by line into a file
     * 
     * @param       data    array of strings to be written into a file
     * @param       fileName        full path including the name of file to write data on
     */
    public static void writeFile(String [] data, String fileName) {
            try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                    for (String s : data) {
                            bw.write(s.trim() + "\n");
                    }
                    bw.close();
            }
            catch (FileNotFoundException fN){
                    fN.printStackTrace();
            }
            catch (IOException e){
                    System.out.println(e);
            }
    }
    
    /**
     * Count the number of words in a text file ignoring white spaces
     * 
     * @param       fileName        full path including the name of file to count words from
     * 
     * @return      number of words contained in the file
     */
    public static int wordCount (String fileName) {
            ArrayList<String> data = new ArrayList<String> ();
            data = readFile(fileName);
            int wordCount = 0;
            for (String line : data) {
                    // read each line and separate out all words
                    String [] arr = line.trim().split(" ");
                    for (String str : arr) {
                            if (str.replace(" ", "").length() > 0)
                                    wordCount++;
                    }
            }
            return wordCount;
    }
    
    /**
     * Count the number of characters in a text file
     * 
     * @param       fileName        full path including the name of file to count the characters from
     * @param       ignoreWhiteSpace        if true, then white spaces will not be counted
     * 
     * @return      number of characters contained in the file
     */
    public static int characterCount (String fileName, boolean ignoreWhiteSpace) {
            ArrayList<String> data = new ArrayList<String> ();
            data = readFile(fileName);
            int characterCount = 0;
            for (String line : data) {
                    if (ignoreWhiteSpace)
                            characterCount += line.trim().replace(" ", "").length();
                    else
                            characterCount += line.length();
            }
            return characterCount;
    }
    
    /**
     * Count the number of lines in a text file
     * 
     * @param       fileName        full path including the name of file to count the lines from
     * 
     * @return      number of lines
     */
    public static int lineCount (String fileName) {
            ArrayList<String> data = new ArrayList<String> ();
            data = readFile(fileName);
            return data.size();
    }
    
    
    private static final String alphabets = "abcdefghijklmnopqrstuvwxyz";
	private static final String nums = "0123456789";
	
    /**
     * Create a text file with random strings, choosing from a variety of characters that include
     * the characters a-z and 0-9
     * 
     * @param       fileName        full path including the name of the file to be created
     * @param       numOfLines      number of lines of data to be written in the file
     * @param       minWordsPerLine         minimum number of words to be written in the file
     * @param       maxWordsPerLine         maximum number of words to be written in the file
     * @param       minCharactersPerWord    minimum number of characters a word should consist of
     * @param       maxCharactersPerWord    maximum number of characters a word should consist of
     * @param		numsOnly				if true, then numbers will be used
     */
    public static void createRandomStringsFile(String fileName, int numOfLines,
                int minWordsPerLine, int maxWordsPerLine, 
                int minCharactersPerWord, int maxCharactersPerWord, boolean numsOnly) {
            
        /* 
         * Check if the arguments are valid and make sense.
         * Otherwise return.
         */
        // check if all arguments are positive
        if (!(numOfLines > 0 && minWordsPerLine > 0 && maxWordsPerLine > 0
                        && minCharactersPerWord > 0 && maxCharactersPerWord > 0)) {
            System.err.println("Error. Arguments to RandomStringsFile() must be positive.");
            return;
        }
        // check if ranges make sense
        if (!(minWordsPerLine <= maxWordsPerLine && minCharactersPerWord <= maxCharactersPerWord)) {
            System.err.println("Error. Arguments specifying ranges to RandomStringsFile() must be valid.");
            return;
        }
        Random r = new Random();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            
            /*
             * The logic is to generate a
             * random number of random numbers, and these numbers 
             * will be used as indices to make a pure random string
             * out of the string numsAndAlphabets or nums.
             */
            while (numOfLines > 0) {
                int randomWordsPerLine = r.nextInt(maxWordsPerLine + 1);
                String s = "";
                if (randomWordsPerLine >= minWordsPerLine) {
                    for (int i = 0; i < randomWordsPerLine;){
                        int randomCharsPerLine = r.nextInt(maxCharactersPerWord + 1);
                        if (randomCharsPerLine >= minCharactersPerWord) {
                            int [] randomIndices = new int[randomCharsPerLine];
                            for (int j = 0 ; j < randomCharsPerLine; j++) {
                        		if(numsOnly)
                        			s += nums.charAt(r.nextInt(nums.length()));
                        		else
                        			s += alphabets.charAt(r.nextInt(alphabets.length()));
                            }
                            s += " ";	// Separate different words by spaces.
                            i++; // Increment i only if the condition 
                            	 // is met, otherwise keep trying.
                        }
                    }
                    bw.write(s + "\n");
                    numOfLines--;
                }       
            }       // end while loop
            bw.close();
        }       // end try block
        catch (FileNotFoundException fN){
           	fN.printStackTrace();
        }
        catch (IOException e){
                System.out.println(e);
        }       
    }
    
}