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
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test Order Controller.
 */
public class OrderControllerTest {
  public static OrderController controller;

  /**
   * Setup method.
   */
  @BeforeClass
  public static void before() {
    OrderController controller = new OrderController(new InMemoryOrderRepository());
    OrderControllerTest.controller = controller;
  }

  @Test
  public void testAddNewOrder() {
    try {
      controller.add(new Order(1, LocalDate.now(), LocalDate.ofYearDay(2024, 2),
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
      assertEquals(1, orders.size());
      assertEquals("Calle 3 # 24 - 43", orders.get(0).getSourceAddress());
    } catch (Exception e) {
      fail("Error getting all orders.");
    }
  }

  @Test
  public void testEditOrder() {
    try {
      Order order = new Order(1, LocalDate.now(), LocalDate.ofYearDay(2024, 2), "Calle 3 # 24 - 43",
          "Calle 4 # 25 - 9", "Saliendo", "Jonh Doe", "Company X", 3000, false, "", "",
          new Responsible("Chic Elletson", "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359",
              "bmartt0@netscape.com"));
      // create an order
      controller.add(order);

      // edited order with destination address
      Order orderEdit = new Order(1, LocalDate.now(), LocalDate.ofYearDay(2024, 2),
          "Calle 3 # 24 - 43", "Calle 6 # 25 - 9", "Saliendo", "Jonh Doe", "Company X", 3000, false,
          "", "", new Responsible("Chic Elletson", "24dcd50d-a38c-42c7-888c-1c783d988fea",
              "(210) 5769359", "bmartt0@netscape.com"));

      assertEquals(orderEdit, controller.edit(orderEdit));
      assertEquals(orderEdit.getDestinationAddress(),
          controller.edit(orderEdit).getDestinationAddress());
      orderEdit.setNumberOrder(2);
      assertNull(controller.edit(orderEdit));

    } catch (Exception e) {
      fail("Error getting all orders.");
    }
  }
}
