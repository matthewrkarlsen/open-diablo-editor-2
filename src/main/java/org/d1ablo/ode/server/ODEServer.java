/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.server;

import io.undertow.Undertow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds the undertow server and associated key properties and methods.
 */
public class ODEServer {

    private static final Logger logger = LoggerFactory.getLogger(ODEServer.class);

    private boolean binaryLoaded;
    private Undertow undertow;

    public ODEServer() {
        this.binaryLoaded = false;
    }

    public void setLoadedTrue() {
        this.binaryLoaded = true;
    }

    public void setUndertow(Undertow undertow) {
        this.undertow = undertow;
    }

    public void start() {
        logger.info("Starting ODE server.");
        undertow.start();
        System.out.println("ODE server started.");
        System.out.println("Please navigate to http://localhost:43131/ in a web browser.");
        System.out.println("You need JavaScript enabled in your browser to be able to use the editor.");
    }

    public void stop() {
        if (undertow != null) {
            undertow.stop();
        }
    }

    public Boolean isLoaded() {
        return binaryLoaded;
    }
}
