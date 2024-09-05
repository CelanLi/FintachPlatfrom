package com.celan.commom.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.StringJoiner;

public class KeyUtil {
    public static String trim(String filename){
        StringJoiner sj = new StringJoiner("\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("-----BEGIN PRIVATE KEY-----") || line.contains("-----END PRIVATE KEY-----")) {
                    continue;
                }
                sj.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read private key", e);
        }
        return sj.toString();
    }

    public static PrivateKey getPrivateKey(String filename) {
        String privateKeyContent = trim(filename);
        privateKeyContent = privateKeyContent.replaceAll("\\n", "");
        // Convert the private key content to a PrivateKey object
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Could not generate private key", e);
        }
    }
}
