package co.edu.uptc.model;

import org.bson.Document;

/**
 * This class represents the properties of a node in the graph.
 */
public class PropertiesNode implements Documentable {
  private Double osmid;
  private double latitude;
  private double longitude;
  private Double streetCount;
  private String highway;

  /**
   * Constructor of the Properties class.
   *
   * @param osmid The OpenStreetMap identifier of the node.
   * @param latitude The latitude of the node.
   * @param longitude The longitude of the node.
   * @param streetCount The number of streets that intersect in the node.
   * @param highway The type of highway that the node represents.
   */
  public PropertiesNode(Double osmid, double latitude, double longitude, Double streetCount,
      String highway) {
    this.osmid = osmid;
    this.latitude = latitude;
    this.longitude = longitude;
    this.streetCount = streetCount;
    this.highway = highway;
  }

  public Double getOsmid() {
    return osmid;
  }

  public void setOsmid(Double osmid) {
    this.osmid = osmid;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public Double getStreetCount() {
    return streetCount;
  }

  public void setStreetCount(Double streetCount) {
    this.streetCount = streetCount;
  }

  public String getHighway() {
    return highway;
  }

  public void setHighway(String highway) {
    this.highway = highway;
  }

  @Override
  public Document toDocument() {
    Document document = new Document();
    document.append("osmid", osmid);
    document.append("y", latitude);
    document.append("x", longitude);
    document.append("street_count", streetCount);
    document.append("highway", highway);
    return document;
  }

  /**
   * This method creates a Properties object from a Document object.
   *
   * @param document The Document object to convert.
   * @return A Properties object with the data of the Document object.
   */
  public static PropertiesNode fromDocument(Document document) {
    Double osmid = document.getDouble("osmid");
    double latitude = document.getDouble("y");
    double longitude = document.getDouble("x");
    Double streetCount = document.getDouble("street_count");
    String highway = document.getString("highway");
    return new PropertiesNode(osmid, latitude, longitude, streetCount, highway);
  }
}
