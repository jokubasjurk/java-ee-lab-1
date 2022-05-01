package vu.lt.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class DepartmentNumberGenerator implements Serializable {

    public Integer generateDepartmentNumber() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Integer generatedDepartmentNumber = new Random().nextInt(100);
        return generatedDepartmentNumber;
    }
}