package co.edu.uptc.infrastructure.orders;

import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import java.util.ArrayList;

/**
 * Class that represents the repository of orders in memory.
 */
public class InMemoryOrderRepository implements OrderRepository {
  private ArrayList<Order> orders;

  public InMemoryOrderRepository() {
    this.orders = new ArrayList<>();
  }

  @Override
  public void save(Order order) {
    this.orders.add(order);
  }
}
