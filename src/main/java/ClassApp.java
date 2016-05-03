import java.util.ArrayList;


public class ClassApp{
  private String mVariable;
  private Integer mIntVar;
  private static ArrayList<ClassApp> classApp = new ArrayList<ClassApp>();

  public ConstructorApp(String variable){
    mVariable = variable;
  }
  public String getVariable(){
    return mVariable;
  }
}
