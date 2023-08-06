package listadt;

import java.util.function.Function;

/**
 * This class represents a mutable list implementation.
 *
 * @param <T> the type of element in this list
 */
public class MutableListADTImpl<T> extends ListADTImpl<T> implements MutableListADT<T> {

  /**
   * Constructs an empty MutableListADTImpl.
   */
  public MutableListADTImpl() {
    super();
  }

  /**
   * Constructs a MutableListADTImpl with the elements from the given list.
   *
   * @param list the list to copy elements from
   */
  private MutableListADTImpl(ListADT<T> list) {
    for (int i = 0; i < list.getSize(); i++) {
      this.addBack(list.get(i));
    }
  }

  @Override
  public ImmutableListADT<T> getImmutableList() {
    ImmutableListADTImpl.ImmutableListBuilder<T> builder = ImmutableListADTImpl.getBuilder();
    for (int i = 0; i < getSize(); i++) {
      builder = builder.add(get(i));
    }
    return builder.build();
  }

  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new MutableListADTImpl<>(super.map(converter));
  }
}
