package co.edu.uptc.model;

import java.util.List;

/**
 * Interface that represents the repository of reponsible.
 */
public interface ResponsibleRepository {



  /**
   * Method that allows to save an responsible.
   *
   * @param responsible - Responsible to be saved.
   */
  void save(Responsible responsible);

  /**
   * Method that allows to find all the responsible.
   *
   * @return List of responsible.
   */
  List<Responsible> findAll();

  /**
   * Method that allows you to edit an responsible.
   *
   * @param editResponsible - Responsible to edit.
   * @return Edited responisble.
   */
  Responsible edit(Responsible editResponsible);

  /**
   * Method that allows you to delete an order.
   *
   * @param responsibleId - responsable to delete.
   * @return Deleted responsable.
   */
  Responsible erase(String responsibleId);

  /**
   * Method that allows you to find an responsible by id.
   *
   * @param responsibleId - Order to setting.
   * @return Find responsible.
   */
  Responsible findById(String responsibleId);
}
