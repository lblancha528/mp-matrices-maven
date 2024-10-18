package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Lily Blanchard
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** 2D array where first dimension is rows. */
  T[][] theMatrix;

  /** Number of columns in the matrix. */
  int width;

  /** Number of rows in the matrix. */
  int height;

  /** The default value new cells are set to.
   * null by true default.
   */
  T defaultVal = null;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    this.width = width;
    this.height = height;
    this.defaultVal = def;
    this.theMatrix = (T[][]) new Object[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        theMatrix[i][j] = defaultVal;
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this.width = width;
    this.height = height;
    this.theMatrix = (T[][]) new Object[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        theMatrix[i][j] = defaultVal;
      } // for
    } // for
  } // MatrixV0(int, int)

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row < this.height && col < this.width
    && row > -1 && col > -1) {
      return this.theMatrix[row][col];
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row < this.height && col < this.width
        && row > -1 && col > -1) {
      this.theMatrix[row][col] = val;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row > this.height || row < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      this.height++;
      T[][] newMatrix = (T[][]) new Object[this.height][this.width];
      for (int i = 0; i < this.height; i++) {
        if (i < row) {
          for (int j = 0; j < this.width; j++) {
            newMatrix[i][j] = this.theMatrix[i][j];
          } // for
        } else if (i == row) {
          for (int j = 0; j < this.width; j++) {
            newMatrix[i][j] = defaultVal;
          } // for
        } else {
          // i > row
          for (int j = 0; j < this.width; j++) {
            newMatrix[i][j] = this.theMatrix[i-1][j];
          } // for
        } // if
      } // for
      this.theMatrix = newMatrix;
    } // if
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row > this.height || row < 0) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.width) {
      throw new ArraySizeException();
    } else {
      this.height++;
      T[][] newMatrix = (T[][]) new Object[this.height][this.width];
      for (int i = 0; i < this.height; i++) {
        if (i < row) {
          for (int j = 0; j < this.width; j++) {
            newMatrix[i][j] = this.theMatrix[i][j];
          } // for
        } else if (i == row) {
          for (int j = 0; j < this.width; j++) {
            newMatrix[i][j] = vals[j];
          } // for
        } else {
          // i > row
          for (int j = 0; j < this.width; j++) {
            newMatrix[i][j] = this.theMatrix[i-1][j];
          } // for
        } // if
      } // for
      this.theMatrix = newMatrix;
    } // if
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col > this.width + 1 || col < 0 ) {
      throw new IndexOutOfBoundsException();
    } else {
      this.width++;
      T[][] newMatrix = (T[][]) new Object[this.height][this.width];
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          if (j < col) {
            newMatrix[i][j] = this.theMatrix[i][j];
          } else if (j == col) {
            newMatrix[i][j] = defaultVal;
          } else {
            // j > col
            newMatrix[i][j] = this.theMatrix[i][j-1];
          } // if
        } // for
      } // for
      this.theMatrix = newMatrix;
    } // if
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col > this.width + 1 || col < 0 ) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.height) {
      throw new ArraySizeException();
    } else {
      this.width++;
      T[][] newMatrix = (T[][]) new Object[this.height][this.width];
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          if (j < col) {
            newMatrix[i][j] = this.theMatrix[i][j];
          } else if (j == col) {
            newMatrix[i][j] = vals[i];
          } else {
            // j > col
            newMatrix[i][j] = this.theMatrix[i][j-1];
          } // if
        } // for
      } // for
      this.theMatrix = newMatrix;
    } // if
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (row > this.height || row < 0) {
      throw new IndexOutOfBoundsException();
    } else {
      
      T[][] newMatrix = (T[][]) new Object[this.height - 1][this.width];
      for (int i = 0; i < this.height; i++) {
        if (i < row) {
          for (int j = 0; j < this.width; j++) {
            newMatrix[i][j] = this.theMatrix[i][j];
          } // for
        } else if (i == row) {
          continue;
        } else {
          // i > row
          for (int j = 0; j < this.width; j++) {
            newMatrix[i-1][j] = this.theMatrix[i][j];
          } // for
        } // if
      } // for
      this.height--;
      this.theMatrix = newMatrix;
    } // if
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (col > this.width || col < 0 ) {
      throw new IndexOutOfBoundsException();
    } else {
      
      T[][] newMatrix = (T[][]) new Object[this.height][this.width - 1];
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          if (j < col) {
            newMatrix[i][j] = this.theMatrix[i][j];
          } else if (j == col) {
            continue;
          } else {
            // j > col
            newMatrix[i][j-1] = this.theMatrix[i][j];
          } // if
        } // for
      } // for
      this.width--;
      this.theMatrix = newMatrix;
    } // if
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (startCol > this.width || startCol < 0 
        || startRow > this.height || startRow < 0
        || endCol > this.width || endCol < 0
        || endRow > this.height || endRow < 0
        || endRow < startRow || endCol < startCol) {
      throw new IndexOutOfBoundsException();
    } else {
      T[][] newMatrix = (T[][]) new Object[this.height][this.width];
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          // if i and j are within region range, place defval
          if (i >= startRow && i < endRow
              && j >= startCol && j < endCol) {
                // if (i,j) is a point within the filling region
            newMatrix[i][j] = val;
          } else {
            newMatrix[i][j] = this.theMatrix[i][j];
          } // if
        } // for
      } // for
      this.theMatrix = newMatrix;
    } // if
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if (startCol > this.width || startCol < 0 
        || startRow > this.height || startRow < 0
        || endCol > this.width || endCol < 0
        || endRow > this.height || endRow < 0
        || endRow < startRow || endCol < startCol) {
      throw new IndexOutOfBoundsException();
    } else {
      T[][] newMatrix = (T[][]) new Object[this.height][this.width];
      int p = 0; // number of points placed
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          // if i and j are within region range, place defval
          if (i >= startRow && i < endRow
              && j >= startCol && j < endCol
              && i == (startRow + (p * deltaRow))
              && j == (startCol + (p * deltaCol))) {
                // (i,j) is a point on the line
            newMatrix[i][j] = val;
            p++;
          } else {
            newMatrix[i][j] = this.theMatrix[i][j];
          } // if
        } // for
      } // for
      this.theMatrix = newMatrix;
    } // if
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    T[][] newMatrix = (T[][]) new Object[this.height][this.width];
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
            newMatrix[i][j] = this.theMatrix[i][j];
        } // for
      } // for
    Matrix<T> matrixTwo = new MatrixV0(this.width, this.height, this.defaultVal);
    return matrixTwo;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      other = (Matrix) other;
      if (other.height() != this.height || other.width != this.width)
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
            if (this.theMatrix[i][j] != other.theMatrix[i][j]) {
              return false;
            } // if
        } // for
      } // for
      return true;
    } else {
      return false;
    } // if
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
