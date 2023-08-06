package ledger;

/**
 * This class represents a single date.
 */
public class SimpleDate {
  private final int date;
  private final int month;
  private final int year;

  /**
   * Create a ledger.SimpleDate object from the given date, month and year
   * @param d the date of the object
   * @param m the month of the object, starting from 1
   * @param y the year of the object
   */
  public SimpleDate(int d,int m,int y) {
    this.date = d;
    this.month = m;
    this.year = y;
  }

  /**
   * Create a ledger.SimpleDate object that is a copy of the given date
   * @param other the ledger.SimpleDate object that must be equal to this
   */
  public SimpleDate(SimpleDate other) {
    this.date = other.date;
    this.month = other.month;
    this.year = other.year;
  }

  /**
   * Get the date of this object
   * @return the date of this object
   */
  public int getDate() {return this.date;}

  /**
   * Get the month of this object
   * @return the month of this object, between 1 and 12
   */
  public int getMonth() {return this.month;}

  /**
   * Get the year of this object
   * @return the year of this object
   */
  public int getYear() {return this.year;}

}
