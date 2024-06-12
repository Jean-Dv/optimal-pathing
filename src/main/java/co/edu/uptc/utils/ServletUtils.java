package co.edu.uptc.utils;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class provides method to work with HttpServlet.
 */
public class ServletUtils {
  /**
   * Method allows a servlet to perform preliminary processing of a request.
   *
   * @param request {@link HttpServletRequest} request information for HTTP servlets.
   * @param response {@link HttpServletResponse} HTTP-specific functionality in sending a response.
   * @param url forward URL.
   * @throws IOException Signals that an I/O exception of some sort has occurred
   * @see HttpServletRequest#getRequestDispatcher(String)
   * @see RequestDispatcher#forward(ServletRequest, ServletResponse)
   */
  public static void forward(HttpServletRequest request, HttpServletResponse response, String url)
      throws ServletException, IOException {
    RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
    requestDispatcher.forward(request, response);

  }

}
