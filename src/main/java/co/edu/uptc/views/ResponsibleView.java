package co.edu.uptc.views;

import co.edu.uptc.controller.responsible.ResponsibleController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.responsible.MongoResponsibleRepository;
import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.ResponsibleRepository;
import co.edu.uptc.utils.ServletUtils;
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
 * Class that represents the view for the responsible.
 */
@WebServlet("/responsible")
public class ResponsibleView extends HttpServlet {

  private static final Logger logger = LogManager.getLogger(ResponsibleView.class);

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {

      MongoClient mongoClient =
          MongoClientFactory.createClient("responsibleView", "mongodb://localhost:27017");
      ResponsibleRepository responsibleRepository = new MongoResponsibleRepository(mongoClient);
      ResponsibleController responsibleController =
          new ResponsibleController(responsibleRepository);
      String idFromParameter = req.getParameter("id");
      if (idFromParameter == null || !StringUtils.isUuid(idFromParameter)) {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }
      Responsible responsibleDeleted = responsibleController.delete(idFromParameter);
      if (responsibleDeleted == null) {
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (Exception e) {
      ResponsibleView.logger.error(e);
    }

  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String id = req.getParameter("id");
    String action = req.getParameter("action");

    if (action != null && action.equals("edit") && id != null) {
      MongoClient mongoClient =
          MongoClientFactory.createClient("responsibleView", "mongodb://localhost:27017");
      ResponsibleRepository responsibleRepository = new MongoResponsibleRepository(mongoClient);
      ResponsibleController responsibleController =
          new ResponsibleController(responsibleRepository);
      Responsible responsible = responsibleController.getById(id);
      req.getSession().setAttribute("responsible", responsible);
      ServletUtils.forward(req, resp, "/pages/editresponsible.jsp");

    }

    ServletUtils.forward(req, resp, "/pages/addresponsible.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String method = req.getParameter("_method");
    if (method != null && method.equals("put")) {
      this.doPut(req, resp);
      return;
    }

    String name = req.getParameter("name");
    String numberIdentification = req.getParameter("numberIdentification");
    String phone = req.getParameter("phone");
    String email = req.getParameter("email");

    if (name == null || name.isEmpty() || numberIdentification == null
        || numberIdentification.isEmpty() || phone == null || phone.isEmpty() || email == null
        || email.isEmpty()) {

      req.setAttribute("errorMessage", "Error: todos los campos son obligatorios");
      ServletUtils.forward(req, resp, "/pages/addresponsible.jsp");
      return;
    }
    if (!name.matches("[a-zA-Z]+")) {
      req.setAttribute("errorMessageName", "Error: solo se aceptan letras");
      ServletUtils.forward(req, resp, "/pages/addresponsible.jsp");
      return;
    }

    if (!email.contains("@")) {
      req.setAttribute("errorMessageEmail", "Email invalido");
      ServletUtils.forward(req, resp, "/pages/addresponsible.jsp");
      return;
    }

    if (!phone.matches("[0-9]+")) {
      req.setAttribute("errorMessagephone", "Error: solo se aceptan números");
      ServletUtils.forward(req, resp, "/pages/addresponsible.jsp");
      return;
    }
    if (!numberIdentification.matches("[0-9]+")) {
      req.setAttribute("errorMessagenumberIdentification", "Error: solo se aceptan números");
      ServletUtils.forward(req, resp, "/pages/addresponsible.jsp");
      return;
    }

    MongoClient mongoClient =
        MongoClientFactory.createClient("responsibleView", "mongodb://localhost:27017");
    ResponsibleRepository responsibleRepository = new MongoResponsibleRepository(mongoClient);
    ResponsibleController responsibleController = new ResponsibleController(responsibleRepository);

    Responsible responsible = new Responsible(name, numberIdentification, phone, email);
    responsibleController.add(responsible);
    resp.sendRedirect("/project-programation/responsibles");
    return;
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String name = req.getParameter("name");
    String numberIdentification = req.getParameter("numberIdentification");
    String phone = req.getParameter("phone");
    String email = req.getParameter("email");

    if (name == null || name.isEmpty() || numberIdentification == null
        || numberIdentification.isEmpty() || phone == null || phone.isEmpty() || email == null
        || email.isEmpty()) {

      req.setAttribute("errorMessage", "Error: todos los campos son obligatorios");
      ServletUtils.forward(req, resp, "/pages/editresponsible.jsp");
      return;
    }
    if (!name.matches("[a-zA-Z]+")) {
      req.setAttribute("errorMessageName", "Error: opcion invalida");
      ServletUtils.forward(req, resp, "/pages/editresponsible.jsp");
      return;
    }

    if (!email.contains("@")) {
      req.setAttribute("errorMessageEmail", "Email invalido");
      ServletUtils.forward(req, resp, "/pages/editresponsible.jsp");
      return;
    }

    if (!phone.matches("[0-9]+")) {
      req.setAttribute("errorMessagephone", "Error: solo se aceptan números");
      ServletUtils.forward(req, resp, "/pages/editresponsible.jsp");
      return;
    }
    if (!numberIdentification.matches("[0-9]+")) {
      req.setAttribute("errorMessagenumberIdentification", "Error: solo se aceptan números");
      ServletUtils.forward(req, resp, "/pages/editresponsible.jsp");
      return;
    }

    String id = req.getParameter("id");
    MongoClient mongoClient =
        MongoClientFactory.createClient("responsibleView", "mongodb://localhost:27017");
    ResponsibleRepository responsibleRepository = new MongoResponsibleRepository(mongoClient);
    ResponsibleController responsibleController = new ResponsibleController(responsibleRepository);

    Responsible responsible = responsibleController.getById(id);
    responsible.setName(name);
    responsible.setNumberIdentification(numberIdentification);
    responsible.setPhone(phone);
    responsible.setEmail(email);

    responsibleController.edit(responsible);
    resp.sendRedirect("/project-programation/responsibles");


  }



}
