package com.grough.automata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScaleTest {
    @Test
    void test1() {
        Scale scale = new Scale(0, 1, 0, 1);

        assertEquals(0.f, scale.rescale(0));
        assertEquals(1.f, scale.rescale(1));
        assertEquals(0.5f, scale.rescale(0.5f));
    }

    @Test
    void test2() {
        Scale scale = new Scale(0, 1, -1, 1);

        assertEquals(-1.f, scale.rescale(0));
        assertEquals(1.f, scale.rescale(1));
        assertEquals(0.f, scale.rescale(0.5f));
    }

    @Test
    void test3() {
        Scale scale = new Scale(10, 20, 0, 1);

        assertEquals(0.f, scale.rescale(10));
        assertEquals(1.f, scale.rescale(20));
        assertEquals(0.5f, scale.rescale(15));
        assertEquals(-1.f, scale.rescale(0));
        assertEquals(2.f, scale.rescale(30));
    }

    void test4() {
        Scale scale = new Scale(-1, 1, 0, 1);

        assertEquals(0.f, scale.rescale(-1));
        assertEquals(0.5f, scale.rescale(0));
        assertEquals(1.f, scale.rescale(1));
    }

    void test5() {
        Scale scale = new Scale(-1, 1, 200, -200);

        assertEquals(200.f, scale.rescale(-1));
        assertEquals(0.f, scale.rescale(0));
        assertEquals(-200.f, scale.rescale(1));
    }
}
