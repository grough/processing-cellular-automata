package com.grough.cellularAutomata;

import processing.awt.PGraphicsJava2D;
import processing.core.PGraphics;

import static processing.core.PApplet.*;
import static processing.core.PConstants.PI;

/**
 * Evolve the value of a rectangular grid of cells.
 */
public abstract class Automaton<T> {
    int frame = 0;
    int cols, rows, col, row;
    Grid<T> grid;
    PGraphicsJava2D graphics;

    /**
     * Construct with a default size.
     */
    protected Automaton() {
        size(16, 16);
    }

    /**
     * Set the size of the grid.
     * @param cols Columns
     * @param rows Rows
     */
    public void size(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        graphics = new PGraphicsJava2D();
        graphics.setSize(this.cols, this.rows);
        grid = new Grid<T>(this.cols, this.rows);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.col = col;
                this.row = row;
                grid.setRaw(col, row, populate());
            }
        }
    }

    /**
     * Calculate the cell values for the next frame by applying the evolve() method to the previous frame.
     * Paint the frame by applying the shade() method for each cell.
     */
    public void next() {
        Grid<T> gridNext = grid.copy();
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                this.col = col;
                this.row = row;
                gridNext.setRaw(col, row, evolve());
            }
        }
        grid = gridNext;
        graphics.beginDraw();
        graphics.clear();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.col = col;
                this.row = row;
                graphics.stroke(shade());
                graphics.point(col, row);
            }
        }
        graphics.endDraw();
        frame = frame + 1;
    }

    /**
     * Get the painted graphics.
     * @return Graphics
     */
    public PGraphics graphics() {
        return graphics;
    }

    /**
     * Get the current cell's state.
     * @return cell value
     */
    protected T self() {
        return grid.get(col, row);
    }

    /**
     * Get a cell value using absolute coordinates.
     * @param col Column
     * @param row Row
     * @return Cell value
     */
    protected T abs(int col, int row) {
        return grid.get(col, row);
    }

    /**
     * Get a cell value using relative coordinates.
     * @param colOffset Relative column address
     * @param rowOffset Relative row address
     * @return Cell value
     */
    protected T rel(int colOffset, int rowOffset) {
        return grid.get(col + colOffset, row + rowOffset);
    }

    /**
     * Get the current cell column number.
     * @return Current cell column number
     */
    protected int col() {
        return col;
    }

    /**
     * Get the current cell row number.
     * @return Current cell row number
     */
    int row() {
        return row;
    }

    /**
     * Check if the current cell is a given address.
     * @return Whether the current cell matche the gvien address
     */
    boolean is(int col, int row) {
        return this.col == col && this.row == row;
    }

    float scale(float v, float a, float b, float c, float d) {
        return (-v * c + c * b + v * d - a * d) / (b - a);
    }

    float x() {
        return scale(col, 0, cols - 1, -1, 1);
    }

    float y() {
        return scale(row, 0, rows - 1, -1, 1);
    }

    float angle() {
        float theta = atan2(y(), x());
        return  theta > 0 ? theta : theta + 2 * PI;
    }

    float radius() {
        float x = x();
        float y = y();
        return sqrt(x * x + y * y);
    }

    int frame() {
        return frame;
    }

    /**
     * Override to set initial cell value.
     * @return Initial cell value
     */
    protected abstract T populate();

    /**
     * Override to evolve cell value.
     * @return New cell value
     */
    protected abstract T evolve();

    /**
     * Override to define cell color.
     * @return Cell color
     */
    protected abstract int shade();

    /**
     * Print grid values
     */
    public void print() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                println(col, row, grid.getRaw(col, row)) ;
            }
        }
    }
}
