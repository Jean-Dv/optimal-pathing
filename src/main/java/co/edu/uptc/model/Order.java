package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.UUID;
import org.bson.Document;

/**
 * Class that represents the order to send the package.
 */
public class Order extends AggregateRoot {
  private LocalDate dateIssue;
  private LocalDate deadline;
  private String sourceAddress;
  private String destinationAddress;
  private Status status; // change when state class is created
  private String addresseeName;
  private String remitterName;
  private int shippingValue;
  private boolean cashonDelivery;
  private String description;
  private String observation;
  private Responsible responsible;

  public Order() {}

  /**
   * Parameterized constructor.
   *
   * @param id The unique identifier for the order.
   * @param dateIssue The date when the order was issued.
   * @param deadline The deadline for delivering the package.
   * @param sourceAddress The address from which the package will be sent.
   * @param destinationAddress The address to which the package will be delivered.
   * @param status The current status of the order.
   * @param addresseeName The name of the addressee who will receive the package.
   * @param remitterName The name of the remitter who is sending the package.
   * @param shippingValue The cost of shipping the package.
   * @param cashonDelivery Indicates whether cash on delivery is enabled for the package.
   * @param description A brief description of the package.
   * @param observation Any additional observation or note related to the order.
   * @param responsible The responsible person associated with the order.
   */
  public Order(UUID id, LocalDate dateIssue, LocalDate deadline, String sourceAddress,
      String destinationAddress, Status status, String addresseeName, String remitterName,
      int shippingValue, boolean cashonDelivery, String description, String observation,
      Responsible responsible) {
    super(id);
    this.dateIssue = dateIssue;
    this.deadline = deadline;
    this.sourceAddress = sourceAddress;
    this.destinationAddress = destinationAddress;
    this.status = status;
    this.addresseeName = addresseeName;
    this.remitterName = remitterName;
    this.shippingValue = shippingValue;
    this.cashonDelivery = cashonDelivery;
    this.description = description;
    this.observation = observation;
    this.responsible = responsible;
  }

  /**
   * Parameterized constructor.
   *
   * @param dateIssue The date when the order was issued.
   * @param deadline The deadline for delivering the package.
   * @param sourceAddress The address from which the package will be sent.
   * @param destinationAddress The address to which the package will be delivered.
   * @param status The current status of the order.
   * @param addresseeName The name of the addressee who will receive the package.
   * @param remitterName The name of the remitter who is sending the package.
   * @param shippingValue The cost of shipping the package.
   * @param cashonDelivery Indicates whether cash on delivery is enabled for the package.
   * @param description A brief description of the package.
   * @param observation Any additional observation or note related to the order.
   * @param responsible The responsible person associated with the order.
   */
  public Order(LocalDate dateIssue, LocalDate deadline, String sourceAddress,
      String destinationAddress, Status status, String addresseeName, String remitterName,
      int shippingValue, boolean cashonDelivery, String description, String observation,
      Responsible responsible) {
    super();
    this.dateIssue = dateIssue;
    this.deadline = deadline;
    this.sourceAddress = sourceAddress;
    this.destinationAddress = destinationAddress;
    this.status = status;
    this.addresseeName = addresseeName;
    this.remitterName = remitterName;
    this.shippingValue = shippingValue;
    this.cashonDelivery = cashonDelivery;
    this.description = description;
    this.observation = observation;
    this.responsible = responsible;
  }

  public LocalDate getDateIssue() {
    return dateIssue;
  }

  public void setDateIssue(LocalDate dateIssue) {
    this.dateIssue = dateIssue;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public String getSourceAddress() {
    return sourceAddress;
  }

  public void setSourceAddress(String sourceAddress) {
    this.sourceAddress = sourceAddress;
  }

  public String getDestinationAddress() {
    return destinationAddress;
  }

  public void setDestinationAddress(String destinationAddress) {
    this.destinationAddress = destinationAddress;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getAddresseeName() {
    return addresseeName;
  }

  public void setAddresseeName(String addresseeName) {
    this.addresseeName = addresseeName;
  }

  public String getRemitterName() {
    return remitterName;
  }

  public void setRemitterName(String remitterName) {
    this.remitterName = remitterName;
  }

  public int getShippingValue() {
    return shippingValue;
  }

  public void setShippingValue(int shippingValue) {
    this.shippingValue = shippingValue;
  }

  public boolean isCashonDelivery() {
    return cashonDelivery;
  }

  public void setCashonDelivery(boolean cashonDelivery) {
    this.cashonDelivery = cashonDelivery;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getObservation() {
    return observation;
  }

  public void setObservation(String observation) {
    this.observation = observation;
  }

  public Responsible getResponsible() {
    return responsible;
  }

  public void setResponsible(Responsible responsible) {
    this.responsible = responsible;
  }

  @Override
  public String toString() {
    return "Order [id=" + id + ", dateIssue=" + dateIssue + ", deadline=" + deadline
        + ", sourceAddress=" + sourceAddress + ", destinationAddress=" + destinationAddress
        + ", status=" + status + ", addresseeName=" + addresseeName + ", remitterName="
        + remitterName + ", shippingValue=" + shippingValue + ", cashonDelivery=" + cashonDelivery
        + ", description=" + description + ", observation=" + observation + ", responsible="
        + responsible + "]";
  }

  @Override
  public Document toDocument() {
    Document document = new Document();
    document.append("_id", this.id.toString());
    document.append("dateIssue", this.dateIssue.toString());
    document.append("deadline", this.deadline.toString());
    document.append("sourceAddress", this.sourceAddress);
    document.append("destinationAddress", this.destinationAddress);
    document.append("status", this.status);
    document.append("addresseeName", this.addresseeName);
    document.append("remitterName", this.remitterName);
    document.append("shippingValue", this.shippingValue);
    document.append("cashonDelivery", this.cashonDelivery);
    document.append("description", this.description);
    document.append("observation", this.observation);
    document.append("responsible", this.responsible.toDocument());
    return document;
  }

  /**
   * Method to convert a document to an order object.
   *
   * @param document Document to convert.
   * @return The order object.
   */
  public static Order fromDocument(Document document) {
    UUID id = UUID.fromString(document.getString("_id"));
    LocalDate dateIssue = LocalDate.parse(document.getString("dateIssue"));
    LocalDate deadline = LocalDate.parse(document.getString("deadline"));
    String sourceAddress = document.getString("sourceAddress");
    String destinationAddress = document.getString("destinationAddress");
    Status status = Status.valueOf(document.getString("status"));
    String addresseeName = document.getString("addresseeName");
    String remitterName = document.getString("remitterName");
    int shippingValue = document.getInteger("shippingValue");
    boolean cashonDelivery = document.getBoolean("cashonDelivery");
    String description = document.getString("description");
    String observation = document.getString("observation");
    Responsible responsible = Responsible.fromDocument((Document) document.get("responsible"));
    return new Order(id, dateIssue, deadline, sourceAddress, destinationAddress, status,
        addresseeName, remitterName, shippingValue, cashonDelivery, description, observation,
        responsible);
  }
}
