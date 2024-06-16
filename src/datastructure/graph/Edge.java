package datastructure.graph;

/**
 * Generic edge of graphs
 * @param <D> type of the data object in the graph vertexes
 */
public class Edge<D> {

	/** edge source */
	public Vertex<D> source;

	/** edge destination */
	public Vertex<D> dest;

	/**
   	 * Constructs an edge
	 * @param source the source vertex
   	 * @param dest the destination vertex 
	 */		
	public Edge(Vertex<D> source, Vertex<D> dest) {
		this.source = source; this.dest = dest;
	}

	public void setSource(Vertex<D> source){
		this.source = source;
	}

	public void setDest(Vertex<D> dest){
		this.dest = dest;
	}

	public Vertex<D> getSource(){
		return this.source;
	}

	public Vertex<D> getDest(){
		return this.dest;
	}
}
