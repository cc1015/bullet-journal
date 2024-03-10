package cs3500.pa04.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetupJsonTest {
  SetupJson setupJson;
  FleetSpecJson specs;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    specs = new FleetSpecJson(1, 2, 2, 1);
    setupJson = new SetupJson(9, 6, specs);
  }

  /**
   * Checks that getWidth returns 9.
   */
  @Test
  public void testGetWidth() {
    assertEquals(setupJson.getWidth(), 9);
  }

  /**
   * Checks that getHeight returns 6.
   */
  @Test
  public void testGetHeight() {
    assertEquals(setupJson.getHeight(), 6);
  }

  /**
   * Checks that getFleetSpec returns specs as a map.
   */
  @Test
  public void testGetFleetSpec() {
    assertEquals(setupJson.getFleetSpec(), specs.makeMap());
  }
}