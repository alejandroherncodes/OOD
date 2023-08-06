/**
 * Represents a Decorator (like a wrapper) for Pill Counter
 */
public class PillCounterDecorator implements PillCounter {

  protected PillCounter delegate;

  /**
   * Constructs an instance of a PillCounterDecorator
   *
   * @param d the pill counter
   */
  public PillCounterDecorator(PillCounter d) {
    this.delegate = d;
  }

  /**
   * Add the specific number of pills to this counter. This is a general method to be implemented
   * in different machines with varying pill distributing capabilities.
   *
   * @param count the count to be added
   */
  @Override
  public void addPill(int count) {
    this.delegate.addPill(count);
  }

  /**
   * Remove a pill from this counter. This method is called in the case a malfunction where too
   * many pills are dispensed is detected. This method allows one pill to be removed at a time.
   */
  @Override
  public void removePill() {
    this.delegate.removePill();
  }

  /**
   * Reset the counter.
   */
  @Override
  public void reset() {
    this.delegate.reset();
  }

  /**
   * Return the amount of pills counted so far.
   *
   * @return int     the number of pills counted as an int
   */
  @Override
  public int getPillCount() {
    return this.delegate.getPillCount();
  }
}