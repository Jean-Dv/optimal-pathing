package co.edu.uptc.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Class that represents the properties utilities.
 */
public class PropertiesUtils {
  /**
   * Loads the properties from the specified path.
   *
   * @param path The path of the properties file.
   * @return The properties.
   */
  public static Properties loadProperties(String path) {
    try {
      String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
      String appConfigPath = rootPath + path;
      Properties appProps = new Properties();
      appProps.load(new FileInputStream(appConfigPath));
      return appProps;
    } catch (Exception e) {
      return new Properties();
    }
  }
}
