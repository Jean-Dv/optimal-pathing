package co.edu.uptc.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import co.edu.uptc.controller.responsible.ResponsibleController;
import co.edu.uptc.infrastructure.responsible.InMemoryResponsibleRepository;
import co.edu.uptc.model.Responsible;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

/**
 * Test responsible Controller.
 */
public class ResponsibleControllerTest {

  private ResponsibleController controller;


  /**
   * Setup method.
   */
  @Before
  public void before() {
    ResponsibleController controller =
        new ResponsibleController(new InMemoryResponsibleRepository());
    this.controller = controller;

  }

  @Test
  public void testAddNewResponsible() {
    try {
      controller.add(new Responsible(UUID.randomUUID(), "juan", "4565313130", "31031484020",
          "juferi2003@gmail.com"));
    } catch (Exception e) {
      fail("Error adding new responsible.");
    }
  }

  @Test
  public void testGetAllResponsible() {
    try {
      List<Responsible> responsible = controller.getAll();
      assertEquals(0, responsible.size());

    } catch (Exception e) {
      fail("Error getting all responsible.");
    }
  }

  @Test
  public void testEditResponsible() {
    try {

      Responsible responsible = new Responsible(UUID.randomUUID(), "juan", "4565313130",
          "31031484020", "juferi2003@gmail.com");
      // create an responsible
      controller.add(responsible);

      // edited responsible with email
      Responsible responsibleEdit = new Responsible(responsible.getId(), "juan", "4565313130",
          "31031484020", "juan.fernandez07@uptc.edu.co");

      assertEquals(responsibleEdit, controller.edit(responsibleEdit));
      assertEquals(responsibleEdit.getEmail(), controller.edit(responsibleEdit).getEmail());
      responsibleEdit.setId(UUID.randomUUID());
      assertNull(controller.edit(responsibleEdit));

    } catch (Exception e) {
      fail("Error getting all responsible.");
    }
  }

  @Test
  public void testDeleteResponsible() {
    try {

      Responsible responsible = new Responsible(UUID.randomUUID(), "juan", "4565313130",
          "31031484020", "juferi2003@gmail.com");
      controller.add(responsible);

      assertEquals(responsible, controller.delete(responsible.getId().toString()));
      assertNull(controller.delete(responsible.getId().toString()));
    } catch (Exception e) {
      fail("Error deleting responsible.");
    }

  }

  @Test
  public void testFindById() {
    try {
      // Crear un nuevo responsable
      Responsible responsible = new Responsible(UUID.randomUUID(), "juan", "4565313130",
          "31031484020", "juferi2003@gmail.com");

      controller.add(responsible);
      Responsible foundResponsible = controller.getById(responsible.getId().toString());

      assertEquals(responsible, foundResponsible);
      assertNull(controller.getById(UUID.randomUUID().toString()));
    } catch (Exception e) {
      fail("Error finding responsible by id.");
    }
  }

}
