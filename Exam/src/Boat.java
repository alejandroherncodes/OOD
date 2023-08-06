// Represents a Boat
public class Boat extends Vessel {
  private boolean trailable;
  private PropulsionType propulsionType;
  private BoatType boatType;

  public Boat(String id, Integer manufacturingYear, MakeModel makeModel, Float msrp,
              Float length, Integer passengers, boolean trailable,
              PropulsionType propulsionType, BoatType boatType) {
    super(id, manufacturingYear, makeModel, msrp, length, passengers);
    this.trailable = trailable;
    this.propulsionType = propulsionType;
    this.boatType = boatType;
  }

  /** Discounts or bumps the price of this vehicle
   *
   * @return Float         the new price as a float
   */
  public Float estimatePrice() {
    Float price = super.estimatePrice();

    if (!isTrailable() && getBoatType().equals(BoatType.FishingBoats)) {
      price *= 0.9f;
    }

    if (getLength() > 18 && getPassengers() < 5) {
      price *= 0.75f;
    }

    return price;
  }

  public boolean isTrailable() {
    return trailable;
  }

  public PropulsionType getPropulsionType() {
    return propulsionType;
  }

  public BoatType getBoatType() {
    return boatType;
  }

  public void setTrailable(boolean trailable) {
    this.trailable = trailable;
  }

  public void setPropulsionType(PropulsionType propulsionType) {
    this.propulsionType = propulsionType;
  }

  public void setBoatType(BoatType boatType) {
    this.boatType = boatType;
  }
}
