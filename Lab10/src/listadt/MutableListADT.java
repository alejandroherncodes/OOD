package listadt;

/**
 * This interface represents a mutable list.
 * @param <T> the type of element in this list
 */
public interface MutableListADT<T> extends ListADT<T> {

  /**
   * Creates and returns an immutable version of this list
   * @return a mutable list as a ListADT object
   */
  ImmutableListADT<T> getImmutableList();
}