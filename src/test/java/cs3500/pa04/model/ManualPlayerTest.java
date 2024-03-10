package cs3500.pa04.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for ManualPlayer class.
 */
class ManualPlayerTest {
  GameData data;
  ManualPlayer manualPlayer;
  EnumMap<ShipType, Integer> specifications;
  Ship carrier;
  List<Coord> carrierCoords;
  Ship battleship1;
  List<Coord> battleshipCoords;
  Ship battleship2;
  List<Coord> battleshipCoords2;
  Ship destroyer;
  List<Coord> destroyerCoords;
  Ship submarine1;
  List<Coord> subCoords1;
  Ship submarine2;
  List<Coord> subCoords2;
  List<Ship> ships = new ArrayList<>();

  /**
   * Initialize data.
   */
  @BeforeEach
  public void setup() {
    data = new GameData(6, 6);
    manualPlayer = new ManualPlayer(data, new Random(1));

    specifications = new EnumMap<>(ShipType.class);
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 2);

    carrierCoords = new ArrayList<>();
    carrierCoords.add(new Coord(1, 0));
    carrierCoords.add(new Coord(1, 1));
    carrierCoords.add(new Coord(1, 2));
    carrierCoords.add(new Coord(1, 3));
    carrierCoords.add(new Coord(1, 4));
    carrierCoords.add(new Coord(1, 5));
    carrier = new Ship(ShipType.CARRIER, carrierCoords);


    battleshipCoords = new ArrayList<>();
    battleshipCoords.add(new Coord(2, 0));
    battleshipCoords.add(new Coord(2, 1));
    battleshipCoords.add(new Coord(2, 2));
    battleshipCoords.add(new Coord(2, 3));
    battleshipCoords.add(new Coord(2, 4));
    battleship1 = new Ship(ShipType.BATTLESHIP, battleshipCoords);

    battleshipCoords2 = new ArrayList<>();
    battleshipCoords2.add(new Coord(3, 0));
    battleshipCoords2.add(new Coord(3, 1));
    battleshipCoords2.add(new Coord(3, 2));
    battleshipCoords2.add(new Coord(3, 3));
    battleshipCoords2.add(new Coord(3, 4));
    battleship2 = new Ship(ShipType.BATTLESHIP, battleshipCoords2);

    destroyerCoords = new ArrayList<>();
    destroyerCoords.add(new Coord(5, 0));
    destroyerCoords.add(new Coord(5, 1));
    destroyerCoords.add(new Coord(5, 2));
    destroyerCoords.add(new Coord(5, 3));
    destroyer = new Ship(ShipType.DESTROYER, destroyerCoords);

    subCoords1 = new ArrayList<>();
    subCoords1.add(new Coord(0, 3));
    subCoords1.add(new Coord(0, 4));
    subCoords1.add(new Coord(0, 5));
    submarine1 = new Ship(ShipType.SUBMARINE, subCoords1);

    subCoords2 = new ArrayList<>();
    subCoords2.add(new Coord(4, 3));
    subCoords2.add(new Coord(4, 4));
    subCoords2.add(new Coord(4, 5));
    submarine2 = new Ship(ShipType.SUBMARINE, subCoords2);

