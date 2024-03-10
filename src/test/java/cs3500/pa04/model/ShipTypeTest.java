package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for ShipType enum class.
 */
class ShipTypeTest {
  /**
   * Checks that getSize returns 6 for CARRIER
   */
  @Test
  public void testGetSizeCarrier() {
    assertEquals(ShipType.CARRIER.getSize(), 6);
  }

  /**
   * Checks that getSize returns 5 for BATTLESHIP
   */
  @Test
  public void testGetSizeBattleShip() {
    assertEquals(ShipType.BATTLESHIP.getSize(), 5);
  }

  /**
   * Checks that getSize returns 4 for DESTROYER
   */
  @Test
  public void testGetSizeDestroyer() {
    assertEquals(ShipType.DESTROYER.getSize(), 4);
  }

  /**
   * Checks that getSize returns 3 for SUBMARINE
   */
  @Test
  public void testGetSizeSubmarine() {
    assertEquals(ShipType.SUBMARINE.getSize(), 3);
  }

  /**
   * Checks that toChar returns 'C' for CARRIER
   */
  @Test
  public void testToCharCarrier() {
    assertEquals(ShipType.CARRIER.toChar(), 'C');
  }

  /**
   * Checks that toChar returns 'B' for BATTLESHIP
   */
  @Test
  public void testToCharBattleship() {
    assertEquals(ShipType.BATTLESHIP.toChar(), 'B');
  }

  /**
   * Checks that toChar returns 'C' for DESTOYER
   */
  @Test
  public void testToCharDestroyer() {
    assertEquals(ShipType.DESTROYER.toChar(), 'D');
  }

  /**
   * Checks that toChar returns 'S' for SUBMARINE
   */
  @Test
  public void testToCharSubmarine() {
    assertEquals(ShipType.SUBMARINE.toChar(), 'S');
  }
}