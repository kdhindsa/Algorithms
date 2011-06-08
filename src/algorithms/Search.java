package algorithms;

/**
 * @author kdhindsa
 * 
 * collection of different searching algorithms
 */
public class Search {
        
        /**
         * @author kdhindsa
         * 
         * algorithm to perform linear search
         */
        public static class LinearSearch {
                /**
                 * search for a particular string specified by target
                 * and return its index in the array supplied
                 * 
                 * @param       arr             array of strings to search from
                 * @param       target  string to be searched
                 * 
                 * @return      index location of target, if not found -1 is returned
                 */
                public int search (String [] arr, String target) {
                        int index = 0;
                        for (String s : arr) {
                                if (s.compareTo(target) == 0)
                                        return index;
                                index++;
                        }
                        // signal an error
                        return -1;      
                }
        }

}