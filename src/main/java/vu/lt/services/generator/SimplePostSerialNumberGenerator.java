package vu.lt.services.generator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class SimplePostSerialNumberGenerator implements PostSerialNumberGenerator, Serializable {

    public String generatePostSerialNumber() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return Integer.toString(new Random().nextInt(100));
    }
}