package co.edu.uptc.views;

import co.edu.uptc.controller.responsible.ResponsibleController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.responsible.MongoResponsibleRepository;
import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.ResponsibleRepository;
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
 * Class that represents the view for the listed responsibles.
 */
@WebServlet("/responsibles")
public class ResponsiblesView extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    MongoClient mongoClient =
        MongoClientFactory.createClient("responsibleView", "mongodb://localhost:27017");
    ResponsibleRepository responsibleRepository = new MongoResponsibleRepository(mongoClient);
    ResponsibleController responsibleController = new ResponsibleController(responsibleRepository);
    List<Responsible> responsibles = responsibleController.getAll();

    req.getSession().setAttribute("responsibles", responsibles);
    ServletUtils.forward(req, resp, "/pages/responsible.jsp");
  }

}
