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

  /**
   * Method that allows you to edit an order.
   *
   * @param editOrder - Order to edit.
   * @return Edited order.
   */
  Order edit(Order editOrder);
}
