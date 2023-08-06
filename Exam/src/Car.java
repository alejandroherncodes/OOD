// Represents any Car
public class Car extends AVehicle {
  BodyType bodyType;

  /** Compiles a Car and its fields
   *
   * @param id                  the id of the Vehicle
   * @param manufacturingYear   the year it was made
   * @param makeModel           the make and model
   * @param msrp                the msrp
   * @param bodyType            the body type of the car
   */
  public Car(String id, Integer manufacturingYear, MakeModel makeModel,
             Float msrp, BodyType bodyType) {
    super(id, manufacturingYear, makeModel, msrp);
    this.bodyType = bodyType;
  }

  /** Discounts or bumps the base price of this vehicle
   *
   * @return Float         the new price as a float
   */
  public Float estimatePrice() {
    Float basePrice = super.estimatePrice();

    // Rare New Car bump
    if (this instanceof NewCar) {
      if (((NewCar) this).getNumAvailable() < 15) {
        basePrice *= 1.2f;
      }
    }
    // Used Convertible Bump
    if (this instanceof UsedCar) {
      if (((UsedCar) this).getBodyType().equals(BodyType.Convertible)) {
        basePrice *= 1.6f;
      }
    }

    return basePrice;
  }

  public BodyType getBodyType() {
    return bodyType;
  }

 /**
   * This enum represents the body type of a Car. Every car can be a Sedan,
   * SUV, Hatchback, Van, Truck, or a Convertible
   */
  public enum BodyType {Sedan, SUV, Hatchback, Van, Truck, Convertible}

  public void setBodyType(BodyType bodyType) {
    this.bodyType = bodyType;
  }
}

