package co.edu.uptc.model;

/**
 * Class that represents a settings.
 */
public class Settings {

  private String sourceAddress;

  public Settings() {}

  public Settings(String sourceAddress) {
    this.sourceAddress = sourceAddress;
  }

  public String getSourceAddress() {
    return sourceAddress;
  }

  public void setSourceAddress(String sourceAddress) {
    this.sourceAddress = sourceAddress;
  }

  @Override
  public String toString() {
    return "Settings [sourceAddress=" + sourceAddress + "]";
  }
}
