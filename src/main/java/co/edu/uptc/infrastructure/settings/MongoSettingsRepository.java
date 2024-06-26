package co.edu.uptc.infrastructure.settings;



import co.edu.uptc.infrastructure.MongoRepository;
import co.edu.uptc.model.Settings;
import co.edu.uptc.model.SettingsRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * Class that represents the repository of settings in MongoDB.
 */
public class MongoSettingsRepository extends MongoRepository<Settings>
    implements SettingsRepository {

  public MongoSettingsRepository(MongoClient client) {
    super(client);
  }

  @Override
  public void save(Settings setting) {
    this.persist(setting.getId().toString(), setting);
  }

  @Override
  public List<Settings> findAll() {
    FindIterable<Document> documents = this.searchAll();
    ArrayList<Settings> settings = new ArrayList<>();
    for (Document document : documents) {
      settings.add(Settings.fromDocument(document));
    }
    return settings;
  }

  @Override
  public Settings edit(Settings editSetting) {
    Document settingDocument = this.persist(editSetting.getId().toString(), editSetting);
    return settingDocument != null ? Settings.fromDocument(settingDocument) : null;
  }

  @Override
  public Settings erase(String settingId) {
    Document settingDocument = this.remove(settingId);
    return settingDocument != null ? Settings.fromDocument(settingDocument) : null;
  }

  @Override
  public Settings findById(String settingId) {
    Document settingDocument = this.searchById(settingId);
    return settingDocument != null ? Settings.fromDocument(settingDocument) : null;
  }

  @Override
  protected String collectionName() {
    return "settings";
  }
}
