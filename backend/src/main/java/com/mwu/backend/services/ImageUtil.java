package com.mwu.backend.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtil {

    public static byte[] compressImage(byte[] imageData) {

        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(imageData);
        deflater.finish();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(imageData.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            byteArrayOutputStream.write(buffer, 0, count);
        }
        try {
            byteArrayOutputStream.close();
        } catch (Exception e) {

        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] imageData) {
        Inflater inflater = new Inflater();
        inflater.setInput(imageData);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(imageData.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                byteArrayOutputStream.write(buffer, 0, count);
            }
            byteArrayOutputStream.close();
        } catch (Exception e) {
            // Handle exception
        } finally {
            inflater.end();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
