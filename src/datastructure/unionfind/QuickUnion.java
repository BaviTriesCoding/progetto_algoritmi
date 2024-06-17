package datastructure.unionfind;

/**
 * This class implements the Quick Union data structure for disjoint-set forests.
 * Sets are initially singletons (each node points to itself as the parent set).
 * The parent of a set is updated during the union operation when the set is the second operand.
 *
 * @param <D> the type of data stored in the nodes of the sets.
 */
public class QuickUnion<D> implements UnionFind<D, QUnode<D>, QUset> {

    /**
     * Creates a Quick Union data structure.
     */
    public QuickUnion() {
    }

    /**
     * Creates a new set containing a single element with the provided data.
     *
     * @param d the data to be stored in the new set's representative node.
     * @return the newly created set (represented by its representative node).
     */
    public QUnode<D> makeSet(D d) {
        QUset singleton = new QUset();  // Create a new empty set (QUset)
        QUnode<D> node = new QUnode<>(d, singleton);  // Create a new node with the data and point it to the empty set
        return node;
    }

    /**
     * Merges two sets by linking the root of the smaller set to the root of the larger set.
     * This implementation has a constant time complexity (O(1)) for the union operation itself.
     *
     * @param s the first set to be merged.
     * @param t the second set to be merged.
     */
    public void union(QUset s, QUset t) {
        if (!s.equals(t) && s.equals(s.parent) && t.equals(t.parent)) {  // Check if the sets are different and both are root sets
            t.parent = s;  // Update the parent of the second set to be the root of the first set
        }
    }

    /**
     * Finds the set (represented by its root set) to which a given node belongs.
     * This operation involves traversing the tree of parent pointers starting from the node
     * until the root set is reached. The time complexity is O(h), where h is the height of the tree.
     * In the worst case, the height can be n-1 (for a chain-like structure), leading to an O(n) complexity.
     *
     * @param n the node for which to find the parent set.
     * @return the root set (QUset) containing the node.
     */
    public QUset find(QUnode<D> n) {
        QUset temp = n.set;  // Start from the node's current set
        while (!temp.equals(temp.parent)) {  // Traverse the parent pointers until the root set is found
            temp = temp.parent;
        }
        return temp;
    }
}
