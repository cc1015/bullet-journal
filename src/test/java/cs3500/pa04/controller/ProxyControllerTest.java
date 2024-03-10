package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.FleetSpecJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.model.AutomatedPlayer;
import cs3500.pa04.model.Coord;
import cs3500.pa04.model.GameData;
import cs3500.pa04.model.ShipType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mock.Mocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for ProxyController class.
 */
class ProxyControllerTest {
  private ByteArrayOutputStream testLog;
  private ProxyController controller;
  private ObjectMapper mapper;
  private GameData data;


  /**
   * Reset the test log before each test is run.
   */
  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    this.mapper = new ObjectMapper();
    this.data = new GameData(9, 6);

    // place submarine on player's board
    Coord c1 = new Coord(0, 0);
    Coord c2 = new Coord(1, 0);
    Coord c3 = new Coord(2, 0);
    List<Coord> coords1 = new ArrayList<>();
    coords1.add(c1);
    coords1.add(c2);
    coords1.add(c3);
    data.placeOwnShip(ShipType.SUBMARINE, coords1);

    // place destroyer on player's board
    Coord c4 = new Coord(0, 1);
    Coord c5 = new Coord(1, 1);
    Coord c6 = new Coord(2, 1);
    Coord c7 = new Coord(3, 1);
    List<Coord> coords2 = new ArrayList<>();
    coords2.add(c4);
    coords2.add(c5);
    coords2.add(c6);
    coords2.add(c7);
    data.placeOwnShip(ShipType.DESTROYER, coords2);

    // place battleship on player's board
    Coord c8 = new Coord(0, 2);
    Coord c9 = new Coord(1, 2);
    Coord c10 = new Coord(2, 2);
    Coord c11 = new Coord(3, 2);
    Coord c12 = new Coord(4, 2);
    List<Coord> coords3 = new ArrayList<>();
    coords3.add(c8);
    coords3.add(c9);
    coords3.add(c10);
    coords3.add(c11);
    coords3.add(c12);
    data.placeOwnShip(ShipType.BATTLESHIP, coords3);


