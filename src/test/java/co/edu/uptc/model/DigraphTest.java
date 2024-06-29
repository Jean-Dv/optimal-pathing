package co.edu.uptc.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * This class represents the digraph test.
 */
public class DigraphTest {


  private List<Node> nodes = new ArrayList<>();
  private List<Edge> edges = new ArrayList<>();

  /**
   * Setup method.
   */
  @Before
  public void setUp() {

    Node node = new Node(1, new Properties(1, 5.7142417, -72.9368016, 4, null),
        new Point(new Position(-72.9368016, 5.7142417)));

    Node node2 = new Node(2, new Properties(2, 5.7142417, -72.9368016, 4, null),
        new Point(new Position(-72.9368016, 5.7142417)));

    Node node3 = new Node(3, new Properties(3, 5.7142417, -72.9368016, 4, null),
        new Point(new Position(-72.9368016, 5.7142417)));

    Node node4 = new Node(4, new Properties(4, 5.7142417, -72.9368016, 4, null),
        new Point(new Position(-72.9368016, 5.7142417)));


    nodes.add(node);
    nodes.add(node2);
    nodes.add(node3);
    nodes.add(node4);

    PropertiesEdge propertiesEdge = new PropertiesEdge(1, 2, 0, null, "unclassified", false, null,
        0, 0, 0, null, null, null, null, null);

    Position position1 = new Position(-72.9289882, 5.6652957);
    Position position2 = new Position(-72.9285979, 5.6654118);
    Position position3 = new Position(-72.9285403, 5.6654145);

    List<Position> positions = new ArrayList<>();
    positions.add(position3);
    positions.add(position2);
    positions.add(position1);
    LineString geometry = new LineString(positions);

    Edge edge = new Edge(12, propertiesEdge, geometry);

    PropertiesEdge propertiesEdge2 = new PropertiesEdge(2, 3, 0, null, "unclassified", false, null,
        0, 0, 0, null, null, null, null, null);

    Edge edge2 = new Edge(13, propertiesEdge2, geometry);

    PropertiesEdge propertiesEdge3 = new PropertiesEdge(2, 4, 0, null, "unclassified", false, null,
        0, 0, 0, null, null, null, null, null);

    Edge edge3 = new Edge(13, propertiesEdge3, geometry);

    PropertiesEdge propertiesEdge4 = new PropertiesEdge(3, 1, 0, null, "unclassified", false, null,
        0, 0, 0, null, null, null, null, null);

    Edge edge4 = new Edge(13, propertiesEdge4, geometry);


    edges.add(edge);
    edges.add(edge2);
    edges.add(edge3);
    edges.add(edge4);



  }

  @Test
  public void addNode() {

    Node node = new Node(1, new Properties(316951892, 5.7142417, -72.9368016, 4, null),
        new Point(new Position(-72.9368016, 5.7142417)));

    Node node2 = new Node(1, new Properties(316951892, 5.7142417, -72.9368016, 4, null),
        new Point(new Position(-72.9368016, 5.7142417)));

    Digraph digraph = new Digraph();
    digraph.addNode(node);
    assertTrue("The node should be in the node list", digraph.getNodes().contains(node));
    assertFalse("The node should be in the node list", digraph.getNodes().contains(node2));

  }

  @Test
  public void addEdge() {
    Digraph digraph = new Digraph(nodes, edges);
    List<Edge> edges = digraph.getAdjacencyList().get(nodes.get(0));

    boolean edgeFound = false;
    boolean edgeFound2 = false;
    for (Edge edge : edges) {
      if (edge.getIdentifier() == 12) {
        edgeFound = true;
        break;
      }
    }
    for (Edge edge : edges) {
      if (edge.getIdentifier() == 15) {
        edgeFound2 = true;
        break;
      }
    }

    assertTrue(edgeFound);
    assertFalse(edgeFound2);

    // System.out.println(digraph.toString());

  }
}
