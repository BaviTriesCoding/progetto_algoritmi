package datastructure.graph;

import java.util.LinkedList;

/**
 * This class implements a weighted graph using an adjacency list representation.
 *
 * @param <D> the type of data stored in the graph's vertices.
 */
public class WeightedGraphAL<D> extends GraphAL<D> implements WeightedGraph<D> {

    /**
     * Constructs an empty weighted graph using an adjacency list representation.
     */
    public WeightedGraphAL() {
        super();  // Call the constructor of the base class GraphAL
    }

    /**
     * Adds a weighted edge to the graph.
     *
     * @param e the edge to be added (source, destination, weight)
     */
    public void addEdge(Edge<D> e) {
        // Cast the source and destination vertices to VertexAL (assuming it's a subclass) to access the index
        VertexAL<D> source = (VertexAL<D>) e.source;
        VertexAL<D> dest = (VertexAL<D>) e.dest;

        LinkedList<Edge<D>> sourceList = vertexes.get(source.index).adjac; // Get the adjacency list for the source vertex
        sourceList.add(e);// Add the edge to the source vertex's adjacency list
        WeightedEdge<D> reverseEdge = new WeightedEdge<D>(e.dest, e.source, ((WeightedEdge<D>) e).weight);// Create a corresponding edge with reversed source and destination for the destination vertex (undirected graph)
        LinkedList<Edge<D>> destList = vertexes.get(dest.index).adjac;// Get the adjacency list for the destination vertex
        destList.add(reverseEdge);// Add the reversed edge to the destination vertex's adjacency list

        // Update the total number of edges in the graph (considering undirected edges are added twice)
        this.m = this.m + 2;
    }
}
