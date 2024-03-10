package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for Coord class.
 */
class CoordTest {
  Coord coord = new Coord(1, 7);

  /**
   * Checks that getX returns 1.
   */
  @Test
  public void testGetX() {
    assertEquals(coord.getX(), 1);
  }

  /**
   * Checks that getY returns 7.
   */
  @Test
  public void testGetY() {
    assertEquals(coord.getY(), 7);
  }

  /**
   * Checks that equals returns true for two different coordinates with the same X and Y.
   */
  @Test
  public void testEqualsPass() {
    Coord anotherCoord = new Coord(1, 7);

    assertTrue(anotherCoord.equals(coord));
  }

  /**
   * Checks that equals returns false for two different coordinates with the different X and Y.
   */
  @Test
  public void testEqualsFailCoord() {
    Coord anotherCoord = new Coord(2, 1);

    assertFalse(anotherCoord.equals(coord));
  }

  /**
   * Checks that equals returns false for a coordinate and a String.
   */
  @Test
  public void testEqualsFailObj() {
    assertFalse(coord.equals("String"));
  }

  /**
   * Checks that toString correctly returns a coordinate as a String.
   */
  @Test
  public void testToString() {
    assertEquals(coord.toString(), "1 7");
  }
}