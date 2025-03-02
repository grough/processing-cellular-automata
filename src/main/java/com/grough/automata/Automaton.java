package com.grough.automata;

import processing.awt.PGraphicsJava2D;

import static processing.core.PApplet.*;
import static processing.core.PConstants.PI;

/**
 * Evolve the value of a rectangular grid of cells.
 */
public abstract class Automaton<T> {
    int frame = 0;
    int cols, rows, col, row;
    int width, height;
    Grid<T> grid;
    PGraphicsJava2D graphics;
    Domain domain;

    /**
     * Construct with a default size.
     */
    protected Automaton() {
        domain = new Domain();
        size(16, 16);
        dimensions(640, 640);
    }

    /**
     * Set the size of the grid.
     *
     * @param cols Columns
     * @param rows Rows
     */
    public void size(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        domain.size(cols, rows);
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
     * Set the output dimensions.
     *
     * @param width  Output width
     * @param height Output height
     */
    public void dimensions(int width, int height) {
        this.width = width;
        this.height = height;
        graphics = new PGraphicsJava2D();
        graphics.setSize(this.width, this.height);
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

                graphics.noStroke();
                graphics.fill(shade());

                Box b = box();
                graphics.rect(b.x1, b.y1, b.w, b.h);
            }
        }
        graphics.endDraw();
        frame = frame + 1;
    }

    private Box box() {
        float boxWidth = (float) width / cols;
        float boxHeight = (float) height / rows;
        float x1 = col() * boxWidth;
        float y1 = row() * boxHeight;
        float x2 = x1 + boxWidth;
        float y2 = y1 + boxHeight;
        return new Box(x1, y1, x2, y2);
    }

    /**
     * Get the value of a cell.
     *
     * @param col Column number
     * @param row Row number
     * @return Cell value at col, row
     */
    public T get(int col, int row) {
        return grid.get(col, row);
    }

    /**
     * Get the painted graphics.
     *
     * @return Graphics
     */
    public PGraphicsJava2D graphics() {
        return graphics;
    }

    /**
     * Get the current cell's state.
     *
     * @return cell value
     */
    protected T self() {
        return grid.get(col, row);
    }

    /**
     * Get a cell value using absolute coordinates.
     *
     * @param col Column
     * @param row Row
     * @return Cell value
     */
    protected T abs(int col, int row) {
        return grid.get(col, row);
    }

    /**
     * Get a cell value using relative coordinates.
     *
     * @param colOffset Relative column address
     * @param rowOffset Relative row address
     * @return Cell value
     */
    protected T rel(int colOffset, int rowOffset) {
        return grid.get(col + colOffset, row + rowOffset);
    }

    /**
     * Get the current cell column number.
     *
     * @return Current cell column number
     */
    protected int col() {
        return col;
    }

    /**
     * Get the current cell row number.
     *
     * @return Current cell row number
     */
    protected int row() {
        return row;
    }

    /**
     * Check if the current cell is a given address.
     *
     * @param col Column to check
     * @param row Row to check
     * @return Whether the current cell matches the given address
     */
    protected boolean is(int col, int row) {
        return this.col == col && this.row == row;
    }

    /**
     * Get the bipolar X coordinate of the cell.
     *
     * @return X coordinate
     */
    protected float x() {
        return domain.x(col);
    }

    /**
     * Get the bipolar Y coordinate of the cell.
     *
     * @return Y coordinate
     */
    protected float y() {
        return domain.y(row);
    }

    /**
     * Get the angle of the cell.
     *
     * @return Angle in radians
     */
    protected float angle() {
        float theta = atan2(y(), x());
        return theta > 0 ? theta : theta + 2 * PI;
    }

    /**
     * Set amout of coordinate fuzzing.
     *
     * @param amount Fuzz amount betweem 0..1
     */
    public void fuzz(float amount) {
        domain.dither(amount);
    }

    /**
     * Get the cell's distance from the origin.
     *
     * @return Radius
     */
    protected float radius() {
        float x = x();
        float y = y();
        return sqrt(x * x + y * y);
    }

    /**
     * Get the current frame number
     *
     * @return Frame number
     */
    protected int frame() {
        return frame;
    }

    /**
     * Override to set initial cell value.
     *
     * @return Initial cell value
     */
    protected abstract T populate();

    /**
     * Override to evolve cell value.
     *
     * @return New cell value
     */
    protected abstract T evolve();

    /**
     * Override to define cell color.
     *
     * @return Cell color
     */
    protected abstract int shade();

    /**
     * Print grid values
     */
    public void print() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                println(col, row, grid.getRaw(col, row));
            }
        }
    }
}
