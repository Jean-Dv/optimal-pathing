package co.edu.uptc.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.mongodb.client.MongoClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for {@link MongoClientFactory}.
 *
 * <p>
 * It tests the creation of a new {@link MongoClient} instance and the reuse of an existing one. It
 * also tests the creation of a new {@link MongoClient} instance with a different name.
 * </p>
 */
public class MongoClientFactoryTest {
  public static MongoClient client;

  public MongoClientFactoryTest() {}

  @BeforeClass
  public static void before() {
    MongoClientFactoryTest.client =
        MongoClientFactory.createClient("test", "mongodb://localhost:27017");
  }

  @AfterClass
  public static void after() {
    MongoClientFactoryTest.client.close();
  }

  @Test
  public void createNewClientWithGivenName() {
    MongoClient newClient = MongoClientFactory.createClient("test2", "mongodb://localhost:27017");
    assertTrue(newClient instanceof MongoClient);
    assertNotEquals(newClient, MongoClientFactoryTest.client);
    newClient.close();
  }

  @Test
  public void shouldReturnTheSameClient() {
    MongoClient newClient = MongoClientFactory.createClient("test", "mongodb://localhost:27017");
    assertTrue(newClient instanceof MongoClient);
    assertEquals(newClient, MongoClientFactoryTest.client);
    newClient.close();
  }
}
