package co.edu.uptc.views;

import java.io.File;
import javax.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * Log4jInit class.
 */
public class Log4jInit extends HttpServlet {
  /**
   * Initialize the log4j configuration.
   */
  public void init() {
    String prefix = this.getServletContext().getRealPath("/");
    String fileParameter = this.getInitParameter("log4j-init-file");
    File file = new File(prefix + fileParameter);

    if (file != null) {
      LoggerContext context = (LoggerContext) LogManager.getContext(false);
      context.setConfigLocation(file.toURI());
    }
  }
}
