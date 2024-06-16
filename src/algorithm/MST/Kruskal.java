package algorithm.MST;

import datastructure.graph.*;
import datastructure.unionfind.*;
import algorithm.sorting.*;

import java.util.Arrays;

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
    public <T extends Comparable<T>> void compute(WeightedGraph<D> g) {
		QuickFindSize<Vertex<D>> UF = new QuickFindSize<>();
		this.t = new WeightedGraphAL<>();
		for(int i=0; i<g.vertexNum(); i++){
			UF.makeSet(g.vertexes().get(i));
		}
		WeightedEdge<D>[] tmp = g.edges().toArray(new WeightedEdge [g.edgeNum()]);
		Sorting.heapsort((T[]) Arrays.stream(tmp).toArray());
		for(int i=0;i<g.edgeNum();i++){
			QFnode<Vertex<D>> u = new QFnode<>(g.edges().get(i).source, null, null);
			QFnode<Vertex<D>> v = new QFnode<>(g.edges().get(i).dest, null, null);
			QFRset<Vertex<D>> Tu = (QFRset<Vertex<D>>) UF.find(u);
			QFRset<Vertex<D>> Tv = (QFRset<Vertex<D>>) UF.find(v);
			if(!Tu.equals(Tv)){
				this.t.addEdge(g.edges().get(i));
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