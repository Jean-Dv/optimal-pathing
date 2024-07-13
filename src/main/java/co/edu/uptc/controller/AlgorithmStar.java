package co.edu.uptc.controller;

import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.digraph.DigraphLoader;
import co.edu.uptc.model.Digraph;
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

    Digraph digraph = digraphLoader.loadDigraph();
    if (digraph != null) {
      adjacencyList = digraph.getAdjacencyList();
      nodes = digraph.getNodes();
    } else {
      adjacencyList = new HashMap<>();
      nodes = new ArrayList<>();
      throw new RuntimeException("Failed to load graph from MongoDB");
    }

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
  public List<Path> findShortestPaths(Node start, Node finish, int k) {
    PriorityQueue<Path> openList =
        new PriorityQueue<>(Comparator.comparingDouble(Path::getEstimatedCost));
    Map<Node, List<Double>> scoreMap = new HashMap<>();
    List<Path> allPaths = new ArrayList<>();

    scoreMap.put(start, new ArrayList<>(List.of(0.0)));
    openList.add(new Path(start, start, new ArrayList<>(), 0, heuristic(start, finish)));

    while (!openList.isEmpty()) {
      Path currentPath = openList.poll();
      Node currentNode = currentPath.getFinish();

      if (currentNode.equals(finish)) {
        allPaths.add(currentPath);
        if (allPaths.size() >= k) {
          break;
        }
        continue;
      }

      for (Edge edge : adjacencyList.get(currentNode)) {
        Node neighbor = findNode(edge.getProperties().getEndNode());
        if (neighbor == null) {
          continue;
        }

        double tentativeGscore = currentPath.getCost()
            + (edge.getProperties().getLength() / edge.getProperties().getMaxspeed());

        List<Edge> newEdges = new ArrayList<>(currentPath.getEdges());
        newEdges.add(edge);
        double finalScore = tentativeGscore + heuristic(neighbor, finish);
        Path newPath = new Path(start, neighbor, newEdges, tentativeGscore, finalScore);

        if (!scoreMap.containsKey(neighbor)) {
          scoreMap.put(neighbor, new ArrayList<>(List.of(tentativeGscore)));
          openList.add(newPath);
        } else {
          List<Double> scores = scoreMap.get(neighbor);

          if (scores.size() < k || tentativeGscore < scores.get(scores.size() - 1)) {
            scores.add(tentativeGscore);
            scores.sort(Double::compareTo);
            if (scores.size() > k) {
              scores.remove(scores.size() - 1);
            }
            openList.add(newPath);
          }
        }
      }
    }
    allPaths.sort(Comparator.comparingDouble(Path::getEstimatedCost));
    allPaths.get(0).setColor("#4D5656");

    for (int i = 1; i < allPaths.size(); i++) {
      allPaths.get(i).setColor("#909497");
    }
    return allPaths;
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

  public List<Node> getNodes() {
    return nodes;
  }


}

