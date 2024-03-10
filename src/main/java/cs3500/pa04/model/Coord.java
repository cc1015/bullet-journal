package cs3500.pa04.model;

/**
 * Represents a coordinate on a Battle Salvo board.
 */
public class Coord {
  private int coordX;
  private int coordY;

  /**
   * Sets x and y to the given x and y values.
   *
   * @param x The x value.
   * @param y The y value.
   */
  public Coord(int x, int y) {
    this.coordX = x;
    this.coordY = y;
  }

  /**
   * Returns this x value.
   *
   * @return This Coord's x value.
   */
  public int getX() {
    return this.coordX;
  }

  /**
   * Returns this y value.
   *
   * @return This Coord's y value.
   */
  public int getY() {
    return this.coordY;
  }

  /**
   * Overriding equals() method to return true if x and y values are equal.
   *
   * @param other The other object being compared to.
   * @return Whether this Coord and the given object are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Coord)) {
      return false;
    }
    Coord that = (Coord) other;
    return this.coordX == that.getX() && this.coordY == that.getY();
  }

  @Override
  public String toString() {
    return this.coordX + " " + this.coordY;
  }
}
