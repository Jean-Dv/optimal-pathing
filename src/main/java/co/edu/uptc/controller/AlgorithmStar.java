package co.edu.uptc.controller;

import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.digraph.DigraphLoader;
import co.edu.uptc.model.Edge;
import co.edu.uptc.model.Node;
import co.edu.uptc.model.Path;
import com.mongodb.client.MongoClient;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Represents a controller class for the A* algorithm.
 */
public class AlgorithmStar {

  private MongoClient mongoClient;
  private DigraphLoader digraphLoader;
  private Map<Node, List<Edge>> adjacencyList;
  private List<Node> nodes;

  /**
   * Constructor for initializing the A* algorithm controller. It sets up MongoDB client, loads a
   * digraph from a specified database, and initializes necessary data structures for pathfinding.
   */
  public AlgorithmStar() {
    mongoClient =
        MongoClientFactory.createClient("testingDigraphLoader", "mongodb://localhost:27017");
    digraphLoader = new DigraphLoader(mongoClient);
    digraphLoader.setDatabase("logistics");

    adjacencyList = digraphLoader.loadDigraph().getAdjacencyList();
    nodes = digraphLoader.loadDigraph().getNodes();
  }



  public AlgorithmStar(Map<Node, List<Edge>> adjacencyList, List<Node> nodes) {
    this.nodes = nodes;
    this.adjacencyList = adjacencyList;
  }



  private double heuristic(Node node, Node finish) {
    return Math.sqrt(Math
        .pow(node.getProperties().getLatitude() - finish.getProperties().getLatitude(), 2)
        + Math.pow(node.getProperties().getLongitude() - finish.getProperties().getLongitude(), 2));
  }

  /**
   * Finds the shortest paths using the A* search algorithm from the start node to the finish node.
   *
   * @param start The starting node of the search.
   * @param finish The destination node where the search terminates.
   * @return A list of shortest paths from start to finish, sorted by their estimated cost.
   */
  public List<Path> findShortestPaths(Node start, Node finish) {
    PriorityQueue<Path> openList =
        new PriorityQueue<>(Comparator.comparingDouble(Path::getEstimatedCost));
    Map<Node, Double> scoreMap = new HashMap<>();
    List<Path> fastestPaths = new ArrayList<>(); // Para almacenar las dos rutas más rápidas

    scoreMap.put(start, 0.0);
    openList.add(new Path(start, start, new ArrayList<>(), 0, heuristic(start, finish)));

    while (!openList.isEmpty()) {
      Path currentPath = openList.poll();
      Node currentNode = currentPath.getFinish();

      if (currentNode.equals(finish)) {
        fastestPaths.add(currentPath);
        if (fastestPaths.size() > 2) {
          fastestPaths.sort(Comparator.comparingDouble(Path::getEstimatedCost));
          fastestPaths.subList(2, fastestPaths.size()).clear();
        }
        continue;
      }

      for (Edge edge : adjacencyList.get(currentNode)) {
        Node neighbor = findNode(edge.getProperties().getEndNode());
        if (neighbor == null) {
          continue;
        }

        double tentativeGscore = scoreMap.getOrDefault(currentNode, Double.POSITIVE_INFINITY)
            + (edge.getProperties().getLength() / edge.getProperties().getMaxspeed());

        if (tentativeGscore < scoreMap.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
          List<Edge> newEdges = new ArrayList<>(currentPath.getEdges());
          newEdges.add(edge);
          scoreMap.put(neighbor, tentativeGscore);
          double finalScore = tentativeGscore + heuristic(neighbor, finish);
          openList.add(new Path(start, neighbor, newEdges, tentativeGscore, finalScore));
        }
      }
    }

    fastestPaths.sort(Comparator.comparingDouble(Path::getEstimatedCost));
    fastestPaths.get(0).setColor("#4D5656");

    for (int i = 1; i < fastestPaths.size(); i++) {
      fastestPaths.get(i).setColor("#909497");
    }
    return fastestPaths;
  }


  /**
   * Finds a node in the list of nodes by its OSM identifier.
   *
   * @param idNode The OSM identifier of the node to find.
   * @return The node found that matches the specified OSM identifier, or null if no node with that
   *         identifier is found.
   */
  public Node findNode(double idNode) {
    for (Node node : nodes) {
      if (node.getProperties().getOsmid().equals(idNode)) {
        return node;
      }
    }
    return null;
  }


}
