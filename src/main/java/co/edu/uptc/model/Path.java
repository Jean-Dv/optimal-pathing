package co.edu.uptc.model;

import java.util.List;

public class Path {

  private Node start;
  private Node finish;
  private List<Edge> edges;
  private double cost;
  private double estimatedCost;


  public Path(Node start, Node finish, List<Edge> edges, double cost, double estimatedCost) {
    this.start = start;
    this.finish = finish;
    this.edges = edges;
    this.cost = cost;
    this.estimatedCost = estimatedCost;
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



}
