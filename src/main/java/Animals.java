import java.util.ArrayList;

public class Animals {
  private static ArrayList<Animals> animalsArray = new ArrayList<Animals>();
  private static ArrayList<Customers> customersArray;

  private int id;
  private String name;
  private String gender;
  private String date_of_admit;
  private String type_of_animal;
  private String breed;
  private int adoption_status;

  public Animals(String animalName, String genderType, String dateOfAdmit, String typeOfAnimal, String breedType, int adoptionStatus){
    name = animalName;
    gender = genderType;
    date_of_admit = dateOfAdmit;
    type_of_animal = typeOfAnimal;
    breed = breedType;
    adoption_status = adoptionStatus;
    animalsArray.add(this);
    id = animalsArray.size();
    customersArray = new ArrayList<Customers>();
  }

  public String getAnimalName() {
    return name;
  }

  public String getGender(){
    return = gender;
  }

  public String getDateOfAdmit(){
    return date_of_admit;
  }

  public String getAnimalType(){
    return = type_of_animal;
  }

  public String getBreedType(){
    return = breed;
  }

  public String getAdoptionStatus(){
    return = adoption_status;
  }

  public int getId() {
    return id;
  }

  public ArrayList<Customers> getCustomer() {
    return customersArray;
  }

  public void addCustomer(Customers customer) {
    customersArray.add(customer);
  }

  public static ArrayList<Animals> allAnimals() {
    return animalsArray;
  }

  @Override
  public boolean equals(Object otherAnimal){
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getAnimalName().equals(newAnimal.getAnimalName()) &&
             this.getGender().equals(newAnimal.getGender()) &&
             this.getDateOfAdmit().equals(newAnimal.getDateOfAdmit()) &&
             this.getAnimalType().equals(newAnimal.getAnimalType()) &&
             this.getBreedType().equals(newAnimal.getBreedType()) &&
             this.getAdoptionStatus().equals(newAnimal.getAdoptionStatus()) &&
             this.getId() == newAnimal.getId();
    }
  }

  public static Animals find (int id) {
    try {
      return animalsArray.get(id - 1);
    } catch (IndexOutOfBoundsException exception) {
      return null;
    }
  }

}
