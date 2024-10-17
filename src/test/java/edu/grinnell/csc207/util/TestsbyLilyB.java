package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import javax.imageio.spi.ImageWriterSpi;
import org.junit.jupiter.api.Test;

/**e
 * A variety of tests for the Matrix class.
 *
 * @author Lily Blanchard
 */
class TestsbyLilyB {

  /**
   * Tests for MatrixV0.set(int, int, T) and MatrixV0.get(int, int).
   */
  @Test
  public void testGetAndSet() throws ArraySizeException {
    Matrix<Integer> testA = new MatrixV0(4, 5);
    testA.set(2, 3, 4);
    assertEquals(4, testA.get(2, 3), "get after set");
    assertEquals(null, testA.get(2, 2), "get unset cell");
    assertMatrixEquals(
        new Integer[][]
            {{null, null, null, null},
             {null, null, null, null},
             {null, null, null, null},
             {null, null, 4, null},
             {null, null, null, null}},
        testA,
        "set cell (2,3)");
    try {
      testA.get(5, 7);
      System.err.println("invalid get() did not throw exception");
    } catch (IndexOutOfBoundsException e) {
    } // try/catch
  } // testGetAndSet()

  /**
   * Tests for MatrixV0.insertRow(int) and MatrixV0.insertCol(int).
   */
  @Test
  public void testInsertRowAndColDef() throws ArraySizeException {
    Matrix<String> testC = new MatrixV0(3, 4);
    assertMatrixEquals(
        new String[][]
            {{null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null}},
        testC,
        "declaration status");
    testC.insertRow(1);
    assertMatrixEquals(
        new String[][]
            {{null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null},
             {null, null, null}},
        testC,
        "after add row");
    testC.insertCol(2);
    assertMatrixEquals(
        new String[][]
            {{null, null, null, null},
             {null, null, null, null},
             {null, null, null, null},
             {null, null, null, null},
             {null, null, null, null}},
        testC,
        "after add col");
  } // testInsertRowAndColDef

  /**
   * Tests for MatrixV0.insertRow(int, T[]) and MatrixV0.insertCol(int, T[]).
   */
  @Test
  public void testInsertRowCust() throws ArraySizeException {
    Matrix<String> testD = new MatrixV0(5, 3, "X");
    assertMatrixEquals(
        new String[][]
            {{"X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X"}},
        testD,
        "declaration status, custom default value");
    testD.insertRow(2, new String[] { "A", "B", "C", "D", "E" });
    assertMatrixEquals(
        new String[][]
            {{"X", "X", "X", "X", "X"},
            {"X", "X", "X", "X", "X"},
            {"A", "B", "C", "D", "E"},
            {"X", "X", "X", "X", "X"}},
        testD,
        "add custom row");
    testD.insertCol(4, new String[] {"W", "X", "Y", "Z"});
    assertMatrixEquals(
        new String[][]
            {{"X", "X", "X", "X", "W", "X"},
            {"X", "X", "X", "X", "X", "X"},
            {"A", "B", "C", "D", "Y", "E"},
            {"X", "X", "X", "X", "Z", "X"}},
        testD,
        "add custom col");
  } // testInsertRowCust()

  /**
   * Tests for MatrixV0.deleteRow(int) and MatrixV0.deleteCol(int).
   */
  @Test
  public void testDeleteRowAndCol() throws ArraySizeException {
    Matrix<Integer> testE = new MatrixV0(5, 5);
    testE.set(1, 2, 3);
    testE.set(4, 3, 6);
    assertMatrixEquals(
        new Integer[][]
            {{null, null, null, null, null},
            {null, null, 3, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, 6, null}},
        testE,
        "declaration status");
    testE.deleteRow(1);
    assertMatrixEquals(
        new Integer[][]
            {{null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, 6, null}},
        testE,
        "delete row");
    testE.deleteCol(3);
    assertMatrixEquals(
      new Integer[][]
          {{null, null, null, null},
          {null, null, null, null},
          {null, null, null, null},
          {null, null, null, null}},
      testE,
      "delete row");
  } // testDeleteRowAndCol()

