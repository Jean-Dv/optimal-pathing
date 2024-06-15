package co.edu.uptc.views;

import co.edu.uptc.controller.orders.OrderController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.orders.MongoOrderRepository;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.Status;
import co.edu.uptc.utils.ServletUtils;
import co.edu.uptc.utils.StringUtils;
import com.mongodb.client.MongoClient;
import java.io.IOException;
import java.time.LocalDate;
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
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String method = req.getParameter("_method");
    if (method != null && method.equals("put")) {
      this.doPut(req, resp);
      return;
    }
    String cashonDelivery = req.getParameter("isCashOn"); // on para si, y null para no
    String destinationAddress = req.getParameter("destinationAddress");
    String descriptionAddress = req.getParameter("descriptionAddress");
    String remitterName = req.getParameter("remitterName");
    String addresseeName = req.getParameter("addresseeName");
    String price = req.getParameter("price");
    String responsible = req.getParameter("responsible");
    boolean isCashOn = false;

    if (descriptionAddress == null || destinationAddress == null || remitterName == null
        || addresseeName == null || price == null || responsible == null
        || descriptionAddress.isEmpty() || destinationAddress.isEmpty() || price.isEmpty()
        || responsible.isEmpty() || remitterName.isEmpty() || addresseeName.isEmpty()) {

      req.setAttribute("errorMessage", "Error: All fields are required");
      ServletUtils.forward(req, resp, "/pages/addorder.jsp");
      return;
    }

    if (cashonDelivery != null && cashonDelivery.equals("on")) {
      isCashOn = true;
    } else {
      isCashOn = false;
    }

    if (!remitterName.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")
        || !addresseeName.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {
      req.setAttribute("errorMessageString", "only letters");
      ServletUtils.forward(req, resp, "/pages/addorder.jsp");
      return;
    }

    MongoClient mongoClient =
        MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
    OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
    OrderController orderController = new OrderController(orderRepository);

    // TODO: You must complete the spaces since you are only jarcodiating.

    Order order = new Order(LocalDate.now(), LocalDate.now(), "", destinationAddress,
        Status.WAREHOUSE_EXIT, addresseeName, remitterName, Integer.parseInt(price), isCashOn,
        descriptionAddress, "", new Responsible(responsible, "", "", ""));
    orderController.add(order);
    resp.sendRedirect("/project-programation/orders");
    return;

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String id = req.getParameter("id");
    String action = req.getParameter("action");

    if (action != null && action.equals("edit") && id != null) {
      MongoClient mongoClient =
          MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
      OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
      OrderController orderController = new OrderController(orderRepository);

      Order order = orderController.getById(id);
      req.getSession().setAttribute("order", order);
      ServletUtils.forward(req, resp, "/pages/editorder.jsp");
    }

    if (action != null && action.equals("editstate") && id != null) {
      MongoClient mongoClient =
          MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
      OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
      OrderController orderController = new OrderController(orderRepository);

      Order order = orderController.getById(id);
      req.getSession().setAttribute("order", order);
      ServletUtils.forward(req, resp, "/pages/editstate.jsp");
    }

    ServletUtils.forward(req, resp, "/pages/addorder.jsp");

  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String pathInfo = req.getPathInfo();

    if (pathInfo != null && pathInfo.equals("/editstate")) {
      String state = req.getParameter("state");
      String id = req.getParameter("id");

      if (state == null || state.isEmpty()) {
        ServletUtils.forward(req, resp, "/pages/orders.jsp");
        return;
      }

      MongoClient mongoClient =
          MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
      OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
      OrderController orderController = new OrderController(orderRepository);

      Status status = Status.fromString(state);
      orderController.editStatus(id, status);
      
      resp.sendRedirect("/project-programation/orders");
    } else {

      final String isCashon = req.getParameter("cashonDelivery");
      String id = req.getParameter("id");
      String destinationAddress = req.getParameter("destinationAddress");
      String descriptionAddress = req.getParameter("descriptionAddress");
      String remitterName = req.getParameter("remitterName");
      String addresseeName = req.getParameter("addresseeName");
      String price = req.getParameter("price");
      String state = req.getParameter("state");

      if (descriptionAddress == null || destinationAddress == null || remitterName == null
          || addresseeName == null || price == null || descriptionAddress.isEmpty()
          || destinationAddress.isEmpty() || remitterName.isEmpty() || addresseeName.isEmpty()
          || price.isEmpty() || state == null || state.isEmpty()) {

        req.setAttribute("id", id);
        req.setAttribute("action", "edit");
        req.setAttribute("all_fields_required", "Error: All fields are required");
        ServletUtils.forward(req, resp, "/pages/editorder.jsp");
        return;
      }

      if (!remitterName.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")
          || !addresseeName.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {
        req.setAttribute("only_letters_are_accepted", "only letters");
        ServletUtils.forward(req, resp, "/pages/editorder.jsp");
        return;
      }

      MongoClient mongoClient =
          MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
      OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
      OrderController orderController = new OrderController(orderRepository);

      Order order = orderController.getById(id);

      order.setDestinationAddress(destinationAddress);
      order.setDescription(descriptionAddress);
      order.setRemitterName(remitterName);
      order.setAddresseeName(addresseeName);
      order.setCashonDelivery(isCashon != null);
      order.setShippingValue(Integer.parseInt(price));
      order.setStatus(Status.fromString(state));
      orderController.edit(order);

      resp.sendRedirect("/project-programation/orders");
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      MongoClient mongoClient =
          MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
      OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
      final OrderController orderController = new OrderController(orderRepository);
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
