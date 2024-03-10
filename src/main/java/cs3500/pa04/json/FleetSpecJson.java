package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.model.ShipType;
import java.util.EnumMap;
import java.util.Map;

/**
 * Record to represent a fleet specification map.
 *
 * @param carrierCount    The number of carriers.
 * @param battleshipCount The number of battleships.
 * @param destroyerCount  The number of destroyer.
 * @param subCount        The number of submarines.
 */
public record FleetSpecJson(
    @JsonProperty("CARRIER") int carrierCount,
    @JsonProperty("BATTLESHIP") int battleshipCount,
    @JsonProperty("DESTROYER") int destroyerCount,
    @JsonProperty("SUBMARINE") int subCount) {

  /**
   * Returns this FleetSpecJson as a map.
   *
   * @return This FleetSpecJson as a map of ShipType to Integer.
   */
  public Map<ShipType, Integer> makeMap() {
    Map<ShipType, Integer> specs = new EnumMap<>(ShipType.class);
    specs.put(ShipType.CARRIER, carrierCount);
    specs.put(ShipType.BATTLESHIP, battleshipCount);
    specs.put(ShipType.DESTROYER, destroyerCount);
    specs.put(ShipType.SUBMARINE, subCount);

    return specs;
  }

}
