package edu.hw8.Task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.BlockingQueue;

public class HashGenerator {

    private final BlockingQueue<String> queue;

    public HashGenerator(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @SuppressWarnings("MagicNumber")
    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
                .substring(1));
        }
        return stringBuffer.toString();
    }

    public Password generateHash() {
        try {
            String toHash = queue.take();
            byte[] bytesOfMessage = toHash.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theMD5digest = md.digest(bytesOfMessage);

            return new Password(toHash, convertByteArrayToHexString(theMD5digest));
        } catch (InterruptedException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
