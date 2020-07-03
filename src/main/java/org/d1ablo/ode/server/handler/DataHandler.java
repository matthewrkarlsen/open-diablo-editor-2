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

import com.fasterxml.jackson.databind.json.JsonMapper;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.apache.commons.io.IOUtils;
import org.d1ablo.ode.stores.CompleteStore;

import java.io.IOException;
import java.io.InputStream;

/**
 * Handles requests to obtain or upload the central JSON data.
 */
public class DataHandler implements HttpHandler {

    private final CompleteStore completeStore;
    private final JsonMapper writingJsonMapper;
    private final JsonMapper readingJsonMapper;

    public DataHandler(CompleteStore completeStore, JsonMapper writingJsonMapper, JsonMapper readingJsonMapper) {
        this.completeStore = completeStore;
        this.writingJsonMapper = writingJsonMapper;
        this.readingJsonMapper = readingJsonMapper;
    }

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        String relativePath = httpServerExchange.getRelativePath();
        if(relativePath.equals("/obtain")) {
            httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            String jsonString = writingJsonMapper.writeValueAsString(completeStore);
            httpServerExchange.getResponseSender().send(jsonString);
        } else if (relativePath.equals("/upload")) {
            System.out.println("Saving data...");
            httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            InputStream inputStream = httpServerExchange.getInputStream();
            try {
                byte[] bytes = IOUtils.toByteArray(inputStream);
                String str = new String(bytes);
                CompleteStore receivedStore = readingJsonMapper.readValue(str, CompleteStore.class);
                completeStore.updateWith(receivedStore);
                completeStore.writeAllData();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpServerExchange.getResponseSender().send("");
        } else {
            httpServerExchange.setStatusCode(404);
            httpServerExchange.getResponseSender().send("");
        }
    }
}
