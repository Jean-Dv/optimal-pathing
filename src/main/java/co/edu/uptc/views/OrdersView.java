package co.edu.uptc.views;

import co.edu.uptc.controller.orders.OrderController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.orders.MongoOrderRepository;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import co.edu.uptc.utils.ServletUtils;
import com.mongodb.client.MongoClient;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class that represents the view for the listed orders.
 */
@WebServlet("/orders")
public class OrdersView extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    MongoClient mongoClient =
        MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
    OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
    OrderController orderController = new OrderController(orderRepository);
    List<Order> orders = orderController.getAll();

    req.setAttribute("orders", orders);
    ServletUtils.forward(req, resp, "/pages/orders.jsp");
  }
}
