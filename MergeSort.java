package proj3;

import java.lang.reflect.Array;

public class MergeSort <E extends Comparable <E> > implements Sorter<E> {
	
	
	/* (non-Javadoc)
	 * @see proj3.Sorter#sort(java.lang.Comparable[])
	 */
	public void sort(E[] array) {
		mergeSortRec(array, 0, array.length-1);
	}
	
	
	/**
	 * The recursive portion of the merge sort algorithm. 
	 * @param array the input list to be sorted
	 * @param firstIndex marks where to begin the merge sort
	 * @param lastIndex marks where to end the merge sort
	 */
	private void mergeSortRec(E[] array, int firstIndex, int lastIndex) {
		//as long as the array is not empty, continue with recursive sort (more than one element)
		if(firstIndex == lastIndex)
			return;

		int mid = (firstIndex + lastIndex)/2;
		//split the array until it is one element
		mergeSortRec(array, firstIndex, mid);
		mergeSortRec(array, mid+1, lastIndex);
		//finally merges the two arrays 
		merge(array, firstIndex, mid, mid+1, lastIndex);
	}

	/**
	 * @param array input array that will be merged together
	 * @param leftFirst start index of first array
	 * @param leftLast end index
	 * @param rightFirst start index of second sub-array
	 * @param rightLast end index
	 */
	private void merge(E [] array, int leftFirst, int leftLast, int rightFirst, int rightLast) {
		
		///create generic temporary array
		@SuppressWarnings("unchecked")
		E tmpArray[] = (E[]) Array.newInstance(array.getClass().getComponentType(), rightLast - leftFirst + 1);
	
		//create front indexes to keep track of the two sub arrays
		//start index of tmp array at 0
		int indexLeft = leftFirst;
		int indexRight = rightFirst;
		int index = 0;
		
		//while loop to pick the smallest elements from each sub array
		while (indexLeft <= leftLast && indexRight <= rightLast){
			
			if(array[indexLeft].compareTo(array[indexRight]) <= 0){
				tmpArray[index] = array [indexLeft];
				indexLeft++;
			}
			else{
				tmpArray[index] = array [indexRight];
				indexRight++;
			}
			index++;
		}
		
		//two while loops to complete the copying of the arrays if there are any elements left
		while(indexLeft <= leftLast){
			tmpArray[index] = array [indexLeft];
			indexLeft++;
			index++;
		}
		
		while(indexRight <= rightLast){
			tmpArray[index] = array [indexRight];
			indexRight++;
			index++;
		}
		
		//copy the tmp array to the original array
		for(int i = 0; i < tmpArray.length; i++){	
			 array[leftFirst] = tmpArray[i];
			 leftFirst++;
		}

	}

}
