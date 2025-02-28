package com.grough.automata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScaleTest {

    @Test
    void testAdd() {
        Scale scale = new Scale();
        scale.setScale(0, 15, -1, 1);
        assertEquals(2 + 3, 5, "2 + 3 should equal 5");
    }
}