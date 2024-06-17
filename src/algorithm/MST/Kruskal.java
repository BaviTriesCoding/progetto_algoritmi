package algorithm.MST;

import datastructure.graph.*;
import datastructure.unionfind.*;
import algorithm.sorting.*;

import java.util.HashMap;

/**
 * This class implements Kruskal's algorithm for finding the Minimum Spanning Tree (MST)
 * of a weighted graph.
 *
 * @param <D> the type of data stored in the graph's nodes.
 */
public class Kruskal<D> implements MST<D> {

    // The weighted graph on which the MST is computed
    private WeightedGraph<D> t;

    // The total weight of the MST
    private double weight;

    /**
     * Computes the Minimum Spanning Tree (MST) of the specified weighted graph.
     *
     * @param g the weighted graph to find the MST for
     */
    public void compute(WeightedGraph<D> g) {
        // Create a Quick Union Rank data structure for efficient union-find operations
        QuickUnionRank<Vertex<D>> UF = new QuickUnionRank<>();

        // Map each node's data to its corresponding QuickUnionRank node
        HashMap<D, QUnode<Vertex<D>>> hasUF = new HashMap<>();

        // Initialize the MST graph and weight
        this.t = new WeightedGraphAL<>();
        this.weight = 0;

        // Add all vertices from the input graph to the MST graph and create corresponding QuickUnionRank nodes
        for (int i = 0; i < g.vertexNum(); i++) {
            hasUF.put(g.vertexes().get(i).data, UF.makeSet(g.vertexes().get(i)));
            this.t.addVertex(g.vertexes().get(i).data);
        }

        // Convert the graph's edges to an array and sort them in ascending order of weight (using Merge Sort)
        WeightedEdge<D>[] tmp = new WeightedEdge[g.edgeNum()];
        g.edges().toArray(tmp);
        Sorting.mergesort(tmp);

        // Process edges in sorted order
        for (int i = 0; i < g.edgeNum(); i++) {
            // Find the QuickUnionRank sets for the source and destination nodes of the current edge
            QURset Tu = (QURset) UF.find(hasUF.get(tmp[i].source.data));
            QURset Tv = (QURset) UF.find(hasUF.get(tmp[i].dest.data));

            // Check if adding the edge would create a cycle (if they belong to the same set)
            if (!Tu.equals(Tv)) {
                // If not a cycle, add the edge to the MST and update weight
                this.t.addEdge(tmp[i]);
                this.weight += tmp[i].weight;

                // Merge the sets of the source and destination nodes in the QuickUnionRank structure
                UF.union(Tu, Tv);
            }
        }
    }

    /**
     * Returns the constructed Minimum Spanning Tree (MST).
     *
     * @return the Minimum Spanning Tree (MST) as a WeightedGraph
     */
    public WeightedGraph<D> spanningTree() {
        return this.t;
    }

    /**
     * Returns the total weight of the Minimum Spanning Tree (MST).
     *
     * @return the total weight of the MST
     */
    public double totalWeight() {
        return this.weight;
    }
}
