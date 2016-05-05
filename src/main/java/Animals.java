import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Animals {
  private int id;
  private String name;
  private String gender;
  private String date_of_admit;
  private String type_of_animal;
  private String breed;
  private int adoption_status;
  private int customerid;

  public Animals(String name, String gender, String date_of_admit, String type_of_animal, String breed, int adoption_status, int customerid){
    this.customerid = customerid;
    this.name = name;
    this.gender = gender;
    this.date_of_admit = date_of_admit;
    this.type_of_animal = type_of_animal;
    this.breed = breed;
    this.adoption_status = adoption_status;
  }

  public String getAnimalName() {
    return name;
  }

  public String getGender(){
    return gender;
  }

  public String getDateOfAdmit(){
    return date_of_admit;
  }

  public String getAnimalType(){
    return type_of_animal;
  }

  public String getBreedType(){
    return breed;
  }

  public int getAdoptionStatus(){
    return adoption_status;
  }

  public int getId() {
    return id;
  }

  public int getCustomerId() {
    return customerid;
  }

  public void update(int newCustomerId) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET customerid = :customerid WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("customerid", newCustomerId)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }



  public static List<Animals> allAnimals() {
    String sql = "SELECT id, name, gender, date_of_admit, type_of_animal, breed, adoption_status, customerid FROM animals";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Animals.class);
    }
  }


  @Override
  public boolean equals(Object otherAnimal){
    if (!(otherAnimal instanceof Animals)) {
      return false;
    } else {
      Animals newAnimal = (Animals) otherAnimal;
      return this.getAnimalName().equals(newAnimal.getAnimalName()) &&
             this.getGender().equals(newAnimal.getGender()) &&
             this.getDateOfAdmit().equals(newAnimal.getDateOfAdmit()) &&
             this.getAnimalType().equals(newAnimal.getAnimalType()) &&
             this.getBreedType().equals(newAnimal.getBreedType()) &&
             this.getAdoptionStatus() == (newAnimal.getAdoptionStatus()) &&
             this.getCustomerId() == (newAnimal.getCustomerId()) &&
             this.getId() == newAnimal.getId();
    }
  }

    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO Animals(name, gender, date_of_admit, type_of_animal, breed, adoption_status, customerid) VALUES (:name, :gender, :date_of_admit, :type_of_animal, :breed, :adoption_status, :customerid)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .addParameter("gender", this.gender)
          .addParameter("date_of_admit", this.date_of_admit)
          .addParameter("type_of_animal", this.type_of_animal)
          .addParameter("breed", this.breed)
          .addParameter("adoption_status", this.adoption_status)
          .addParameter("customerid", this.customerid)
          .executeUpdate()
          .getKey();
      }
    }

    public static Animals find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM Animals where id=:id";
        Animals animals = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Animals.class);
        return animals;
      }
    }
    public List<Customers> getCustomers() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM Customers where id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Customers.class);
    }
  }

}
