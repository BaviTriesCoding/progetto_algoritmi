package datastructure.unionfind;

/**
 * This class implements the Quick Find data structure for disjoint-set forests.
 * Each node has a parent set (the set it belongs to) and a reference to its brother node
 * (used to efficiently traverse nodes within a set).
 *
 * @param <D> the type of data stored in the nodes of the sets.
 */
public class QuickFind<D> implements UnionFind<D, QFnode<D>, QFset<D>> {

    /**
     * Creates a Quick Find data structure.
     */
    public QuickFind() {
    }

    /**
     * Creates a new set containing a single element with the provided data.
     *
     * @param d the data to be stored in the new set's representative node.
     * @return the newly created set (represented by its representative node).
     */
    public QFnode<D> makeSet(D d) {
        QFnode<D> node = new QFnode<>(d, null, null);  // Create a node with the data
        node.parent = new QFset<>(node, node);      // Create a new set (QFRset) with the node as its representative and itself as the last node
        return node;
    }

    /**
     * Merges two sets by linking the root of the smaller set to the root of the larger set.
     * This implementation has a time complexity of O(n) for the union operation,
     * where n is the size of the smaller set, due to iterating through all nodes in the smaller set.
     *
     * @param s the first set to be merged.
     * @param t the second set to be merged.
     */
    public void union(QFset<D> s, QFset<D> t) {
        if (!s.equals(t)) {  // Check if the sets are already the same
            QFnode<D> temp = t.first;  // Start iterating from the first node of the smaller set
            while (!temp.equals(t.last)) {  // Loop until the last node
                temp.parent = s;           // Set the parent of the current node to the larger set's representative
                s.last.next = temp;        // Append the current node to the end of the larger set's linked list
                s.last = temp;             // Update the last node of the larger set
                temp = temp.next;          // Move to the next node in the smaller set
            }
        }
    }

    /**
     * Finds the set (represented by its representative node) to which a given node belongs.
     *
     * @param n the node for which to find the parent set.
     * @return the set (QFset) containing the node.
     */
    public QFset<D> find(QFnode<D> n) {
        return n.parent;
    }
}
