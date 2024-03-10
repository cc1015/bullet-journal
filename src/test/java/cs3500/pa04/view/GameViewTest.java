package cs3500.pa04.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import mock.MockAppendable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for GameView class.
 */
class GameViewTest {
  Appendable output;
  GameView view;

  /**
   * Initializes data.
   */
  @BeforeEach
  public void initData() {
    output = new StringBuilder();
    view = new GameView(output);
  }

  /**
   * Checks that view appends board to the output
   */
  @Test
  public void testDisplayBoard() {
    StringBuilder testBoardString = new StringBuilder();
    // for each cell, append to the output string and append a space after.
    // for each row, append a new line after.
    for (int i = 0; i < 4; i += 1) {
      for (int j = 0; j < 4; j += 1) {
        testBoardString.append('0');
        testBoardString.append(" ");
      }
      testBoardString.append("\n");
    }

    view.displayBoard(testBoardString.toString());
    assertEquals(output.toString(), testBoardString.toString() + "\n");
  }

  /**
   * Checks that display welcome appends the correct message to the output.
   */
  @Test
  public void testWelcome() {
    view.displayWelcome();
    assertEquals(output.toString(), "Welcome to BattleSalvo Game!\n");
  }

  /**
   * Checks that displayShipOptions appends correct message to the output for 7 ships.
   */
  @Test
  public void testShipOptions() {
    view.displayShipOptions(7);
    assertEquals(output.toString(), "Please enter your fleet in the order [Carrier "
        + "Battleship Destroyer Submarine].\nRemember, your fleet may not exceed size 7"
        + " (enter \"STOP\" to quit the game)\n");
  }

  /**
   * Checks that displayShipOptionsError appends correct message to the output for 7 ships.
   */
  @Test
  public void testDisplayShipOptionsError() {
    view.displayShipOptionsError(7);
    assertEquals(output.toString(), "Please make sure you fleet size is 7!\n");
  }

  /**
   * Checks that displayBoardOptions appends correct message to output.
   */
  @Test
  public void testDisplayBoardOptions() {
    view.displayBoardOptions();
    assertEquals(output.toString(), "Please enter a board height and width any size between "
        + "6 and 15 inclusive (height width):\n");
  }

  /**
   * Checks that displayBoardOptionsError appends correct message to output.
   */
  @Test
  public void testDisplayBoardOptionsError() {
    view.displayBoardOptionsError();
    assertEquals(output.toString(), "Please enter dimensions that are between 6 and 15 "
        + "inclusive!\n");
  }

  /**
   * Checks that displayNumberEntryError appends correct message to output.
   */
  @Test
  public void testDisplayNumberEntryError() {
    view.displayNumberEntryError();
    assertEquals(output.toString(), "Please enter your inputs in the correct format and "
        + "as numbers!\n");
  }

  /**
   * Checks that displayShootingOption appends the correct message to output.
   */
  @Test
  public void testDisplayShootingOption() {
    view.displayShootingOption(4);
    assertEquals(output.toString(), "Please enter 4 shots in the format x, y. "
        + "Each shot should be on its own line\nThe top left cell is coordinate 0, 0"
        + " (enter \"STOP\" to quit the game)\n");
  }

  /**
   * Checks error throwing for appendable output in view.
   */
  @Test
  public void testError() {
    GameView error = new GameView(new MockAppendable());

    Exception e = assertThrows(RuntimeException.class, () ->
            error.displayShootingOption(1), "Mock throwing an error");
    assertEquals("java.io.IOException: Mock throwing an error", e.getMessage());
  }
}