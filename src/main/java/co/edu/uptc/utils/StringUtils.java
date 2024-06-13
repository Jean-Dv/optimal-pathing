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
    return string.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");
  }
}
