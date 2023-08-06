// Represents any Aircraft
public class Aircraft extends AVehicle {
  int numQFlights;

  /** Compiles an Aircraft and its fields
   *
   * @param id                  the id of the Vehicle
   * @param manufacturingYear   the year it was made
   * @param makeModel           the make and model
   * @param msrp                the msrp
   * @param numQFlights         the number of qualified flights
   */
  public Aircraft(String id, int manufacturingYear, MakeModel makeModel, float msrp, int numQFlights) {
    super(id, manufacturingYear, makeModel, msrp);
    if (numQFlights < 0) {
      throw new IllegalArgumentException("numQFlights can't be less than 0");
    } else {
      this.numQFlights = numQFlights;
    }
  }

  /** Discounts or bumps the base price of this vehicle
   *
   * @return Float         the new price as a float
   */
  @Override
  public Float estimatePrice() {
    Float basePrice = getMsrp() * 1.1f;

    // Airplane Bump
    if (this instanceof Airplane) {
        basePrice *= 1.4f;
    }

    return basePrice;
  }

  public int getNumQualifiedFlights() {
    return numQFlights;
  }

  public int getNumQFlights() {
    return numQFlights;
  }

  public void setNumQFlights(int numQFlights) {
    this.numQFlights = numQFlights;
  }
}
