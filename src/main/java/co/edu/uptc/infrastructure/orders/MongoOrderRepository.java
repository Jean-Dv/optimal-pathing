package co.edu.uptc.infrastructure.orders;

import co.edu.uptc.infrastructure.MongoRepository;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.OrderRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import java.util.ArrayList;
import org.bson.Document;

/**
 * Class that represents the repository of orders in MongoDB.
 */
public class MongoOrderRepository extends MongoRepository<Order> implements OrderRepository {
  public MongoOrderRepository(MongoClient client) {
    super(client);
  }

  @Override
  public void save(Order order) {
    this.persist(order.getId().toString(), order);
  }

  @Override
  public Order edit(Order editOrder) {
    Document orderDocument = this.persist(editOrder.getId().toString(), editOrder);
    return orderDocument != null ? Order.fromDocument(orderDocument) : null;
  }

  @Override
  public ArrayList<Order> findAll() {
    FindIterable<Document> documents = this.searchAll();
    ArrayList<Order> orders = new ArrayList<>();
    for (Document document : documents) {
      orders.add(Order.fromDocument(document));
    }
    return orders;
  }

  @Override
  public Order erase(String id) {
    Document orderDocument = this.remove(id);
    return orderDocument != null ? Order.fromDocument(orderDocument) : null;
  }

  @Override
  public Order findById(String id) {
    Document orderDocument = this.searchById(id);
    return orderDocument != null ? Order.fromDocument(orderDocument) : null;
  }

  protected String collectionName() {
    return "orders";
  }
}