  /**
   * Tests for MatrixV0.height() and MatrixV0.width().
   */
  @Test
  public void testHeightAndWidth() throws ArraySizeException {
    Matrix<String> testB = new MatrixV0(3, 5);
    assertEquals(5, testB.height(), "height from declaration");
    assertEquals(3, testB.width(), "width from declaration");
    testB.insertRow(2);
    testB.insertCol(3);
    assertEquals(6, testB.height(), "height after insert row");
    assertEquals(4, testB.width(), "width after insert column");
    testB.deleteRow(1);
    assertEquals(5, testB.height(), "height after remove row");
    assertEquals(4, testB.width(), "width after remove row");
    testB.deleteCol(2);
    assertEquals(5, testB.height(), "height after remove column");
    assertEquals(3, testB.width(), "width after remove column");
  } // testHeightAndWidth()

  /**
   * Tests for MatrixV0.fillRegion(int, int, int, int, T).
   */
  @Test
  public void testFillRegion() throws ArraySizeException {
    Matrix<String> testF = new MatrixV0(4, 6);
    testF.fillRegion(1, 1, 3, 4, "G");
    assertMatrixEquals(
      new String[][]
          {{null, null, null, null, null, null},
          {null, "G", "G", "G", null, null},
          {null, "G", "G", "G", null, null},
          {null, null, null, null, null, null}},
      testF,
      "fill middle chunk");
    testF.fillRegion(0, 3, 2, 6, "R");
    assertMatrixEquals(
      new String[][]
          {{null, null, null, "R", "R", "R"},
          {null, "G", "G", "R", "R", "R"},
          {null, "G", "G", "G", "G", null},
          {null, null, null, null, null, null}},
      testF,
      "fill top left corner");
  } // testFillRegion()

  /**
   * Tests for MatrixV0.fillLine(int, int, int, int, int, int, T).
   */
  @Test
  public void testFillLine() throws ArraySizeException {
    Matrix<String> testG = new MatrixV0(6, 6);
    assertMatrixEquals(
      new String[][]
          {{null, null, null, null, null, null},
          {null, null, null, null, null, null},
          {null, null, null, null, null, null},
          {null, null, null, null, null, null},
          {null, null, null, null, null, null},
          {null, null, null, null, null, null}},
      testG,
      "declaration status");
    testG.fillLine(2, 1, 1, 2, 6, 6, "H");
    assertMatrixEquals(
      new String[][]
          {{null, null, null, null, null, null},
          {null, null, null, null, null, null},
          {null, "H", null, null, null, null},
          {null, null, null, "H", null, null},
          {null, null, null, null, null, "H"},
          {null, null, null, null, null, null}},
      testG,
      "down 1, over 2");
    testG.fillLine(4, 2, -1, 1, 6, 6, "T");
    assertMatrixEquals(
      new String[][]
          {{null, null, null, null, null, null},
          {null, null, null, null, null, "T"},
          {null, "H", null, null, "T", null},
          {null, null, null, "T", null, null},
          {null, null, "T", null, null, "H"},
          {null, null, null, null, null, null}},
      testG,
      "up 1, over 1");
    testG.fillLine(0, 0, 1, 2, 6, 6, "M");
    assertMatrixEquals(
      new String[][]
          {{"M", null, null, null, null, null},
          {null, null, null, null, null, "T"},
          {null, "M", null, null, "T", null},
          {null, null, null, "T", null, null},
          {null, null, "M", null, null, "H"},
          {null, null, null, null, null, null}},
      testG,
      "down 2, over 1");
  } // testFillLine()

  /**
   * Tests for MatrixV0.clone() and MatrixV0.equals(Object).
   */
  @Test
  public void testCloneAndEquals() throws ArraySizeException {
    Matrix<Integer> testH = new MatrixV0(3, 3);
    testH.set(1, 2, 7);
    testH.set(0, 1, 4);
    testH.insertCol(2, new Integer[] {7, 8, 9});
    Matrix<Integer> testI = testH.clone();
    assertMatrixEquals(
      new Integer[][]
          {{null, 4, 7, null},
          {null, null, 8, 7},
          {null, null, 9, null}},
      testI,
      "clone");

    assertEquals(true, testH.equals(testI), "clones are equal");
    Matrix<String> testJ = new MatrixV0(3, 3);
    assertEquals(false, testH.equals(testJ), "same size, different T");
    String sample = "hello world";
    assertEquals(false, testH.equals(sample), "not a matrix");
  } // testCloneAndEquals()
} // class TestsbyLilyB
