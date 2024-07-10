package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents the digraph.
 */
public class Digraph {
  private List<Node> nodes;
  private Map<Node, List<Edge>> adjacencyList;
  private Map<Node, Integer> indegree;

  /**
   * Initializes an empty digraph.
   */
  public Digraph() {
    this.nodes = new ArrayList<>();
    this.adjacencyList = new HashMap<>();
    this.indegree = new HashMap<>();
  }

  /**
   * Initializes a digraph with given lists of nodes and edges.
   *
   * @param nodes the list of nodes
   * @param edges the list of edges
   */
  public Digraph(List<Node> nodes, List<Edge> edges) {
    this();
    for (Node node : nodes) {
      addNode(node);
    }
    for (Edge edge : edges) {
      addEdge(edge);
    }
  }

  /**
   * Adds a node to the digraph.
   *
   * @param node the node to add
   */
  public void addNode(Node node) {
    nodes.add(node);
    adjacencyList.put(node, new ArrayList<>());
    indegree.put(node, 0);
  }

  /**
   * Adds the directed edge from startNode to endNode to this digraph.
   *
   * @param edge the edge to add
   */
  public void addEdge(Edge edge) {
    Node startNode = findNode(edge.getProperties().getStartNode());
    Node endNode = findNode(edge.getProperties().getEndNode());

    if (startNode == null || endNode == null) {
      throw new IllegalArgumentException("One or both nodes not found in the graph");
    }

    adjacencyList.get(startNode).add(edge);
    indegree.put(endNode, indegree.get(endNode) + 1);
  }

  /**
   * Find the node by identifier.
   *
   * @param id the identifier of the node to find
   * @return the node if found, otherwise null
   */
  public Node findNode(Double id) {
    for (Node node : nodes) {
      if (node.getProperties().getOsmid().equals(id)) {
        return node;
      }
    }
    return null;
  }

  /**
   * Returns a string representation of the digraph.
   *
   * @return string representation of the digraph
   */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Node node : nodes) {
      s.append(node.getIdentifier()).append(": ");
      List<Edge> edges = adjacencyList.get(node);
      if (edges != null) {
        for (Edge edge : edges) {
          Node endNode = findNode(edge.getProperties().getEndNode());
          s.append(endNode.getIdentifier()).append(" ");
        }
      }
      s.append("\n");
    }
    return s.toString();
  }

  public List<Node> getNodes() {
    return nodes;
  }

  public void setNodes(List<Node> nodes) {
    this.nodes = nodes;
  }

  public Map<Node, List<Edge>> getAdjacencyList() {
    return adjacencyList;
  }

  public void setAdjacencyList(Map<Node, List<Edge>> adjacencyList) {
    this.adjacencyList = adjacencyList;
  }

  public Map<Node, Integer> getIndegree() {
    return indegree;
  }

  public void setIndegree(Map<Node, Integer> indegree) {
    this.indegree = indegree;
  }
}
