package co.edu.uptc.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import co.edu.uptc.controller.orders.OrderController;
import co.edu.uptc.infrastructure.orders.InMemoryOrderRepository;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.Responsible;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Order Controller.
 */
public class OrderControllerTest {
  public OrderController controller;

  /**
   * Setup method.
   */
  @Before
  public void before() {
    OrderController controller = new OrderController(new InMemoryOrderRepository());
    this.controller = controller;
  }

  @Test
  public void testAddNewOrder() {
    try {
      controller.add(new Order(UUID.randomUUID(), LocalDate.now(), LocalDate.ofYearDay(2024, 2),
          "Calle 3 # 24 - 43", "Calle 4 # 25 - 9", "Saliendo", "Jonh Doe", "Company X", 3000, false,
          "", "", new Responsible("Chic Elletson", "24dcd50d-a38c-42c7-888c-1c783d988fea",
              "(210) 5769359", "bmartt0@netscape.com")));
    } catch (Exception e) {
      fail("Error adding new order.");
    }
  }

  @Test
  public void testGetAllOrders() {
    try {
      List<Order> orders = controller.getAll();
      assertEquals(0, orders.size());

    } catch (Exception e) {
      fail("Error getting all orders.");
    }
  }

  @Test
  public void testEditOrder() {
    try {
      Order order = new Order(LocalDate.now(), LocalDate.ofYearDay(2024, 2), "Calle 3 # 24 - 43",
          "Calle 4 # 25 - 9", "Saliendo", "Jonh Doe", "Company X", 3000, false, "", "",
          new Responsible("Chic Elletson", "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359",
              "bmartt0@netscape.com"));
      // create an order
      controller.add(order);

      // edited order with destination address
      Order orderEdit = new Order(order.getId(), LocalDate.now(), LocalDate.ofYearDay(2024, 2),
          "Calle 3 # 24 - 43", "Calle 6 # 25 - 9", "Saliendo", "Jonh Doe", "Company X", 3000, false,
          "", "", new Responsible("Chic Elletson", "24dcd50d-a38c-42c7-888c-1c783d988fea",
              "(210) 5769359", "bmartt0@netscape.com"));

      assertEquals(orderEdit, controller.edit(orderEdit));
      assertEquals(orderEdit.getDestinationAddress(),
          controller.edit(orderEdit).getDestinationAddress());
      orderEdit.setId(UUID.randomUUID());
      assertNull(controller.edit(orderEdit));

    } catch (Exception e) {
      fail("Error getting all orders.");
    }
  }

  @Test
  public void testDeleteOrder() {
    try {
      Order order = new Order(UUID.randomUUID(), LocalDate.now(), LocalDate.ofYearDay(2024, 2),
          "Calle 3 # 24 - 43", "Calle 4 # 25 - 9", "Saliendo", "Jonh Doe", "Company X", 3000, false,
          "", "", new Responsible("Chic Elletson", "24dcd50d-a38c-42c7-888c-1c783d988fea",
              "(210) 5769359", "bmartt0@netscape.com"));

      controller.add(order);

      assertEquals(order, controller.delete(order.getId().toString()));
      assertNull(controller.delete(order.getId().toString()));
    } catch (Exception e) {
      fail("Error deleting order.");
    }
  }
}
