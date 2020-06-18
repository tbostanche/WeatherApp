import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class LocationManagerTest {
  private LocationManager lm;

  @BeforeEach
  public void setUp() {
    lm = new LocationManager();
  }

  @AfterEach
  public void tearDown() {
  }

  @Test
  public void test000_Check_Temperature_Is_Collected() {
    try {
      Weather test = lm.getWeatherData("Chicago");
      assert (test.temp != 0);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }
}