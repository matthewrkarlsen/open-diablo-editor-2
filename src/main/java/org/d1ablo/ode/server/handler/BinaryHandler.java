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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.d1ablo.ode.bintool.StringExtractor;
import org.d1ablo.ode.bintool.rw.ReaderWriter;
import org.d1ablo.ode.server.ODEServer;
import org.d1ablo.ode.stores.CompleteStore;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.UUID;

/**
 * Handles the paths responsible for uploading binaries to the server and downloading binaries from the server.
 */
public class BinaryHandler implements HttpHandler {

    private final CompleteStore completeStore;
    private final ODEServer odeServer;

    private File tempExe;

    public BinaryHandler(CompleteStore completeStore, ODEServer odeServer) {
        this.completeStore = completeStore;
        this.odeServer = odeServer;
    }

    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        String relativePath = httpServerExchange.getRelativePath();
        if(relativePath.equals("/load")) {
            InputStream inputStream = httpServerExchange.getInputStream();
            byte[] receivedBytes = IOUtils.toByteArray(inputStream);
            //FIXME -- the code below could be tidied and made more robust.
            byte splitByteOne = 0x0D;
            byte splitByteTwo = 0x0A;
            byte splitByteThree = 0x00;
            int idx1 = -1;
            int idx2 = -1;
            for(int i = 0; i < receivedBytes.length - 4; i++) {
                if(receivedBytes[i] == splitByteOne && idx1 == -1) {
                    byte b2 = receivedBytes[i+1];
                    if(b2 == splitByteTwo) {
                        byte b3 = receivedBytes[i+2];
                        if(b3 == splitByteOne) {
                            byte b4 = receivedBytes[i+3];
                            if(b4 == splitByteTwo) {
                                idx1 = i + 4; //the +4 is important or we get unwanted bytes
                            }
                        }
                    }
                }
                if(receivedBytes[i] == splitByteThree) {
                    byte b2 = receivedBytes[i+1];
                    if(b2 == splitByteThree) {
                        byte b3 = receivedBytes[i+2];
                        if(b3 == splitByteOne) {
                            byte b4 = receivedBytes[i+3];
                            if(b4 == splitByteTwo) {
                                idx2 = i + 2; //the +2 is important or we truncate the file
                            }
                        }
                    }
                }
            }
            if(idx1 == -1 || idx2 == -1) {
                throw new IllegalStateException("Required multipart form indices not found");
            }

            Path tempDirectoryPath = Files.createTempDirectory("open-diablo-editor-");
            UUID uuid = UUID.randomUUID();
            String tempFileName = "Diablo_" + uuid + ".exe";
            tempExe = new File(tempDirectoryPath.toString(), tempFileName);
            try {
                byte[] dataCutOut = Arrays.copyOfRange(receivedBytes, idx1, idx2);
                FileUtils.writeByteArrayToFile(tempExe, dataCutOut);
            } catch (IOException e) {
                e.printStackTrace();
            }

            RandomAccessFile randomAccessFile;
            try {
                randomAccessFile = new RandomAccessFile(tempExe, "rw");
            } catch (FileNotFoundException e) {
                throw new IllegalStateException(e);
            }
            ReaderWriter rw = new ReaderWriter(randomAccessFile);

            StringExtractor stringExtractor = new StringExtractor(rw.getReader());
            completeStore.initialiseUsingBinary(rw, stringExtractor);
            odeServer.setLoadedTrue();
        } else if (relativePath.equals("/obtain")) {
            System.out.println("Obtaining binary...");
            httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/octet-stream");
            byte[] bytes = FileUtils.readFileToByteArray(tempExe);
            ByteBuffer bb = ByteBuffer.wrap(bytes);
            httpServerExchange.getResponseSender().send(bb);
        } else {
            httpServerExchange.setStatusCode(404);
            httpServerExchange.getResponseSender().send("");
        }
    }
}
