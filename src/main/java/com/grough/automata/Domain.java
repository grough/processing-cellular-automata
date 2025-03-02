package com.grough.automata;

/**
 * Normalize coordinates of a grid with columns and rows.
 */
class Domain {
    int cols, rows;
    boolean minor = true;
    Scale xScale, yScale;
    float dither = 0.f;
    float pad = 0.f;

    Domain() {
        size(16, 16);
    }

    /**
     * Set the grid size.
     *
     * @param cols Number of columns in the grid
     * @param rows Number of rows in the grid
     */
    public void size(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;

        int min = Math.min(cols, rows);
        float xAspect = cols / (float) min;
        float yAspect = rows / (float) min;

        float range = 2.f; // -1..1
        pad = range / 2.f / min;

        xScale = new Scale(0, cols - 1, -xAspect + pad, xAspect - pad);
        yScale = new Scale(0, rows - 1, -yAspect + pad, yAspect - pad);
    }

    /**
     * Set domain dither amount.
     * @param amount Dither amount between 0..1
     */
    public void dither(float amount) {
        dither = amount;
    }

    float fuzz() {
        return dither * ((float)Math.random() * 2.f * pad - pad);
    }

    /**
     * Get the X coordinate of a column.
     *
     * @param column Column number
     * @return X coordinate value
     */
    public float x(int column) {
        return xScale.rescale((float) column) + fuzz();
    }

    /**
     * Get the Y coordinate of a row.
     *
     * @param row Row number
     * @return Y coordinate value
     */
    public float y(int row) {
        return yScale.rescale((float) row) + fuzz();
    }
}
