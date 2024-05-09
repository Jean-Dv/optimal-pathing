package co.edu.uptc.model;

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
}
