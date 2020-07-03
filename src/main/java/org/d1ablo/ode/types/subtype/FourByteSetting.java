/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.types.subtype;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.d1ablo.ode.bintool.BinEditTool;

/**
 * A setting relating to four contiguous bytes within the executable.
 */
public class FourByteSetting {

    @JsonProperty("value")
    private long value;

    @JsonProperty("min")
    private final long min;

    @JsonProperty("max")
    private final long max;

    private final BinEditTool binEditTool;

    @JsonCreator
    public FourByteSetting(@JsonProperty("value") long value,
                           @JsonProperty("min") long min,
                           @JsonProperty("max") long max,
                           @JacksonInject BinEditTool binEditTool) {
        this.value = value;
        this.min = min;
        this.max = max;
        this.binEditTool = binEditTool;
    }

    public void setValue(long value) {
        if(value >= min && value <= max) {
            this.value = value;
        } else {
            String msg = "Value " + value + " is outside of acceptable range: [" + min + "," + max + "]";
            throw new IllegalStateException(msg);
        }
    }

    public long getValue() {
        return this.value;
    }

    @JsonIgnore
    public byte[] asBytes() {
        return binEditTool.convertLongToFourBytes(this.value);
    }
}
