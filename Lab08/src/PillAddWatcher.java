import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Pill add watcher/monitor to add/remove breakpoints without changing other code.
 */
public class PillAddWatcher extends PillCounterDecorator {

  private List<Integer> addCounts;

  /**
   * Constructs a {@code PillAddMonitor} object
   * @param d                  the pill counter
   */
  public PillAddWatcher(PillCounter d) {
    super(d);
    addCounts = new ArrayList<Integer>();
    addCounts.add(0);
  }

  /**
   * Gets the full list of add counts
   * @return ArrayList    list of all addCounts
   */
  public List<Integer> getAddCounts() {
    return new ArrayList<Integer>(addCounts);
  }

  /**
   * Adds one pill to the list of addCounts
   * @param count     one count to be added
   */
  @Override
  public void addPill(int count) {
    addCounts.set(addCounts.size() - 1, addCounts.get(addCounts.size() - 1) + 1);
    super.addPill(count);
  }

  /**
   * Resets/clears the list of addCounts
   */
  @Override
  public void reset() {
    addCounts.add(0);
    super.reset();
  }
}