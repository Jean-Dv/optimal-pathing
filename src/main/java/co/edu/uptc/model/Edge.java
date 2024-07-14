package co.edu.uptc.model;


import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.Position;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * Class that represents the edge in the digraph.
 */
public class Edge extends AggregateRoot {

  private FeatureType type = FeatureType.FEATURE;
  private Double identifier;
  private PropertiesEdge properties;
  private LineString geometry;

  /**
   * Constructor of the edge class.
   *
   * @param identifier The identifier of the edge.
   * @param properties The properties of the edge.
   * @param geometry The geometry of the edge.
   */
  public Edge(Double identifier, PropertiesEdge properties, LineString geometry) {
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

  public PropertiesEdge getProperties() {
    return properties;
  }

  public void setProperties(PropertiesEdge properties) {
    this.properties = properties;
  }

  public LineString getGeometry() {
    return geometry;
  }

  public void setGeometry(LineString geometry) {
    this.geometry = geometry;
  }

  @Override
  public Document toDocument() {
    Document document = new Document();
    Document geometryDocument = new Document();
    geometryDocument.append("type", "LineString");
    geometryDocument.append("coordinates",
        this.geometry.getCoordinates().stream().map(coordinate -> coordinate.getValues()).toList());
    document.append("type", this.type.toString());
    document.append("id", this.identifier);
    document.append("properties", this.properties.toDocument());
    document.append("geometry", geometryDocument);
    return document;
  }

  /**
   * This method creates a edge object from a Document object.
   *
   * @param document The document to convert.
   * @return The node created from the document.
   */
  public static Edge fromDocument(Document document) {
    Double identifier = document.getDouble("id");
    PropertiesEdge properties = PropertiesEdge.fromDocument((Document) document.get("properties"));

    List<List<Double>> coordinates = getCoordinatesList(
        ((Document) document.get("geometry")).getList("coordinates", List.class));

    LineString geometry = new LineString(convertToPositionList(coordinates));

    return new Edge(identifier, properties, geometry);
  }

  /**
   * Converts a list of raw coordinates into a list of lists of doubles.
   *
   * @param rawCoordinates a list of raw coordinate data, where each element may be a list of
   *        numbers
   * @return a list of lists of doubles representing valid coordinate pairs found in rawCoordinates
   */
  public static List<List<Double>> getCoordinatesList(List<?> rawCoordinates) {

    List<List<Double>> coordinates = new ArrayList<>();

    for (Object rawList : rawCoordinates) {
      if (rawList instanceof List<?>) {
        List<Double> coordinatePair = new ArrayList<>();
        for (Object obj : (List<?>) rawList) {
          coordinatePair.add((double) obj);
        }
        coordinates.add(coordinatePair);
      }
    }
    return coordinates;
  }

  /**
   * Converts a list of coordinate pairs represented as lists of doubles into a list of Position
   * objects. Each coordinate pair must contain exactly two elements: latitude and longitude.
   *
   * @param coordinates a list of lists of doubles representing coordinate pairs
   * @return a list of Position objects representing the converted coordinates
   */
  public static List<Position> convertToPositionList(List<List<Double>> coordinates) {
    List<Position> positionList = new ArrayList<>();
    for (List<Double> coordinatePair : coordinates) {
      if (coordinatePair.size() == 2) {
        double latitude = coordinatePair.get(0);
        double longitude = coordinatePair.get(1);
        positionList.add(new Position(latitude, longitude));
      }
    }
    return positionList;
  }

}
