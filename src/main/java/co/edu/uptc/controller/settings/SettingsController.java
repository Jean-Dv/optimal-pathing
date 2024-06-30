package co.edu.uptc.controller.settings;


import co.edu.uptc.model.Settings;
import co.edu.uptc.model.SettingsRepository;
import java.util.List;

/**
 * Class that represents the controller for the settings.
 */
public class SettingsController {

  private SettingsRepository repository;

  public SettingsController(SettingsRepository repository) {
    this.repository = repository;
  }

  /**
   * Method that allows to create a new setting.
   *
   * @param setting - setting to be created.
   */
  public void add(Settings setting) {
    this.repository.save(setting);
  }

  /**
   * Method that allows to find all the settings.
   *
   * @return List of settings.
   */
  public List<Settings> getAll() {
    return this.repository.findAll();
  }

  /**
   * Method that allows you to edit an setting.
   *
   * @param editSetting - setting to edit.
   * @return Edited setting.
   */
  public Settings edit(Settings editSetting) {
    return this.repository.edit(editSetting);
  }

  /**
   * Method that allows you to delete an setting.
   *
   * @param settingId - Settings id to delete.
   */
  public Settings delete(String settingId) {
    return this.repository.erase(settingId);
  }

  /**
   * method to obtain a setting by id.
   *
   * @param settingId - Settings id to find.
   */
  public Settings getById(String settingId) {
    return this.repository.findById(settingId);
  }

}
