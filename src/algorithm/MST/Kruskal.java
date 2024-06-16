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
    public void compute(WeightedGraph<D> g) {
		QuickUnionRank<Vertex<D>> UF = new QuickUnionRank<>();
		Sorting<D> sort = new Sorting<>();
		this.t = new WeightedGraphAL<>();

		for(int i=0; i<g.vertexNum(); i++){
			UF.makeSet(g.vertexes().get(i));
		}
		sort.heapsort(g.edges());
		for(int i=0;i<g.edgeNum();i++){
			QURset singleton1 = new QURset();
			QUnode<Vertex<D>> u = new QUnode<>(g.edges().get(i).source, singleton1);
			QURset singleton2 = new QURset();
			QUnode<Vertex<D>> v = new QUnode<>(g.edges().get(i).dest, singleton2);
			QURset Tu = (QURset) UF.find(u);
			QURset Tv = (QURset) UF.find(v);
			if(!Tu.equals(Tv)){
				this.t.addEdge(g.edges().get(i));
				this.weight = this.weight + ((WeightedEdge<D>)g.edges().get(i)).weight;
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