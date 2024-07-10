package co.edu.uptc.model;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import java.util.List;
import org.bson.Document;

/**
 * This class represents a node in the digraph.
 */
public class Node extends AggregateRoot {
  private FeatureType type = FeatureType.FEATURE;
  private Double identifier;
  private Properties properties;
  private Point geometry;

  /**
   * Constructor of the Node class.
   *
   * @param identifier The identifier of the node.
   * @param properties The properties of the node.
   * @param geometry The geometry of the node.
   */
  public Node(Double identifier, Properties properties, Point geometry) {
    this.identifier = identifier;
    this.properties = properties;
    this.geometry = geometry;
  }

  public FeatureType getType() {
    return type;
  }

  public void setType(FeatureType type) {
    this.type = type;
  }

  public Double getIdentifier() {
    return identifier;
  }

  public void setIdentifier(Double identifier) {
    this.identifier = identifier;
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public Point getGeometry() {
    return geometry;
  }

  public void setGeometry(Point geometry) {
    this.geometry = geometry;
  }

  @Override
  public Document toDocument() {
    Document document = new Document();
    Document geometryDocument = new Document();
    geometryDocument.append("type", "Point");
    geometryDocument.append("coordinates", this.geometry.getCoordinates().getValues());

    document.append("type", this.type.toString());
    document.append("id", this.identifier);
    document.append("properties", this.properties.toDocument());
    document.append("geometry", geometryDocument);
    return document;
  }

  /**
   * This method creates a node from a document.
   *
   * @param document The document to convert.
   * @return The node created from the document.
   */
  public static Node fromDocument(Document document) {
    Double identifier = document.getDouble("id");
    Properties properties = Properties.fromDocument((Document) document.get("properties"));
    List<Double> coordinates =
        ((Document) document.get("geometry")).getList("coordinates", Double.class);
    Point geometry = new Point(new Position(coordinates.get(0), coordinates.get(1)));
    return new Node(identifier, properties, geometry);
  }
}
