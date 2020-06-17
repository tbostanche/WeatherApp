import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIDriver extends Application {
  private static final int WINDOW_WIDTH = 1380;
  private static final int WINDOW_HEIGHT = 800;
  
 
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GUI.fxml"));
            Scene scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
      
    public static void main(String[] args) {
        launch(args);
    }
}


 