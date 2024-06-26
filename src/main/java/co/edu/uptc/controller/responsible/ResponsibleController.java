package co.edu.uptc.controller.responsible;


import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.ResponsibleRepository;
import java.util.List;

/**
 * Class that represents the controller for the order.
 */
public class ResponsibleController {

  private ResponsibleRepository repository;

  public ResponsibleController(ResponsibleRepository repository) {
    this.repository = repository;
  }

  /**
   * Method that allows to create a new responsible.
   *
   * @param responsible - Responsible to be created.
   */
  public void add(Responsible responsible) {
    this.repository.save(responsible);
  }

  /**
   * Method that allows to find all the responsible.
   *
   * @return List of responsible.
   */
  public List<Responsible> getAll() {
    return this.repository.findAll();
  }

  /**
   * Method that allows you to edit an responsible.
   *
   * @param editResponsible - Responsible to edit.
   * @return Edited responsible.
   */
  public Responsible edit(Responsible editResponsible) {
    return this.repository.edit(editResponsible);
  }

  /**
   * Method that allows you to delete an responsible.
   *
   * @param responsibleId - Responsible id to delete.
   */
  public Responsible delete(String responsibleId) {
    return this.repository.erase(responsibleId);
  }

  /**
   * Method that allows you to find an responsible by id.
   *
   * @param id - Responsible id to find.
   */
  public Responsible getById(String id) {
    return this.repository.findById(id);
  }


}
