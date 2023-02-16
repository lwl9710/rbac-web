package com.basic.project.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DigestUtil {
    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder();
    private static final Base64.Decoder BASE64_DECODER = Base64.getDecoder();
    private static final MessageDigest MD5_DIGEST = getSignDigest("MD5");


    public static String getMD5String(String content) {
        return getMD5String(content.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

    public static String getMD5String(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] digest = MD5_DIGEST.digest(bytes);
        for (byte k : digest) {
            int v = k & 0xff;
            if(v < 16) {
                stringBuilder.append(0);
            }
            stringBuilder.append(Integer.toHexString(v));
        }
        return stringBuilder.toString();
    }

    public static String encodeBase64String(String content) {
        return encodeBase64String(content.getBytes(StandardCharsets.UTF_8));
    }

    public static String encodeBase64String(byte[] bytes) {
        return BASE64_ENCODER.encodeToString(bytes);
    }

    public static String decodeBase64String(String content) {
        return decodeBase64String(content.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodeBase64String(byte[] bytes) {
        return new String(BASE64_DECODER.decode(bytes));
    }

    public static MessageDigest getSignDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
