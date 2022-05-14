package vu.lt.services.generator;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@ApplicationScoped
public class DefaultPostSerialNumberGenerator implements PostSerialNumberGenerator, Serializable {

    public String generatePostSerialNumber() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}