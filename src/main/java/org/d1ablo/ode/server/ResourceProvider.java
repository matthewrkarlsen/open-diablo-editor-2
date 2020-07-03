/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.server;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Reads a specified resource path and returns the read data in string or byte format.
 */
public class ResourceProvider {

    /**
     * Reads specified resource path and returns the read data in string format.
     * @param resourcePath the relative path of the resource (from inside the resources directory)
     * @return a string representation of the resource
     */
    public String obtainString(String resourcePath) {
        InputStream inputStream = ResourceProvider.class.getClassLoader().getResourceAsStream(resourcePath);
        if(inputStream == null) {
            throw new IllegalStateException("Failed to find resource " + resourcePath);
        }
        String fileString;
        try {
            fileString = IOUtils.toString(inputStream, Charsets.toCharset("UTF-8"));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to convert resource stream (" + resourcePath + ") to string");
        }
        return fileString;
    }

    /**
     * Reads specified resource path and returns the read data in byte format.
     * @param resourcePath the relative path of the resource (from inside the resources directory)
     * @return the bytes of the specified resource
     */
    public byte[] obtainBytes(String resourcePath) {
        InputStream inputStream = ResourceProvider.class.getClassLoader().getResourceAsStream(resourcePath);
        if(inputStream == null) {
            throw new IllegalStateException("Failed to find resource " + resourcePath);
        }
        byte[] fileBytes;
        try {
            fileBytes = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to convert resource stream (" + resourcePath + ") to bytes");
        }
        return fileBytes;
    }
}
