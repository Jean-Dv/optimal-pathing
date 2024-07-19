package co.edu.uptc.controller.orders;

import co.edu.uptc.controller.AlgorithmStar;
import co.edu.uptc.model.Node;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import co.edu.uptc.model.Path;
import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.Route;
import co.edu.uptc.model.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.model.geojson.Point;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

  /**
   * Generates a JSON representation of optimal routes for an order, including status and route
   * details. If no optimal routes are found for the order, returns a JSON with status false.
   *
   * @param order The order for which to generate the JSON representation of optimal routes.
   * @return A JSON string representing the optimal routes and status of the order.
   */
  public String routesGson(Order order) {
    boolean ok = false;
    List<Path> data = new ArrayList<>();
    Node start = null;
    Node finish = null;

    if (order == null || order.getOptimalRoutes() == null || order.getOptimalRoutes().isEmpty()) {
      Route route = new Route(ok, start, finish, data);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      return gson.toJson(route);
    }

    ok = true;
    data = order.getOptimalRoutes();
    start = order.getOptimalRoutes().get(0).getStar();
    finish = order.getOptimalRoutes().get(0).getFinish();
    Route route = new Route(ok, start, finish, data);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(route);
  }


  /**
   * Filters orders based on specified criteria.
   *
   * @param dateIssue Date when orders were issued. Null to ignore filtering by issue date.
   * @param deadline Delivery deadline for orders. Null to ignore filtering by deadline.
   * @param sourceAddress Source address of orders. Null to ignore filtering by source address.
   * @param destinationAddress Destination address of orders. Null to ignore filtering by
   *        destination address.
   * @param status Status of orders. Null to ignore filtering by status.
   * @param addresseeName Name of the addressee. Null to ignore filtering by addressee name.
   * @param remitterName Name of the remitter. Null to ignore filtering by remitter name.
   * @param shippingValue Shipping cost of orders. Less than or equal to zero to ignore filtering by
   *        shipping value.
   * @param cashonDelivery Indicates if cash on delivery is enabled. True to include orders with
   *        cash on delivery, false to exclude them.
   * @param description Description of orders. Null to ignore filtering by description.
   * @param observation Observations related to orders. Null to ignore filtering by observation.
   * @param responsible Responsible person associated with orders. Null to ignore filtering by
   *        responsible person.
   * @param optimalRoutes Optimal routes for orders. Null to ignore filtering by optimal routes.
   * @return List of orders matching all specified criteria.
   */
  public List<Order> filterOrders(LocalDate dateIssue, LocalDate deadline, String sourceAddress,
      String destinationAddress, Status status, String addresseeName, String remitterName,
      int shippingValue, boolean cashonDelivery, String description, String observation,
      Responsible responsible, List<Path> optimalRoutes) {
    return repository.findAll().stream()
        .filter(order -> (dateIssue == null || order.getDateIssue().equals(dateIssue))
            && (deadline == null || order.getDeadline().equals(deadline))
            && (sourceAddress == null || order.getSourceAddress().equalsIgnoreCase(sourceAddress))
            && (destinationAddress == null
                || order.getDestinationAddress().equalsIgnoreCase(destinationAddress))
            && (status == null || order.getStatus() == status)
            && (addresseeName == null || order.getAddresseeName().equalsIgnoreCase(addresseeName))
            && (remitterName == null || order.getRemitterName().equalsIgnoreCase(remitterName))
            && (shippingValue <= 0 || order.getShippingValue() == shippingValue)
            && (!cashonDelivery || order.isCashonDelivery())
            && (description == null || order.getDescription().contains(description))
            && (observation == null || order.getObservation().contains(observation))
            && (responsible == null || order.getResponsible().equals(responsible))
            && (optimalRoutes == null || order.getOptimalRoutes().equals(optimalRoutes)))
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a paginated list of orders.
   *
   * @param page the page number to retrieve, starting from 1.
   * @param pageSize the number of orders per page.
   * @return a list of orders for the specified page. If the page number exceeds the total number of
   *         pages, an empty list is returned.
   */
  public List<Order> getOrders(int page, int pageSize) {
    List<Order> orders = getAll();

    int totalOrders = orders.size();
    int offset = (page - 1) * pageSize;

    if (offset >= totalOrders) {
      return List.of();
    }
    int end = Math.min(offset + pageSize, totalOrders);
    return getAll().subList(offset, end);

  }

  /**
   * Retrieves a paginated list of filtered orders.
   *
   * @param orders the list of orders to filter and paginate.
   * @param page the page number to retrieve, starting from 1.
   * @param pageSize the number of orders per page.
   * @return a list of orders for the specified page from the filtered list. If the page number
   *         exceeds the total number of pages in the filtered list, an empty list is returned.
   */
  public List<Order> getOrdersFilter(List<Order> orders, int page, int pageSize) {
    List<Order> ordersFilter = orders;

    int totalOrders = ordersFilter.size();
    int offset = (page - 1) * pageSize;

    if (offset >= totalOrders) {
      return List.of();
    }
    int end = Math.min(offset + pageSize, totalOrders);
    return orders.subList(offset, end);

  }
}
