package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ShipType;
import java.util.Map;

/**
 * A record for a setup JSON message.
 *
 * @param width     The width of the board.
 * @param height    The height of the board.
 * @param fleetSpec The ship specifications of the board.
 */
public record SetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") FleetSpecJson fleetSpec) {

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Map<ShipType, Integer> getFleetSpec() {
    return fleetSpec.makeMap();
  }
}
