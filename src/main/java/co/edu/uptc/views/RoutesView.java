package co.edu.uptc.views;

import co.edu.uptc.controller.orders.OrderController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.orders.MongoOrderRepository;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import com.mongodb.client.MongoClient;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles the retrieval and display of order data in JSON format.
 */
@WebServlet("/routes")
public class RoutesView extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String idOrder = req.getParameter("id");

    MongoClient mongoClient =
        MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
    OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
    OrderController orderController = new OrderController(orderRepository);

    Order order = orderController.getById(idOrder);
    String json = orderController.routesGson(order);

    // Mostrar el JSON en la respuesta
    resp.setContentType("application/json");
    resp.setCharacterEncoding("UTF-8");
    resp.getWriter().write(json);


  }


}
