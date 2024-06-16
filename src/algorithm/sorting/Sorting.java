package algorithm.sorting;

/**
 * This class contains various sorting algorithms
 */
public class Sorting {

	private static <T extends Comparable<T>> void swap(T[] A, int i, int j) {
		T tmp = A[i];
		A[i]  = A[j];
		A[j]  = tmp;
	}


	/**
	 * Sorts the specified array according to the ordering induced by the compareTo() method in &Theta;(n<sup>2</sup>)
	 * <p>
	 * Implements the selectionsort algorithm.
	 * <ul>
	 * <li> Worst/Average/Best-case cost: &Theta;(n<sup>2</sup>)
	 * </ul>
	 * @param A the array to be sorted
	 * @param <T> class of the object in the array
	 */
	public static <T extends Comparable<T>> void selectionsort(T[] A) {
		for(int i=0; i<A.length-1; i++){
			int m = i;
			for(int j=i; j<A.length; j++){
				if(A[m].compareTo(A[j])<0){
					m=j;
				}
			}
			if(m!=i){swap(A, i, m);}
		}
	}

	/**
	 * Sorts the specified array according to the ordering induced by the compareTo() method in O(n<sup>2</sup>)
	 * <p>
	 * Implements the insertionsort algorithm.
	 * <ul>
	 * <li> Worst/Average-case cost: &Theta;(n<sup>2</sup>)
	 * <li> Best-case cost: &Theta;(n)
	 * </ul>
	 * @param A the array to be sorted
	 * @param <T> class of the object in the array
	 */
	public static <T extends Comparable<T>> void insertionsort(T[] A) {
		for(int i=1; i<A.length; i++){
			int j=i;
			while(j>1 && A[j].compareTo(A[j-1])<0){
				swap(A, j, j-1);
				j--;
			}
		}
	}

	/**
	 * Sorts the specified array according to the ordering induced by the compareTo() method in &Theta; (nlogn)
	 * <P>
	 * Implements the mergesort algorithm.
	 * <ul>
	 * <li> Worst/Average/Best-case cost: &Theta;(nlogn)
	 * </ul>
	 * @param A the array to be sorted
	 * @param <T> class of the object in the array
	 */
	public static <T extends Comparable<T>> void mergesort(T[] A) {
		mergesortfunction(A, 0, A.length-1);
	}

	private static <T extends Comparable<T>> void mergesortfunction(T[] A, int firstIndex, int lastIndex){
		if(firstIndex<lastIndex){
			int middleIndex = (firstIndex + lastIndex) / 2;
			mergesortfunction(A, firstIndex, middleIndex);
			mergesortfunction(A, middleIndex + 1, lastIndex);
			merge(A, firstIndex, middleIndex, lastIndex);
		}
	}

	private static <T extends Comparable<T>> void merge(T[] A, int firstIndex, int middleIndex, int lastIndex){
		T[] B = (T[]) new Comparable[lastIndex-firstIndex+1];
		int i = firstIndex;//i itera il primo sottoarray
		int j = middleIndex+1;//j itera il secondo sottoarray
		int k = 0;//k itera l'array B

		while(i<=middleIndex && j<=lastIndex){//il primo while va a ordinare gli elementi dei due sottoarray
			if(A[i].compareTo(A[j])<0){// in odine crescente, e si ferma quando uno dei due sottoarray non ha più elementi
				B[k] = A[i];
				i++;
			}else{
				B[k] = A[j];
				j++;
			}
			k++;
		}
		while(i<=middleIndex){//gli altri due while finiscono il lavoro
			B[k] = A[i];
			k++;
			i++;
		}
		while(j<=lastIndex){
			B[k] = A[j];
			k++;
			j++;
		}
		for(k=0; k <lastIndex-firstIndex+1; k++){//il for trasferisce tutti gli elementi di B in A
			A[firstIndex+k] = B[k];
		}
	}


	/**
	 * Sorts the specified array according to the ordering induced by the compareTo() method in O(n<sup>2</sup>) and O(nlogn) on the average
	 * <p>
	 * Implements the quicksort algorithm.
	 * <ul>
	 * <li> Worst-case cost:  &Theta;(n<sup>2</sup>)
	 * <li> Average/Best-case cost: &Theta;(nlogn)
	 * </ul>
	 * @param A the array to be sorted
	 * @param <T> class of the object in the array
	 */
	public static <T extends Comparable<T>> void quicksort(T[] A) {
		quicksortfunction(A, 0, A.length-1);
	}
	private static <T extends Comparable<T>> void quicksortfunction(T[] A, int firstIndex, int lastIndex){
		if(firstIndex < lastIndex){
			int middleIndex = partition(A, firstIndex, lastIndex);
			quicksortfunction(A, firstIndex, middleIndex-1);
			quicksortfunction(A, middleIndex+1, lastIndex);
		}
	}
	private static <T extends Comparable<T>> int partition(T[] A, int p, int r){
		T x = A[r];
		int i = p;
		for(int j = p; j<r; j++){
			if(A[j].compareTo(x)<=0){
				swap(A, i, j);
				i++;
			}
		}
		swap(A, i, r);
		return i;
	}

	/**
	 * Sorts the specified array according to the ordering induced by the compareTo() method in &Theta; (nlogn)
	 * <p>
	 * Implements the heapsort algorithm.
	 * <ul>
	 * <li> Worst/Average-cost: &Theta;(nlogn)
	 * <li> Best-case cost: &Theta;(n)
	 * </ul>
	 * @param A the array to be sorted
	 * @param <T> class of the object in the array
	 */

	public static <T extends Comparable<T>> void heapsort(T[] A) {
		heapify(A, A.length - 1, 0);
		for (int c = (A.length - 1); c > 0; c--) {
			T k = findmax(A);
			deletemax(A, c);
			A[c] = k;
		}
	}

	private static <T extends Comparable<T>> void heapify(T[] A, int n, int i) {
		if (i >= n) return;
		heapify(A, n, left(i));
		heapify(A, n, right(i));
		fixheap(A, n, i);
	}

	private static int left(int i) {
		return ( 2*i + 1 );
	}

	private static int right(int i) {
		return ( 2*i + 2 );
	}

	private static <T extends Comparable<T>> void fixheap(T[] A, int c, int i) {
		int l = left(i), r = right(i);
		if (l > c) return;
		int max = l;
		if (r <= c && A[l].compareTo(A[r]) < 0)
			max = r;
		if (A[i].compareTo(A[max]) < 0) {
			swap(A, i, max);
			fixheap(A, c, max);
		}
	}

	private static <T extends Comparable<T>> T findmax(T[] A) {
		return A[0];
	}

	private static <T extends Comparable<T>> void deletemax(T[] A, int c) {
		if (c <= 0) return;
		A[0] = A[c];
		c--;
		fixheap(A, c, 0);
	}

}