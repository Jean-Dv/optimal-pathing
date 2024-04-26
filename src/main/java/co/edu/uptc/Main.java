package co.edu.uptc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class.
 */
public class Main {
  private static Logger logger = LogManager.getLogger(Main.class);

  public static void main(String[] args) {
    int a = 10;
    logger.info("Hello World!" + a);
  }
}
