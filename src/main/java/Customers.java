import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Customers{
  private int id;
  private String name;
  private String phone;
  private String animal_preference;
  private String breed_preference;
  private int adoption_status;
  private int animalid;

  public Customers(String name, String phone, String animal_preference, String breed_preference, int adoption_status, int animalid){
    this.name = name;
    this.phone = phone;
    this.animal_preference = animal_preference;
    this.breed_preference = breed_preference;
    this.adoption_status = adoption_status;
    this.animalid = animalid;
  }

  public String getCustomerName(){
    return name;
  }
  public String getPhone(){
    return phone;
  }
  public String getAnimalPreference() {
    return animal_preference;
  }
  public String getBreedPreference() {
    return breed_preference;
  }
  public int checkAdoptionStatus(){
    return adoption_status;
  }
  public int getAnimalId() {
    return animalid;
  }

  public int getId(){
    return id;
  }

  public void update(int newAnimalId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE customers SET animalid = :animalid WHERE id = :id";
      con.createQuery(sql)
        .addParameter("animalid", newAnimalId)
        .addParameter("id", this.id)
        .executeUpdate();
    }
}

  public String checkBreedPreference(String userInput){
    if(userInput.equals("Dogs")){
      return "dogbreeds";
    } else if(userInput.equals("Cats")){
      return "catbreeds";
    } else if(userInput.equals("Bunnies")){
      return "bunnybreeds";
    } else if(userInput.equals("Chimera")){
      return "chimerabreeds";
    } else {
      return "We don't have that breed";
    }
  }

  public static List<Customers> allCustomers() {
    String sql = "SELECT id, name, phone, animal_preference, breed_preference, adoption_status, animalid FROM customers";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Customers.class);
    }
  }

  @Override
  public boolean equals(Object otherCustomer){
    if (!(otherCustomer instanceof Customers)) {
      return false;
    } else {
      Customers newCustomer = (Customers) otherCustomer;
      return this.getCustomerName().equals(newCustomer.getCustomerName()) &&
             this.getPhone().equals(newCustomer.getPhone()) &&
             this.getAnimalPreference().equals(newCustomer.getAnimalPreference()) &&
             this.getBreedPreference().equals(newCustomer.getBreedPreference()) &&
             this.checkAdoptionStatus()== (newCustomer.checkAdoptionStatus()) &&
             this.getAnimalId() == (newCustomer.getAnimalId()) &&
             this.getId() == newCustomer.getId();
    }
  }
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Customers(name, phone, animal_preference, breed_preference, adoption_status, animalid) VALUES (:name, :phone, :animal_preference, :breed_preference, :adoption_status, :animalid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("phone", this.phone)
        .addParameter("animal_preference", this.animal_preference)
        .addParameter("breed_preference", this.breed_preference)
        .addParameter("adoption_status", this.adoption_status)
        .addParameter("animalid", this.animalid)
        .executeUpdate()
        .getKey();
    }
  }
  public static Customers find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Customers where id=:id";
      Customers customers = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Customers.class);
      return customers;
    }
  }
  public List<Animals> getAnimals() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM Animals where id=:id";
    return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Animals.class);
  }
}

}
