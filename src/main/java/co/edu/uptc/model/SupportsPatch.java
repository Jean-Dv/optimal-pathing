package co.edu.uptc.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface that represents the support for the patch method.
 */
public interface SupportsPatch {
  /**
   * Method that allows to do the patch.
   *
   * @param req - Request.
   * @param resp - Response.
   * @throws ServletException - If there is an error.
   * @throws IOException - If there is an error.
   */
  abstract void doPatch(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException;
}
