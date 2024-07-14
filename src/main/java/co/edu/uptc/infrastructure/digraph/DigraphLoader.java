package co.edu.uptc.infrastructure.digraph;


import co.edu.uptc.infrastructure.MongoRepository;
import co.edu.uptc.model.Digraph;
import co.edu.uptc.model.Edge;
import co.edu.uptc.model.Node;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;


/**
 * Class that represents the loader of the digraph in MongoDB.
 * 
 */
public class DigraphLoader {
  private static final Logger logger = LogManager.getLogger(MongoRepository.class);
  private MongoClient client;
  private String database;

  /**
   * Constructor of the class.
   *
   * @param client The mongo client.
   */
  public DigraphLoader(MongoClient client) {
    Properties props = this.loadProperties();
    this.database = props.getProperty("mongodb.database", "logistics");
    this.client = client;
  }

  /**
   * Load the digraph from the database.
   *
   * @return The digraph.
   */
  public Digraph loadDigraph() {
    List<Node> nodes = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();

    this.getCollectionNodes().find().map(Node::fromDocument).into(nodes);
    this.getCollectionEdges().find().map(Edge::fromDocument).into(edges);
    return new Digraph(nodes, edges);
  }

  /**
   * Loads the properties from the application.properties file.
   *
   * @return The properties.
   */
  private Properties loadProperties() {
    try {
      String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      String appConfigPath = rootPath + "application.properties";
      Properties appProps = new Properties();
      appProps.load(new FileInputStream(appConfigPath));
      return appProps;
    } catch (FileNotFoundException e) {
      DigraphLoader.logger.error("The file was not found.", e);
      return new Properties();
    } catch (IOException e) {
      DigraphLoader.logger.error("An I/O error occurred.", e);
      return new Properties();
    }
  }

  /**
   * Get the collection of nodes.
   *
   * @return The collection of nodes in documents.
   */
  private MongoCollection<Document> getCollectionNodes() {
    return this.client().getDatabase(this.database).getCollection("nodes");
  }

  /**
   * Get the collection of edges.
   *
   * @return The collection of edges in documents.
   */
  private MongoCollection<Document> getCollectionEdges() {
    return this.client().getDatabase(this.database).getCollection("edges");
  }

  /**
   * Gets the mongo client.
   *
   * @return The mongo client.
   */
  private MongoClient client() {
    return this.client;
  }

  /**
   * Sets the database.
   *
   * @param database The database.
   */
  public void setDatabase(String database) {
    this.database = database;
  }
}
