import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Noah's Ark");
  }

  @Test
  public void goToCustomerSide() {
    goTo("http://localhost:4567/");
    click("a", withText("I Want to Adopt!"));
    assertThat(pageSource()).contains("Welcome To Noah's Ark Animal Shelter!");
  }

  @Test
  public void goToNewCustomerForm(){
    goTo("http://localhost:4567/");
    click("a", withText("I Want to Adopt!"));
    click("a", withText("I am a new Customer"));
    assertThat(pageSource()).contains("Name");
  }
  @Test
  public void fillNewCustomerForm(){
    goTo("http://localhost:4567/");
    click("a", withText("I Want to Adopt!"));
    click("a", withText("I am a new Customer"));
    fill("#name").with("Timmy");
    fill("#phone").with("9675309");
    fillSelect("#preference").withValue("Dogs");
    submit(".btn");
    fillSelect("#dogbreeds").withValue("Pitbull");
    submit(".btn");
    assertThat(pageSource()).contains("Thanks for boarding the ark!");
  }

  @Test
  public void makeSureCustomerCreated(){
    goTo("http://localhost:4567/");
    click("a", withText("I Want to Adopt!"));
    click("a", withText("I am a new Customer"));
    fill("#name").with("Timmy");
    fill("#phone").with("9675309");
    fillSelect("#preference").withValue("Dogs");
    submit(".btn");
    fillSelect("#dogbreeds").withValue("Pitbull");
    submit(".btn");
    click("a", withText("Click Here"));
    assertThat(pageSource()).contains("Timmy");
  }

  @Test
  public void makeSureCustomerInfoPopulated(){
    goTo("http://localhost:4567/customer/new");
    fill("#name").with("Bob");
    fill("#phone").with("2222222222");
    fillSelect("#preference").withValue("Cats");
    submit(".btn");
    fillSelect("#catbreeds").withValue("Tabby");
    submit(".btn");
    click("a", withText("Click Here"));
    click("a", withText("Bob"));
    assertThat(pageSource()).contains("Tabby");
  }
}
