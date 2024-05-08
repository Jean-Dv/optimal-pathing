package co.edu.uptc.model;

import java.time.LocalDate;

/**
 * Class that represents the order to send the package.
 */
public class Order {
  private int numberOrder;
  private LocalDate dateIssue;
  private LocalDate deadline;
  private String sourceAddress;
  private String destinationAddress;
  private String status; // change when state class is created
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
   * @param numberOrder The unique identifier for the order.
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
  public Order(int numberOrder, LocalDate dateIssue, LocalDate deadline, String sourceAddress,
      String destinationAddress, String status, String addresseeName, String remitterName,
      int shippingValue, boolean cashonDelivery, String description, String observation,
      Responsible responsible) {
    this.numberOrder = numberOrder;
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

  public int getNumberOrder() {
    return numberOrder;
  }

  public void setNumberOrder(int numberOrder) {
    this.numberOrder = numberOrder;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
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
    return "Order [numberOrder=" + numberOrder + ", dateIssue=" + dateIssue + ", deadline="
        + deadline + ", sourceAddress=" + sourceAddress + ", destinationAddress="
        + destinationAddress + ", status=" + status + ", addresseeName=" + addresseeName
        + ", remitterName=" + remitterName + ", shippingValue=" + shippingValue
        + ", cashonDelivery=" + cashonDelivery + ", description=" + description + ", observation="
        + observation + ", responsible=" + responsible + "]";
  }



}
