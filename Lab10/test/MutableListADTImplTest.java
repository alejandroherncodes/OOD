import org.junit.Before;
import org.junit.Test;

import listadt.ImmutableListADT;
import listadt.ListADT;
import listadt.MutableListADT;
import listadt.MutableListADTImpl;

import static org.junit.Assert.*;

public class MutableListADTImplTest {
  private MutableListADT<String> stringList;

  @Before
  public void setUp() {
    stringList = new MutableListADTImpl<>();
    stringList.addBack("played");
    stringList.addBack("dog");
    stringList.addBack("with");
    stringList.addBack("me");
    stringList.add(2, "the");
  }

  @Test
  public void testMap() {
    // Convert the list of strings to a list that contains the length of each word in the list
    String sentence = "The quick brown fox jumps over the lazy dog";
    String[] words = sentence.split("\\s+");
    stringList = new MutableListADTImpl<>();
    for (String w : words) {
      stringList.addBack(w);
    }

    ListADT<Integer> wordLengths = stringList.map(String::length);
    assertEquals("The size of the mapped list does not match the original list!",
            stringList.getSize(), wordLengths.getSize());

    for (int i = 0; i < words.length; i++) {
      assertEquals("Word is \"" + stringList.get(i) + "\"", words[i].length(),
              (int) wordLengths.get(i));
    }

    assertEquals("The size of the original list has changed after removing an element!",
            9, stringList.getSize());

    ImmutableListADT<String> immutableWords = stringList.getImmutableList();
    MutableListADT<String> mutableWords = immutableWords.getMutableList();

    mutableWords.remove("dog");
    assertEquals("The size of the immutable list has changed after removing an element!",
            9, immutableWords.getSize());
    assertEquals("The size of the mutable list has changed after removing an element!",
            8, mutableWords.getSize());
  }
}
