package co.edu.uptc.model;

/**
 * This enumeration represents the type of feature that a node can have.
 */
public enum FeatureType {
  FEATURE("Feature");

  private String value;

  FeatureType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
