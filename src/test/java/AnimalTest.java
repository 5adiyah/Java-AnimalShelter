import java.util.ArrayList;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExternalResource;
import java.util.List;

public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // @Before
  //     public void setUp() {
  //       DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animal_shelter_test", null, null);
  //     }
  //
  //   @After
  //   public void tearDown() {
  //     try(Connection con = DB.sql2o.open()) {
  //       String deleteAnimalsQuery = "DELETE FROM animals *;";
  //       String deleteCustomersQuery = "DELETE FROM customers *;";
  //       con.createQuery(deleteAnimalsQuery).executeUpdate();
  //       con.createQuery(deleteCustomersQuery).executeUpdate();
  //     }
  //   }
    @Test
      public void Animals_instantiatesCorrectly_true() {
        Animals myAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 1);
        assertEquals(true, myAnimals instanceof Animals);
      }

    @Test
    public void getAnimalInfo_returnsName_true(){
      Animals boogy = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 1);
      assertEquals("Boogy", boogy.getAnimalName());
      assertEquals("Male", boogy.getGender());
      assertEquals("05-03-16", boogy.getDateOfAdmit());
      assertEquals("Dog", boogy.getAnimalType());
      assertEquals("Corgi", boogy.getBreedType());
      assertEquals(0, boogy.getAdoptionStatus());
      assertEquals(1, boogy.getCustomerId());
    }

  // @Test
  // public void getAnimals_initiallyEmpty(){
  //   assertEquals(Animals.getCustomerId(), 0);
  // }

  @Test
  public void update_updatesCustomerId(){
    Animals myAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 13);
    // Customers myCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
    // myCustomers.save();
    myAnimals.update(14);
    myAnimals.save();
    assertEquals(14, Animals.find(myAnimals.getId().getCustomerId()));
  }

  @Test
   public void equals_returnsTrueIfAnimalsAretheSame() {
   Animals firstAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 1);
   Animals secondAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 1);
   assertTrue(firstAnimals.equals(secondAnimals));
  }

  @Test
    public void save_CheckAnimalsAretheSameInDB() {
    Animals myAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 1);
    myAnimals.save();
    assertTrue(Animals.allAnimals().get(0).equals(myAnimals));
  }

  @Test
  public void save_assignsIdToObject() {
  Animals myAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 1);
  myAnimals.save();
  Animals savedAnimals = Animals.allAnimals().get(0);
  assertEquals(myAnimals.getId(), savedAnimals.getId());
  }

  @Test
  public void find_findsAnimalsInDatabase_true() {
    Animals myAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, 1);
    myAnimals.save();
    Animals savedAnimals = Animals.find(myAnimals.getId());
    assertTrue(myAnimals.equals(savedAnimals));
  }

  @Test
   public void save_savesCustomerIdIntoDB_true() {
   Customers myCustomers = new Customers("Betty Sue", "123-456-7890", "bird", "parakeet", 0, 1);
   myCustomers.save();
   Animals myAnimals = new Animals("Boogy", "Male","05-03-16","Dog","Corgi", 0, myCustomers.getId());
   myAnimals.save();
   Animals savedAnimals = Animals.find(myAnimals.getId());
   assertEquals(savedAnimals.getCustomerId(), myCustomers.getId());
 }
}
