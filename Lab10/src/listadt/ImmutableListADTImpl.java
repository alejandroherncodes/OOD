package listadt;

import java.util.function.Function;

/**
 * This class represents an immutable list of objects.
 *
 * @param <T> the type of elements in this list
 */
public class ImmutableListADTImpl<T> implements ImmutableListADT<T> {

  private ListADT<T> list;

  /**
   * Constructs an object for ImmutableListADTImpl.
   */
  private ImmutableListADTImpl() {
    list = new ListADTImpl();
  }

  @Override
  public int getSize() {
    return list.getSize();
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return list.get(index);
  }

  @Override
  public <R> ImmutableListADT<R> map(Function<T, R> converter) {
    ListADT<R> result = list.map(converter);
    ImmutableListADTImpl<R> resultList = new ImmutableListADTImpl<>();
    for (int i = 0; i < result.getSize(); i++) {
      resultList.addBack(result.get(i));
    }
    return resultList;
  }

  /**
   * Adds the data to the back of the list.
   *
   * @param data the data to be added
   */
  private void addBack(T data) {
    list.addBack(data);
  }

  public static <R> ImmutableListBuilder<R> getBuilder() {
    return new ImmutableListBuilder<>();
  }

  @Override
  public MutableListADT<T> getMutableList() {
    MutableListADT<T> result = new MutableListADTImpl<>();
    for (int i = 0; i < list.getSize(); i++) {
      result.addBack(list.get(i));
    }
    return result;
  }

  /**
   * This class acts as a builder for the above class.
   *
   * @param <T> the type of elements whose list is to be built
   */
  public static class ImmutableListBuilder<T> {

    private ImmutableListADTImpl<T> iList;

    private ImmutableListBuilder() {
      iList = new ImmutableListADTImpl<>();
    }

    /**
     * Adds an element to the back of this list.
     *
     * @param data the element to be added
     * @return the builder
     */
    public ImmutableListBuilder<T> add(T data) {
      iList.addBack(data);
      return this;
    }

    /**
     * Returns the immutable list built using this builder.
     *
     * @return the immutable list
     */
    public ImmutableListADT<T> build() {
      return iList;
    }
  }
}
