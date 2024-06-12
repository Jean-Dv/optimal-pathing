package co.edu.uptc.utils;

/**
 * Utility class for string operations.
 */
public class StringUtils {
  /**
   * Checks if a string is a valid UUID.
   *
   * @param string The string to check.
   * @return True if the string is a valid UUID, false otherwise.
   */
  public static boolean isUuid(String string) {
    return string
        .matches("/^[0-9A-F]{8}-[0-9A-F]{4}-[4][0-9A-F]{3}-[89AB][0-9A-F]{3}-[0-9A-F]{12}$/i");
  }
}
