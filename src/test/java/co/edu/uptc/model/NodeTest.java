package co.edu.uptc.model;

import static org.junit.Assert.assertEquals;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.bson.Document;
import org.junit.Test;

/**
 * This class represents a node in the digraph.
 */
public class NodeTest {
  private String geojson = """
      {
        "type": "Feature",
        "id": 19.0,
        "properties": {
          "osmid": 1016183119.0,
          "y": 5.7142417,
          "x": -72.9368016,
          "street_count": 4.0,
          "highway": null
        },
        "geometry": { "type": "Point", "coordinates": [ -72.9368016, 5.7142417 ] }
      }
      """;

  @Test
  public void testFromDocument() {
    Node node = Node.fromDocument(Document.parse(this.geojson));
    assertEquals("Feature", node.getType().toString());
  }

  @Test
  public void testToDocument() {
    Node node = new Node(19.0, new PropertiesNode(1016183119.0, 5.7142417, -72.9368016, 4.0, null),
        new Point(new Position(-72.9368016, 5.7142417)));
    Document documentExpected = Document.parse(geojson);
    assertEquals(documentExpected, node.toDocument());
  }
}
