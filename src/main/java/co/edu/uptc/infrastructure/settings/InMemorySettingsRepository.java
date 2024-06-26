package co.edu.uptc.infrastructure.settings;

import co.edu.uptc.model.Settings;
import co.edu.uptc.model.SettingsRepository;
import java.util.ArrayList;


/**
 * Class that represents the repository of orders in memory.
 */
public class InMemorySettingsRepository implements SettingsRepository {
  private ArrayList<Settings> settings;


  public InMemorySettingsRepository() {
    this.settings = new ArrayList<>();
  }

  @Override
  public void save(Settings setting) {
    this.settings.add(setting);
  }

  @Override
  public ArrayList<Settings> findAll() {
    return this.settings;
  }

  @Override
  public Settings edit(Settings editSettings) {
    for (Settings setting : settings) {
      if (setting.getId().toString().equals(editSettings.getId().toString())) {
        setting = editSettings;
        return setting;
      }

    }
    return null;
  }

  @Override
  public Settings erase(String settingId) {

    for (Settings setting : settings) {
      if (setting.getId().toString().equals(settingId)) {
        settings.remove(setting);
        return setting;
      }
    }
    return null;
  }

  @Override
  public Settings findById(String settingId) {
    return this.settings.stream().filter(order -> order.getId().toString().equals(settingId))
        .findFirst().orElse(null);
  }

}
