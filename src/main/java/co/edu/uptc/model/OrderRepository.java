package co.edu.uptc.model;

import java.util.List;

/**
 * Interface that represents the repository of orders.
 */
public interface OrderRepository {
  /**
   * Method that allows to save an order.
   *
   * @param order - Order to be saved.
   */
  void save(Order order);

  /**
   * Method that allows to find all the orders.
   *
   * @return List of orders.
   */
  List<Order> findAll();
}
