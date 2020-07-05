/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.enums;

/**
 * A host value. This is used to determine whether the server listens on localhost (127.0.0.1)
 * or 0.0.0.0. If listening on localhost, the server accepts connections from the local machine
 * only. If listening on 0.0.0.0 the server accepts connections from computers further afield.
 * Clearly 0.0.0.0 is potentially dangerous. However, when used inside a Docker container, 0.0.0.0
 * is currently necessary, because 'host.docker.internal' is not currently supported when the host
 * machine is running Linux. Hopefully this will change soon and we can use 'host.docker.internal'
 * instead of 0.0.0.0. See https://github.com/docker/for-linux/issues/264.
 */
public enum Host {
    ZERO_ZERO_ZERO_ZERO("0.0.0.0"),
    LOCALHOST("localhost");

    private final String value;

    Host(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
