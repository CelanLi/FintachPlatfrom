package com.celan.front;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootTest
class MicrWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generates a 256-bit key
        byte[] secretBytes = key.getEncoded();
        String secretString = Base64.getEncoder().encodeToString(secretBytes);
        System.out.println("Secret key: " + secretString);
    }

}
