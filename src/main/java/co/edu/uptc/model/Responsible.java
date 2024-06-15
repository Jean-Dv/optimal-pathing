package co.edu.uptc.model;

import java.util.UUID;
import org.bson.Document;

/**
 * Class that represents a responsible person.
 */
public class Responsible extends AggregateRoot {
  private String name;
  private String numberIdentification;
  private String phone;
  private String email;

  /**
   * Constructor method.
   *
   * @param name - Name of the responsible person.
   * @param numberIdentification - Identification of the responsible person.
   * @param phone - Phone number of the responsible person.
   * @param email - Email of the responsible person.
   */
  public Responsible(UUID id, String name, String numberIdentification, String phone,
      String email) {
    super(id);
    this.name = name;
    this.numberIdentification = numberIdentification;
    this.phone = phone;
    this.email = email;
  }

  /**
   * Constructor method.
   *
   * @param name - Name of the responsible person.
   * @param numberIdentification - Identification of the responsible person.
   * @param phone - Phone number of the responsible person.
   * @param email - Email of the responsible person.
   */
  public Responsible(String name, String numberIdentification, String phone, String email) {
    this.name = name;
    this.numberIdentification = numberIdentification;
    this.phone = phone;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNumberIdentification() {
    return numberIdentification;
  }

  public void setNumberIdentification(String numberIdentification) {
    this.numberIdentification = numberIdentification;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public String toString() {
    return "Responsible [name=" + this.name + ", id=" + this.id + ", phone=" + this.phone
        + ", email=" + this.email + "]";
  }

  @Override
  public Document toDocument() {
    Document document = new Document();
    document.append("_id", this.id.toString());
    document.append("name", this.name);
    document.append("numberIdentification", this.numberIdentification);
    document.append("phone", this.phone);
    document.append("email", this.email);
    return document;
  }


  /**
   * Method to convert a document to a responsible object.
   *
   * @param document Document to convert.
   * @return Responsible object.
   */
  public static Responsible fromDocument(Document document) {
    UUID id = UUID.fromString(document.getString("_id"));
    String name = document.getString("name");
    String numberIdentification = document.getString("numberIdentification");
    String phone = document.getString("phone");
    String email = document.getString("email");
    return new Responsible(id, name, numberIdentification, phone, email);
  }

}
