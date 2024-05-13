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

  @Override
  public ArrayList<Order> findAll() {
    return this.orders;
  }

  @Override
  public Order edit(Order editOrder) {
    for (Order order : orders) {
      if (order.getId().toString().equals(editOrder.getId().toString())) {
        order = editOrder;
        return order;
      }

    }
    return null;
  }

  @Override
  public Order erase(String orderId) {
    for (Order order : orders) {
      if (order.getId().toString().equals(orderId)) {
        orders.remove(order);
        return order;
      }
    }
    return null;
  }
}
