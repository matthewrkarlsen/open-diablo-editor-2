package org.d1ablo.ode.bintool;

import org.d1ablo.ode.arraytool.ArrayTool;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BinDiffToolTest {

    @Test
    public void givenTwoIdenticalStreams_whenCompare_thenNoBinDiffs() {
        byte[] bytes1 = ArrayTool.getRandomByteArray(1024);
        byte[] bytes2 = ArrayTool.copyArray(bytes1);
        BinDiffTool binDiffTool = new BinDiffTool();
        List<BinDiff> binDiffList;
        try (InputStream stream1 = new BufferedInputStream(new ByteArrayInputStream(bytes1));
             InputStream stream2 = new BufferedInputStream(new ByteArrayInputStream(bytes2))) {
            binDiffList = binDiffTool.compareEqualLengthStreams(stream1, stream2, bytes1.length);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        assertEquals(0, binDiffList.size());
    }

    @Test
    public void givenTwoStreamsWithOneByteDiff_whenCompare_thenOneCorrectBinDiff() {
        byte[] bytes1 = ArrayTool.getRandomByteArray(1024);
        byte[] bytes2 = ArrayTool.copyArray(bytes1);
        bytes1[512] = (byte) 0;
        bytes2[512] = (byte) 255;
        BinDiffTool binDiffTool = new BinDiffTool();
        List<BinDiff> binDiffList;
        try (InputStream stream1 = new BufferedInputStream(new ByteArrayInputStream(bytes1));
             InputStream stream2 = new BufferedInputStream(new ByteArrayInputStream(bytes2))) {
            binDiffList = binDiffTool.compareEqualLengthStreams(stream1, stream2, bytes1.length);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        assertEquals(1, binDiffList.size());
        BinDiff binDiff = binDiffList.get(0);
        assertEquals(512, binDiff.getIndex());
        assertEquals(0, binDiff.getFileOneByteValue());
        assertEquals(255, binDiff.getFileTwoByteValue());
    }

    @Test
    public void givenTwoStreamsWithThreeByteDiffs_whenCompare_thenThreeCorrectBinDiffs() {
        byte[] bytes1 = ArrayTool.getRandomByteArray(1024);
        byte[] bytes2 = ArrayTool.copyArray(bytes1);
        bytes1[512] = (byte) 0;
        bytes2[512] = (byte) 255;
        bytes1[671] = (byte) 0;
        bytes2[671] = (byte) 255;
        bytes1[885] = (byte) 0;
        bytes2[885] = (byte) 255;
        BinDiffTool binDiffTool = new BinDiffTool();
        List<BinDiff> binDiffList;
        try (InputStream stream1 = new BufferedInputStream(new ByteArrayInputStream(bytes1));
             InputStream stream2 = new BufferedInputStream(new ByteArrayInputStream(bytes2))) {
            binDiffList = binDiffTool.compareEqualLengthStreams(stream1, stream2, bytes1.length);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        assertEquals(3, binDiffList.size());

        BinDiff binDiff = binDiffList.get(0);
        assertEquals(512, binDiff.getIndex());
        assertEquals(0, binDiff.getFileOneByteValue());
        assertEquals(255, binDiff.getFileTwoByteValue());

        BinDiff binDiff1 = binDiffList.get(1);
        assertEquals(671, binDiff1.getIndex());
        assertEquals(0, binDiff1.getFileOneByteValue());
        assertEquals(255, binDiff1.getFileTwoByteValue());

        BinDiff binDiff2 = binDiffList.get(2);
        assertEquals(885, binDiff2.getIndex());
        assertEquals(0, binDiff2.getFileOneByteValue());
        assertEquals(255, binDiff2.getFileTwoByteValue());
    }
}