    assertEquals("", logToString());
  }

  /**
   * Check that the controller returns name and game type when given join.
   */
  @Test
  public void testResponseForJoin() {
    MessageJson join = new MessageJson("join", mapper.createObjectNode());

    JsonNode sampleMessage = createSampleMessage("join", join);

    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(null,
          new Random(1)));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    this.controller.executeGame();

    String expected = "{\"method-name\":\"join\",\"arguments\":{\"name\":\"cc1015\",\"game-type\":"
        + "\"SINGLE\"}}";
    assertEquals(expected, logToString());
  }

  /**
   * Check that the controller returns specs when given setup.
   */
  @Test
  public void testResponseForSetup() {
    FleetSpecJson specs = new FleetSpecJson(1, 2, 2, 1);
    SetupJson setup = new SetupJson(9, 6, specs);
    JsonNode sampleMessage = createSampleMessage("setup", setup);

    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(null,
          new Random(1)));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    this.controller.executeGame();

    String expected = "{\"method-name\":\"setup\","
        + "\"arguments\":"
        + "{\"fleet\":"
        + "[{\"coord\":{\"x\":2,\"y\":1},\"length\":6,\"direction\":\"HORIZONTAL\"},"
        + "{\"coord\":{\"x\":0,\"y\":0},\"length\":5,\"direction\":\"VERTICAL\"},"
        + "{\"coord\":{\"x\":2,\"y\":5},\"length\":5,\"direction\":\"HORIZONTAL\"},"
        + "{\"coord\":{\"x\":3,\"y\":4},\"length\":4,\"direction\":\"HORIZONTAL\"},"
        + "{\"coord\":{\"x\":4,\"y\":3},\"length\":4,\"direction\":\"HORIZONTAL\"},"
        + "{\"coord\":{\"x\":2,\"y\":0},\"length\":3,\"direction\":\"HORIZONTAL\"}]}}";
    assertEquals(expected, logToString());
  }

  /**
   * Check that the controller returns 3 shots when given take-shots
   * (there are 3 ships on the player's board).
   */
  @Test
  public void testResponseForTakeShots() {
    MessageJson takeShots = new MessageJson("take-shots", mapper.createObjectNode());

    JsonNode sampleMessage = createSampleMessage("take-shots", takeShots);

    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(data,
          new Random(1)));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    this.controller.executeGame();

    String expected =
        "{\"method-name\":\"take-shots\","
            + "\"arguments\":{\"coordinates\":"
            + "[{\"x\":3,\"y\":8},"
            + "{\"x\":4,\"y\":4},"
            + "{\"x\":1,\"y\":3}]}}";
    assertEquals(expected, logToString());
  }

  /**
   * Check that the controller returns the shots that hit a ship when given shots from opponent
   * (2 hit ships, 1 did not).
   */
  @Test
  public void testResponseForReportDamage() {
    CoordJson[] successfulCoordJsons = {new CoordJson(0, 0), new CoordJson(2, 0),
        new CoordJson(2, 8)};

    ArrayNode array = mapper.valueToTree(successfulCoordJsons);
    JsonNode coordinatesJsonNode = mapper.createObjectNode().set("coordinates", array);

    MessageJson response = new MessageJson("report-damage", coordinatesJsonNode);
    JsonNode sampleMessage = JsonUtils.serializeRecord(response);

    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(data,
          new Random(1)));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    this.controller.executeGame();

    String expected = "{\"method-name\":\"report-damage\","
        + "\"arguments\":{\"coordinates\":"
        + "[{\"x\":0,\"y\":0},"
        + "{\"x\":2,\"y\":0}]}}";
    assertEquals(expected, logToString());
  }

  /**
   * Check that the controller returns empty successful-hits when given successful-hits from server.
   */
  @Test
  public void testResponseForSuccessfulHits() {
    CoordJson[] successfulCoordJsons = {new CoordJson(3, 8), new CoordJson(1, 3)};

    ArrayNode array = mapper.valueToTree(successfulCoordJsons);
    JsonNode coordinatesJsonNode = mapper.createObjectNode().set("coordinates", array);

    MessageJson response = new MessageJson("successful-hits", coordinatesJsonNode);
    JsonNode sampleMessage = JsonUtils.serializeRecord(response);

    Mocket socket = new Mocket(this.testLog, List.of(sampleMessage.toString()));

    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(data,
          new Random(1)));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    this.controller.executeGame();

    String expected = "{\"method-name\":\"successful-hits\","
        + "\"arguments\":{}}";
    assertEquals(expected, logToString());
  }

  /**
   * Check that the controller returns empty end-game when given end-game json from server.
   */
  @Test
  public void testEndGame() {

    JsonNode endGameNode = mapper.createObjectNode().put("result", "WIN")
        .put("reason", "Your opponent ran out of ships");

    MessageJson response = new MessageJson("end-game", endGameNode);
    JsonNode finalMessage = JsonUtils.serializeRecord(response);

    Mocket socket = new Mocket(this.testLog, List.of(finalMessage.toString()));

    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(data,
          new Random(1)));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    this.controller.executeGame();

    String expected = "{\"method-name\":\"end-game\","
        + "\"arguments\":{}}";
    assertEquals(expected, logToString());
  }

  /**
   * Check that the controller throws an error when given an illegal method name from the server.
   */
  @Test
  public void testInvalidMethodName() {

    MessageJson response = new MessageJson("INVALID-METHOD-NAME", null);
    JsonNode finalMessage = JsonUtils.serializeRecord(response);

    System.out.print(finalMessage);
    Mocket socket = new Mocket(this.testLog, List.of(finalMessage.toString()));

    try {
      this.controller = new ProxyController(socket, new AutomatedPlayer(data,
          new Random(1)));
    } catch (IOException e) {
      fail(); // fail if the dealer can't be created
    }

    assertThrows(IllegalStateException.class, () -> this.controller.executeGame());
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName   name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson =
        new MessageJson(messageName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }
}