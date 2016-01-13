package proj3;

import java.util.Random;

public class QuickSort <E extends Comparable <E> > implements Sorter<E> {

	public void sort(E[] array) {
		
		quickSortRec(array, 0, array.length-1);
	}

	/**
	 * @param array array that will be partitioned and recursively sorted
	 * @param left the beginning of the array
	 * @param right the end of the array
	 */
	private void quickSortRec(E[] array, int left, int right) {
		
		//using selection sort did not show visible improvement, so I left it out (which is why I don't use it for merge sort as well) 
/*		if (array.length <= 40){
			SelectionSort x = new SelectionSort();
			x.sort(array);
		}*/
		
		int pivotIndex = findpivot(array, left, right);
		int newPivotIndex = partition(array, left, right, pivotIndex);

		//recursively sort the arrays that are greater than one element
		if(newPivotIndex - left > 1)
			quickSortRec(array, left, newPivotIndex - 1);
		
		if(right - newPivotIndex > 1)
			quickSortRec(array, newPivotIndex + 1, right);
			
	}
	
	/**
	 * @param array the input sub-array that is being recursively sorted
	 * @param left beginning index of the sub-array used to find the pivot
	 * @param right ending index
	 * @return
	 */
	private int findpivot(E[] array, int left, int right) {
		
		Random rand = new Random();
		int x = rand.nextInt(right - left) + left; //before improvement this was left
		int y = rand.nextInt(right - left) + left; //before improvement this was (left+right)/2
		int z = rand.nextInt(right - left) + left; //before improvement this was right
		
		//if x is greater than or equal to y and less than 0 then it is the median
		if(array[x].compareTo(array[y]) >= 0 && array[x].compareTo(array[z]) < 0)
			return x;
		//if y is greater than x and less than z, it is the median
		else if(array[y].compareTo(array[x]) >= 0 && array[y].compareTo(array[z]) < 0)
			return y;
		//otherwise it is z
		else
			return z;
	}

	/**
	 * @param array the sub-array passed in that will be sorted
	 * @param left the beginning index to mark the sub-array
	 * @param right the right index
	 * @param pivot the pivot that is passed in to compare each element to
	 * @return
	 */
	private int partition(E[] array, int left, int right, int pivot) {
	
		//first move the pivot to the end of the sub-array and move the right index to the element before the last one
		E tmp = array[right];
		array[right] = array[pivot];
		array[pivot] = tmp;
		
		pivot = right;
		right = right - 1;
		
		//while loop to keep moving the indexes as long as they don't cross
		while (left <= right){
			
			//while the element is not greater than the pivot continue because it is on the correct side
			while (array[left].compareTo(array[pivot]) < 0){
				left++;
			}
			//same for the right side
			while (right >= left && array[right].compareTo(array[pivot]) >= 0){
				right--;
			}
			//if the bounds cross, we swap the two elements because they are on the wrong sides of the array
			if(right > left){
				E tmp1 = array[left];
				array[left] = array[right];
				array[right] = tmp1;
			}
		}
		
		//then we swap the pivot with its original and final location
		E tmp2 = array[left];
		array[left] = array[pivot];
		array[pivot] = tmp2;
		return left;
	}

}
