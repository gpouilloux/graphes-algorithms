package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Specific converter which enable to transform an array of primitive ints into a list of Integer and vice-versa...
 * @author gpouillo
 *
 */
public class ListConverter {
	
	private ListConverter() {
		// private constructor for static class
	}
	
	/**
	 * Convert an array of primitive ints to a list of Integer
	 * @param array the array to convert
	 * @return the list generated
	 */
	public static List<Integer> toList(int[] array) {
		List<Integer> res = new ArrayList<>();
		
		for(int i=0; i<array.length; i++) {
			res.add(new Integer(array[i]));
		}
		
		return res;
	}

	/**
	 * Convert a list of Integer in an array of primitive ints
	 * @param list the list of Integer to convert
	 * @return the array of ints
	 */
	public static int[] toArray(List<Integer> list) {
		int size = list.size();
		int[] res = new int[size];
		
		for(int i=0; i<size; i++) {
			res[i] = list.get(i);
		}
		
		return res;
	}
}
