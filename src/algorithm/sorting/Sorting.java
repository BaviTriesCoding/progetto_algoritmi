package algorithm.sorting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import datastructure.graph.*;

/**
 * This class contains various sorting algorithms
 */
public class Sorting<D>{

	/*private static <T extends Comparable<T>> void swap(T[] A, int i, int j) {
		T tmp = A[i];
		A[i]  = A[j];
		A[j]  = tmp;
	}*/
	
	public void swap(ArrayList<Edge<D>> A, int i, int j){
		WeightedEdge<D> tmp = new WeightedEdge<>(A.get(i).getSource(), A.get(i).getDest(), ((WeightedEdge<D>) A.get(i)).getWeight());
		A.get(i).setSource(A.get(j).getSource());
		A.get(i).setDest(A.get(j).getDest());
		((WeightedEdge<D>) A.get(i)).setWeight(((WeightedEdge<D>)A.get(j)).getWeight());
		A.get(j).setSource(tmp.getSource());
		A.get(j).setDest(tmp.getDest());
		((WeightedEdge<D>) A.get(j)).setWeight(tmp.getWeight());
	}

	public void selectionsort(ArrayList<Edge<D>> A) {
		for(int i=0; i<A.size()-1; i++){
			int m=i;
			for(int j=i; j<A.size(); j++){
				if(((WeightedEdge<D>) A.get(j)).weight < ((WeightedEdge<D>) A.get(m)).weight){
					m=j;
				}
			}
			if(m!=i){
				swap(A, i, m);
			}
		}
	}

	/**
	 * Sorts the specified array according to the ordering induced by the compareTo() method in &Theta;(nlogn)
	 * <p>
	 * Implements the heapsort algorithm.
	 * <ul>
	 * <li> Worst/Average-cost: &Theta;(nlogn)
	 * <li> Best-case cost: &Theta;(n)	
	 * </ul>
	 * @param A the array to be sorted
	 */
		
	/*public static <T extends Comparable<T>> void heapsort(T[] A) {
		heapify(A, A.length - 1, 0);
		for (int c = (A.length - 1); c > 0; c--) {
			T k = findmax(A);
			deletemax(A, c);
			A[c] = k;
		}
	}*/
	public void heapsort(ArrayList<Edge<D>> A){
		heapify(A, A.size()-1, 0);
		for(int c = (A.size()-1); c > 0; c--){
			Edge<D> k = findmax(A);
			deletemax(A, c);
			A.set(c, k);
		}
	}
	
	/*private static <T extends Comparable<T>> void heapify(T[] A, int n, int i) {
		if (i >= n) return;
		heapify(A, n, left(i));
		heapify(A, n, right(i));
		fixheap(A, n, i);
	}*/
	private void heapify(ArrayList<Edge<D>> A, int n, int i){
		if( i >= n) return;
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
			
	/*private static <T extends Comparable<T>> void fixheap(T[] A, int c, int i) {
		int l = left(i), r = right(i);
		if (l > c) return;
		int max = l;
		if (r <= c && A[l].compareTo(A[r]) < 0)
			max = r;
		if (A[i].compareTo(A[max]) < 0) {
			swap(A, i, max);
			fixheap(A, c, max);
		}
	}*/
	private void fixheap(ArrayList<Edge<D>> A, int c, int i){
		int l = left(i), r = right(i);
		if(l > c) return;
		int max = l;
		if( r <= c && ((WeightedEdge<D>) A.get(l)).weight < ((WeightedEdge<D>) A.get(r)).weight){
			max = r;
		}
		if(((WeightedEdge<D>) A.get(i)).weight < ((WeightedEdge<D>) A.get(max)).weight){
			swap(A, i, max);
			fixheap(A, c, max);
		}
	}
	
	/*private static <T extends Comparable<T>> T findmax(T[] A) {
		return A[0];
	}*/
	private Edge<D> findmax(ArrayList<Edge<D>> A){ return A.get(0);}
	
	/*private static <T extends Comparable<T>> void deletemax(T[] A, int c) {
		if (c <= 0) return;
		A[0] = A[c];
		c--;
		fixheap(A, c, 0);
	}*/
	private void deletemax(ArrayList<Edge<D>> A, int c){
		if(c <= 0) return;
		A.set(0, A.get(c));
		fixheap(A, c-1, 0);
	}
					
}