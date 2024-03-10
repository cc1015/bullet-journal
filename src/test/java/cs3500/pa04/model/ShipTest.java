package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Ship class.
 */
class ShipTest {
  Ship ship;
  List<Coord> coords;

  @BeforeEach
  public void initData() {
    coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    ship = new Ship(ShipType.DESTROYER, coords);
  }

  /**
   * Checks that getCoordinates returns a ship's list of coordinates.
   */
  @Test
  public void testGetCoords() {
    assertEquals(ship.getCoordinates(), coords);
  }

  /**
   * Checks that getType returns a ship's type.
   */
  @Test
  public void testGetType() {
    assertEquals(ship.getType(), ShipType.DESTROYER);
  }

  /**
   * Checks that two different Ships with the same coordinates and the same type return true for
   * equals.
   */
  @Test
  public void testEqualsPass() {
    ArrayList<Coord> otherCoords = new ArrayList<>();
    otherCoords.add(new Coord(0, 0));
    otherCoords.add(new Coord(1, 0));
    otherCoords.add(new Coord(2, 0));
    otherCoords.add(new Coord(3, 0));

    Ship otherShip = new Ship(ShipType.DESTROYER, otherCoords);

    assertTrue(ship.equals(otherShip));
  }

  /**
   * Checks that two different Ships with different coordinates and the same type return false for
   * equals.
   */
  @Test
  public void testEqualsFail1() {
    ArrayList<Coord> otherCoords = new ArrayList<>();
    otherCoords.add(new Coord(0, 1));
    otherCoords.add(new Coord(1, 1));
    otherCoords.add(new Coord(2, 1));
    otherCoords.add(new Coord(3, 1));

    Ship otherShip = new Ship(ShipType.DESTROYER, otherCoords);

    assertFalse(ship.equals(otherShip));
  }

  /**
   * Checks that two different Ships with the different coordinates and the different type
   * return false for equals.
   */
  @Test
  public void testEqualsFail2() {
    ArrayList<Coord> otherCoords = new ArrayList<>();
    otherCoords.add(new Coord(0, 1));
    otherCoords.add(new Coord(1, 1));
    otherCoords.add(new Coord(2, 1));

    Ship otherShip = new Ship(ShipType.SUBMARINE, otherCoords);

    assertFalse(ship.equals(otherShip));
  }

  /**
   * Checks that a Ship and a String are not equal.
   */
  @Test
  public void testEqualsFail3() {
    assertFalse(ship.equals("String"));
  }
}