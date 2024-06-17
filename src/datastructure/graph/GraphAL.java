package datastructure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Implementation of graphs using adjacent lists
 *
 * @param <D> type of the data object in the graph vertexes
 */
public class GraphAL<D> implements Graph<D> {

	/** number of vertexes */
	protected int n; // stores the number of vertices in the graph

	/** number of edges */
	protected int m; // stores the number of edges in the graph

	/** list of vertexes */
	protected ArrayList<VertexAL<D>> vertexes; // stores the list of vertices in the graph

	/**
	 * Constructs a graph with implementation using adjacent lists
	 */
	public GraphAL() {
		n = 0; // initialize number of vertices to 0
		m = 0; // initialize number of edges to 0
		vertexes = new ArrayList<VertexAL<D>>(); // create an empty ArrayList to store vertices
	}

	public int vertexNum() {
		return this.n; // return the number of vertices
	}

	public int edgeNum() {
		return this.m; // return the number of edges
	}

	public ArrayList<Vertex<D>> vertexes() {
		ArrayList<Vertex<D>> list = new ArrayList<>();
		for (int i = 0; i < this.n; i++) {
			list.add(this.vertexes.get(i)); // add each vertex from vertexes list to a new list
		}
		return list;
	}

	public ArrayList<Edge<D>> edges() {
		ArrayList<Edge<D>> list = new ArrayList<>();
		for (int i = 0; i < this.n; i++) {
			list.addAll(vertexes.get(i).adjac); // add all adjacent edges of each vertex to the list
		}
		return list;
	}

	public int outDegree(Vertex<D> v) {
		return ((VertexAL<D>) v).adjac.size(); // return the size of the adjacency list (out-degree)
	}

	public ArrayList<Edge<D>> outEdges(Vertex<D> v) {
		int index = this.vertexes.indexOf(v);
		if (index != -1) {
			return new ArrayList<>(this.vertexes.get(index).adjac); // return a copy of the adjacency list
		} else {
			return null; // vertex not found
		}
	}

	public Edge<D> areAdjacent(Vertex<D> x, Vertex<D> y) {
		int index = this.vertexes.indexOf(x);
		if (index != -1) {
			int i = this.vertexes.get(index).adjac.indexOf(y);
			if (i != -1) {
				return this.vertexes.get(index).adjac.get(i); // return the edge if adjacent
			} else {
				return null; // vertices not adjacent
			}
		} else {
			return null; // vertex x not found
		}
	}

	public Vertex<D> addVertex(D data) {
		VertexAL<D> v = new VertexAL<>(data, this.n); // create a new vertex with data and index
		this.vertexes.add(v); // add the vertex to the vertexes list
		this.n = this.n + 1; // increment the number of vertices
		return v;
	}

	public void addEdge(Edge<D> e) {
		LinkedList<Edge<D>> l = vertexes.get(((VertexAL<D>) e.source).index).adjac; // get adjacency list of source vertex
		l.add(e); // add the edge to the source vertex's adjacency list
		LinkedList<Edge<D>> r = vertexes.get(((VertexAL<D>) e.dest).index).adjac; // get adjacency list of destination vertex
		Edge<D> tmp = new Edge<D>(e.dest, e.source); // create a reversed edge for undirected graph
		r.add(tmp); // add the reversed edge to the destination vertex's adjacency list
		this.m = this.m + 2; // increment the number of edges (considering undirected edges)
	}
	public void removeVertex(Vertex<D> v) {
		VertexAL<D> vAL = (VertexAL<D>)v;
		m = m - vAL.adjac.size(); // decrement number of edges by size of adjacency list (outgoing edges)
		n = n - 1; // decrement number of vertices
		if (vAL.index == n) {
			vertexes.remove(n); // remove vertex if it's the last one
		} else {
			VertexAL<D> vert = vertexes.remove(n); // remove the last vertex and store it
			vert.index = vAL.index; // update the index of the removed vertex
			vertexes.set(vert.index,vert); // set the vertex at the old index to the removed vertex (maintain order)
		}
		Edge<D> e;
		for (int i=0; i<vertexes.size(); i++) {
			Iterator<Edge<D>> it = (vertexes.get(i)).adjac.iterator();
			while (it.hasNext()) {
				e = it.next();
				if (e.dest == v) {
					it.remove(); // remove edges pointing to the removed vertex
					m = m - 1; // decrement number of edges
				}
			}
		}
	}

	public void removeEdge(Edge<D> e) {
		int index = this.vertexes.indexOf((VertexAL<D>)e.source);
		if(index!=-1){
			if(this.vertexes.get(index).adjac.remove(e)){ // remove edge from source's adjacency list
				this.m = this.m - 1; // decrement number of edges
			}
		}
		index = this.vertexes.indexOf((VertexAL<D>)e.dest);
		if(index!=-1){
			if(this.vertexes.get(index).adjac.remove(e)){ // remove reversed edge from destination's adjacency list (undirected graph)
				this.m = this.m - 1; // decrement number of edges
			}
		}
	}

	/**
	 *  Returns the index of a vertex
	 *  @param v vertex
	 *  @return the index of the vertex v
	 */
	protected int index(VertexAL<D> v) {
		return v.index; // return the vertex's index
	}

	/**
	 *  Returns the vertex with a given index
	 *  @param i the index
	 *  @return the vertex v with index i
	 */
	protected VertexAL<D> vertex(int i) {
		if (i < 0 || i >= n) return null; // check for valid index
		return vertexes.get(i); // return the vertex at the index
	}

}

