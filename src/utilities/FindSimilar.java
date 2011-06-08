package utilities;
import java.util.ArrayList;
import algorithms.Similar;


public class FindSimilar {
	
	/**
	 * Find similar strings in the array strs, using Jaccard Index algorithm.
	 * 
	 * @param strQuery		String for which similar results will be returned.
	 * @param strList		Array containing the strings to be searched from for similar results.
	 * @param threshold_ji	Value between 0.0 and 1.0, inclusive should be specified.
	 * 						Two strings will qualify to be similar if their Jaccard Index is greater
	 * 						than or equal to the specified threshold_ji. If threshold is a small value then two 
	 * 						strings can qualify easily. If it is large, then qualifying criteria is
	 * 						tighter.
	 * @return				ArrayList of strings that are similar to strQuery based on the specified threshold_ji.
	 */
	public ArrayList<String> findSimilar_JaccardIndex(String strQuery, String[] strList, double threshold_ji) {
		ArrayList<String> sList = new ArrayList<String>();
		ArrayList<String> similarStrs = new ArrayList<String>();
		
		for(String s : strList) {
			sList.add(s);
		}
		
		Similar sim = new Similar();
		
		for(int i = 0; i < sList.size(); i++) {
			if(sim.jaccardIndex(sList.get(i), strQuery) >= threshold_ji) {
				similarStrs.add(sList.get(i));
			}
		}
		
		return similarStrs;
	}
	
	/**
	 * Find similar strings in the array strs, using Levenshtein Distance algorithm.
	 * 
	 * @param strQuery		String for which similar results will be returned.
	 * @param strList		Array containing the strings to be searched from for similar results.
	 * @param threshold_ld	Two strings will qualify to be similar if their Levenshtein Distance is
	 * 						less than or equal to the specified threshold. If threshold_ld is a large value then two 
	 * 						strings can qualify easily. If it is small, then the qualifying criteria is
	 * 						tighter.
	 * @return				ArrayList of strings that are similar to strQuery based on the specified threshold_ld.
	 */
	public ArrayList<String> findSimilar_LevenshteinDistance(String strQuery, String[] strList, int threshold_ld) {
		ArrayList<String> sList = new ArrayList<String>();
		ArrayList<String> similarStrs = new ArrayList<String>();
		
		for(String s : strList) {
			sList.add(s);
		}
		
		Similar sim = new Similar();
		
		for(int i = 0; i < sList.size(); i++) {
			if(sim.levenshteinDistance(sList.get(i), strQuery) <= threshold_ld) {
				similarStrs.add(sList.get(i));
			}
		}
		
		return similarStrs;
	}

}
