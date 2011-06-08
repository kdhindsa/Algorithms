package algorithms;
import java.util.ArrayList;

/**
 * 
 * @author kdhindsa
 * 
 * Implementation of sorting algorithms.
 *
 */
public class Sort {
        
    /**
     * Algorithm to sort using selection sort technique.
     * 
     * @author kdhindsa
     * 
     */
    public static class SelectionSort {
        /**
         * Sort an array of strings using selection sort algorithm
         * and return the sorted array of strings.
         * @param arr   an array of String
         * @return sorted array of String
         */
        public String [] sort(String [] arr) {
        	if (arr == null)
        		return null;
        	
            int size = arr.length;
            if (size < 2) {
                return arr;
            }
            
            for (int out = 0; out < size - 1; out++){
                for (int in = out + 1; in < size; in++){
                    arr[in].trim();
                    arr[out].trim();
                    if (isLessThanOrEqualTo(arr[in], arr[out])){
                        String temp = arr[in];
                        arr[in] = arr[out];
                        arr[out] = temp;
                    }
                }
            }
            return arr;
        }
    }
        
    /**
     * Algorithm to sort using bubble sort technique.
     * 
     * @author kdhindsa
     * 
     */
    public static class BubbleSort {
        /**
         * Sort an array of strings using bubble sort algorithm
         * and return the sorted array of strings.
         * @param arr   an array of String
         * @return arr  sorted array of String
         */
        public String [] sort(String [] arr) {
        	if (arr == null)
        		return null;
        	
            int size = arr.length;
            if (size < 2) {
                return arr;
            }
            
            for (int out = size - 1; out > 1; out--){
                for (int in = 0; in < out; in++){
                    arr[in].trim();
                    arr[in + 1].trim();
                    if (isLessThanOrEqualTo(arr[in + 1], arr[in])){
                        String temp = arr[in];
                        arr[in] = arr[in + 1];
                        arr[in + 1] = temp;
                    }
                }
            }
            return arr;
        }
    }
        
    /**
     * Algorithm to sort using merge sort technique.
     * @author kdhindsa
     *
     */        
    public static class MergeSort {
    	private String[] A;

    	public String[] sort(String[] arr) {
    		if (arr == null)
        		return null;
    		
    		this.A = arr;

    		mergesort(0, A.length - 1);
    		return A;
    	}

    	private void mergesort(int low, int high) {
    		// Check if low is smaller then high, if not then the array is sorted
    		if (low < high) {
    			// Get the index of the element which is in the middle
    			int middle = (low + high) / 2;
    			// Sort the left side of the array
    			mergesort(low, middle);
    			// Sort the right side of the array
    			mergesort(middle + 1, high);
    			// Combine them both
    			merge(low, middle, high);
    		}
    	}

    	private void merge(int low, int middle, int high) {

    		// Helperarray
    		String[] helper = new String[A.length];

    		// Copy both parts into the helper array
    		for (int i = low; i <= high; i++) {
    			helper[i] = A[i];
    		}

    		int i = low;
    		int j = middle + 1;
    		int k = low;
    		// Copy the smallest values from either the left or the right side back
    		// to the original array
    		while (i <= middle && j <= high) {
    			if (isLessThanOrEqualTo(helper[i], helper[j])) {
    				A[k] = helper[i];
    				i++;
    			} else {
    				A[k] = helper[j];
    				j++;
    			}
    			k++;
    		}
    		// Copy the rest of the left side of the array into the target array
    		while (i <= middle) {
    			A[k] = helper[i];
    			k++;
    			i++;
    		}
    		helper = null;

    	}
    }
        
    /**
     * Algorithm to sort using quick sort technique.
     * @author kdhindsa
     *
     */
    public static class QuickSort {
    	public String [] sort(String [] A) {
    		if (A == null)
        		return null;
    		
    		ArrayList<String> array = new ArrayList<String>(A.length);
    		for(String s : A) {
    			array.add(s);
    		}
    		array = quickSort(array);
    		// copy array contents into A and return
    		A = array.toArray(new String[array.size()]);
    		return A;
    	}
    	
    	private ArrayList<String> quickSort(ArrayList<String> array) {
    		if(array.size() <= 1)
    			return array;

    		ArrayList<String> lesser = new ArrayList<String>();
    		ArrayList<String> greater = new ArrayList<String>();
    		
    		String pivot =	array.remove(array.size() / 2); 
    		
    		for(String s : array) {
    			if (isLessThanOrEqualTo(s, pivot)) {
    				lesser.add(s);
    			}
    			else {
    				greater.add(s);
    			}
    		}
    		
    		lesser = quickSort(lesser);
    		greater = quickSort(greater);
    		
    		// Concatenate lesser, pivot and greater
    		array.clear();
    		array.addAll(lesser);
    		array.add(pivot);
    		array.addAll(greater);
    		
    		return array;
    	}
    }
        
    /**
     * test if a file has been sorted correctly
     * @param       arr     an array of String
     * @return true if arr is sorted
     */
    public static boolean testSortCorrectness(String [] arr) {
    	if (arr == null)
    		return false;
    	
        if (arr.length == 0)
                return true;
        String line1 = arr[0];
        for (String line2 : arr) {
                if(!isLessThanOrEqualTo(line1, line2))
                        return false;
                line1 = line2;
        }
        return true;
    }
        
    /**
     * compare two strings lexicographically 
     * 
     * @param       a       first string
     * @param       b       second string
     * 
     * @return      true if a <= b --- similar to string compare
     */
    public static boolean isLessThanOrEqualTo(String a, String b){	
        return a.compareToIgnoreCase(b) <= 0;    	
    }

}