package p2.backend.Beans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.lang.EquivalentFactory;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import p2.backend.BackendApplication;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
//@WebMvcTest(value=AnimalController.class)
@SpringBootTest(classes = BackendApplication.class)
public class LocationTest {

    @Test
    public void getterAndSetterCorrectness() throws Exception {
        new BeanTester().testBean(Location.class);
    }

    @Test
    public void hashLocationTest() throws Exception {
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(new LocationFactory());

    }

    @Test
    public void equalsLocationTest() throws Exception{
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(new LocationFactory());
    }

}

class LocationFactory implements EquivalentFactory<Location> {

    @Override
    public Location create() {
        Animal mockAnimal = new Animal();
        Location mockLocation = new Location(1.234, 5.678, mockAnimal);

        return mockLocation;
    }
}