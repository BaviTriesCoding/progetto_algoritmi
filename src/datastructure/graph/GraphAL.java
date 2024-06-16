package datastructure.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Implementation of graphs using adjacent lists
 * @param <D> type of the data object in the graph vertexes
 */	
public class GraphAL<D> implements Graph<D> {

	/** number of vertexes */
	protected int n;
	
	/** number of edges */
	protected int m;
	
	/** list of vertexes */
	protected ArrayList<VertexAL<D>> vertexes;

	/**
   	 * Constructs a graph with implementation using adjacent lists
	 */		
	public GraphAL(){
		n = 0;
		m = 0;
		vertexes = new ArrayList<VertexAL<D>>();
	}

	public int vertexNum() {
		return this.n;
	}

	public int edgeNum() {
		return this.m;
	}

	public ArrayList<Vertex<D>> vertexes() {
		ArrayList<Vertex<D>> list = new ArrayList<>();
		for(int i=0; i<this.n; i++){
			list.add(this.vertexes.get(i));
		}
		return list;
	}
	
	public ArrayList<Edge<D>> edges() {
		ArrayList<Edge<D>> list = new ArrayList<>();
		for(int i=0; i<this.n; i++){
            list.addAll(vertexes.get(i).adjac);
		}
		return list;
	}

	public int outDegree(Vertex<D> v) {
		int index = this.vertexes.indexOf(v);
		return vertexes.get(index).adjac.size();
	}
	
	public ArrayList<Edge<D>> outEdges(Vertex<D> v) {
		int index = this.vertexes.indexOf(v);
		if(index!=-1){
			return new ArrayList<>(this.vertexes.get(index).adjac);
		}else{
			return null;
		}
	}
	
	public Edge<D> areAdjacent(Vertex<D> x, Vertex<D> y) {
		int index = this.vertexes.indexOf(x);
		if(index!=-1){
			int i = this.vertexes.get(index).adjac.indexOf(y);
			if (i != -1) {
				return this.vertexes.get(index).adjac.get(i);
			} else {
				return null;
			}
		}else{
			return null;
		}
	}

	public Vertex<D> addVertex(D data) {
		VertexAL<D> v = new VertexAL<>(data, this.n);
		this.vertexes.add(v);
		this.n = this.n + 1;
		return v;
	}

	public void addEdge(Edge<D> e) {
		int index = this.vertexes.indexOf(e.source);
		if(index!=-1){
			this.vertexes.get(index).adjac.add(e);
		}
	}
	
	public void removeVertex(Vertex<D> v) {
		VertexAL<D> vAL = (VertexAL<D>)v;
		m = m - vAL.adjac.size();
		n = n - 1;
		if (vAL.index == n) {
			vertexes.remove(n);
		} else {
			VertexAL<D> vert = vertexes.remove(n);
			vert.index = vAL.index;
			vertexes.set(vert.index,vert);
		}
		Edge<D> e;
		for (int i=0; i<vertexes.size(); i++) {
			Iterator<Edge<D>> it = (vertexes.get(i)).adjac.iterator();
			while (it.hasNext()) { 
				e = it.next();
				if (e.dest == v) {
					it.remove();
					m = m - 1;
				}
			}					
		}
	}

	public void removeEdge(Edge<D> e) {
		int index = this.vertexes.indexOf(e.source);
		if(index!=-1){
			if(this.vertexes.get(index).adjac.remove(e)){
				this.m = this.m - 1;
			}
		}
	}

	/**
	 *  Returns the index of a vertex
     *  @param v vertex
	 *  @return the index of the vertex v
	 */	
	protected int index(VertexAL<D> v) {
		return v.index;
	}

	/**
	 *  Returns the vertex with a given index
     *  @param i the index
	 *  @return the vertex v with index i
	 */		
 	protected VertexAL<D> vertex(int i) {
		if (i < 0 || i >= n) return null;
		return vertexes.get(i);
 	}

}
