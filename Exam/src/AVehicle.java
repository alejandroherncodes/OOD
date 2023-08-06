// This abstract class represents any Vehicle
public abstract class AVehicle {
  private String id;
  private Integer manufacturingYear;
  private MakeModel makeModel;
  private Float msrp;

  /** Compiles a vehicle and its fields
   *
   * @param id                  the id of the Vehicle
   * @param manufacturingYear   the year it was made
   * @param makeModel           the make and model
   * @param msrp                the msrp
   */
  public AVehicle(String id, Integer manufacturingYear, MakeModel makeModel, float msrp) {
    this.id = id;
    if (2023 < manufacturingYear) {
      throw new IllegalArgumentException("Vehicle doesn't exist yet");
    } else {
      this.manufacturingYear = manufacturingYear;
    }
    this.makeModel = makeModel;
    if (msrp < 0) {
      throw new IllegalArgumentException("No MSRP.");
    } this.msrp = msrp;
  }

  /** Generates the base price of this vehicle
   *
   * @return Float         the base price as a float
   */
  public Float estimatePrice() {
    if (this.manufacturingYear >= 2023) {
      return this.msrp;
      // Any year after or equal to the current year will calculate a price
    } else {                        // Age Discount Factor
      return (Float) (this.msrp - (0.2f * (2023 - this.manufacturingYear)));
    }
  }

  public String getId() {
    return id;
  }

  public Integer getManufacturingYear() {
    return manufacturingYear;
  }

  public MakeModel getMakeModel() {
    return makeModel;
  }

  public Float getMsrp() {
    return msrp;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setManufacturingYear(Integer manufacturingYear) {
    this.manufacturingYear = manufacturingYear;
  }

  public void setMakeModel(MakeModel makeModel) {
    this.makeModel = makeModel;
  }

  public void setMsrp(Float msrp) {
    this.msrp = msrp;
  }
}

