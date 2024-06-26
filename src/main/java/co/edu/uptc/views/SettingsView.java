package co.edu.uptc.views;

import co.edu.uptc.controller.settings.SettingsController;
import co.edu.uptc.infrastructure.MongoClientFactory;
import co.edu.uptc.infrastructure.settings.MongoSettingsRepository;
import co.edu.uptc.model.Settings;
import co.edu.uptc.model.SettingsRepository;
import co.edu.uptc.utils.ServletUtils;
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
 * Class that represents the view for the settings.
 */
@WebServlet("/settings")
public class SettingsView extends HttpServlet {
  private static final Logger logger = LogManager.getLogger(SettingsView.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    MongoClient mongoClient =
        MongoClientFactory.createClient("settingsView", "mongodb://localhost:27017");
    SettingsRepository settingsRepository = new MongoSettingsRepository(mongoClient);
    SettingsController settingsController = new SettingsController(settingsRepository);
    if (settingsController.getAll().isEmpty()) {
      req.setAttribute("notFoundSettings", "Settings not found");
      ServletUtils.forward(req, resp, "/pages/settings.jsp");
      return;
    }
    Settings settings = settingsController.getAll().get(0);

    req.getSession().setAttribute("settings", settings);
    ServletUtils.forward(req, resp, "/pages/settings.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      String sourceAddress = req.getParameter("sourceAddress");
      if (sourceAddress == null || sourceAddress.isEmpty()) {
        req.setAttribute("source_address_is_required", "La dirección de origen es requerida");
        ServletUtils.forward(req, resp, "/pages/settings.jsp");
        return;
      }

      MongoClient mongoClient =
          MongoClientFactory.createClient("settingsView", "mongodb://localhost:27017");
      SettingsRepository settingsRepository = new MongoSettingsRepository(mongoClient);
      SettingsController settingsController = new SettingsController(settingsRepository);

      if (settingsController.getAll().isEmpty()) {
        settingsController.add(new Settings(sourceAddress));
        req.setAttribute("successfully", "Configuración guardada correctamente");
        ServletUtils.forward(req, resp, "/pages/settings.jsp");
        return;
      }
      Settings settings = settingsController.getAll().get(0);
      settings.setSourceAddress(sourceAddress);
      settingsController.edit(settings);
      req.setAttribute("successfully", "Configuración guardada correctamente");
      ServletUtils.forward(req, resp, "/pages/settings.jsp");
      return;
    } catch (Exception e) {
      SettingsView.logger.error("Error: ", e);
      req.setAttribute("intern_error", "Error interno, por favor intente de nuevo.");
      ServletUtils.forward(req, resp, "/pages/settings.jsp");
    }

  }
}
