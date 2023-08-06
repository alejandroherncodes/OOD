package ledger;

/**
 * This class represents a single ledger item.
 */
public class LedgerItem {
  private final String description;
  private final ItemType type;
  private final int dollars;
  private final int cents;
  private final SimpleDate dateOccurred;

  /**
   * Create a single item given its details.
   * @param description the description of this item
   * @param type the type of this item (expense or income)
   * @param dollars the amount of whole dollars for this item
   * @param cents the amount of whole cents for this item
   * @param date the date when this item occurred
   * @param month the month when this item occurred
   * @param year the year when this item occurred
   * @throws IllegalArgumentException if the description is null, or the dollar or cent amount is negative
   */
  public LedgerItem(String description, ItemType type,int dollars, int cents, int date, int month, int year)
    throws IllegalArgumentException {
    if (description ==null) {
      throw new IllegalArgumentException("Error: Description of item cannot be null");
    }
    if (type==null) {
      throw new IllegalArgumentException("Error: Type of item cannot be null");
    }
    if (dollars<0 || cents<0) {
      throw new IllegalArgumentException("Error: dollar and cent amount of item cannot be negative");
    }
    this.description = description;
    this.type = type;
    this.dollars = dollars;
    this.cents = cents;
    this.dateOccurred = new SimpleDate(date,month,year);
  }

  /**
   * Get the description of this item.
   * @return the description of this item
   */
  public String getDescription() {return description;}

  /**
   * Get the type of this item.
   * @return the type of this item, as an enumeration
   */
  public ItemType getType() { return type;}

  /**
   * Get the total amount of this item.
   * @return the item as a money amount (DDD.CC)
   */
  public double getAmount() {return dollars + (double)cents/100; }

  /**
   * Get the date on which this item occurred.
   * @return the date of this item, as a ledger.SimpleDate object
   */
  public SimpleDate getDateOccurred() { return new SimpleDate(this.dateOccurred);}

  public int getYear() {
    return dateOccurred.getYear();
  }

  public int getMonth() {
    return dateOccurred.getMonth();
  }

  public int getDate() {
    return dateOccurred.getDate();
  }
}
