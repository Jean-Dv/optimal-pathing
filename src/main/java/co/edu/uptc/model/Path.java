package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bson.Document;

/**
 * Class that represents a path.
 */
public class Path extends AggregateRoot {

  private Node start;
  private Node finish;
  private List<Edge> edges;
  private double cost;
  private double estimatedCost;
  private String color;

  /**
   * Parameterized constructor.
   *
   * @param start The initial node.
   * @param finish The finish node.
   * @param edges The edges that have that path.
   * @param cost The real cost of the road.
   * @param estimatedCost It is estimated cost of the route used for the search.
   */
  public Path(Node start, Node finish, List<Edge> edges, double cost, double estimatedCost) {
    this.start = start;
    this.finish = finish;
    this.edges = edges;
    this.cost = cost;
    this.estimatedCost = estimatedCost;
    this.color = "#BDC3C7";

  }

  /**
   * Parameterized constructor.
   *
   * @param id The unique identifier for the path.
   * @param start The initial node.
   * @param finish The finish node.
   * @param edges The edges that have that path.
   * @param cost The real cost of the road.
   * @param estimatedCost It is estimated cost of the route used for the search.
   * @param color The color is what identifies it as the shortest route.
   */
  public Path(UUID id, Node start, Node finish, List<Edge> edges, double cost, double estimatedCost,
      String color) {
    super(id);
    this.start = start;
    this.finish = finish;
    this.edges = edges;
    this.cost = cost;
    this.estimatedCost = estimatedCost;
    this.color = color;
  }

  public Node getStar() {
    return start;
  }

  public void setStar(Node star) {
    this.start = star;
  }

  public Node getFinish() {
    return finish;
  }

  public void setFinish(Node finish) {
    this.finish = finish;
  }

  public List<Edge> getEdges() {
    return edges;
  }

  public void setEdges(List<Edge> edges) {
    this.edges = edges;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public double getEstimatedCost() {
    return estimatedCost;
  }

  public void setEstimatedCost(double estimatedCost) {
    this.estimatedCost = estimatedCost;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Start: ").append(start.getIdentifier()).append(", Finish: ")
        .append(finish.getIdentifier()).append(", Cost: ").append(cost).append(", Estimated Cost: ")
        .append(estimatedCost).append(", Path: ");
    for (Edge edge : edges) {
      sb.append(edge.getProperties().getStartNode()).append(" -> ")
          .append(edge.getProperties().getEndNode()).append(" ");
    }
    return sb.toString();
  }


  @Override
  public Document toDocument() {
    Document document = new Document();
    document.append("_id", this.id.toString());
    document.append("nodeStart", this.start.toDocument());
    document.append("nodeFinish", this.finish.toDocument());
    document.append("cost", this.cost);
    document.append("estimatedCost", this.estimatedCost);
    document.append("colorPath", this.color);
    List<Document> edgesDocuments = this.edges.stream().map(Edge::toDocument).toList();
    document.append("edges", edgesDocuments);
    return document;
  }

  /**
   * Method to convert a document to an path object.
   *
   * @param document Document to convert.
   * @return The path object.
   */
  public static Path fromDocument(Document document) {

    UUID id = UUID.fromString(document.getString("_id"));
    Node start = Node.fromDocument((Document) document.get("nodeStart"));
    Node finish = Node.fromDocument((Document) document.get("nodeFinish"));
    List<Edge> edges = new ArrayList<>();
    List<Document> edgesDocuments = document.getList("edges", Document.class);
    for (Document document2 : edgesDocuments) {
      edges.add(Edge.fromDocument(document2));
    }
    double cost = document.getDouble("cost");
    double estimatedCost = document.getDouble("estimatedCost");
    String color = document.getString("colorPath");

    return new Path(id, start, finish, edges, cost, estimatedCost, color);

  }



}
