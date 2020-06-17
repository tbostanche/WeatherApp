import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LocationManager {
  // HM is temporary, will replace with a Trie in final application
  private HashMap<String, Location> locations;
  private HttpClient client;
  private final String key;

  /**
   * No-args constructor
   */
  public LocationManager() {
    client = HttpClient.newHttpClient();
    key = Secure.APIKEY; // Stores API key, supplement with your own if you wish to use this program
    locations = new HashMap<String, Location>();

    // This is for testing, final product will be a Trie
    locations.put("Chicago", new Location("4887398", 41.850029, -87.650047, "Chicago", "US"));
    locations.put("London", new Location("2643743", 51.50853, -0.12574, "London", "GB"));
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


    return new Weather(idWeather, description);
  }



}
