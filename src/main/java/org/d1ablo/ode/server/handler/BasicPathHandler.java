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
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.d1ablo.ode.server.ResourceProvider;

import java.io.InputStream;

/**
 * Returns the html file associated with each basic path.
 */
public class BasicPathHandler implements HttpHandler {

    private final ResourceProvider resourceProvider;

    public BasicPathHandler(ResourceProvider resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        if(httpServerExchange.getRequestURI().equals("/")) {
            httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
            httpServerExchange.getResponseSender()
                    .send(resourceProvider.obtainString("html/frontpage.html"));
        } else {
            httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
            String relativePath = httpServerExchange.getRelativePath();
            if(relativePath.matches("(/)([a-z\\-]{1,20})")) {
                String resourcePath = "html" + relativePath + ".html";
                InputStream inputStream = BasicPathHandler.class.getClassLoader().getResourceAsStream(resourcePath);
                if(inputStream == null) {
                    throw new IllegalStateException("Failed to find html resource");
                }
                String htmlFile = IOUtils.toString(inputStream, Charsets.toCharset("UTF-8"));
                httpServerExchange.getResponseSender().send(htmlFile);
            } else {
                httpServerExchange.setStatusCode(404);
                httpServerExchange.getResponseSender().send("");
            }
        }
    }
}
