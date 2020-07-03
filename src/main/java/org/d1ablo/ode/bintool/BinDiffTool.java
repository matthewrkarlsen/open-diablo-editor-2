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

import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Identifies binary differences on two (non-directory) files of equal size.
 */
public class BinDiffTool {

    /**
     * Return the binary differences between the two files located at the specified file paths.
     *
     * @param filePath1 the path-and-file-name pointing to the first file to compare
     * @param filePath2 the path-and-file-name pointing to the second file to compare
     * @return the list of binary differences
     */
    public List<BinDiff> reportBinDiffs(String filePath1, String filePath2) {
        File originalFile = new File(filePath1);
        File modifiedFile = new File(filePath2);
        checkValidFile(originalFile);
        checkValidFile(modifiedFile);
        long fileLength = getFileLength(originalFile, modifiedFile);
        List<BinDiff> binDiffs;
        try(InputStream origStream = new BufferedInputStream(FileUtils.openInputStream(originalFile));
            InputStream modStream = new BufferedInputStream(FileUtils.openInputStream(modifiedFile))) {
            binDiffs = compareEqualLengthStreams(origStream, modStream, fileLength);
        } catch (IOException e) {
            throw new BinDiffException(e);
        }
        return binDiffs;
    }

    private long getFileLength(File originalFile, File modifiedFile) {
        long file1Length = FileUtils.sizeOf(originalFile);
        long file2Length = FileUtils.sizeOf(modifiedFile);
        checkLengthEqual(file1Length, file2Length);
        return file1Length;
    }

    private void checkLengthEqual(long file1Length, long file2Length) {
        if (file1Length != file2Length) {
            throw new BinDiffException("Modified length != original length");
        }
    }

    private void checkValidFile(File f) {
        if(!f.exists()) {
            throw new BinDiffException("File to compare does not exist");
        }
        if(f.isDirectory()) {
            throw new BinDiffException("This code does not support directory comparison (file is a directory)");
        }
    }

    /**
     * Produce a list of binary differences from comparison of two streams with the specified expected length.
     *
     * @param s1 the first stream to compare
     * @param s2 the second stream to compare
     * @param length the expected number of bytes in each stream
     * @return the list of binary differences
     * @throws IOException thrown if either of the streams are shorter than expected
     */
    public List<BinDiff> compareEqualLengthStreams(InputStream s1, InputStream s2, long length) throws IOException {
        List<BinDiff> diffs = new ArrayList<>();
        for(long i = 0; i < length; i++) {
            int s1Val = s1.read();
            int s2Val = s2.read();
            if(s1Val != s2Val) {
                diffs.add(new BinDiff(i, s1Val, s2Val));
            }
        }
        return diffs;
    }
}
