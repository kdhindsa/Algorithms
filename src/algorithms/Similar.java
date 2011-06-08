package algorithms;
import java.util.Hashtable;

public class Similar {
	
	/**
	 * Levenshtein Distance algorithm to determine 
	 * similarity between two strings. The strings are
	 * case insensitive.
	 * 
	 * @param s1
	 * @param s2
	 * @return	Levenshtein Distance between s1 and s2
	 */
	public int levenshteinDistance(String s1, String s2) {
		if(s1 == null || s2 == null)
			return -1;
		
		// Remove extra white spaces in a string
		s1 = cleanup(s1);
		s2 = cleanup(s2);
		
		int [][]d = new int[s1.length() + 1][s2.length() + 1];
		
		// Convert strings to lower case
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		
		int i, j;
		// the distance of any first string to an empty second string
		for(i = 0; i < s1.length() + 1 ; i++)
			d[i][0] = i;
		
		// the distance of any second string to an empty first string
		for(j = 0; j < s2.length() + 1; j++)
			d[0][j] = j;

		for(j = 1; j < s2.length() + 1; j++) {
			for(i = 1; i < s1.length() + 1; i++) {
				if(s1.charAt(i - 1) == s2.charAt(j - 1))
					d[i][j] = d[i - 1][j - 1];
				else
					// min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + 1)
					d[i][j] = (d[i - 1][j] + 1) < Math.min(d[i][j - 1] + 1, d[i - 1][j - 1] + 1)
								? d[i - 1][j] + 1 : Math.min(d[i][j - 1] + 1, d[i - 1][j - 1] + 1);
			}
		}
		
		return d[s1.length()][s2.length()];
	}
	
	/**
	 * Jaccard Index Algorithm to calculate similarity
	 * between two strings s1 and s2.
	 * @param s1
	 * @param s2
	 * @return	Jaccard Index between the two strings. 
	 * 			Higher the index, the more they are similar.
	 */
	public float jaccardIndex(String s1, String s2) {
		if(s1 == null || s2 == null)
			return -1;
		
		// Remove extra white spaces in a string
		s1 = cleanup(s1);
		s2 = cleanup(s2);
		
		// Generate bigrams for the strings
		String [] bigram_s1 = new String[s1.length() + 1];
		String [] bigram_s2 = new String[s2.length() + 1];
		for(int i = 0; i < s1.length() + 1; i++) {
			if(i == 0)
				bigram_s1[i] = Character.toString('$') + Character.toString(s1.charAt(i));
			
			else if(i > 0 && i < s1.length())
				bigram_s1[i] = Character.toString(s1.charAt(i - 1)) + Character.toString(s1.charAt(i));
			
			else if(i == s1.length())
				bigram_s1[i] = Character.toString(s1.charAt(i - 1)) + Character.toString('$');
		}
		for(int i = 0; i < s2.length() + 1; i++) {
			if(i == 0)
				bigram_s2[i] = Character.toString('$') + Character.toString(s2.charAt(i));
			
			else if(i > 0 && i < s2.length())
				bigram_s2[i] = Character.toString(s2.charAt(i - 1)) + Character.toString(s2.charAt(i));
			
			else if(i == s2.length())
				bigram_s2[i] = Character.toString(s2.charAt(i - 1)) + Character.toString('$');
		}
		
		// Calculate s1_bigram UNION s2_bigram
		Hashtable ht = new Hashtable();
		
		for(String s : bigram_s1)
			ht.put(s, new Integer(1));	// 1 is a dummy value
		
		for(String s : bigram_s2)
			ht.put(s, new Integer(1));	// 1 is a dummy value

		int union = ht.size();
		
		// Calculate total duals in both bigrams
		int total = bigram_s1.length + bigram_s2.length;
		
		// total - union => intersection
		float jacIndex = (float)(total - union) / (float)union;
		
		return jacIndex;
	}
	
	/**
	 * Remove leading, trailing and extra white spaces in a string
	 * @param s
	 * @return	string s with leading, trailing and extra white spaces removed
	 */
	private static String cleanup(String s) {
		s = s.trim();		
		String [] arr = s.split("\\s+");	// Split using whitespace as a delimiter

		s = "";
		for(int i = 0; i < arr.length; i++) {
			if(i == arr.length - 1)
				s += arr[i];
			else
				s += arr[i] + " ";
		}
		return s;
	}

}
