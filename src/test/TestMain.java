package test;

import java.util.ArrayList;
import algorithms.*;
import utilities.*;

// TODO Figure out unit and functional testing

public class TestMain {
	private static final String line 		= "_________________________________file___________________________";
	private static final String dottedLine 	= "............................................................";
	
	// Test text files
    private static final String TESTFILE_10000_STRINGS = "/home/kdhindsa/workspace/Algorithms/testfiles/testFile_10000_Strings.txt";
    private static final String TESTFILE_100000_STRINGS = "/home/kdhindsa/workspace/Algorithms/testfiles/testFile_100000_Strings.txt";
    private static final String TESTFILE_100_NUMBERS = "/home/kdhindsa/workspace/Algorithms/testfiles/testFile_100_Numbers.txt";
    private static final String TESTFILE_100000_NUMBERS = "/home/kdhindsa/workspace/Algorithms/testfiles/testFile_100000_Numbers.txt";
    private static final String DICTIONARY = "/home/kdhindsa/workspace/Algorithms/testfiles/US.dic";
    
    // Test strings for Levenstein Distance
    // These test data were generated by using an online
    // Levenshtein Distance calculator at: http://gtools.org/levenshtein-calculate.php
    private static final String LEV_STRING_A1 = "white house";
    private static final String LEV_STRING_A2 = "empIre state building";
    private static final int LEV_DIST_A = 17;
    
    private static final String LEV_STRING_B1 = "baba black    sheep";
    private static final String LEV_STRING_B2 = "blasphemy    laws   are  bad   ";
    private static final int LEV_DIST_B = 16;
    
    private static final String LEV_STRING_C1 = "what are you talking about";
    private static final String LEV_STRING_C2 = "about a very efficient sorting algorithm";
    private static final int LEV_DIST_C = 27;
    
    private static final String LEV_STRING_D1 = "laptop";
    private static final String LEV_STRING_D2 = "desktop";
    private static final int LEV_DIST_D = 4;
    
    // Test strings for Jaccard Index
    private static final String JAC_STRING_A1 = "sunday";
    private static final String JAC_STRING_A2 = "saturday";
    private static final float JAC_INDEX_A = 4 / (float)12;
    
    private static final String JAC_STRING_B1 = "abcd";
    private static final String JAC_STRING_B2 = "abac";
    private static final float JAC_INDEX_B = 2 / (float)8;
    
    private static enum TestAlg {
    	selection, bubble, merge, quick,	// Sorting algorithms
    	levenshtein, jaccard,				// Similarity algorithms
    	findSimilarLevenshtein,				// Utility to find similar strings using Levenshtein Distance algorithm
    	findSimilarJaccard					// Utility to find similar strings using Jaccard Index algorithm
    }
    
    /**
     * @param args => [selection | bubble | merge | quick | levenshtein | jaccard | findSimilarLevenshtein | findSimilarJaccard]
     */
    public static void main(String [] args){
    	
    	// Generate file with random lines of text
    	// File.createRandomStringsFile(TESTFILE_100000_Numbers, 100000, 1, 1, 1, 9, true);
    	
    	// Loop through all arguments
    	for(String arg : args) {
    		try {
        		switch(TestAlg.valueOf(arg)) {
        		case selection:
        			System.out.println(dottedLine);
		        	System.out.println("Selection Sort (n = 10000)");
		            testSelectionSort(TESTFILE_10000_STRINGS, false);
		            
		            System.out.println(dottedLine);
		            
		            System.out.println("Selection Sort (n = 100000)");
		            testSelectionSort(TESTFILE_100000_STRINGS, false);
		            System.out.println(dottedLine);
        			break;
        			
        		case bubble:
        			System.out.println(dottedLine);
		            System.out.println("Bubble Sort (n = 10000)");
		            testBubbleSort(TESTFILE_10000_STRINGS, false);
		            
		            System.out.println(dottedLine);
		            
		            System.out.println("Bubble Sort (n = 100000)");
		            testBubbleSort(TESTFILE_100000_STRINGS, false);
		            System.out.println(dottedLine);
        			break;
        			
        		case merge:
        			System.out.println(dottedLine);
		            System.out.println("Merge Sort (n = 10000)");
		            testMergeSort(TESTFILE_10000_STRINGS, false);
		            
		            System.out.println(dottedLine);
		            
		            System.out.println("Merge Sort (n = 100000)");
		            testMergeSort(TESTFILE_100000_STRINGS, false);
		            System.out.println(dottedLine);
        			break;
        			
        		case quick:
        			System.out.println(dottedLine);
		            System.out.println("Quick Sort (n = 10000)");
		            testQuickSort(TESTFILE_10000_STRINGS, false);
		            
		            System.out.println(dottedLine);
		            
		            System.out.println("Quick Sort (n = 100000)");
		            testQuickSort(TESTFILE_100000_STRINGS, false);
		            System.out.println(dottedLine);
        			break;
        			
        		case levenshtein:
        			System.out.println(dottedLine);
        			testLevenshteinDistance();
        			System.out.println(dottedLine);
        			break;
        		
        		case jaccard:
        			System.out.println(dottedLine);
        			testJaccardIndex();
        			System.out.println(dottedLine);
        			break;
        		
        		case findSimilarLevenshtein:
        			System.out.println(dottedLine);
        			testFindSimilarLevenshteinDistance(DICTIONARY, "emancipate", 2);
        			System.out.println(dottedLine);
        			break;
        		
        		case findSimilarJaccard:
        			System.out.println(dottedLine);
        			testFindSimilarJaccardIndex(DICTIONARY, "emancipate", 0.5);
        			System.out.println(dottedLine);
        			break;
        		
        		default:
        			// Since we are switching an enum type, default is meaningless here
        			// because an exception is thrown if the enum type is not found.
        		}
    		}
    		catch(Exception e) {
    			System.err.println("Invalid argument: " + arg);
    		}
    	}
    }       
        
