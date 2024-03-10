package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for CoordDirection enum class.
 */
class CoordDirectionTest {
  /**
   * Checks that getDx for NORTH returns 0.
   */
  @Test
  public void testGetDxNorth() {
    assertEquals(CoordDirection.NORTH.getDx(), 0);
  }

  /**
   * Checks that getDx for SOUTH returns 0.
   */
  @Test
  public void testGetDxSouth() {
    assertEquals(CoordDirection.SOUTH.getDx(), 0);
  }


  /**
   * Checks that getDx for WEST returns -1.
   */
  @Test
  public void testGetDxWest() {
    assertEquals(CoordDirection.WEST.getDx(), -1);
  }


  /**
   * Checks that getDx for EAST returns 1.
   */
  @Test
  public void testGetDxEast() {
    assertEquals(CoordDirection.EAST.getDx(), 1);
  }

  /**
   * Checks that getDy for NORTH returns -1.
   */
  @Test
  public void testGetDyNorth() {
    assertEquals(CoordDirection.NORTH.getDy(), -1);
  }

  /**
   * Checks that getDy for SOUTH returns 1.
   */
  @Test
  public void testGetDySouth() {
    assertEquals(CoordDirection.SOUTH.getDy(), 1);
  }


  /**
   * Checks that getDy for WEST returns 0.
   */
  @Test
  public void testGetDyWest() {
    assertEquals(CoordDirection.WEST.getDy(), 0);
  }


  /**
   * Checks that getDy for EAST returns 0.
   */
  @Test
  public void testGetDyEast() {
    assertEquals(CoordDirection.EAST.getDy(), 0);
  }

  /**
   * Checks that onlyNecessaryDirections returns NORTH and EAST in a list.
   */
  @Test
  public void testOnlyNeccessaryDirections() {
    ArrayList<CoordDirection> coordDirections = new ArrayList<>();

    coordDirections.add(CoordDirection.SOUTH);
    coordDirections.add(CoordDirection.EAST);

    assertEquals(CoordDirection.getNecessaryValues(), coordDirections);
  }
}