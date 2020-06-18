import java.util.Collections;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class GUIController {
  private LocationManager lm; 
  
  @FXML
  private ComboBox<String> citySelector;
  
  @FXML
  private Label currentTemp;
  
  @FXML
  private Label currentWeather;
  
  @FXML
  private Label currentLong;
  
  @FXML
  private Label currentLat;
  
  
  public GUIController() {}
  
  public void initialize() {
    lm = new LocationManager();
    ObservableList<String> cities = FXCollections.observableArrayList(); 

    
    for (String location : lm.getAllLocations()) {
      cities.add(location);
    }
    Collections.sort(cities);
    citySelector.setItems(cities);;
  }
}
