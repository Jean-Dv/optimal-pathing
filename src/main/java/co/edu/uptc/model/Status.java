package co.edu.uptc.model;

/**
 * Represents the status of a order.
 */
public enum Status {
  WAREHOUSE_EXIT("Warehouse exit"), ON_WAY("On way"), DEVOLUTION("Devolution"), DELIVERED(
      "Delivered"), DELAY("Delay");

  private String status;

  Status(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  /**
   * Method that allows to obtain the status of a order.
   *
   * @param text - Status of the order.
   * @return Status of the order.
   */
  public static Status fromString(String text) {
    for (Status status : Status.values()) {
      if (status.status.equalsIgnoreCase(text)) {
        return status;
      }
    }
    return null;
  }
}
