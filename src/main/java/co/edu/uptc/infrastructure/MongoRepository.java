package co.edu.uptc.infrastructure;

import co.edu.uptc.model.AggregateRoot;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

/**
 * Class that represents the repository for the mongo database.
 */
public abstract class MongoRepository<T extends AggregateRoot> {
  private static final Logger logger = LogManager.getLogger(MongoRepository.class);
  private MongoClient client;
  private String database;

  /**
   * Constructor of the class.
   *
   * @param client The mongo client.
   * @throws FileNotFoundException The exception thrown when the file is not found.
   * @throws IOException The exception thrown when an I/O error occurs.
   */
  public MongoRepository(MongoClient client) {
    Properties appProps = loadProperties();
    this.database = appProps.getProperty("mongodb.database");
    this.client = client;
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
      MongoRepository.logger.error("The file was not found.", e);
      return new Properties();
    } catch (IOException e) {
      MongoRepository.logger.error("An I/O error occurred.", e);
      return new Properties();
    }
  }

  /**
   * Gets the name of the collection to use.
   *
   * @return The name of the collection
   */
  protected abstract String collectionName();

  /**
   * Gets the mongo client.
   *
   * @return The mongo client.
   */
  protected MongoClient client() {
    return this.client;
  }

  /**
   * Gets the collection to use.
   *
   * @return The collection.
   */
  protected MongoCollection<Document> collection() {
    return this.client().getDatabase(this.database).getCollection(this.collectionName());
  }

  /**
   * This method persist data from an aggregate root.
   *
   * @param id The id of the aggregate root.
   * @param aggregateRoot The aggregate root.
   */
  protected Document persist(String id, T aggregateRoot) {
    MongoCollection<Document> collection = this.collection();
    Document queryDocument = new Document("_id", id);
    Document documentToSave = new Document("$set", aggregateRoot.toDocument());
    return collection.findOneAndUpdate(queryDocument, documentToSave,
        new FindOneAndUpdateOptions().upsert(true));
  }

  /**
   * This method search an aggregate root by id.
   *
   * @param id The identifier of the aggregate root.
   * @return The aggregate root found.
   */
  protected Document searchById(String id) {
    MongoCollection<Document> collection = this.collection();
    Document queryDocument = new Document("_id", id);
    Document document = collection.find(queryDocument).first();
    return document;
  }

  /**
   * This method delete an aggregate root by id.
   *
   * @param id The identifier of the aggregate root.
   */
  protected Document remove(String id) {
    MongoCollection<Document> collection = this.collection();
    Document queryDocument = new Document("_id", id);
    return collection.findOneAndDelete(queryDocument);
  }

  /**
   * This method search all aggregate roots.
   *
   * @return The list of aggregate roots found.
   */
  protected FindIterable<Document> searchAll() {
    MongoCollection<Document> collection = this.collection();
    return collection.find();
  }
}
