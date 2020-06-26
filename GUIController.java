import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import org.controlsfx.control.textfield.TextFields;
import org.json.simple.parser.ParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GUIController {
  private LocationManager lm;

  @FXML
  private Label currentTemp;

  @FXML
  private Label currentWeather;

  @FXML
  private Label currentLong;

  @FXML
  private Label currentLat;
  
  @FXML
  private TextField citySearchBar;


  public GUIController() {
  }

  public void initialize() {
    lm = new LocationManager();
    ArrayList<String> cities = new ArrayList<String>();
    


    for (String location : lm.getAllLocations()) {
      cities.add(location);
    }
    Collections.sort(cities);
    TextFields.bindAutoCompletion(citySearchBar, cities);
  }

  public void updateWeather(ActionEvent event) {
    String citySelected = citySearchBar.getText();
    try {
      Location selected = lm.getLocation(citySelected);
      Weather selectedData = lm.getWeatherData(citySelected);
      
      currentTemp.setText("Current Temperature: " + selectedData.getTemp());
      currentWeather.setText("Current Weather: " + selectedData.getDesc());
      currentLat.setText("City's Latitude: " + selected.getLat());
      currentLong.setText("City's Longitude: " + selected.getLon());

    } catch (IllegalArgumentException e1) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle(e1.getClass().toString());
      alert.setHeaderText(e1.getClass().toString());
      String s = "Whoops, we cannot find that location in our system. Please try another.";
      alert.setContentText(s);
      alert.show();
    } catch (IOException e2) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle(e2.getClass().toString());
      alert.setHeaderText(e2.getClass().toString());
      String s =
          "Whoops, it looks like there was an IOException thrown. Please contact the creator.";
      alert.setContentText(s);
      alert.show();
    } catch (InterruptedException e3) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle(e3.getClass().toString());
      alert.setHeaderText(e3.getClass().toString());
      String s =
          "Whoops, the connection was interrupted between WeatherApp and OpenWeatherMap's API. Please "
              + "check your internet connection. If you are connected to stable and speedy internet, "
              + "the issue could be with the API. Please try again in a few moments.";
      alert.setContentText(s);
      alert.show();
    } catch (ParseException e4) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle(e4.getClass().toString());
      alert.setHeaderText(e4.getClass().toString());
      String s =
          "Whoops, it looks like we could not parse the data for that location. Please try another.";
      alert.setContentText(s);
      alert.show();
    }
  }
}
