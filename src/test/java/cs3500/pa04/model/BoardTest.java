package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Board class.
 */
class BoardTest {
  Board board;
  Board smallBoard;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    board = new Board(7, 10);
    smallBoard = new Board(4, 4);
  }

  /**
   * Checks that a Board is constructed correctly.
   */
  @Test
  public void testConstruct() {
    assertNotNull(board);
  }

  /**
   * Checks that getHeight returns correct height.
   */
  @Test
  public void testGetHeight() {
    assertEquals(board.getHeight(), 7);
  }

  /**
   * Checks that getWidth returns correct width.
   */
  @Test
  public void testGetWidth() {
    assertEquals(board.getWidth(), 10);
  }

  /**
   * Checks that getGrid returns the correct char[][].
   */
  @Test
  public void testGetGrid() {
    char[][] testBoard = new char[7][10];

    for (int i = 0; i < testBoard.length; i += 1) {
      for (int j = 0; j < testBoard[0].length; j += 1) {
        testBoard[i][j] = '0';
      }
    }

    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < testBoard.length; i += 1) {
      for (int j = 0; j < testBoard[0].length; j += 1) {
        char character = testBoard[i][j];
        testBoardString.append(character);
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    StringBuilder output = new StringBuilder();

    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < board.getGrid().length; i += 1) {
      for (int j = 0; j < board.getGrid()[0].length; j += 1) {
        String character = board.getGrid()[i][j].toString();
        output.append(character);
        output.append(" ");
      }
      output.append("\n");
    }

    assertEquals(output.toString(), testBoardString.toString());
  }

  /**
   * Checks that getEmptyCoords returns all coordinates of board as no shots have been made yet.
   */
  @Test
  public void testGetEmptyCoords() {
    ArrayList<Coord> coords = new ArrayList<>();
    for (int i = 0; i < board.getGrid().length; i += 1) {
      for (int j = 0; j < board.getGrid()[0].length; j += 1) {
        Coord coord = new Coord(j, i);
        coords.add(coord);
      }
    }

    assertEquals(board.getEmptyCoords(), coords);
  }

  /**
   * Checks that getShipsHitCount returns 0 for a board that has no ships and 1 for a board
   * that has 1 ship.
   */
  @Test
  public void testGetShipsCount() {
    assertEquals(board.getTotalShipCount(), 0);

    ArrayList<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(0, 1));
    coords.add(new Coord(0, 2));
    board.placeShip(ShipType.SUBMARINE, coords);

    assertEquals(board.getTotalShipCount(), 1);
  }

  /**
   * Checks that getPossibleCoords returns all possible coords for a 4 by 4 board for Carrier.
   * Returns no coords because Carrier does not fit on a 4 by 4 board.
   */
  @Test
  public void testGetPossibleCoordsCarrier() {
    assertEquals(smallBoard.getPossibleCoords(6), new ArrayList<ArrayList<Coord>>());
  }

  /**
   * Checks that getPossibleCoords returns all possible coords for a 4 by 4 board for Battleship.
   * Returns no coords because Carrier does not fit on a 4 by 4 board.
   */
  @Test
  public void testGetPossibleCoordsBattleship() {
    assertEquals(smallBoard.getPossibleCoords(5), new ArrayList<ArrayList<Coord>>());
  }

  /**
   * Checks that getPossibleCoords returns all possible coords for a 4 by 4 board for Destroyer.
   */
  @Test
  public void testGetPossibleCoordsDestroyer() {
    List<Coord> coords1 = new ArrayList<>();
    coords1.add(new Coord(0, 0));
    coords1.add(new Coord(1, 0));
    coords1.add(new Coord(2, 0));
    coords1.add(new Coord(3, 0));

    List<Coord> coords2 = new ArrayList<>();
    coords2.add(new Coord(0, 1));
    coords2.add(new Coord(1, 1));
    coords2.add(new Coord(2, 1));
    coords2.add(new Coord(3, 1));

    List<Coord> coords3 = new ArrayList<>();
    coords3.add(new Coord(0, 2));
    coords3.add(new Coord(1, 2));
    coords3.add(new Coord(2, 2));
    coords3.add(new Coord(3, 2));

    List<Coord> coords4 = new ArrayList<>();
    coords4.add(new Coord(0, 3));
    coords4.add(new Coord(0, 2));
    coords4.add(new Coord(0, 1));
    coords4.add(new Coord(0, 0));

    List<Coord> coords5 = new ArrayList<>();
    coords5.add(new Coord(0, 3));
    coords5.add(new Coord(1, 3));
    coords5.add(new Coord(2, 3));
    coords5.add(new Coord(3, 3));

    List<Coord> coords6 = new ArrayList<>();
    coords6.add(new Coord(1, 3));
    coords6.add(new Coord(1, 2));
    coords6.add(new Coord(1, 1));
    coords6.add(new Coord(1, 0));

    List<Coord> coords7 = new ArrayList<>();
    coords7.add(new Coord(2, 3));
    coords7.add(new Coord(2, 2));
    coords7.add(new Coord(2, 1));
    coords7.add(new Coord(2, 0));

    List<Coord> coords8 = new ArrayList<>();
    coords8.add(new Coord(3, 3));
    coords8.add(new Coord(3, 2));
    coords8.add(new Coord(3, 1));
    coords8.add(new Coord(3, 0));

    ArrayList<List<Coord>> possibleCoords = new ArrayList<>();
    possibleCoords.add(coords1);
    possibleCoords.add(coords2);
    possibleCoords.add(coords3);
    possibleCoords.add(coords4);
    possibleCoords.add(coords5);
    possibleCoords.add(coords6);
    possibleCoords.add(coords7);
    possibleCoords.add(coords8);

    assertEquals(smallBoard.getPossibleCoords(4).size(), possibleCoords.size());
  }

  /**
   * Checks that placeShip correctly updates board with the Ship on the given cell and updates
   * the Board's list of ships.
   */
  @Test
  public void testPlaceShip() {
    assertEquals(smallBoard.getTotalShipCount(), 0);

    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    smallBoard.placeShip(ShipType.DESTROYER, coords);

    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < smallBoard.getGrid().length; i += 1) {
      for (int j = 0; j < smallBoard.getGrid()[0].length; j += 1) {
        if ((j == 0 || j == 1 || j == 2 || j == 3) && (i == 0)) {
          testBoardString.append('D');
        } else {
          testBoardString.append('0');
        }
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    assertEquals(smallBoard.toString(), testBoardString.toString());
    assertEquals(smallBoard.getTotalShipCount(), 1);
  }

  /**
   * Checks that shoot places a 'M' at a previously empty cell
   */
  @Test
  public void testShootMiss1() {
    smallBoard.shoot(new Coord(0, 0));

    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < smallBoard.getGrid().length; i += 1) {
      for (int j = 0; j < smallBoard.getGrid()[0].length; j += 1) {
        if ((j == 0) && (i == 0)) {
          testBoardString.append('M');
        } else {
          testBoardString.append('0');
        }
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    assertEquals(smallBoard.toString(), testBoardString.toString());
  }

  /**
   * Checks that shoot places a 'M' at a previously missed cell
   */
  @Test
  public void testShootMiss2() {
    smallBoard.shoot(new Coord(0, 0));
    smallBoard.shoot(new Coord(0, 0));

    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < smallBoard.getGrid().length; i += 1) {
      for (int j = 0; j < smallBoard.getGrid()[0].length; j += 1) {
        if ((j == 0) && (i == 0)) {
          testBoardString.append('M');
        } else {
          testBoardString.append('0');
        }
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    assertEquals(smallBoard.toString(), testBoardString.toString());
  }

  /**
   * Checks that shoot places a 'H' at a cell that has a ship
   */
  @Test
  public void testShootHit() {
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    smallBoard.placeShip(ShipType.DESTROYER, coords);

    smallBoard.shoot(new Coord(0, 0));

    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < smallBoard.getGrid().length; i += 1) {
      for (int j = 0; j < smallBoard.getGrid()[0].length; j += 1) {
        if ((j == 0) && (i == 0)) {
          testBoardString.append('H');
        } else if ((j == 1 || j == 2 || j == 3) && (i == 0)) {
          testBoardString.append('D');
        } else {
          testBoardString.append('0');
        }
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    assertEquals(smallBoard.toString(), testBoardString.toString());
  }

  /**
   * Checks that place miss correctly places a miss at the given cell
   */
  @Test
  public void testPlaceMiss() {
    smallBoard.placeMiss(new Coord(0, 0));

    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < smallBoard.getGrid().length; i += 1) {
      for (int j = 0; j < smallBoard.getGrid()[0].length; j += 1) {
        if ((j == 0) && (i == 0)) {
          testBoardString.append('M');
        } else {
          testBoardString.append('0');
        }
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    assertEquals(smallBoard.toString(), testBoardString.toString());
  }

  /**
   * Checks that place hit correctly places a hit at the given cell
   */
  @Test
  public void testPlaceHit() {
    smallBoard.placeHit(new Coord(0, 0));

    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < smallBoard.getGrid().length; i += 1) {
      for (int j = 0; j < smallBoard.getGrid()[0].length; j += 1) {
        if ((j == 0) && (i == 0)) {
          testBoardString.append('H');
        } else {
          testBoardString.append('0');
        }
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    assertEquals(smallBoard.toString(), testBoardString.toString());
  }

  /**
   * Checks that updateShipsSunk correctly returns the number of ships hit after hitting no ships
   * and that it returns one after a destroyer is sunk.
   */
  @Test
  public void testShipsSunk() {
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    smallBoard.placeShip(ShipType.DESTROYER, coords);

    assertEquals(smallBoard.getTotalShipCount(), 1);

    smallBoard.updateShipsSunk();

    smallBoard.shoot(new Coord(0, 0));
    smallBoard.shoot(new Coord(1, 0));
    smallBoard.shoot(new Coord(2, 0));
    smallBoard.shoot(new Coord(3, 0));

    assertEquals(smallBoard.getTotalShipCount(), 0);
  }

  /**
   * Checks that allShipsSunk returns false for a board with a ship on it.
   */
  @Test
  public void testAllShipsSunkFalse() {
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    smallBoard.placeShip(ShipType.DESTROYER, coords);

    assertFalse(smallBoard.allShipsSunk());
  }

  /**
   * Checks that allShipsSunk returns true for a board with no ships on it.
   */
  @Test
  public void testShipsSunkTrue() {
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    smallBoard.placeShip(ShipType.DESTROYER, coords);

    assertEquals(smallBoard.getTotalShipCount(), 1);

    smallBoard.updateShipsSunk();

    smallBoard.shoot(new Coord(0, 0));
    smallBoard.shoot(new Coord(1, 0));
    smallBoard.shoot(new Coord(2, 0));
    smallBoard.shoot(new Coord(3, 0));

    assertTrue(smallBoard.allShipsSunk());
  }

  /**
   * Checks that toString correctly renders a Board's grid as a String.
   */
  @Test
  public void testToString() {
    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < smallBoard.getGrid().length; i += 1) {
      for (int j = 0; j < smallBoard.getGrid()[0].length; j += 1) {
        testBoardString.append('0');
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    assertEquals(testBoardString.toString(), smallBoard.toString());
  }

  /**
   * Checks that two boards with the same grid string format returns true for equals.
   */
  @Test
  public void testEqualsPass() {
    Board anotherBoard = new Board(4, 4);

    assertTrue(anotherBoard.equals(smallBoard));
  }

  /**
   * Checks that two boards with different grid string format returns true for equals.
   */
  @Test
  public void testEqualsFailBoard() {
    Board anotherBoard = new Board(5, 4);

    assertFalse(anotherBoard.equals(smallBoard));
  }

  /**
   * Checks that a Board and a String are not equal.
   */
  @Test
  public void testEqualsFailObj() {
    assertFalse(smallBoard.equals("String"));
  }
}