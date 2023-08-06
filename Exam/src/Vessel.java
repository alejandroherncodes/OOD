// Represents any Vessel
public class Vessel extends AVehicle {
  private Float length;
  private Integer passengers;

  /**
   * This enum represents the type of a boat. Every boat
   * can either be a FishingBoat, Bowrider, CabinCruiser, JetBoat.
   *
   * For inheritance in Boat constructor only.
   */
  public enum BoatType { FishingBoats, Bowriders, CabinCruisers, JetBoats, }

  /**
   * This enum represents the propulsion type of a water Vessel. Every boat can be powered by
   * SailPower, InboardEngine, OutboardEngine, or JetPropulsion
   */
  public enum PropulsionType { SailPower, InboardEngine, OutboardEngine, JetPropulsion, }

  /** Compiles a Vessel and its fields
   *
   * @param id                  the id of the Vessel
   * @param manufacturingYear   the year it was made
   * @param makeModel           the make and model
   * @param msrp                the msrp
   * @param length              the length of the Vessel
   * @param passengers          the max capacity of the Vessel
   */
  public Vessel(String id, Integer manufacturingYear, MakeModel makeModel, Float msrp,
                Float length, Integer passengers) {
    super(id, manufacturingYear, makeModel, msrp);
    if (length <= 0) {
      throw new IllegalArgumentException("Length can't be 0");
    } else {
      this.length = length;
    }
    if (passengers < 0) {
      throw new IllegalArgumentException("Passengers can't be less than 0.");
    } else {
      this.passengers = passengers;
    }
  }

  /** Discounts or bumps the base price of this vehicle
   *
   * @return Float         the new price as a float
   */
  public Float estimatePrice() {
    Float basePrice = super.estimatePrice();

    // Sail Power Bump
    if (this instanceof Boat) {
      if (((Boat) this).getPropulsionType().equals(PropulsionType.SailPower)) {
        basePrice *= 1.25f;
      }
    }

    // 7+ Passengers Bump
    if (getPassengers() >= 7) {
      basePrice *= 1.3f;
    }

    return basePrice;
  }

  public Float getLength() {
    return length;
  }

  public Integer getPassengers() {
    return passengers;
  }

  public void setLength(Float length) {
    this.length = length;
  }

  public void setPassengers(Integer passengers) {
    this.passengers = passengers;
  }
}
