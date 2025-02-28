package com.grough.automata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Animation extends Automaton<Boolean> {
    @Override
    protected Boolean populate() {
        if (is(0, 0)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected Boolean evolve() {
        return false;
    }

    @Override
    protected int shade() {
        return self() ? 1 : 0;
    }
}

class AutomatonTest {

    @Test
    void test() {
        Animation a = new Animation();

        a.size(16, 16);
        a.dimensions(64, 64);

        assertEquals(a.get(0, 0), true);
        assertEquals(a.get(0, 1), false);

        a.next();
        a.graphics();
        assertEquals(a.get(0, 0), false);
    }
}