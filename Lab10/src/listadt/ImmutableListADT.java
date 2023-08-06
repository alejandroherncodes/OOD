package listadt;

/**
 * This interface represents an immutable list.
 *
 * @param <T> the type of elements in this list
 */
public interface ImmutableListADT<T> extends CommonListADT<T> {
  /**
   * Creates and returns a mutable version of this list.
   *
   * @return a mutable list as a MutableListADT object
   */
  MutableListADT<T> getMutableList();
}
