/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.bintool;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class FileCopier {

    /**
     * Copy an originally supplied file to a newly specified path and return a new file object for that path.
     *
     * FIXME -- could just use Files.copy(originalFile.toPath(), newFile.toPath());
     *
     * @param originalPath the original path to copy
     * @param newPath the path at which to create a new copy of the file
     * @return the file associated with the new path
     */
    public static File copyFile(String originalPath, String newPath) throws FileAlreadyExistsException {
        File originalFile = new File(originalPath);
        File newFile = createAbsentFile(newPath);
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(originalFile));
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFile))) {
            inputStream.transferTo(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return newFile;
    }

    private static File createAbsentFile(String filePath) throws FileAlreadyExistsException {
        File newFile = new File(filePath);
        checkFileDoesNotExist(newFile);
        createNewFile(newFile);
        return newFile;
    }

    private static void createNewFile(File newFile) {
        try {
            boolean success = newFile.createNewFile();
            if(!success) {
                throw new IllegalStateException("Fatal. Failed to create new file.");
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void checkFileDoesNotExist(File file) throws FileAlreadyExistsException {
        if(file.exists()) {
            throw new FileAlreadyExistsException("Trying to create a new file over an existing file. Aborting.");
        }
    }
}
