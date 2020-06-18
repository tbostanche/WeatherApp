
public class Weather {
  String id;
  String description;
  double temp;
 
  /**
   * Main constructor for the class
   * @param id              | The weather's id code
   * @param description     | English description of the weather
   */
  public Weather(String id, String description, double temp) {
    this.id = id;
    this.description = description;
    this.temp = kelvinToFarenheit(temp);
  }
  
  private double kelvinToFarenheit(double tempKelvin) {
    double tempFarenheit = (((tempKelvin - 273.15) * 9/5) + 32);
    return Math.floor(tempFarenheit);
  }
  
  public String getID() {
    return id;
  }
  
  public String getDesc() {
    return description;
  }
  
  public double Temp() {
    return temp;
  }
  
}
