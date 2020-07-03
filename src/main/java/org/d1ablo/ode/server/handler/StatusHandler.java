/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.server.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.d1ablo.ode.server.ODEServer;

/**
 * Responsible for providing the 'binary loaded' status to the front end, on request.
 */
public class StatusHandler implements HttpHandler {

    private final ODEServer odeServer;

    public StatusHandler(ODEServer odeServer) {
        this.odeServer = odeServer;
    }

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        httpServerExchange.getResponseSender()
                .send("{ \"loaded\": " + odeServer.isLoaded() + " }");
    }
}
