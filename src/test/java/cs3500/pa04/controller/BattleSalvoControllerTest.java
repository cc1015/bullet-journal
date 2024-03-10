package cs3500.pa04.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.StringReader;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for BattleSalvoController class.
 */

class BattleSalvoControllerTest {
  String sequence;
  Appendable output;
  StringBuilder expectedOutput;
  String welcome;
  String boardOptions;
  String numberError;
  String end;
  String aiWin;
  String userWin;
  String userLose;
  String tie;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    sequence = "";
    output = new StringBuilder();
    expectedOutput = new StringBuilder();

    welcome = "Welcome to BattleSalvo Game!\n";
    boardOptions = "Please enter a board height and width any size between 6 and 15 "
        + "inclusive (height width):\n";
    numberError = "Please enter your inputs in the correct format and as numbers!\n";
    end = "Game has ended.\n";
    aiWin = "AI won!\n";
    userWin = "You won!\n";
    userLose = "You lost!\n";
    tie = "It was a tie!\n";
  }

  /**
   * Checks that invalid board size prompts error dialogue
   */
  @Test
  public void invalidDimensions() {
    sequence = "1 2\n6 6\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter dimensions that are between 6 and 15 inclusive!\n");
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship "
        + "Destroyer Submarine].\nRemember, your fleet may not exceed size 6"
        + " (enter \"STOP\" to quit the game)\n");

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that invalid board size prompts error dialogue
   */
  @Test
  public void invalidDimensions2() {
    sequence = "20 20\n6 6\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter dimensions that are between 6 and 15 inclusive!\n");
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship "
        + "Destroyer Submarine].\nRemember, your fleet may not exceed size 6"
        + " (enter \"STOP\" to quit the game)\n");

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that words input as board size prompts error dialogue
   */
  @Test
  public void invalidDimensionsWords() {
    sequence = "word\n6 6\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append(numberError);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship "
        + "Destroyer Submarine].\nRemember, your fleet may not exceed size 6"
        + " (enter \"STOP\" to quit the game)\n");

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that entering a too-large fleet size prompts error message
   */
  @Test
  public void testInvalidFleetLarge() {
    sequence = "6 6\n2 2 2 2\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("Please make sure you fleet size is 6!\n");
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that entering a too-small fleet size prompts error message
   */
  @Test
  public void testInvalidFleetSmall() {
    sequence = "6 6\n1 1 1 1\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("Please make sure you fleet size is 6!\n");
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that entering a word for fleet information prompts input format error.
   */
  @Test
  public void testInvalidFleetWords() {
    sequence = "6 6\nword\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append(numberError);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that entering a coordinate out of bounds (too large) prompts error message
   */
  @Test
  public void testInvalidCoordsLarge() {
    sequence = "6 6\n1 1 2 2\n7 7\n1 0\n1 1\n1 2\n1 3\n1 4\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("Please enter coordinates that are within the range of the board!\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append(end);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that entering a coordinate out of bounds (too small) prompts error message
   */
  @Test
  public void testInvalidCoordsSmall() {
    sequence = "6 6\n1 1 2 2\n-1 -1\n1 0\n1 1\n1 2\n1 3\n1 4\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("Please enter coordinates that are within the range of the board!\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append(end);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that entering a coordinate out of bounds (too small) prompts error message
   */
  @Test
  public void testInvalidCoordsSmallLarge() {
    sequence = "6 6\n1 1 2 2\n-1 20\n1 0\n1 1\n1 2\n1 3\n1 4\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("Please enter coordinates that are within the range of the board!\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append(end);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that entering a coordinate out of bounds (too small) prompts error message
   */
  @Test
  public void testInvalidCoordsSmallLarge2() {
    sequence = "6 6\n1 1 2 2\n20 -1\n1 0\n1 1\n1 2\n1 3\n1 4\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("Please enter coordinates that are within the range of the board!\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append(end);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that entering words for coordinate prompts input format error.
   */
  @Test
  public void testInvalidCoordsWords() {
    sequence = "6 6\n1 1 2 2\nword\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append(numberError);
    expectedOutput.append(end);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Simulates two shoots outs in BattleSalvo where user inputs correctly formatted input the
   * first time. Checks that boards are updated correctly.
   */
  @Test
  public void testExecuteGame() {
    sequence = "6 6\n1 1 2 2\n0 0\n0 1\n0 2\n0 3\n0 4\n0 5\n1 0\n1 1\n1 2\n1 3\n1 4\n1 5\nSTOP";
    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M 0 0 0 0 0 \n"
        + "M 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H B B B H 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");

    expectedOutput.append("M H 0 0 0 0 \n"
        + "M H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 H S S \n"
        + "0 M S S S M \n"
        + "0 D D D D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H H B B H M \n\n");

    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append(end);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that a game ends once all opponent ships have been sunk and that win message is
   * displayed.
   */
  @Test
  public void testGameEndWin() {
    sequence = "6 6\n1 1 2 2\n0 2\n0 3\n0 4\n0 5\n1 0\n1 1\n1 2\n1 3\n1 4\n1 5\n2 1"
        + "\n2 2\n2 3\n2 4\n2 5\n3 0\n3 1\n3 2\n4 2\n4 3\n4 4\n4 5\n5 1\n5 2\n5 3\n0 0\n"
        + "0 1\n";

    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("0 H 0 0 0 0 \n"
        + "0 H 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H B B B H 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("0 H 0 0 0 0 \n"
        + "0 H H 0 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 H S S \n"
        + "0 M S S S M \n"
        + "0 D D D D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H H B B H M \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("0 H 0 H 0 0 \n"
        + "0 H H H 0 0 \n"
        + "H H H H 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n\n");
    expectedOutput.append("0 M 0 H S H \n"
        + "0 M S S S M \n"
        + "0 H D D D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 5 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("0 H 0 H 0 0 \n"
        + "0 H H H 0 H \n"
        + "H H H H H 0 \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n\n");
    expectedOutput.append("0 M 0 H H H \n"
        + "0 M S S S M \n"
        + "0 H D D D 0 \n"
        + "H H H C C C \n"
        + "D D D H 0 0 \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 4 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 H 0 0 \n"
        + "M H H H 0 H \n"
        + "H H H H H H \n"
        + "H H H 0 H H \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n\n");
    expectedOutput.append("0 M 0 H H H \n"
        + "0 M S S S M \n"
        + "0 H D D D 0 \n"
        + "H H H C C H \n"
        + "D D D H 0 0 \n"
        + "H H H H H M \n\n");
    expectedOutput.append(userWin);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that a game ends once all user ships have been sunk and that lose message is
   * displayed.
   */
  @Test
  public void testGameEndLost() {
    sequence = "6 6\n1 1 2 2\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0"
        + "\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0"
        + "\n0 0\n0 0\n";

    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H B B B H 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 H S S \n"
        + "0 M S S S M \n"
        + "0 D D H D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H H B B H M \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 H S S \n"
        + "0 M S S S M \n"
        + "0 D H H D 0 \n"
        + "H H H H C C \n"
        + "H D D H 0 M \n"
        + "H H B H H M \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 H S S \n"
        + "M M H H S M \n"
        + "0 H H H D 0 \n"
        + "H H H H H C \n"
        + "H D H H 0 M \n"
        + "H H B H H M \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M M H H H \n"
        + "M M H H S M \n"
        + "0 H H H D M \n"
        + "H H H H H H \n"
        + "H H H H 0 M \n"
        + "H H B H H M \n\n");
    expectedOutput.append("Please enter 3 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("M M M H H H \n"
        + "M M H H H M \n"
        + "M H H H H M \n"
        + "H H H H H H \n"
        + "H H H H M M \n"
        + "H H H H H M \n\n");
    expectedOutput.append(userLose);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Checks that a game ends once all opponent ships have been sunk and user ships have been sunk
   * and that draw message is displayed.
   */
  @Test
  public void testGameEndDraw() {
    sequence = "6 6\n1 1 2 2\n0 2\n0 3\n0 4\n0 5\n1 0\n1 1\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0"
        + "\n0 0\n0 0\n0 0\n0 0\n0 0\n0 0\n1 2\n1 3\n1 4\n1 5\n2 1"
        + "\n2 2\n2 3\n2 4\n2 5\n3 0\n3 1\n0 0\n0 0\n3 2\n4 2\n4 3\n4 4\n4 5\n5 1\n5 2\n5 3\n";

    output = new StringBuilder();
    Readable input = new StringReader(sequence);

    BattleSalvoController controller = new BattleSalvoController(input, output,
        new Random(1));

    controller.executeGame();

    expectedOutput.append(welcome);
    expectedOutput.append(boardOptions);
    expectedOutput.append("Please enter your fleet in the order [Carrier Battleship Destroyer "
        + "Submarine].\nRemember, your fleet may not exceed size 6 (enter \"STOP\" to quit the "
        + "game)\n");
    expectedOutput.append("0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n"
        + "0 0 0 0 0 0 \n\n");
    expectedOutput.append("0 0 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "C C C C C C \n"
        + "D D D D 0 0 \n"
        + "B B B B B 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("0 H 0 0 0 0 \n"
        + "0 H 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 S S S \n"
        + "0 0 S S S 0 \n"
        + "0 D D D D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H B B B H 0 \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 0 0 0 \n"
        + "0 H 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 H S S \n"
        + "0 M S S S M \n"
        + "0 D D D D 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H H B B H M \n\n");
    expectedOutput.append("Please enter 6 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 0 0 0 \n"
        + "0 H 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n"
        + "H 0 0 0 0 0 \n\n");
    expectedOutput.append("0 M 0 H S H \n"
        + "0 M S S S M \n"
        + "0 H D D H 0 \n"
        + "H C H C C C \n"
        + "D D D H 0 0 \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 5 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 0 0 0 \n"
        + "0 H H 0 0 0 \n"
        + "H H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n"
        + "H H 0 0 0 0 \n\n");
    expectedOutput.append("M M 0 H H H \n"
        + "0 M S H H M \n"
        + "0 H D D H 0 \n"
        + "H C H C C H \n"
        + "D D D H 0 0 \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 4 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 0 0 0 \n"
        + "0 H H 0 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n\n");
    expectedOutput.append("M M M H H H \n"
        + "0 M S H H M \n"
        + "0 H D D H 0 \n"
        + "H C H H H H \n"
        + "D D D H 0 M \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 4 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 H 0 0 \n"
        + "0 H H H 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n"
        + "H H H 0 0 0 \n\n");
    expectedOutput.append("M M M H H H \n"
        + "M M S H H M \n"
        + "M H D D H 0 \n"
        + "H C H H H H \n"
        + "D D H H 0 M \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 4 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 H 0 0 \n"
        + "0 H H H 0 0 \n"
        + "H H H H H 0 \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 0 0 \n\n");
    expectedOutput.append("M M M H H H \n"
        + "M M H H H M \n"
        + "M H D D H 0 \n"
        + "H H H H H H \n"
        + "D H H H 0 M \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 2 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 H 0 0 \n"
        + "0 H H H 0 H \n"
        + "H H H H H 0 \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n\n");
    expectedOutput.append("M M M H H H \n"
        + "M M H H H M \n"
        + "M H H D H 0 \n"
        + "H H H H H H \n"
        + "H H H H 0 M \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 1 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 H 0 0 \n"
        + "0 H H H 0 H \n"
        + "H H H H H H \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n\n");
    expectedOutput.append("M M M H H H \n"
        + "M M H H H M \n"
        + "M H H D H 0 \n"
        + "H H H H H H \n"
        + "H H H H M M \n"
        + "H H H H H M \n\n");
    expectedOutput.append("Please enter 1 shots in the format x, y. Each shot should be on its "
        + "own line\nThe top left cell is coordinate 0, 0 (enter \"STOP\" to quit the game)\n");
    expectedOutput.append("M H 0 H 0 0 \n"
        + "0 H H H 0 H \n"
        + "H H H H H H \n"
        + "H H H 0 H H \n"
        + "H H H 0 H 0 \n"
        + "H H H 0 H 0 \n\n");
    expectedOutput.append("M M M H H H \n"
        + "M M H H H M \n"
        + "M H H H H 0 \n"
        + "H H H H H H \n"
        + "H H H H M M \n"
        + "H H H H H M \n\n");
    expectedOutput.append(tie);

    assertEquals(output.toString(), expectedOutput.toString());
  }
}