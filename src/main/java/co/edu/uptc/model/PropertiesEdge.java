package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;


/**
 * Class that represents the Properties.
 */
public class PropertiesEdge implements Documentable {
  private Double startNode;
  private Double endNode;
  private double key;
  private List<Double> osmid;
  private List<String> highway;
  private boolean oneway;
  private List<Boolean> reversed;
  private double length;
  private double maxspeed;
  private double weight;
  private List<String> lanes;
  private List<String> name;
  private List<String> width;
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

  public PropertiesEdge(Double startNode, Double endNode, double key, List<Double> osmid,
      List<String> highway, boolean oneway, List<Boolean> reversed, double length, double maxspeed,
      double weight, List<String> lanes, List<String> name, List<String> width, String bridge,
      String junction) {
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

  public Double getStartNode() {
    return startNode;
  }

  public void setStartNode(Double startNode) {
    this.startNode = startNode;
  }

  public Double getEndNode() {
    return endNode;
  }

  public void setEndNode(Double endNode) {
    this.endNode = endNode;
  }

  public double getKey() {
    return key;
  }

  public void setKey(double key) {
    this.key = key;
  }

  public List<Double> getOsmid() {
    return osmid;
  }

  public void setOsmid(List<Double> osmid) {
    this.osmid = osmid;
  }

  public List<String> getHighway() {
    return highway;
  }

  public void setHighway(List<String> highway) {
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

  public List<String> getLanes() {
    return lanes;
  }

  public void setLanes(List<String> lanes) {
    this.lanes = lanes;
  }

  public List<String> getName() {
    return name;
  }

  public void setName(List<String> name) {
    this.name = name;
  }

  public List<String> getWidth() {
    return width;
  }

  public void setWidth(List<String> width) {
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
    List<Boolean> reversed = new ArrayList<Boolean>();
    List<String> name = new ArrayList<String>();
    List<String> highway = new ArrayList<String>();
    List<String> lanes = new ArrayList<String>();
    List<String> width = new ArrayList<String>();
    List<Double> osmid = new ArrayList<>();
    try {
      String highwayString = document.getString("highway");
      highway.add(highwayString);
    } catch (ClassCastException e) {
      highway = document.getList("highway", String.class);
    }
    try {
      reversed = document.getList("reversed", Boolean.class);
    } catch (ClassCastException e) {
      String reversedString = document.getString("reversed");
      if (reversedString.toLowerCase().equals("true")) {
        reversed.add(true);
      } else {
        reversed.add(false);
      }
    }
    try {
      String nameString = document.getString("name");
      name.add(nameString);
    } catch (ClassCastException e) {
      name = document.getList("name", String.class);
    }
    try {
      String lanesString = document.getString("lanes");
      lanes.add(lanesString);
    } catch (ClassCastException e) {
      lanes = document.getList("lanes", String.class);
    }
    try {
      String widthString = document.getString("width");
      width.add(widthString);
    } catch (ClassCastException e) {
      width = document.getList("width", String.class);
    }
    try {
      osmid = document.getList("osmid", Double.class);
    } catch (ClassCastException e) {
      String osmidString = document.getString("osmid");
      osmid.add(Double.parseDouble(osmidString));
    }
    boolean oneway = document.getBoolean("oneway");
    Double startNode = document.getDouble("u");
    System.out.println(startNode);
    Double endNode = document.getDouble("v");
    Double key = document.getDouble("key");


    double length = document.getDouble("length");
    Double maxspeed = document.getDouble("maxspeed");
    double weight = document.getDouble("weight");
    String bridge = document.getString("bridge");
    String junction = document.getString("junction");
    return new PropertiesEdge(startNode, endNode, key, osmid, highway, oneway, reversed, length,
        maxspeed, weight, lanes, name, width, bridge, junction);
  }


}
