package co.edu.uptc.infrastructure.responsible;

import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.ResponsibleRepository;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents the repository of responsibles in MongoDB.
 */
public class InMemoryResponsibleRepository implements ResponsibleRepository {

  private ArrayList<Responsible> responsibles;

  public InMemoryResponsibleRepository() {
    this.responsibles = new ArrayList<>();
  }

  @Override
  public void save(Responsible responsible) {
    this.responsibles.add(responsible);
  }

  @Override
  public List<Responsible> findAll() {

    return this.responsibles;
  }

  @Override
  public Responsible edit(Responsible editResponsible) {

    for (Responsible responsible : responsibles) {
      if (responsible.getId().toString().equals(editResponsible.getId().toString())) {
        responsible = editResponsible;
        return responsible;
      }
    }

    return null;
  }

  @Override
  public Responsible erase(String responsibleId) {
    for (Responsible responsible : responsibles) {

      if (responsible.getId().toString().equals(responsibleId)) {
        responsibles.remove(responsible);
        return responsible;
      }
    }
    return null;
  }

  @Override
  public Responsible findById(String responsibleId) {
    return this.responsibles.stream()
        .filter(responsible -> responsible.getId().toString().equals(responsibleId)).findFirst()
        .orElse(null);
  }

}
