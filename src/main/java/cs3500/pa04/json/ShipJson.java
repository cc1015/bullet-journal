package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record to represents a Ship JSON.
 *
 * @param coord The starting coordinate of the ship.
 * @param length The length of the ship.
 * @param direction The direction of the ship.
 */
public record ShipJson(
    @JsonProperty("coord") CoordJson coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction) {
}
