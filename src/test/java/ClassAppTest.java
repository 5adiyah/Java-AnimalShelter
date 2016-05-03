import org.junit.*;
import static org.junit.Assert.*;

public class AppTest {

  @Test
  public void methodWeAreTesting_behaviorWeAreTesting_expectedResult() {
    MainClassName testMainClassName = new MainClassName();
    String expectedResult = "This" ;
    assertEquals(expectedResult, testMainClassName.methodToTest("test input"));
  }
}