    /**
     * Test selection sort algorithm.
     * @param testFile		Full path of the file to test the algorithm with.
     * @param writeToFile	If true, then write the sorted contents to a new file.
     */
    public static void testSelectionSort(String testFile, boolean writeToFile) {
        ArrayList<String> fileArray =   File.readFile(testFile);
        String [] strArray = new String[fileArray.size()];
        fileArray.toArray(strArray);
        
        Sort.SelectionSort selectionSort = new Sort.SelectionSort();
        
        long start = System.currentTimeMillis();	// Get start time
        strArray = selectionSort.sort(strArray);
        long end = System.currentTimeMillis();		// Get end time
        
        float timeElapsed = (end - start) / 1000F;
        
        if(writeToFile)
        	File.writeFile(strArray, testFile + ".SORTED.TEST.SELECTION_SORT");
        
        if (Sort.testSortCorrectness(strArray)) {
                System.out.println("testSelectionSort(): Selection Sort Algorithm Test Passed.");
                System.out.println("Running time (seconds): " + timeElapsed);
        }
        else {
                System.out.println("testSelectionSort(): Selection Sort Algorithm Test Failed.");
        }
    }
    
    /**
     * Test bubble sort algorithm.
     * @param testFile		Full path of the file to test the algorithm with.
     * @param writeToFile	If true, then write the sorted contents to a new file.
     */
    public static void testBubbleSort(String testFile, boolean writeToFile) {
        ArrayList<String> fileArray =   File.readFile(testFile);
        String [] strArray = new String[fileArray.size()];
        fileArray.toArray(strArray);
        
        Sort.BubbleSort bubbleSort = new Sort.BubbleSort();
        
        long start = System.currentTimeMillis();	// Get start time
        strArray = bubbleSort.sort(strArray);
        long end = System.currentTimeMillis();		// Get end time
        
        float timeElapsed = (end - start) / 1000F;
        
        if(writeToFile)
        	File.writeFile(strArray, testFile + ".SORTED.TEST.BUBBLE_SORT");
        
        if (Sort.testSortCorrectness(strArray)) {
                System.out.println("testBubbleSort(): Bubble Sort Algorithm Test Passed.");
                System.out.println("Running time (seconds): " + timeElapsed);
        }
        else {
                System.out.println("testBubbleSort(): Bubble Sort Algorithm Test Failed.");
        }
    }
    
    /**
     * Test merge sort algorithm.
     * @param testFile		Full path of the file to test the algorithm with.
     * @param writeToFile	If true, then write the sorted contents to a new file.
     */
    public static void testMergeSort(String testFile, boolean writeToFile) {       
        ArrayList<String> fileArray =   File.readFile(testFile);
        String [] strArray = new String[fileArray.size()];
        fileArray.toArray(strArray);
        
        Sort.MergeSort mergeSort = new Sort.MergeSort();
        
        long start = System.currentTimeMillis();	// Get start time
        strArray = mergeSort.sort(strArray);
        long end = System.currentTimeMillis();		// Get end time
        
        float timeElapsed = (end - start) / 1000F;
        
        if(writeToFile)
        	File.writeFile(strArray, testFile + ".SORTED.TEST.MERGE_SORT");
        
        if (Sort.testSortCorrectness(strArray)) {
                System.out.println("testMergeSort(): Merge Sort Algorithm Test Passed.");
                System.out.println("Running time (seconds): " + timeElapsed);
        }
        else {
                System.out.println("testMergeSort(): Merge Sort Algorithm Test Failed.");
        }
    }
    
