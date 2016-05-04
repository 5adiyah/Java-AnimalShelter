import java.util.ArrayList;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class CustomerTest {
  // @Rule
  // public DatabaseRule database = new DatabaseRule();

  @Before
    public void setUp() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animal_shelter_test", null, null);
    }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteAnimalsQuery = "DELETE FROM animals *;";
      String deleteCustomersQuery = "DELETE FROM customers *;";
      con.createQuery(deleteAnimalsQuery).executeUpdate();
      con.createQuery(deleteCustomersQuery).executeUpdate();
    }
  }

  @Test
  public void Customers_instantiatesCorrectly_true() {
    Customers myCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
    assertEquals(true, myCustomers instanceof Customers);
  }

  @Test
  public void getCustomerInfo_returnsName_true(){
    Customers bettySue = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
    assertEquals("Betty Sue", bettySue.getCustomerName());
    assertEquals("123-456-7890", bettySue.getPhone());
    assertEquals("bird", bettySue.getAnimalPreference());
    assertEquals("parakeet", bettySue.getBreedPreference());
    assertEquals(0, bettySue.checkAdoptionStatus());
    assertEquals(1, bettySue.getAnimalId());
  }

  @Test
  public void getCustomers_initiallyEmpty(){
    assertEquals(Customers.allCustomers().size(), 0);
  }

  @Test
   public void equals_returnsTrueIfCustomersAretheSame() {
   Customers firstCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
   Customers secondCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
   assertTrue(firstCustomers.equals(secondCustomers));
  }

  @Test
    public void save_CheckCustomersAretheSameInDB() {
    Customers myCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
    myCustomers.save();
    assertTrue(Customers.allCustomers().get(0).equals(myCustomers));
  }

  @Test
    public void save_assignsIdToObject() {
    Customers myCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
    myCustomers.save();
    Customers savedCustomers = Customers.allCustomers().get(0);
    assertEquals(myCustomers.getId(), savedCustomers.getId());
  }

  @Test
  public void find_findsCustomersInDatabase_true() {
    Customers myCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
    myCustomers.save();
    Customers savedCustomers = Customers.find(myCustomers.getId());
    assertTrue(myCustomers.equals(savedCustomers));
  }

  @Test
   public void save_savesCustomerIdIntoDB_true() {
   Animals myAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 1);
   myAnimals.save();
   Customers myCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, myAnimals.getId());
   myCustomers.save();
   Customers savedCustomers = Customers.find(myCustomers.getId());
   assertEquals(savedCustomers.getAnimalId(), myAnimals.getId());
 }

}
