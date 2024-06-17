package datastructure.unionfind;

/**
 * This class implements the Quick Union data structure for disjoint-set forests,
 * with a heuristic based on the rank of sets to improve average performance.
 *
 * @param <D> the type of data stored in the nodes of the sets.
 */
public class QuickUnionRank<D> extends QuickUnion<D> {

    /**
     * Creates a Quick Union data structure with the rank heuristic.
     */
    public QuickUnionRank() {
    }

    /**
     * Creates a new set containing a single element with the provided data.
     *
     * @param d the data to be stored in the new set's representative node.
     * @return the newly created set (represented by its representative node).
     */
    public QUnode<D> makeSet(D d) {
        QURset singleton = new QURset();  // Create a new empty set (QURset)
        QUnode<D> node = new QUnode<>(d, singleton);  // Create a new node with the data and point it to the empty set
        return node;
    }

    /**
     * Merges two sets by linking the root of the smaller rank set to the root of the larger rank set.
     * This implementation uses the rank heuristic to maintain an amortized O(log n) complexity for the union operation.
     *
     * @param s the first set to be merged.
     * @param t the second set to be merged.
     */
    public void union(QUset s, QUset t) {
        if (s.equals(t)) {
            return;  // No need to merge if the sets are already the same
        }

        QURset setS = (QURset) s;  // Cast to access the rank field
        QURset setT = (QURset) t;  // Cast to access the rank field

        if (setS.rank == setT.rank) {
            super.union(s, t);  // Call the base class union (linking roots)
            setS.rank++;        // Increment the rank of the new root set (the one with the larger rank)
        } else if (setS.rank > setT.rank) {
            super.union(s, t);  // Call the base class union with the larger rank set first
        } else {
            super.union(t, s);  // Call the base class union with the larger rank set first (t in this case)
        }
    }
}
