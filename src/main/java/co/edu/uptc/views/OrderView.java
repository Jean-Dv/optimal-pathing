package co.edu.uptc.views;

import co.edu.uptc.controller.orders.OrderController;
import co.edu.uptc.controller.responsible.ResponsibleController;
import co.edu.uptc.controller.settings.SettingsController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.orders.MongoOrderRepository;
import co.edu.uptc.infrastructure.responsible.MongoResponsibleRepository;
import co.edu.uptc.infrastructure.settings.MongoSettingsRepository;
import co.edu.uptc.model.Node;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.ResponsibleRepository;
import co.edu.uptc.model.SettingsRepository;
import co.edu.uptc.model.Status;
import co.edu.uptc.model.SupportsPatch;
import co.edu.uptc.utils.ServletUtils;
import co.edu.uptc.utils.StringUtils;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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
public class OrderView extends HttpServlet implements SupportsPatch {
  private static final String METHOD_PATCH = "PATCH";
  private static final Logger logger = LogManager.getLogger(OrderView.class);

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String method = req.getMethod();
    if (!method.equals(METHOD_PATCH)) {
      super.service(req, resp);
      return;
    }
    this.doPatch(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String method = req.getParameter("_method");
    if (method != null && method.equals("put")) {
      this.doPut(req, resp);
      return;
    }

    if (method != null && method.equals("patch")) {
      this.doPatch(req, resp);
      return;
    }

    final String cashonDelivery = req.getParameter("isCashOn"); // on para si, y null para no
    final String descriptionAddress = req.getParameter("descriptionAddress");
    final String remitterName = req.getParameter("remitterName");
    final String addresseeName = req.getParameter("addresseeName");
    final String price = req.getParameter("price");
    final String responsibleId = req.getParameter("responsible");
    String streetType = req.getParameter("streetType");
    String streetName = req.getParameter("streetName");
    String number = req.getParameter("number");
    String suffix = req.getParameter("suffix");

    if (descriptionAddress == null || remitterName == null || addresseeName == null || price == null
        || responsibleId == null || descriptionAddress.isEmpty() || price.isEmpty()
        || responsibleId.isEmpty() || remitterName.isEmpty() || addresseeName.isEmpty()) {

      req.setAttribute("errorMessage", "Error: todos los campos son obligatorios");
      ServletUtils.forward(req, resp, "/pages/addorder.jsp");
      return;
    }

    if (streetName == null || streetType == null || number == null || suffix == null
        || streetType.isEmpty() || number.isEmpty() || suffix.isEmpty() || streetName.isEmpty()) {
      req.setAttribute("errorMessageAddress", "Error: todos los campos son obligatorios");
      ServletUtils.forward(req, resp, "/pages/addorder.jsp");
      return;
    }
    boolean isCashOn = false;
    if (cashonDelivery != null && cashonDelivery.equals("on")) {
      isCashOn = true;
    } else {
      isCashOn = false;
    }

    if (!remitterName.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")
        || !addresseeName.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {
      req.setAttribute("errorMessageString", "Error: solo se aceptan letras");
      ServletUtils.forward(req, resp, "/pages/addorder.jsp");
      return;
    }
    String destinationAddress = streetType + " " + streetName + " " + number + "-" + suffix;
    MongoClient mongoClient =
        MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
    OrderRepository orderRepository = new MongoOrderRepository(mongoClient);

    SettingsRepository settingsRepository = new MongoSettingsRepository(mongoClient);
    SettingsController settingsController = new SettingsController(settingsRepository);

    String sourceAddress = "";
    if (settingsController.getAll().isEmpty()) {
      sourceAddress = "";
    }
    OrderController orderController = new OrderController(orderRepository);
    sourceAddress = settingsController.getAll().get(0).getSourceAddress();

    MongoClient mongoClientResponsible =
        MongoClientFactory.createClient("responsibleView", "mongodb://localhost:27017");
    ResponsibleRepository responsibleRepository =
        new MongoResponsibleRepository(mongoClientResponsible);
    ResponsibleController responsibleController = new ResponsibleController(responsibleRepository);

    Responsible responsible = responsibleController.getById(responsibleId);
    Order order = new Order(LocalDate.now(), LocalDate.now(), sourceAddress, destinationAddress,
        Status.WAREHOUSE_EXIT, addresseeName, remitterName, Integer.parseInt(price), isCashOn,
        descriptionAddress, "", responsible);

    orderController.add(order);
    if (!sourceAddress.isEmpty() || sourceAddress != null) {
      try {
        Node start = orderController.findNode(geocoding(sourceAddress));
        Node finish = orderController.findNode(geocoding(destinationAddress));
        orderController.editPathOrder(start, finish, order);

      } catch (ApiException | InterruptedException | IOException e) {
        req.setAttribute("errorMessageGoogleMaps", "Dirrección no valida");
        ServletUtils.forward(req, resp, "/pages/addorder.jsp");
        return;
      }
    }

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
      return;
    }

    if (action != null && action.equals("editstate") && id != null) {
      MongoClient mongoClient =
          MongoClientFactory.createClient("orderView", "mongodb://localhost:27017");
      OrderRepository orderRepository = new MongoOrderRepository(mongoClient);
      OrderController orderController = new OrderController(orderRepository);

      Order order = orderController.getById(id);
      req.getSession().setAttribute("order", order);
      ServletUtils.forward(req, resp, "/pages/editstate.jsp");
      return;
    }
    MongoClient mongoClient =
        MongoClientFactory.createClient("responsibleView", "mongodb://localhost:27017");
    ResponsibleRepository responsibleRepository = new MongoResponsibleRepository(mongoClient);
    ResponsibleController responsibleController = new ResponsibleController(responsibleRepository);
    List<Responsible> responsibles = responsibleController.getAll();

    req.getSession().setAttribute("responsibles", responsibles);
    ServletUtils.forward(req, resp, "/pages/addorder.jsp");

  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    final String isCashon = req.getParameter("cashonDelivery");
    String id = req.getParameter("id");
    String destinationAddress = req.getParameter("destinationAddress");
    String descriptionAddress = req.getParameter("descriptionAddress");
    String remitterName = req.getParameter("remitterName");
    String addresseeName = req.getParameter("addresseeName");
    String price = req.getParameter("price");
    String state = req.getParameter("state");
    String responsibleId = req.getParameter("responsible");

    if (descriptionAddress == null || destinationAddress == null || remitterName == null
        || addresseeName == null || price == null || descriptionAddress.isEmpty()
        || destinationAddress.isEmpty() || remitterName.isEmpty() || addresseeName.isEmpty()
        || price.isEmpty() || state == null || state.isEmpty() || responsibleId == null
        || responsibleId.isEmpty()) {

      req.setAttribute("id", id);
      req.setAttribute("action", "edit");
      req.setAttribute("all_fields_required", "Error: todos los campos son obligatorios");
      ServletUtils.forward(req, resp, "/pages/editorder.jsp");
      return;
    }

    if (!remitterName.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")
        || !addresseeName.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {
      req.setAttribute("only_letters_are_accepted", "Error: solo se aceptan letras");
      ServletUtils.forward(req, resp, "/pages/editorder.jsp");
      return;
    }

    MongoClient mongoClientResponsible =
        MongoClientFactory.createClient("responsibleView", "mongodb://localhost:27017");
    ResponsibleRepository responsibleRepository =
        new MongoResponsibleRepository(mongoClientResponsible);
    ResponsibleController responsibleController = new ResponsibleController(responsibleRepository);
    final Responsible responsible = responsibleController.getById(responsibleId);

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
    order.setResponsible(responsible);
    orderController.edit(order);
    resp.sendRedirect("/project-programation/orders");
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

  @Override
  public void doPatch(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String id = req.getParameter("id");
    String state = req.getParameter("state");

    if (state == null || state.isEmpty() || id == null || id.isEmpty()) {
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
  }

  private Point geocoding(String address) throws ApiException, InterruptedException, IOException {
    GeoApiContext context = new GeoApiContext.Builder().apiKey("").build();
    GeocodingResult[] response =
        GeocodingApi.geocode(context, address + ",Sogamoso,Boyaca,Colombia").await();
    if (response != null && response.length > 0) {
      double lng = response[0].geometry.location.lng;
      double lat = response[0].geometry.location.lat;
      double[] location = new double[2];
      location[0] = lat;
      location[1] = lng;

      Point point = new Point(new Position(lng, lat));
      context.shutdown();

      return point;
    } else {
      return null;
    }
  }
}
