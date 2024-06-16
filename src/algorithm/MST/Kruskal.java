package algorithm.MST;

import datastructure.graph.*;
import datastructure.unionfind.*;
import algorithm.sorting.*;
import java.util.HashMap;
/**
 * This class contains the implementation of the Kruskal's algorithm for the construction of a Minimum Spanning Tree (MST) of a weighted graph.
 * 
 * @param <D> type of the data stored in the nodes of the graph
 */
public class Kruskal<D> implements MST<D> {

	// The WeightedGraph on which the MST is computed
	private WeightedGraph<D> t;
	
	// The total weight of the MST
	private double weight;

	/** 
	 * Computes the Minimum Spanning Tree (MST) of the specified weighted graph.
	 * 	
	 * @param g the weighted graph
	 */
    public void compute(WeightedGraph<D> g) {
		QuickUnionRank<Vertex<D>> UF = new QuickUnionRank<>();
		HashMap<D,QUnode<Vertex<D>>> hasUF = new HashMap<>();
		this.t = new WeightedGraphAL<>();
		this.weight = 0;

		for(int i=0; i<g.vertexNum(); i++){
			hasUF.put(g.vertexes().get(i).data, UF.makeSet(g.vertexes().get(i)));
			this.t.addVertex(g.vertexes().get(i).data);
		}
		WeightedEdge<D>[] tmp = new WeightedEdge[g.edgeNum()];
		g.edges().toArray(tmp);
		Sorting.heapsort(tmp);
		for(int i=0;i<g.edgeNum();i++) {
			//System.out.println(hasUF.get(i));
			QURset Tu = (QURset) UF.find(hasUF.get(tmp[i].source.data));
			QURset Tv = (QURset) UF.find(hasUF.get(tmp[i].dest.data));
			if (!Tu.equals(Tv)) {
				this.t.addEdge(tmp[i]);
				this.weight += tmp[i].weight;
				UF.union(Tu, Tv);

			}
		}
    }
	
	/**
	 * Returns the Minimum Spanning Tree (MST) of the weighted graph.
	 * 
	 * @return the Minimum Spanning Tree (MST) of the weighted graph
	 */
	public WeightedGraph<D> spanningTree() {
		return this.t;
	}
	
	/**
	 * Returns the total weight of the Minimum Spanning Tree (MST) of the weighted graph.
	 * 
	 * @return the total weight of the Minimum Spanning Tree (MST) of the weighted graph
	 */
	public double totalWeight() {
		return this.weight;
	}
}