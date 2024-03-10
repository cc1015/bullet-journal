package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record to represent a Coord Json.
 *
 * @param x x coordinate.
 * @param y y coordinate.
 */
public record CoordJson(
    @JsonProperty("x") int x,
    @JsonProperty("y") int y){
}
