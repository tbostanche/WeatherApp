import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LocationManager {

  private HashMap<String, Location> locations;
  private HttpClient client;
  private final String key;
  private static final String CITY_FILE_PATH = "city.list.json";

  /**
   * No-args constructor
   */
  public LocationManager() {
    client = HttpClient.newHttpClient();
    key = Secure.APIKEY; // Stores API key, supplement with your own if you wish to use this program
    locations = new HashMap<String, Location>();
    try {
      buildTable();
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    } catch (IOException e2) {
      e2.printStackTrace();
    } catch (ParseException e3) {
      e3.printStackTrace();
    }
  }
  
  private void buildTable() throws FileNotFoundException, IOException, ParseException {
    Object obj = new JSONParser().parse(new FileReader("city.list.json")); 
    JSONArray cityArray = (JSONArray) obj;
    
    for (Object object : cityArray) {
      JSONObject location = (JSONObject) object;
      String id =  location.get("id").toString();
      JSONObject coord = (JSONObject) location.get("coord");
      String lon = coord.get("lon").toString();
      String lat = coord.get("lat").toString();
      String name = (String) location.get("name");
      String state = (String) location.get("state");
      String country = (String) location.get("country");
      
      Location locationObj = new Location(id, Double.parseDouble(lat), Double.parseDouble(lon), name, state, country);
      locations.put(locationObj.toString(), locationObj);
      
    }
  }
  
  
  public Set<String> getAllLocations() {
    return locations.keySet();
  }
  
  public Location getLocation(String name) throws IllegalArgumentException {
    if (!locations.containsKey(name)) {
      throw new IllegalArgumentException();
    }
    
    return locations.get(name);
  }

  /**
   * Returns the current weather of a given city in the form of a Weather object
   * 
   * @param city | City to gather the weather data of
   * @return | A Weather object
   * @throws IOException
   * @throws InterruptedException
   * @throws ParseException
   */
  public Weather getWeatherData(String city)
      throws IOException, InterruptedException, ParseException {
    // Convert the city name into its ID number
    Location location = locations.get(city);
    String idCity = location.getID();

    // Send the request to the OpenWeatherMapAPI and recieve its response
    String requestURL =
        "http://api.openweathermap.org/data/2.5/weather?id=" + idCity + "&appid=" + key;
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestURL)).build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    // Parses the returned JSON
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(response.body());
    JSONArray weatherArray = (JSONArray) json.get("weather");
    JSONObject weatherData = (JSONObject) weatherArray.get(0);
    String idWeather = weatherData.get("id").toString();
    String description = (String) weatherData.get("description");
    JSONObject main = (JSONObject) json.get("main");
    double tempKelvin = (double)main.get("temp");

    
    return new Weather(idWeather, description, tempKelvin);
  }



}
