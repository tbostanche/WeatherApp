import java.util.Locale;


public class Location {
  String id;
  double lat;
  double lon;
  String name;
  String country;
  
  public Location(String id, double lat, double lon, String name, String countryCode) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
    this.name = name;
    
    Locale country = new Locale("", countryCode);
    this.country = country.getDisplayCountry();
  }
  
  public String getID() {
    return id;
  }
  
  public double getLat() {
    return lat;
  }
  
  public double getLon() {
    return lon;
  }
  
  public String getName() {
    return name;
  }
  
  public String getCountry() {
    return country;
  }
  
}
