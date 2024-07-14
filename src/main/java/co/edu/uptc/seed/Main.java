package co.edu.uptc.seed;

import co.edu.uptc.infrastructure.MongoClientFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

/**
 * Class that represents the main class of the application.
 */
public class Main {
  private static final Logger logger = LogManager.getLogger(Main.class);
  private static final Type DOCUMENT_TYPE = new TypeToken<List<Document>>() {}.getType();
  private MongoClient mongoClient =
      MongoClientFactory.createClient("seed", "mongodb://localhost:27017");
  private MongoCollection<Document> nodesCollection;
  private MongoCollection<Document> edgesCollection;

  /**
   * Main method of the application.
   *
   * @param args Arguments passed to the application.
   */
  public static void main(String[] args) {
    new Main().run();
  }

  private void run() {
    Properties appProps = loadProperties();
    this.nodesCollection = mongoClient
        .getDatabase(appProps.getProperty("mongodb.database", "logistics")).getCollection("nodes");
    this.edgesCollection = mongoClient
        .getDatabase(appProps.getProperty("mongodb.database", "logistics")).getCollection("edges");

    List<Document> nodes = this.loadJson("nodes-compact.geojson");
    this.nodesCollection.deleteMany(new Document());
    this.nodesCollection.insertMany(nodes);
    this.nodesCollection.createIndex(new Document("geometry", "2dsphere"));

    List<Document> edges = this.loadJson("edges-compact.geojson");
    this.edgesCollection.deleteMany(new Document());
    this.edgesCollection.insertMany(edges);
    this.edgesCollection.createIndex(new Document("geometry", "2dsphere"));
  }

  private Properties loadProperties() {
    try {
      String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      String appConfigPath = rootPath + "application.properties";
      Properties appProps = new Properties();
      appProps.load(new FileInputStream(appConfigPath));
      return appProps;
    } catch (FileNotFoundException e) {
      Main.logger.error("The file was not found.", e);
      return new Properties();
    } catch (IOException e) {
      Main.logger.error("An I/O error occurred.", e);
      return new Properties();
    }
  }

  private List<Document> loadJson(String fileName) {
    try {
      Gson gson = new Gson();
      String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      JsonReader reader = new JsonReader(new FileReader(rootPath + "seed/" + fileName));
      return gson.fromJson(reader, DOCUMENT_TYPE);
    } catch (FileNotFoundException e) {
      Main.logger.error("The file was not found.", e);
      return null;
    }
  }
}
