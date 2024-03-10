package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.model.ShipType;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for FleetSpecJson record.
 */
class FleetSpecJsonTest {
  FleetSpecJson fleetSpecJson;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    fleetSpecJson = new FleetSpecJson(1, 2, 2, 1);
  }

  /**
   * Checks if makeMap returns correct map.
   */
  @Test
  public void testMakeMap() {
    Map<ShipType, Integer> specs = new EnumMap<>(ShipType.class);
    specs.put(ShipType.CARRIER, 1);
    specs.put(ShipType.BATTLESHIP, 2);
    specs.put(ShipType.DESTROYER, 2);
    specs.put(ShipType.SUBMARINE, 1);

    assertEquals(fleetSpecJson.makeMap(), specs);
  }
}