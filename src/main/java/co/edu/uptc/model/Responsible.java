package co.edu.uptc.model;

/**
 * Class that represents a responsible person.
 */
public class Responsible {
  private String name;
  private String id;
  private String phone;
  private String email;

  /**
   * Constructor method.
   *
   * @param name - Name of the responsible person.
   * @param id - Identification of the responsible person.
   * @param phone - Phone number of the responsible person.
   * @param email - Email of the responsible person.
   */
  public Responsible(String name, String id, String phone, String email) {
    this.name = name;
    this.id = id;
    this.phone = phone;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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


}
