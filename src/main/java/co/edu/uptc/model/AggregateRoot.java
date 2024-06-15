package co.edu.uptc.model;

import java.util.UUID;

/**
 * This class is the base class for all models to save in database.
 */
public abstract class AggregateRoot implements Documentable {
  protected UUID id;

  protected AggregateRoot() {
    this.id = UUID.randomUUID();
  }

  protected AggregateRoot(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
