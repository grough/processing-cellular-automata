package com.grough.automata;

/**
 * Scale between two coordinate systems.
 */
class Scale {
    float inputMin, inputMax, outputMin, outputMax;

    Scale(float inputMin, float inputMax, float outputMin, float outputMax) {
        this.inputMin = inputMin;
        this.inputMax = inputMax;
        this.outputMin = outputMin;
        this.outputMax = outputMax;
    }

    /**
     * Normalize an input value with respect to the input range.
     *
     * @param value The input value
     * @return Normalized value
     */
    float normalizeFromInput(float value) {
        float translated = value - inputMin;
        float scale = inputMax - inputMin;
        return translated / scale;
    }

    /**
     * Scale and translate a normalized value to the output range.
     *
     * @param value Normalized input value
     * @return Denormalized output value
     */
    float denormalizeToOutput(float value) {
        float scaled = value * (outputMax - outputMin);
        return outputMin + scaled;
    }

    /**
     * Scale a value from the input range to the output range.
     *
     * @param value Input value
     * @return Rescaled value
     */
    float rescale(float value) {
        return denormalizeToOutput(normalizeFromInput(value));
    }
}