    /**
     * Test quick sort algorithm.
     * @param testFile		Full path of the file to test the algorithm with.
     * @param writeToFile	If true, then write the sorted contents to a new file.
     */
    public static void testQuickSort(String testFile, boolean writeToFile) {       
        ArrayList<String> fileArray =   File.readFile(testFile);
        String [] strArray = new String[fileArray.size()];
        fileArray.toArray(strArray);
        
        Sort.QuickSort quickSort = new Sort.QuickSort();
        
        long start = System.currentTimeMillis();	// Get start time
        strArray = quickSort.sort(strArray);
        long end = System.currentTimeMillis();		// Get end time
        
        float timeElapsed = (end - start) / 1000F;
        
        if(writeToFile)
        	File.writeFile(strArray, testFile + ".SORTED.TEST.QUICK_SORT");
        
        if (Sort.testSortCorrectness(strArray)) {
                System.out.println("testQuickSort(): Quick Sort Algorithm Test Passed.");
                System.out.println("Running time (seconds): " + timeElapsed);
        }
        else {
                System.out.println("testQuickSort(): Quick Sort Algorithm Test Failed.");
        }
    }
    
    /**
     * Test Levenshtein Distance algorithm.
     */
    public static void testLevenshteinDistance() {
    	Similar sim = new Similar();
    	if(sim.levenshteinDistance(LEV_STRING_A1, LEV_STRING_A2) == LEV_DIST_A
    			&& sim.levenshteinDistance(LEV_STRING_B1, LEV_STRING_B2) == LEV_DIST_B
    			&& sim.levenshteinDistance(LEV_STRING_C1, LEV_STRING_C2) == LEV_DIST_C
    			&& sim.levenshteinDistance(LEV_STRING_D1, LEV_STRING_D2) == LEV_DIST_D)
    		System.out.println("testLevenshteinDistance(): Levenshtein Distance Algorithm Test Passed.");
    	else
    		System.out.println("testLevenshteinDistance(): Levenshtein Distance Algorithm Test Failed.");
    }
    
    /**
     * Test Jaccard Index algorithm
     */
    public static void testJaccardIndex() {
    	Similar sim = new Similar();
    	if(sim.jaccardIndex(JAC_STRING_A1, JAC_STRING_A2) == JAC_INDEX_A
    			&& sim.jaccardIndex(JAC_STRING_B1, JAC_STRING_B2) == JAC_INDEX_B)
    		System.out.println("testJaccardIndex(): Jaccard Index Algorithm Test Passed.");
    	else
    		System.out.println("testJaccardIndex(): Jaccard Index Algorithm Test Failed.");
    }
    
    /**
     * Test FindSimilar utility, findSimilar_LevenshteinDistance()
     */
    public static void testFindSimilarLevenshteinDistance(String testFile, String strQuery, int threshold_ld) {
    	ArrayList<String> fileArray =   File.readFile(testFile);
    	
    	FindSimilar fs = new FindSimilar();
    	ArrayList<String> simStrs = fs.findSimilar_LevenshteinDistance(strQuery, fileArray, threshold_ld);
    	
    	System.out.println("testFindSimilarLevenshteinDistance(): Strings similar to \"" + strQuery + "\" are");
    	for(String s : simStrs)
    		System.out.println(s);
    }
    
    /**
     * Test FindSimilar utility, findSimilar_JaccardIndex()
     */
    public static void testFindSimilarJaccardIndex(String testFile, String strQuery, double threshold_ji) {
    	ArrayList<String> fileArray =   File.readFile(testFile);
	
    	FindSimilar fs = new FindSimilar();
    	ArrayList<String> simStrs = fs.findSimilar_JaccardIndex(strQuery, fileArray, threshold_ji);
    	
    	System.out.println("testFindSimilarJaccardIndex(): Strings similar to \"" + strQuery + "\" are");
    	for(String s : simStrs)
    		System.out.println(s);
    }
    
        
}

