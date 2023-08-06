// Represents a New Car
public class NewCar extends Car {
  private Integer numAvailable;

  /** Compiles a Car and its fields
   *
   * @param id                  the id of the Vehicle
   * @param manufacturingYear   the year it was made
   * @param makeModel           the make and model
   * @param msrp                the msrp
   * @param bodyType            the body type of the car
   * @param numAvailable        the # of identical cars available nearby
   */
  public NewCar(String id, Integer manufacturingYear, MakeModel makeModel, Float msrp,
                BodyType bodyType, Integer numAvailable) {
    super(id, manufacturingYear, makeModel, msrp, bodyType);
    if (numAvailable < 0) {
      throw new IllegalArgumentException("numAvailable can not be less than 0");
    } else {
      this.numAvailable = numAvailable;
    }
  }

  /** Discounts or bumps the price of this vehicle
   *
   * @return Float         the new price as a float
   */
  public Float estimatePrice() {
    Float price = super.estimatePrice();

    // Convertible Bump
    if (getBodyType().equals(BodyType.Convertible)) {
      price *= 1.6f;
    }

    return price;
  }

  public Integer getNumAvailable() {
    return numAvailable;
  }

  public void setNumAvailable(Integer numAvailable) {
    this.numAvailable = numAvailable;
  }
}