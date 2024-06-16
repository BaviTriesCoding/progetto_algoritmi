package datastructure.unionfind;

import java.util.*;

/**
 * Union find implementation based on Quick Find with heuristic on the size.
 * It modifies the Quick Find implementation of the uniong operation by changing 
 * the parent set of the nodes in the smaller set.
 * In this way, the maximal number of changes in a sequence of n-1 union will
 * be O(n log n), hence the amortized cost of union is O(log n).
 *
 * @param <D> type of the data object 
 */	
public class QuickFindSize<D> extends QuickFind<D> {

	/**
	 * Creates an union find structure following the QuickFind implementation
	 * with heuristic on the size	
	 */	
	public QuickFindSize() { }	

	public QFnode<D> makeSet(D d) {
		QFnode<D> node = new QFnode<>(d, null, null);
		node.parent = new QFRset<>(node, node, 1);
		return node;
	}

	public void union(QFset<D> s, QFset<D> t) {
		if(!s.equals(t)){
			if(((QFRset)s).size >= ((QFRset)t).size){
				super.union(s, t);
				((QFRset)s).size = ((QFRset)s).size + ((QFRset)t).size;
			}else{
				super.union(t, s);
				((QFRset)t).size = ((QFRset)t).size + ((QFRset)s).size;
			}
		}
	}

}
