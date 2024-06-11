package co.edu.uptc.views;

import co.edu.uptc.controller.orders.OrderController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.orders.MongoOrderRepository;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import co.edu.uptc.utils.StringUtils;
import com.mongodb.client.MongoClient;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that represents the view for the order.
 */
@WebServlet("/order")
public class OrderView extends HttpServlet {
  private static final Logger logger = LogManager.getLogger(OrderView.class);

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      MongoClient mongoClient =
          MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
      OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
      OrderController orderController = new OrderController(orderRepository);
      String idFromParameter = req.getParameter("id");
      if (idFromParameter == null || !StringUtils.isUuid(idFromParameter)) {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      Order orderDeleted = orderController.delete(idFromParameter);
      if (orderDeleted == null) {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (Exception error) {
      OrderView.logger.error(error);
    }
  }

}
