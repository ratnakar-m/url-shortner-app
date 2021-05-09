package com.test.shortner;

import com.test.shortner.services.ShortnerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ShortnerApplicationTests {

    @Test
    void contextLoads() {
    }

}
