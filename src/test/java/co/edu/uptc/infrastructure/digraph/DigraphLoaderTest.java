package co.edu.uptc.infrastructure.digraph;

import static org.junit.Assert.assertTrue;

import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.model.Digraph;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.geojson.Position;
import org.junit.Test;

/**
 * This class represents a node in the digraph.
 */
public class DigraphLoaderTest {
  @Test
  public void testLoadDigraph() {
    MongoClient mongoClient =
        MongoClientFactory.createClient("testingDigraphLoader", "mongodb://localhost:27017");
    DigraphLoader digraphLoader = new DigraphLoader(mongoClient);
    digraphLoader.setDatabase("logistics");

    Digraph digraph = digraphLoader.loadDigraph();

    assertTrue(digraph.findNode(1016183119.0).getGeometry().getPosition()
        .equals(new Position(-72.9368016, 5.7142417)));
  }
}
