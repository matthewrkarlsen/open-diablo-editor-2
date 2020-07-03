/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.types.subtype;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A setting related to a single byte within the executable.
 */
public class OneByteSetting {

    @JsonProperty("value")
    private int value;

    @JsonProperty("min")
    private final int min;

    @JsonProperty("max")
    private final int max;

    @JsonCreator
    public OneByteSetting(@JsonProperty("value") int value,
                          @JsonProperty("min") int min,
                          @JsonProperty("max") int max) {
        this.value = value;
        this.min = min;
        this.max = max;
    }

    public OneByteSetting(OneByteSetting oneByteSetting) {
        this.value = oneByteSetting.value;
        this.min = oneByteSetting.min;
        this.max = oneByteSetting.max;
    }

    public void setValue(int value) {
        if(value >= min && value <= max) {
            this.value = value;
        } else {
            String msg = "Value " + value + " is outside of acceptable range: [" + min + "," + max + "]";
            throw new IllegalStateException(msg);
        }
    }

    public int getValue() {
        return this.value;
    }

    @JsonIgnore
    public byte asByte() {
        return (byte) this.value;
    }
}
