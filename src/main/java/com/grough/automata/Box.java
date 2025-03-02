package com.grough.automata;

/**
 * Box with four corners.
 */
public class Box {
    float x1, y1, x2, y2, w, h;

    Box(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.w = x2 - x1;
        this.h = y2 - y1;
    }
}
