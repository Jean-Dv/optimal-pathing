package co.edu.uptc.model;

import java.util.List;

/**
 * This class represents a Route containing a status indicator and a list of paths.
 */
public class Route {
  private boolean ok;
  private Node start;
  private Node finish;
  private List<Path> data;

  /**
   * Default constructor.
   */
  public Route() {}

  /**
   * Constructor method.
   *
   * @param ok Whether the route was successfully processed.
   * @param start The starting node of the route.
   * @param finish The ending node of the route.
   * @param data List of paths comprising the route.
   */
  public Route(boolean ok, Node start, Node finish, List<Path> data) {
    this.ok = ok;
    this.start = start;
    this.finish = finish;
    this.data = data;
  }

  public boolean isOk() {
    return ok;
  }

  public void setOk(boolean ok) {
    this.ok = ok;
  }

  public Node getStart() {
    return start;
  }

  public void setStart(Node start) {
    this.start = start;
  }

  public Node getFinish() {
    return finish;
  }

  public void setFinish(Node finish) {
    this.finish = finish;
  }

  public List<Path> getData() {
    return data;
  }

  public void setData(List<Path> data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Route [ok=" + ok + ", start=" + start + ", finish=" + finish + ", data=" + data + "]";
  }



}
