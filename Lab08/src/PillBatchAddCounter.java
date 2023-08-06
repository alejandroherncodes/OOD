/**
 * Represents an instance of a Pill Batch Add counter
 */
public class PillBatchAddCounter extends PillCounterDecorator{

  private int pillBatch;

  /**
   * Creates an object for a Pill Batch Add Counter
   * @param d
   */
  public PillBatchAddCounter(PillCounter d) {
    super(d);
  }

  /**
   * Add the specific number of pills to this counter. This method
   * is general enough to work with machines with different pill-filling
   * capacities.
   *
   * @param count the count to be added
   */
  @Override
  public void addPill(int count) {
    if (count > 0) {
      this.pillBatch += count;
      this.delegate.addPill(pillBatch); // directly add to the counter
      this.pillBatch = 0;

    }
  }

  /**
   * Return how many pills have been counted so far.
   *
   * @return the number of pills that have been counted so far
   */
  @Override
  public int getPillCount() {
    if (pillBatch > 0) {
      this.delegate.addPill(pillBatch);
    }
    this.pillBatch = 0;
    return this.delegate.getPillCount();
  }

  /**
   * Reset the counter to 0.
   */
  @Override
  public void reset() {
    if (pillBatch > 0) {
      this.getPillCount();
    }
    this.pillBatch = 0;
    this.delegate.reset();
  }
}