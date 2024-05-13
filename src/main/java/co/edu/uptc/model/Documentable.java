package co.edu.uptc.model;

import org.bson.Document;

/**
 * Interface that represents one model is documentable.
 */
public interface Documentable {
  public abstract Document toDocument();
}
