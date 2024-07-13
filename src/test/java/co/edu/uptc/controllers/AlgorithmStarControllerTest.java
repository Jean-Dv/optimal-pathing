package co.edu.uptc.controllers;

import static org.junit.Assert.assertEquals;

import co.edu.uptc.controller.AlgorithmStar;
import co.edu.uptc.model.Digraph;
import co.edu.uptc.model.Edge;
import co.edu.uptc.model.Node;
import co.edu.uptc.model.Path;
import co.edu.uptc.model.PropertiesEdge;
import co.edu.uptc.model.PropertiesNode;
import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for AlgorithmStar class using JUnit.
 */
public class AlgorithmStarControllerTest {

  private List<Node> nodes = new ArrayList<>();
  private List<Edge> edges = new ArrayList<>();

  /**
   * Setup method.
   */
  @Before
  public void setUp() {
    Node node1 = new Node(1.0, new PropertiesNode(1.0, 5.6652957, -72.9289882, 3.0, null),
        new Point(new Position(-72.9289882, 5.6652957)));

    Node node2 = new Node(2.0, new PropertiesNode(2.0, 5.6654118, -72.9285979, 3.0, null),
        new Point(new Position(-72.9285979, 5.6654118)));

    Node node3 = new Node(3.0, new PropertiesNode(3.0, 5.6654145, -72.9285403, 3.0, null),
        new Point(new Position(-72.9285403, 5.6654145)));

    Node node4 = new Node(4.0, new PropertiesNode(4.0, 5.6323957, -72.9232882, 3.0, null),
        new Point(new Position(-72.9232882, 5.6323957)));

    Node node5 = new Node(5.0, new PropertiesNode(5.0, 5.63232357, -72.9212882, 3.0, null),
        new Point(new Position(-72.9212882, 5.63232357)));

    nodes.add(node1);
    nodes.add(node2);
    nodes.add(node3);
    nodes.add(node4);
    nodes.add(node5);

    PropertiesEdge propertiesEdge1 = new PropertiesEdge(1.0, 2.0, 100.0, null, null, false, null,
        50.0, 5.0, 0.0, null, null, null, null, null);

    List<Position> positions1 = new ArrayList<>();
    positions1.add(new Position(-72.9289882, 5.6652957));
    positions1.add(new Position(-72.9285979, 5.6654118));
    LineString geometry1 = new LineString(positions1);

    Edge edge1 = new Edge(1.0, propertiesEdge1, geometry1);
    edges.add(edge1);
    PropertiesEdge propertiesEdge2 = new PropertiesEdge(2.0, 3.0, 150.0, null, null, false, null,
        30.0, 50.0, 0.0, null, null, null, null, null);

    List<Position> positions2 = new ArrayList<>();
    positions2.add(new Position(-72.9285979, 5.6654118));
    positions2.add(new Position(-72.9285403, 5.6654145));
    LineString geometry2 = new LineString(positions2);

    Edge edge2 = new Edge(2.0, propertiesEdge2, geometry2);
    edges.add(edge2);
    PropertiesEdge propertiesEdge3 = new PropertiesEdge(2.0, 4.0, 200.0, null, null, false, null,
        30.0, 40.0, 0.0, null, null, null, null, null);

    List<Position> positions3 = new ArrayList<>();
    positions3.add(new Position(-72.9285979, 5.6654118));
    positions3.add(new Position(-72.9289882, 5.6652957));
    LineString geometry3 = new LineString(positions3);

    Edge edge3 = new Edge(3.0, propertiesEdge3, geometry3);
    edges.add(edge3);

    PropertiesEdge propertiesEdge4 = new PropertiesEdge(3.0, 1.0, 250.0, null, null, false, null,
        9505.0, 5.0, 0.0, null, null, null, null, null);

    List<Position> positions4 = new ArrayList<>();
    positions4.add(new Position(-72.9285403, 5.6654145));
    positions4.add(new Position(-72.9289882, 5.6652957));
    LineString geometry4 = new LineString(positions4);

    Edge edge4 = new Edge(4.0, propertiesEdge4, geometry4);
    edges.add(edge4);

    PropertiesEdge propertiesEdge5 = new PropertiesEdge(2.0, 5.0, 250.0, null, null, false, null,
        10.0, 89.0, 0.0, null, null, null, null, null);

    List<Position> positions5 = new ArrayList<>();
    positions5.add(new Position(-72.9285403, 5.6654145));
    positions5.add(new Position(-72.9289882, 5.6652957));
    LineString geometry5 = new LineString(positions5);

    Edge edge5 = new Edge(5.0, propertiesEdge5, geometry5);
    edges.add(edge5);

    PropertiesEdge propertiesEdge6 = new PropertiesEdge(3.0, 5.0, 250.0, null, null, false, null,
        45.0, 2.0, 0.0, null, null, null, null, null);

    List<Position> positions6 = new ArrayList<>();
    positions6.add(new Position(-72.9285403, 5.6654145));
    positions6.add(new Position(-72.9289882, 5.6652957));
    LineString geometry6 = new LineString(positions6);

    Edge edge6 = new Edge(6.0, propertiesEdge6, geometry6);
    edges.add(edge6);
  }

  @Test
  public void testStarSearch() {
    Digraph digraph = new Digraph(nodes, edges);

    AlgorithmStar algorithmStar = new AlgorithmStar(digraph.getAdjacencyList(), digraph.getNodes());

    System.out.println(algorithmStar.getNodes().size());
    List<Path> paths =
        algorithmStar.findShortestPaths(digraph.getNodes().get(0), digraph.getNodes().get(4), 2);



    assertEquals(2, paths.get(0).getEdges().size());
    assertEquals(2, paths.size());
    String message = "[Start: 1.0, Finish: 5.0, Cost: 10.112359550561798, "
        + "Estimated Cost: 10.112359550561798, Path: 1.0 -> 2.0 2.0 -> 5.0 "
        + ", Start: 1.0, Finish: 5.0, Cost: 33.1, Estimated Cost:"
        + " 33.1, Path: 1.0 -> 2.0 2.0 -> 3.0 3.0 -> 5.0 ]";
    assertEquals(message, paths.toString());


  }

  @Test
  public void testStarSearchMongo() {
    AlgorithmStar algorithmStar = new AlgorithmStar();

    System.out.println(algorithmStar.getNodes().size());
    List<Path> paths = algorithmStar.findShortestPaths(algorithmStar.getNodes().get(1),
        algorithmStar.getNodes().get(2), 2);

    assertEquals(2, paths.size());
    assertEquals(algorithmStar.getNodes().get(2), paths.get(0).getFinish());

  }
}
