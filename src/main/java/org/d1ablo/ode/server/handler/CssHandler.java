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
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

/**
 * Handles requests for the application's css file.
 */
public class CssHandler implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/css");
        InputStream inputStream = CssHandler.class.getClassLoader().getResourceAsStream("style/ode.css");
        if(inputStream == null) {
            throw new IllegalStateException("Failed to find script resource style/ode.css");
        }
        String cssFile = IOUtils.toString(inputStream, Charsets.toCharset("UTF-8"));
        httpServerExchange.getResponseSender().send(cssFile);
    }
}
