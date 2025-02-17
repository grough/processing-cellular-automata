package com.grough.cellularAutomata;

import java.util.ArrayList;

/**
 * A two-dimensional grid.
 */
public class Grid<T> {
    int cols, rows;
    ArrayList<ArrayList<T>> cells;

    /**
     * Construct ta new grid with a given number of columns and rows.
     * @param cols Number of columns in the grid
     * @param rows Number of rows in the grid
     */
    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        cells = new ArrayList<ArrayList<T>>(this.cols);
        for (int col = 0; col < this.cols; col++) {
            cells.add(new ArrayList<T>(rows));
            for (int row = 0; row < rows; row++) {
                cells.get(col).add(null);
            }
        }
    }

    /**
     * Set value of a grid cell directly.
     * @param col Column to set
     * @param row Row to set
     * @param value Value to set
     */
    public void setRaw(int col, int row, T value) {
        cells.get(col).set(row, value);
    }

    /**
     * Get value of a grid cell directly.
     * @param col Column to get
     * @param row Row to get
     * @return Value at col, row
     */
    public T getRaw(int col, int row) {
        return cells.get(col).get(row);
    }

    /**
     * Set value of a grid cell and wrap around if out of bounds.
     * @param col Column to set
     * @param row Row to set
     * @param value Value to set
     */
    public void set(int col, int row, T value) {
        setRaw(mod(col, cols), mod(row, rows), value);
    }
    /**
     * Get value of a grid cell and wrap around if out of bounds.
     * @param col Column to set
     * @param row Row to set
     * @return Value at col, row
     */
    public T get(int col, int row) {
        return getRaw(mod(col, cols), mod(row, rows));
    }

    /**
     * Make a copy of the grid.
     * @return Copied grid
     */
    public Grid<T> copy() {
        Grid<T> newGrid = new Grid<T>(cols, rows);
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                newGrid.setRaw(col, row, getRaw(col, row));
            }
        }
        return newGrid;
    }

    private int mod(int x, int n) {
        return ((x % n) + n) % n;
    }
}
