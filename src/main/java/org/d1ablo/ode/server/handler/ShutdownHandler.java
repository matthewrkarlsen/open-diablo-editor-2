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
import org.d1ablo.ode.server.ResourceProvider;

/**
 * Responsible for handling the shutdown path, serves a final html page and then shuts down the server.
 */
public class ShutdownHandler implements HttpHandler {

    private final ODEServer odeServer;
    private final ResourceProvider resourceProvider;

    public ShutdownHandler(ODEServer odeServer, ResourceProvider resourceProvider) {
        this.odeServer = odeServer;
        this.resourceProvider = resourceProvider;
    }
    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        httpServerExchange.getResponseSender()
                .send(resourceProvider.obtainString("html/shutdown.html"));
        odeServer.stop(); //FIXME -- is there a better way?
    }
}
