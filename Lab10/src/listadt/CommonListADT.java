package listadt;

import java.util.function.Function;

public interface CommonListADT<T> {
  /**
   * Applies a general-purpose map higher-order function to this list and returns a new list of type R.
   *
   * @param converter the function that converts T into R
   * @param <R>       the type of data in the resulting list
   * @return a new list that has the same structure as this list, but with elements of type R
   */
  <R> CommonListADT<R> map(Function<T, R> converter);

  /**
   * Returns the number of objects currently in this list.
   *
   * @return the size of the list
   */
  int getSize();

  /**
   * Retrieves the object at the specified index in this list.
   *
   * @param index the index of the object to be retrieved
   * @return the object at the given index
   * @throws IllegalArgumentException if an invalid index is provided
   */
  T get(int index) throws IllegalArgumentException;
}
