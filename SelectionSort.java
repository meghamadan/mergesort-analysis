package proj3;

public class SelectionSort <E extends Comparable <E> > implements Sorter<E> {

	/* (non-Javadoc)
	 * @see proj3.Sorter#sort(java.lang.Comparable[])
	 */
	@Override
	public void sort(E[] list) {
		
		//first element in the array is the current minimum element
		for(int i = 0; i < list.length - 1; i++){
			E currentMin = list[i];
			int currentMinIndex = i;
			
			//find the smallest element in the rest of the array (if its smaller than current min)
			for(int j = i+1; j < list.length; j++){
				if(currentMin.compareTo(list[j]) > 0){
					currentMin = list[j];
					currentMinIndex = j;
				}
			}
			//switch this new current min for the original min
			if(currentMinIndex != i){
				list[currentMinIndex] = list[i];
				list[i] = currentMin;
			}
		}
	}
}
