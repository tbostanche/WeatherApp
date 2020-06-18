
public class Weather {
  String id;
  String description;
  int temp;
 
  /**
   * Main constructor for the class
   * @param id              | The weather's id code
   * @param description     | English description of the weather
   */
  public Weather(String id, String description, int temp) {
    this.id = id;
    this.description = description;
  }
  
  public String getID() {
    return id;
  }
  
  public String getDesc() {
    return description;
  }
  
  public int Temp() {
    return temp;
  }
  
}
