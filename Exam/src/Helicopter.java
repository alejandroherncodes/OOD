// Represents a Helicopter
public class Helicopter extends Aircraft {

  /** Compiles a Helicopter and its fields
   *
   * @param id                  the id of the Vehicle
   * @param manufacturingYear   the year it was made
   * @param makeModel           the make and model
   * @param msrp                the msrp
   * @param numQFlights         the number of qualified flights
   */
  public Helicopter(String id, int manufacturingYear, MakeModel makeModel, float msrp,
                    int numQFlights) {
    super(id, manufacturingYear, makeModel, msrp, numQFlights);
  }

  /** Discounts or bumps the price of this vehicle
   *
   * @return Float         the new price as a float
   */
  public Float estimatePrice() {
    Float price = super.estimatePrice();

    // A Few Flights Bump
    if (getNumQualifiedFlights() < 100) {
      price *= 1.3f;
    }

    // Helicopter w/ Lots of Flights Discount
    if (getNumQualifiedFlights() > 1500) {
      price = (price/1.3f);
    }

    return price;
  }
}
