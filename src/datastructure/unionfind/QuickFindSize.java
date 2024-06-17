package datastructure.unionfind;

import java.util.*;

/**
 * This class implements the Quick Find data structure for disjoint-set forests,
 * with a heuristic based on the size of the sets to improve average performance.
 *
 * @param <D> the type of data stored in the nodes of the sets.
 */
public class QuickFindSize<D> extends QuickFind<D> {

    /**
     * Creates a Quick Find data structure with the size heuristic.
     */
    public QuickFindSize() {
    }

    /**
     * Creates a new set containing a single element with the provided data.
     *
     * @param d the data to be stored in the new set's representative node.
     * @return the newly created set (represented by its representative node).
     */
    public QFnode<D> makeSet(D d) {
        QFnode<D> node = new QFnode<>(d, null, null);  // Create a node with the data
        node.parent = new QFRset<>(node, node, 1);      // Create a new set (QFRset) with the node as its representative and size 1
        return node;
    }

    /**
     * Merges two sets by linking the root of the smaller set to the root of the larger set.
     * This implementation uses a size heuristic to maintain an amortized O(log n) union operation cost.
     *
     * @param s the first set to be merged.
     * @param t the second set to be merged.
     */
    public void union(QFset<D> s, QFset<D> t) {
        if (!s.equals(t)) {  // Check if the sets are already the same
            // Choose the set with the larger size as the new parent
            if (((QFRset<D>) s).size >= ((QFRset<D>) t).size) {
                super.union(s, t);  // Call the base class union (linking roots)
                ((QFRset<D>) s).size += ((QFRset<D>) t).size;  // Update the size of the larger set
            } else {
                super.union(t, s);  // Call the base class union with the larger set first
                ((QFRset<D>) t).size += ((QFRset<D>) s).size;  // Update the size of the larger set
            }
        }
    }
}
