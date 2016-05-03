import java.util.ArrayList;


public class Customer{
  private int id;
  private String name;
  private String phone;
  private String animal_preference;
  private String breed_preference;
  private int adoption_status;

  public Animals(String animalName, String phoneNumber, String animalPreference, String breedPreference, String adoptionStatus){
    name = animalName;
    phone = phoneNumber;
    animal_preference = animalPreference;
    breed_preference = breedPreference;
    adoption_status = adoptionStatus;
    animalsArray.add(this);
    id = animalsArray.size();
  }

  public ConstructorApp(String variable){
    mVariable = variable;
  }
  public String getVariable(){
    return mVariable;
  }
}
