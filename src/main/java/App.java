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

    get("/customers", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/customers.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/customers/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-customer-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("allAnimals", Animals.allAnimals());
      model.put("template", "templates/animals.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/animals/:id/newCustomer", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String customerName = request.queryParams("name");
      String phone = request.queryParams("phone");
      String preference = request.queryParams("preference");
      String breedPreference = request.queryParams("breedPreference");
      int adoptionStatus = Integer.parseInt(request.queryParams("newAdoptionStatus"));
      //Animals findAnimal = Animals.find(request.params(":id"));
      //animalId = Integer.parseInt(animalId);
      Customers newCustomers = new Customers(customerName, phone, preference, breedPreference, adoptionStatus, 3 );//change
      newCustomers.save();


      model.put("customers", newCustomers);
      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/animals/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Integer individualAnimalId = Integer.parseInt(request.params(":id"));

      Animals individualAnimal = Animals.find(individualAnimalId);
      model.put("individualAnimal", individualAnimal);

      model.put("template", "templates/animal.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());





    // get("/input-page", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //
    //   String userInput = request.queryParams("inputName");
    //
    //   App newApp = new App();
    //
    //   String varName = newApp.methodName(userInput);
    //   model.put("varName", varName);
    //
    //   model.put("template", "templates/input-page.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
  }
}
