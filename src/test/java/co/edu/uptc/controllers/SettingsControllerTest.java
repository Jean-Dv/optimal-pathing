package co.edu.uptc.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import co.edu.uptc.controller.settings.SettingsController;
import co.edu.uptc.infrastructure.settings.InMemorySettingsRepository;
import co.edu.uptc.model.Settings;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Order Controller.
 */
public class SettingsControllerTest {
  public SettingsController controller;


  /**
   * Setup method.
   */
  @Before
  public void before() {
    SettingsController controller = new SettingsController(new InMemorySettingsRepository());
    this.controller = controller;
  }

  @Test
  public void testAddNewSetting() {

    try {
      controller.add(new Settings(UUID.randomUUID(), "Calle 3 # 24 - 43"));
    } catch (Exception e) {
      fail("Error adding new setting.");
    }
  }

  @Test
  public void testGetAllSettings() {
    try {
      List<Settings> settings = controller.getAll();
      assertEquals(0, settings.size());
    } catch (Exception e) {
      fail("Error getting all orders.");
    }
  }

  @Test
  public void testEditSetting() {
    try {
      Settings setting = new Settings(UUID.randomUUID(), "Calle 6 # 25 - 9");
      // create an setting
      controller.add(setting);

      Settings settingEdit = new Settings(setting.getId(), "calle 20 # 56 - 10");

      assertEquals(settingEdit, controller.edit(settingEdit));
      assertEquals(settingEdit.getSourceAddress(), controller.edit(settingEdit).getSourceAddress());

    } catch (Exception e) {
      fail("Error getting all orders.");
    }
  }

  @Test
  public void testDeleteSetting() {
    try {

      Settings setting = new Settings(UUID.randomUUID(), "Calle 6 # 25 - 9");

      controller.add(setting);

      assertEquals(setting, controller.delete(setting.getId().toString()));
      assertNull(controller.delete(setting.getId().toString()));
    } catch (Exception e) {
      fail("Error deleting order.");
    }

  }

}
