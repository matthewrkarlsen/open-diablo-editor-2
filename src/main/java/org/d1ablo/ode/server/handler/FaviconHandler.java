/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.md for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.server.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.d1ablo.ode.server.ResourceProvider;

import java.nio.ByteBuffer;

/**
 * Provides the favicon on demand.
 */
public class FaviconHandler implements HttpHandler {

    private final ResourceProvider resourceProvider;

    public FaviconHandler(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "image/x-icon");
        byte[] faviconBytes = resourceProvider.obtainBytes("image/favicon.ico");
        httpServerExchange.getResponseSender().send(ByteBuffer.wrap(faviconBytes));
    }
}
