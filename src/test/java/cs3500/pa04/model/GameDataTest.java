package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameDataTest {
  GameData data;
  char[][] exGrid;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    data = new GameData(6, 6);
    exGrid = new char[6][6];

    for (int i = 0; i < 6; i += 1) {
      for (int j = 0; j < 6; j += 1) {
        exGrid[i][j] = '0';
      }
    }
  }

  /**
   * Checks the getBoardWidth returns 6.
   */
  @Test
  public void testGetWidth() {
    assertEquals(data.getBoardWidth(), 6);
  }

  /**
   * Checks the getBoardHeight returns 6.
   */
  @Test
  public void testGetHeight() {
    assertEquals(data.getBoardHeight(), 6);
  }

  /**
   * Checks that getMostRecentShots returns an empty list for data with no shots.
   */
  @Test
  public void testGetMostRecentShots() {
    assertEquals(data.getMostRecentShots(), new ArrayList<Coord>());
  }

  /**
   * Checks that getOpponentEmptyCoords returns a list of all coordinates for data with opponent
   * board with all empty coordinates.
   */
  @Test
  public void testGetOpponentEmptyCoords() {
    ArrayList<Coord> coords = new ArrayList<>();

    for (int i = 0; i < 6; i += 1) {
      for (int j = 0; j < 6; j += 1) {
        Coord coord = new Coord(j, i);
        coords.add(coord);
      }
    }
    assertEquals(data.getOpponentEmptyCoords(), coords);
  }

  /**
   * Checks that getOwnShipCount returns 0 for a board with no ships.
   */
  @Test
  public void testGetShipCount() {
    assertEquals(data.getOwnShipCount(), 0);
  }

  /**
   * Checks that getOwnBoard returns correct char[][].
   */
  @Test
  public void testGetOwnBoard() {
    CoordinateStatus[][] ownBoard = data.getOwnBoard();

    StringBuilder exBoardString = new StringBuilder();
    StringBuilder ownBoardString = new StringBuilder();

    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < 6; i += 1) {
      for (int j = 0; j < 6; j += 1) {
        char exBoardChar = exGrid[i][j];
        String ownBoardChar = ownBoard[i][j].toString();

        exBoardString.append(exBoardChar);
        ownBoardString.append(ownBoardChar);
      }
    }
    assertEquals(exBoardString.toString(), ownBoardString.toString());
  }

  /**
   * Checks that getOpponentBoard returns correct char[][].
   */
  @Test
  public void testGetOpponentBoard() {
    CoordinateStatus[][] ownBoard = data.getOwnBoard();

    StringBuilder exBoardString = new StringBuilder();
    StringBuilder ownBoardString = new StringBuilder();

    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < 6; i += 1) {
      for (int j = 0; j < 6; j += 1) {
        char exBoardChar = exGrid[i][j];
        String ownBoardChar = ownBoard[i][j].toString();

        exBoardString.append(exBoardChar);
        ownBoardString.append(ownBoardChar);
      }
    }
    assertEquals(exBoardString.toString(), ownBoardString.toString());
  }

  /**
   * Checks that updateMostRecentShots correctly updates shots.
   */
  @Test
  public void testUpdateMostRecentShots() {
    ArrayList<Coord> shots = new ArrayList<>();
    assertEquals(data.getMostRecentShots(), shots);

    shots.add(new Coord(0, 0));
    data.updateMostRecentShots(shots);

    assertEquals(data.getMostRecentShots(), shots);
  }

  /**
   * Checks that getPossibleCoords for a 6x6 board for a Carrier returns the correct coordinates.
   */
  @Test
  public void testGetPossibleCoords() {
    Board sameBoard = new Board(6, 6);
    List<List<Coord>> samePossibleCoords = sameBoard.getPossibleCoords(6);

    assertEquals(samePossibleCoords, data.getPossibleCoords(6));
  }

  /**
   * Checks that placeOwnShip correctly places ship on own board.
   */
  @Test
  public void testPlaceOwnShip() {
    assertEquals(data.getOwnShipCount(), 0);

    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    data.placeOwnShip(ShipType.DESTROYER, coords);

    CoordinateStatus[][] ownGrid = data.getOwnBoard();

    assertEquals(data.getOwnShipCount(), 1);
    assertEquals(ownGrid[0][0].toString(), "D");
    assertEquals(ownGrid[0][1].toString(), "D");
    assertEquals(ownGrid[0][2].toString(), "D");
    assertEquals(ownGrid[0][3].toString(), "D");
  }

  /**
   * Checks that updateOwnBoard correctly returns shots that hit ships.
   */
  @Test
  public void testUpdateOwnBoard() {
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    data.placeOwnShip(ShipType.DESTROYER, coords);

    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 0));
    shots.add(new Coord(1, 0));
    shots.add(new Coord(2, 0));
    shots.add(new Coord(3, 0));
    shots.add(new Coord(5, 5));

    assertEquals(data.updateOwnBoard(shots), coords);
  }

  /**
   * Checks that updateOpponentBoard updates opponentBoard correctly
   */
  @Test
  public void testUpdateOpponentBoard() {
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 0));
    shots.add(new Coord(1, 0));
    shots.add(new Coord(2, 0));
    shots.add(new Coord(3, 0));
    shots.add(new Coord(5, 5));

    data.updateMostRecentShots(shots);

    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    data.updateOpponentBoardHit(coords);

    CoordinateStatus[][] opponentGrid = data.getOpponentBoard();

    assertEquals(opponentGrid[0][0].toString(), "H");
    assertEquals(opponentGrid[0][1].toString(), "H");
    assertEquals(opponentGrid[0][2].toString(), "H");
    assertEquals(opponentGrid[0][3].toString(), "H");
    assertEquals(opponentGrid[5][5].toString(), "0");
  }

  /**
   * Checks that endGame returns true for data with own board that has no ships left
   */
  @Test
  public void testEndGameTrue() {
    assertTrue(data.endGame());
  }

  /**
   * Checks that endGame returns false for data with own board that has one ships left.
   */
  @Test
  public void testEndGameFalse() {
    List<Coord> coords = new ArrayList<>();
    coords.add(new Coord(0, 0));
    coords.add(new Coord(1, 0));
    coords.add(new Coord(2, 0));
    coords.add(new Coord(3, 0));

    data.placeOwnShip(ShipType.DESTROYER, coords);

    assertFalse(data.endGame());
  }

  /**
   * Checks that ownBoardToString returns ownBoard as a String correctly
   */
  @Test
  public void testOwnBoardToString() {
    StringBuilder ownBoardString = new StringBuilder();

    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < 6; i += 1) {
      for (int j = 0; j < 6; j += 1) {
        ownBoardString.append('0');
        ownBoardString.append(' ');
      }
      ownBoardString.append("\n");
    }

    assertEquals(ownBoardString.toString(), data.ownBoardToString());
  }

  /**
   * Checks that opponentBoardToString returns opponentBoard as a String correctly
   */
  @Test
  public void testOpponentBoardToString() {
    StringBuilder opponentBoardString = new StringBuilder();

    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < 6; i += 1) {
      for (int j = 0; j < 6; j += 1) {
        opponentBoardString.append('0');
        opponentBoardString.append(' ');
      }
      opponentBoardString.append("\n");
    }

    assertEquals(opponentBoardString.toString(), data.opponentBoardToString());
  }
}