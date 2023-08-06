// Represents a Used Car
public class UsedCar extends Car {
  private Integer mileage;
  private Integer previousOwners;
  private Integer minorAccidents;
  private boolean certifiedPreOwned;

  /** Compiles a Car and its fields
   *
   * @param id                  the id of the Vehicle
   * @param manufacturingYear   the year it was made
   * @param makeModel           the make and model
   * @param msrp                the msrp
   * @param bodyType            the body type of the car
   * @param mileage             the mileage on this car
   * @param previousOwners      the # of previous owners
   * @param minorAccidents      the # of minor accidents this car has been thru.
   * @param certifiedPreOwned   true if certifiedPreOwned, false if not.
   */
  public UsedCar(String id, Integer manufacturingYear, MakeModel makeModel, Float msrp,
                 BodyType bodyType, Integer mileage, Integer previousOwners,
                 Integer minorAccidents, boolean certifiedPreOwned) {
    super(id, manufacturingYear, makeModel, msrp, bodyType);
    if (mileage < 0) {
      throw new IllegalArgumentException("mileage can not be less than 0");
    } else {
      this.mileage = mileage;
    }
    if (previousOwners < 1) {
      throw new IllegalArgumentException("previousOwners can not be less than 0");
    } else {
      this.previousOwners = previousOwners;
    }
    if (minorAccidents < 0) {
      throw new IllegalArgumentException("minorAccidents can not be less than 0");
    } else {
      this.minorAccidents = minorAccidents;
    }
    this.certifiedPreOwned = certifiedPreOwned;
  }

  /** Discounts or bumps the price of this vehicle
   *
   * @return Float         the new price as a float
   */
  public Float estimatePrice() {
    Float price = super.estimatePrice();

    // Mileage Discount
    if (getMileage() > 300000) {
      price *= 0.75f;
    }

    // Accidents discount
    if (getMinorAccidents() > 2) {
      price *= 0.65f;
    }

    // Not Certified Pre-Owned Discount
    if (!(getPreviousOwners() < 5 && isCertifiedPreOwned())) {
      price *= 0.8f;
    }

    return price;
  }

  public Integer getMileage() {
    return mileage;
  }

  public Integer getPreviousOwners() {
    return previousOwners;
  }

  public Integer getMinorAccidents() {
    return minorAccidents;
  }

  public boolean isCertifiedPreOwned() {
    return certifiedPreOwned;
  }

  public void setMileage(Integer mileage) {
    this.mileage = mileage;
  }

  public void setPreviousOwners(Integer previousOwners) {
    this.previousOwners = previousOwners;
  }

  public void setMinorAccidents(Integer minorAccidents) {
    this.minorAccidents = minorAccidents;
  }

  public void setCertifiedPreOwned(boolean certifiedPreOwned) {
    this.certifiedPreOwned = certifiedPreOwned;
  }
}