    ships.add(carrier);
    ships.add(battleship1);
    ships.add(battleship2);
    ships.add(destroyer);
    ships.add(submarine1);
    ships.add(submarine2);
  }

  /**
   * Checks if manualPlayer is initialized correctly
   */
  @Test
  public void testConstruct() {
    assertNotNull(manualPlayer);
  }

  /**
   * Checks that setup correctly places 6 ships (1 carrier, 2 battleships, 1 destroyer, and
   * 2 submarines) by creating another list of 6 ships of the same types as the given specifications
   * for setup. Convert the list of generated ships to a list of the ships' types and do the same
   * for the manually created list of ships. Check that the ship types of each list are the same
   * and that there are the same number of each type of ship.
   */
  @Test
  public void testSetup() {
    List<Ship> generatedShips = manualPlayer.setup(6, 6, specifications);
    List<ShipType> generatedShipsTypes = new ArrayList<>();

    List<ShipType> shipsTypes = new ArrayList<>();

    assertEquals(generatedShips.size(), 6);

    generatedShips.forEach((s) -> {
      generatedShipsTypes.add(s.getType());
    });
    ships.forEach((s) -> {
      shipsTypes.add(s.getType());
    });

    assertEquals(generatedShipsTypes.size(), shipsTypes.size());
    assertTrue(generatedShipsTypes.containsAll(shipsTypes));
  }

  /**
   * Checks that name returns "Manual"
   */
  @Test
  public void testName() {
    assertEquals("You", manualPlayer.name());
  }

  /**
   * Checks that takeShots returns the shots that are in mostRecentShots in the Player's data.
   */
  @Test
  public void testTakeShots() {
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(new Coord(0, 0));
    shots.add(new Coord(1, 0));
    shots.add(new Coord(2, 0));
    shots.add(new Coord(3, 0));
    shots.add(new Coord(5, 5));

    data.updateMostRecentShots(shots);

    assertEquals(manualPlayer.takeShots(), shots);
  }

  /**
   * Checks that reportDamage returns the shots that hit a ships on the manualPlayer's board and
   * that the shots on the board were updated correctly.
   */
  @Test
  public void testReportDamage() {
    data.placeOwnShip(ShipType.CARRIER, carrierCoords);
    data.placeOwnShip(ShipType.BATTLESHIP, battleshipCoords);
    data.placeOwnShip(ShipType.BATTLESHIP, battleshipCoords2);
    data.placeOwnShip(ShipType.BATTLESHIP, destroyerCoords);
    data.placeOwnShip(ShipType.SUBMARINE, subCoords1);
    data.placeOwnShip(ShipType.SUBMARINE, subCoords2);

    Coord successShot1 = new Coord(1, 0);
    Coord successShot2 = new Coord(2, 0);
    Coord successShot3 = new Coord(3, 0);
    Coord missShot1 = new Coord(5, 5);

    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(successShot1);
    shots.add(successShot2);
    shots.add(successShot3);
    shots.add(missShot1);

    List<Coord> successfulHits = manualPlayer.reportDamage(shots);

    shots.remove(missShot1);

    assertEquals(successfulHits, shots);

    CoordinateStatus[][] board = data.getOwnBoard();

    assertEquals(board[0][1].toString(), "H");
    assertEquals(board[0][2].toString(), "H");
    assertEquals(board[0][3].toString(), "H");
    assertEquals(board[5][5].toString(), "M");
  }

  /**
   * Checks that successfulHits correctly updates opponent's board.
   */
  @Test
  public void testSuccessfulHits() {
    manualPlayer.setup(6, 6, specifications);

    Coord successShot1 = new Coord(1, 0);
    Coord successShot2 = new Coord(2, 0);
    Coord successShot3 = new Coord(3, 0);
    Coord missShot1 = new Coord(5, 5);

    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(successShot1);
    shots.add(successShot2);
    shots.add(successShot3);
    shots.add(missShot1);

    data.updateMostRecentShots(shots);

    ArrayList<Coord> successfulShots = new ArrayList<>();
    successfulShots.add(successShot1);
    successfulShots.add(successShot2);
    successfulShots.add(successShot3);

    manualPlayer.successfulHits(successfulShots);

    CoordinateStatus[][] board = data.getOpponentBoard();

    assertEquals(board[0][1].toString(), "H");
    assertEquals(board[0][2].toString(), "H");
    assertEquals(board[0][3].toString(), "H");
    assertEquals(board[5][5].toString(), "0");
  }

  /**
   * Placeholder test for endGame, which has no implementation at this point.
   */
  @Test
  public void testEndGame() {
    manualPlayer.endGame(GameResult.DRAW, "draw");
  }
}