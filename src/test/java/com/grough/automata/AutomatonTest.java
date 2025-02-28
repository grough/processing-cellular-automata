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
        return self();
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
        assertEquals(a.get(0, 0), true, "0 0 is true");
        assertEquals(a.get(0, 1), false, "0 1 is false");
    }
}