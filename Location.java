import java.util.Locale;


public class Location {
  String id;
  double lat;
  double lon;
  String name;
  String country;

  /**
   * Main constructor for the class
   * @param id              | The location's ID number
   * @param lat             | The location's latitude
   * @param lon             | The location's longitude
   * @param name            | The location's common name (Most likely English)
   * @param countryCode     | The country code for the country the location resides in.
   */
  public Location(String id, double lat, double lon, String name, String countryCode) {
    this.id = id;
    this.lat = lat;
    this.lon = lon;
    this.name = name;
    
    // Converts the country code into the country's English name
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
