package co.edu.uptc.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import co.edu.uptc.controller.orders.OrderController;
import co.edu.uptc.infrastructure.orders.InMemoryOrderRepository;
import co.edu.uptc.model.Edge;
import co.edu.uptc.model.Node;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.Path;
import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.Status;
import java.time.LocalDate;
import java.util.ArrayList;
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
          "Calle 3 # 24 - 43", "Calle 4 # 25 - 9", Status.WAREHOUSE_EXIT, "Jonh Doe", "Company X",
          3000, false, "", "", new Responsible("Chic Elletson",
              "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359", "bmartt0@netscape.com"),
          new ArrayList<Path>()));
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
          "Calle 4 # 25 - 9", Status.WAREHOUSE_EXIT, "Jonh Doe", "Company X", 3000, false, "", "",
          new Responsible("Chic Elletson", "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359",
              "bmartt0@netscape.com"));
      // create an order
      controller.add(order);

      // edited order with destination address
      Order orderEdit = new Order(order.getId(), LocalDate.now(), LocalDate.ofYearDay(2024, 2),
          "Calle 3 # 24 - 43", "Calle 6 # 25 - 9", Status.WAREHOUSE_EXIT, "Jonh Doe", "Company X",
          3000, false, "", "", new Responsible("Chic Elletson",
              "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359", "bmartt0@netscape.com"),
          new ArrayList<Path>());

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
          "Calle 3 # 24 - 43", "Calle 4 # 25 - 9", Status.WAREHOUSE_EXIT, "Jonh Doe", "Company X",
          3000, false, "", "", new Responsible("Chic Elletson",
              "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359", "bmartt0@netscape.com"),
          new ArrayList<Path>());

      controller.add(order);

      assertEquals(order, controller.delete(order.getId().toString()));
      assertNull(controller.delete(order.getId().toString()));
    } catch (Exception e) {
      fail("Error deleting order.");
    }

  }

  @Test
  public void testEditStatusOrder() {
    Order order = new Order(UUID.randomUUID(), LocalDate.now(), LocalDate.ofYearDay(2024, 2),
        "Diagonal 2 # 1 - 45", "Calle 6 # 15 - 8", Status.WAREHOUSE_EXIT, "Jonh Doe", "Company X",
        3000, false, "", "", new Responsible("Chic Elletson",
            "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359", "bmartt0@netscape.com"),
        new ArrayList<Path>());

    controller.add(order);

    assertEquals(controller.getAll().get(0), controller.getById(order.getId().toString()));
    assertTrue(controller.editStatus(order.getId().toString(), Status.DELAY));
    assertFalse(controller.editStatus("wert-erfghj-422", Status.DELAY));

  }

  @Test
  public void testFilterOrder() {

    Path path = new Path(new Node(null, null, null), new Node(null, null, null),
        new ArrayList<Edge>(), 154, 54);
    ArrayList<Path> pathList = new ArrayList<>();
    pathList.add(path);
    Order order = new Order(UUID.randomUUID(), LocalDate.now(), LocalDate.ofYearDay(2024, 2),
        "Diagonal 2 # 1 - 45", "Calle 6 # 15 - 8", Status.DELIVERED, "Alejandra Doe", "Company X",
        3000, false, "", "", new Responsible("Chic Elletson",
            "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359", "bmartt0@netscape.com"),
        new ArrayList<Path>());

    Order order2 = new Order(UUID.randomUUID(), LocalDate.now(), LocalDate.ofYearDay(2024, 2),
        "Diagonal 2 # 1 - 45", "Calle 6 # 15 - 8", Status.WAREHOUSE_EXIT, "Jonh Doe", "Company X",
        3000, false, "", "", new Responsible("Chic Elletson",
            "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359", "bmartt0@netscape.com"),
        new ArrayList<Path>());

    Order order3 = new Order(UUID.randomUUID(), LocalDate.now(), LocalDate.ofYearDay(2024, 2),
        "Diagonal 2 # 1 - 45", "Calle 5 # 15 - 8", Status.ON_WAY, "Juan Doe", "Company X", 3000,
        false, "delicate", "", new Responsible("Chic Elletson",
            "24dcd50d-a38c-42c7-888c-1c783d988fea", "(210) 5769359", "bmartt0@netscape.com"),
        pathList);

    controller.add(order);
    controller.add(order2);
    controller.add(order3);

    assertEquals(order2, controller.filterOrders(null, null, null, null, Status.WAREHOUSE_EXIT,
        null, null, 0, false, null, null, null, null).get(0));

    assertEquals(order, controller.filterOrders(null, null, null, "Calle 6 # 15 - 8", null, null,
        null, 0, false, null, null, null, null).get(0));

    assertEquals(order3, controller.filterOrders(null, null, null, null, null, null, null, 0, false,
        "delicate", null, null, null).get(0));

    assertEquals(order3, controller.filterOrders(null, null, null, null, null, null, null, 0, false,
        null, null, null, pathList).get(0));
  }
}

