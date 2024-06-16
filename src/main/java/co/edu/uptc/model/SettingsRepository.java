package co.edu.uptc.model;

import java.util.List;

/**
 * Interface that represents the repository of settings.
 */
public interface SettingsRepository {
  /**
   * Method that allows to save an setting.
   *
   * @param setting - Settings to be saved.
   */
  void save(Settings setting);

  /**
   * Method that allows to find all the settings.
   *
   * @return List of settings.
   */
  List<Settings> findAll();

  /**
   * Method that allows you to edit an order.
   *
   * @param editSettings - Settings to edit.
   * @return Edited Setttings.
   */
  Settings edit(Settings editSettings);

  /**
   * Method that allows you to delete an order.
   *
   * @param settingId - Settings to delete.
   * @return Deleted setting.
   */
  Settings erase(String settingId);

  /**
   * Method that allows you to find an order by id.
   *
   * @param settingId - Order to setting.
   * @return Find setting.
   */
  Settings findById(String settingId);

}
