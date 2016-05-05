import java.util.*;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    //
    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/employee-view", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/employee-view.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/customer-type", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/customer-type.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    get("/customers/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-customer-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-pet-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    post("/customers", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String customerName = request.queryParams("name");
      String phone = request.queryParams("phone");
      String preference = request.queryParams("preference");
      model.put("customerName", customerName);
      model.put("phone", phone);
      model.put("preference", preference);
      model.put("template", "templates/breed-choice.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String animalName = request.queryParams("animalName");
      String gender = request.queryParams("gender");
      String admitDate = request.queryParams("admitDate");
      String type = request.queryParams("type");
      model.put("animalName", animalName);
      model.put("gender", gender);
      model.put("admitDate", admitDate);
      model.put("type", type);
      model.put("template", "templates/breed-type.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    post("/customers2", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String customerName = request.queryParams("customerName");
      String phone = request.queryParams("phone");
      String preference = request.queryParams("preference");
      String breedPreference = request.queryParams("breedPreference");
      int adoptionStatus = 0;
      // int newCustomerId = Integer.parseInt(request.queryParams("newCustomerId"));
      //Animals findAnimal = Animals.find(request.params(":id"));
      //animalId = Integer.parseInt(animalId);
      Customers newCustomer = new Customers(customerName, phone, preference, breedPreference, adoptionStatus, 3 );//change
      newCustomer.save();
      model.put("customer", newCustomer);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals2", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String animalName = request.queryParams("animalName");
      String gender = request.queryParams("gender");
      String type = request.queryParams("type");
      String admitDate = request.queryParams("admitDate");
      String breedType = request.queryParams("breedType");
      int adoptionStatus = 0;
      // int newCustomerId = Integer.parseInt(request.queryParams("newCustomerId"));
      //Animals findAnimal = Animals.find(request.params(":id"));
      //animalId = Integer.parseInt(animalId);
      Animals newAnimal = new Animals(animalName, gender, admitDate, type, breedType, adoptionStatus, 3 );//change
      newAnimal.save();
      model.put("animal", newAnimal);
      model.put("template", "templates/admin-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    get("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("allAnimals", Animals.allAnimals());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/view", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("allAnimals", Animals.allAnimals());
      model.put("template", "templates/view-animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/customers", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("allCustomers", Customers.allCustomers());
      model.put("template", "templates/customers.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/customers/view", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("allCustomers", Customers.allCustomers());
      model.put("template", "templates/view-customers.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());



    get("/customer/:cId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Integer individualCustomerId = Integer.parseInt(request.params(":cId"));
      Customers individualCustomer = Customers.find(individualCustomerId);


      model.put("individualCustomer", individualCustomer);
      model.put("template", "templates/customer.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/customer/view/:cId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Integer individualCustomerId = Integer.parseInt(request.params(":cId"));
      Customers individualCustomer = Customers.find(individualCustomerId);


      model.put("individualCustomer", individualCustomer);
      model.put("template", "templates/view-customer.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/customer/:cId/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Integer individualCustomerId = Integer.parseInt(request.params(":cId"));
      Customers individualCustomer = Customers.find(individualCustomerId);

      model.put("individualCustomer", individualCustomer);
      model.put("allAnimals", Animals.allAnimals());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/customer/:cId/animals/:aId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Integer individualCustomerId = Integer.parseInt(request.params(":cId"));
      Customers individualCustomer = Customers.find(individualCustomerId);
      Integer individualAnimalId = Integer.parseInt(request.params(":aId"));
      Animals individualAnimal = Animals.find(individualAnimalId);

      model.put("individualCustomer", individualCustomer);
      model.put("individualAnimal", individualAnimal);
      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/view/:aId", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Integer individualAnimalId = Integer.parseInt(request.params(":aId"));
      Animals individualAnimal = Animals.find(individualAnimalId);

      model.put("individualAnimal", individualAnimal);
      model.put("template", "templates/view-animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/customer/:cId/animals/:aId/adopt", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Integer individualCustomerId = Integer.parseInt(request.params(":cId"));
      Customers individualCustomer = Customers.find(individualCustomerId);
      Integer individualAnimalId = Integer.parseInt(request.params(":aId"));
      Animals individualAnimal = Animals.find(individualAnimalId);

      model.put("allAnimals", Animals.allAnimals());
      model.put("individualCustomer",individualCustomer);
      model.put("individualAnimal", individualAnimal);
      model.put("template", "templates/adopt.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/customer/:cId/animals/:aId/adopt", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Integer individualCustomerId = Integer.parseInt(request.params(":cId"));
      Customers individualCustomer = Customers.find(individualCustomerId);
      Integer individualAnimalId = Integer.parseInt(request.params(":aId"));
      Animals individualAnimal = Animals.find(individualAnimalId);

      individualAnimal.update(individualCustomerId);
      individualCustomer.update(individualAnimalId);

      model.put("allAnimals", Animals.allAnimals());
      model.put("individualCustomer",individualCustomer);
      model.put("individualAnimal", individualAnimal);
      model.put("template", "templates/adopt.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
