package cs3500.pa04;

import cs3500.pa04.controller.BattleSalvoController;
import cs3500.pa04.controller.Controller;
import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.AbPlayer;
import cs3500.pa04.model.AutomatedPlayer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      Readable input = new InputStreamReader(System.in);
      Appendable output = new PrintStream(System.out);
      Controller controller = new BattleSalvoController(input, output, new Random(1));
      controller.executeGame();
    } else if (args.length == 2) {
      int port = Integer.parseInt(args[1]);
      try {
        Socket socket = new Socket(args[0], port);

        AbPlayer computerPlayer = new AutomatedPlayer(null, new Random());
        Controller controller = new ProxyController(socket, computerPlayer);
        controller.executeGame();
      } catch (IOException e) {
        throw new RuntimeException("Could not connect to the server.");
      }
    } else {
      throw new IllegalArgumentException("Incorrect command-line arguments.");
    }
  }
}