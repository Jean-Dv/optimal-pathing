package co.edu.uptc.controller.orders;

import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;

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
}
