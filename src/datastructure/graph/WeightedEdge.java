package datastructure.graph;

/**
 * Generic edge of weighted graphs
 *
 * @param <D> type of the data object in the graph vertexes
 */
public class WeightedEdge<D> extends Edge<D> implements Comparable<WeightedEdge<D>> {

    /**
     * vertex weight
     */
    public double weight;

    /**
     * Constructs an edge of a weighted graph
     *
     * @param source the source vertex
     * @param dest   the destination vertex
     * @param weight edge weight
     */
    public WeightedEdge(Vertex<D> source, Vertex<D> dest, double weight) {
        super(source, dest); // call the constructor of the parent class Edge
        this.weight = weight; // set the edge weight
    }

    @Override
    public int compareTo(WeightedEdge<D> G) {
        // Compares the weights of two WeightedEdge objects
        return Double.compare(this.weight, G.weight);
    }

}
