package co.edu.uptc.infrastructure.responsible;


import co.edu.uptc.infrastructure.MongoRepository;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.Responsible;
import co.edu.uptc.model.ResponsibleRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * Class that represents the repository of responsible in memory.
 */
public class MongoResponsibleRepository extends MongoRepository<Responsible>
    implements ResponsibleRepository {

  public MongoResponsibleRepository(MongoClient client) {
    super(client);
  }

  @Override
  public void save(Responsible responsible) {
    this.persist(responsible.getId().toString(), responsible);
  }

  @Override
  public List<Responsible> findAll() {
    FindIterable<Document> documents = this.searchAll();
    ArrayList<Responsible> responsible = new ArrayList<>();
    for (Document document : documents) {
      responsible.add(Responsible.fromDocument(document));
    }
    return responsible;
  }

  @Override
  public Responsible edit(Responsible editResponsible) {
    Document responsibleDocument =
        this.persist(editResponsible.getId().toString(), editResponsible);
    return responsibleDocument != null ? Responsible.fromDocument(responsibleDocument) : null;
  }

  @Override
  public Responsible erase(String responsibleId) {
    Document responsibleDocument = this.remove(responsibleId);
    return responsibleDocument != null ? Responsible.fromDocument(responsibleDocument) : null;
  }

  @Override
  public Responsible findById(String responsibleId) {
    Document responsibleDocument = this.searchById(responsibleId);
    return responsibleDocument != null ? Responsible.fromDocument(responsibleDocument) : null;
  }

  @Override
  protected String collectionName() {
    return "responsible";
  }


}
