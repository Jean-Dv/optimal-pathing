package co.edu.uptc.model;

import java.util.List;
import org.bson.Document;


/**
 * Class that represents the Properties.
 */
public class PropertiesEdge implements Documentable {
  private int startNode;
  private long endNode;
  private double key;
  private List<Integer> osmid;
  private String highway;
  private boolean oneway;
  private List<Boolean> reversed;
  private double length;
  private double maxspeed;
  private double weight;
  private String lanes;
  private String name;
  private String width;
  private String bridge;
  private String junction;

  /**
   * Constructs a new Properties object.
   *
   * @param startNode represents the start node of the edge in the graph.
   * @param endNode represents the end node of the edge in the graph.
   * @param key additional identifier of the edge within a set of edges between the same nodes.
   * @param osmid The list of OpenStreetMap IDs associated with this edge.
   * @param highway The type of highway (e.g., primary, secondary, unclassified).
   * @param oneway Indicates whether the road is one-way.
   * @param reversed The list indicating if the edge direction is reversed.
   * @param length The length of the edge in meters.
   * @param maxspeed The maximum speed allowed on this edge.
   * @param weight The weight of the edge, used for routing algorithms.
   * @param lanes The number of lanes on this road.
   * @param name The name of the road.
   * @param width The width of the road.
   * @param bridge Indicates if the road is a bridge.
   * @param junction Indicates if the road is a junction.
   */

  public PropertiesEdge(int startNode, long endNode, double key, List<Integer> osmid,
      String highway, boolean oneway, List<Boolean> reversed, double length, double maxspeed,
      double weight, String lanes, String name, String width, String bridge, String junction) {
    this.startNode = startNode;
    this.endNode = endNode;
    this.key = key;
    this.osmid = osmid;
    this.highway = highway;
    this.oneway = oneway;
    this.reversed = reversed;
    this.length = length;
    this.maxspeed = maxspeed;
    this.weight = weight;
    this.lanes = lanes;
    this.name = name;
    this.width = width;
    this.bridge = bridge;
    this.junction = junction;
  }

  public double getStartNode() {
    return startNode;
  }

  public void setStartNode(int startNode) {
    this.startNode = startNode;
  }

  public double getEndNode() {
    return endNode;
  }

  public void setEndNode(long endNode) {
    this.endNode = endNode;
  }

  public double getKey() {
    return key;
  }

  public void setKey(double key) {
    this.key = key;
  }

  public List<Integer> getOsmid() {
    return osmid;
  }

  public void setOsmid(List<Integer> osmid) {
    this.osmid = osmid;
  }

  public String getHighway() {
    return highway;
  }

  public void setHighway(String highway) {
    this.highway = highway;
  }

  public boolean isOneway() {
    return oneway;
  }

  public void setOneway(boolean oneway) {
    this.oneway = oneway;
  }

  public List<Boolean> getReversed() {
    return reversed;
  }

  public void setReversed(List<Boolean> reversed) {
    this.reversed = reversed;
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public double getMaxspeed() {
    return maxspeed;
  }

  public void setMaxspeed(double maxspeed) {
    this.maxspeed = maxspeed;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getLanes() {
    return lanes;
  }

  public void setLanes(String lanes) {
    this.lanes = lanes;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public String getBridge() {
    return bridge;
  }

  public void setBridge(String bridge) {
    this.bridge = bridge;
  }

  public String getJunction() {
    return junction;
  }

  public void setJunction(String junction) {
    this.junction = junction;
  }

  @Override
  public Document toDocument() {
    Document document = new Document();
    document.append("u", startNode);
    document.append("v", endNode);
    document.append("key", key);
    document.append("osmid", osmid);
    document.append("highway", highway);
    document.append("oneway", oneway);
    document.append("reversed", reversed);
    document.append("length", length);
    document.append("maxspeed", maxspeed);
    document.append("weight", weight);
    document.append("lanes", lanes);
    document.append("name", name);
    document.append("width", width);
    document.append("bridge", bridge);
    document.append("junction", junction);
    return document;
  }

  /**
   * This method creates a Properties object from a Document object.
   *
   * @param document The Document object to convert.
   * @return A Properties object with the data of the Document object.
   */
  public static PropertiesEdge fromDocument(Document document) {
    int startNode = document.getInteger("u");
    Long endNode = document.getLong("v");
    int key = document.getInteger("key");
    List<Integer> osmid = document.getList("osmid", Integer.class);
    String highway = document.getString("highway");
    boolean oneway = document.getBoolean("oneway");
    List<Boolean> reverse = document.getList("reverse", Boolean.class);
    double length = document.getDouble("length");
    int maxspeed = document.getInteger("maxspeed");
    double weight = document.getDouble("weight");
    String lanes = document.getString("lanes");
    String name = document.getString("name");
    String width = document.getString("width");
    String bridge = document.getString("bridge");
    String junction = document.getString("junction");
    return new PropertiesEdge(startNode, endNode, key, osmid, highway, oneway, reverse, length,
        maxspeed, weight, lanes, name, width, bridge, junction);
  }


}
