package co.edu.uptc.controller.orders;

import co.edu.uptc.controller.AlgorithmStar;
import co.edu.uptc.model.Node;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import co.edu.uptc.model.Path;
import co.edu.uptc.model.Status;
import com.mongodb.client.model.geojson.Point;
import java.util.List;
import org.bson.Document;

/**
 * Class that represents the controller for the order.
 */
public class OrderController {
  private OrderRepository repository;

  public OrderController(OrderRepository repository) {
    this.repository = repository;
  }

  /**
   * Method that allows to create a new order.
   *
   * @param order - Order to be created.
   */
  public void add(Order order) {
    this.repository.save(order);
  }

  /**
   * Method that allows to find all the orders.
   *
   * @return List of orders.
   */
  public List<Order> getAll() {
    return this.repository.findAll();
  }

  /**
   * Method that allows you to edit an order.
   *
   * @param editOrder - Order to edit.
   * @return Edited order.
   */
  public Order edit(Order editOrder) {
    return this.repository.edit(editOrder);
  }

  /**
   * Method that allows you to delete an order.
   *
   * @param orderId - Order id to delete.
   */
  public Order delete(String orderId) {
    return this.repository.erase(orderId);
  }

  public Order getById(String id) {
    return this.repository.findById(id);
  }

  /**
   * Method that allows you to change the status of the order.
   *
   * @param orderId - Order ID to change status.
   * @param newStatus - State by which it is changed.
   */
  public boolean editStatus(String orderId, Status newStatus) {
    Order order = getById(orderId);
    if (order != null) {
      order.setStatus(newStatus);
      this.repository.edit(order);
      return true;
    }
    return false;
  }

  /**
   * Method to find and return a node based on a given point in the map.
   *
   * @param pointInMap - The point in the map to locate the corresponding node.
   * @return The node corresponding to the given point in the map.
   */
  public Node findNode(Point pointInMap) {
    Document document = this.repository.findByPoint(pointInMap);
    return Node.fromDocument(document);
  }

  /**
   * Method to edit an order by finding and setting the optimal routes between two nodes. This
   * method uses a separate thread to perform the route finding operation asynchronously.
   *
   * @param start - The starting node for the path.
   * @param finish - The finishing node for the path.
   * @param order - The order to update with the optimal routes.
   * @return The updated order with the optimal routes set.
   */
  public Order editPathOrder(Node start, Node finish, Order order) {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        AlgorithmStar algorithmStar = new AlgorithmStar();
        List<Path> optimalRoutes = algorithmStar.findShortestPaths(start, finish, 3);
        order.setOptimalRoutes(optimalRoutes);
        synchronized (order) {
          order.notifyAll();
        }
      }
    });

    thread.start();

    try {
      synchronized (order) {
        order.wait();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
    return this.edit(order);
  }

}
