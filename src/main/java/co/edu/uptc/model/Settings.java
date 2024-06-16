package co.edu.uptc.model;

import java.util.UUID;
import org.bson.Document;

/**
 * Class that represents a settings.
 */
public class Settings extends AggregateRoot {

  private String sourceAddress;

  public Settings() {}

  /**
   * Parameterized constructor.
   *
   * @param sourceAddress The address from which the package will be sent.
   */
  public Settings(String sourceAddress) {
    this.sourceAddress = sourceAddress;
  }

  /**
   * Parameterized constructor.
   *
   * @param id The unique identifier for the settings.
   * @param sourceAddress The address from which the package will be sent.
   */
  public Settings(UUID id, String sourceAddress) {
    super(id);
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
    return "Settings [id=" + id + "sourceAddress=" + sourceAddress + "]";
  }

  @Override
  public Document toDocument() {
    Document document = new Document();
    document.append("_id", this.id.toString());
    document.append("sourceAddress", this.sourceAddress);
    return document;
  }

  /**
   * Method to convert a document to an settings object.
   *
   * @param document Document to convert.
   * @return The settings object.
   */
  public static Settings fromSettings(Document document) {
    UUID id = UUID.fromString(document.getString("_id"));
    String sourceAddress = document.getString("sourceAddress");
    return new Settings(id, sourceAddress);
  }
}
