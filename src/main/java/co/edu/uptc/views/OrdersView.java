package co.edu.uptc.views;

import co.edu.uptc.controller.orders.OrderController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.orders.MongoOrderRepository;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import co.edu.uptc.model.Status;
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

    int currentPage = 1;
    int pageSize = 6;

    String pageParam = req.getParameter("page");
    if (pageParam != null) {
      currentPage = Integer.parseInt(pageParam);
    }

    MongoClient mongoClient =
        MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
    OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
    OrderController orderController = new OrderController(orderRepository);

    List<Order> orders;
    String statusParam = req.getParameter("status");

    int totalOrders;
    if (statusParam != null && !statusParam.isEmpty()) {
      Status status = Status.fromString(statusParam);
      orders = orderController.filterOrders(null, null, null, null, status, null, null, 0, false,
          null, null, null, null);
      totalOrders = orders.size();
      orders = orderController.getOrdersFilter(orders, currentPage, pageSize);
    } else {
      orders = orderController.getOrders(currentPage, pageSize);
      totalOrders = orderController.getAll().size();
    }

    int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
    if (totalPages == 0) {
      totalPages = 1;
    }

    req.getSession().setAttribute("orders", orders);
    req.getSession().setAttribute("currentPage", currentPage);
    req.getSession().setAttribute("totalPages", totalPages);

    ServletUtils.forward(req, resp, "/pages/orders.jsp");
  }
}
