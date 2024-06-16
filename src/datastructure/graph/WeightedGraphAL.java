package datastructure.graph;

import java.util.LinkedList;

/**
 * Implementation of weighted graphs using adjacent lists
 * @param <D> type of the data object in the graph vertexes
 */	
public class WeightedGraphAL<D>	extends GraphAL<D> implements WeightedGraph<D> {

	/**
   	 * Constructs a weighted graph with implementation using adjacent lists
	 */		
	public WeightedGraphAL(){
		super();
	}

	public void addEdge(Edge<D> e) {
		LinkedList<Edge<D>> l = vertexes.get(((VertexAL<D>)e.source).index).adjac;
		l.add(e);
		LinkedList<Edge<D>> r = vertexes.get(((VertexAL<D>)e.dest).index).adjac;
		WeightedEdge<D> tmp = new WeightedEdge<D>(e.dest, e.source, ((WeightedEdge<D>)e).weight);
		r.add(tmp);
		this.m = this.m + 2;
	}
}
