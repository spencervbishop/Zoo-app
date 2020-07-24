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

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@WebMvcTest(value=AnimalController.class)
@SpringBootTest(classes = BackendApplication.class)
public class AnimalTest {

    @Test
    public void getterAndSetterCorrectness() throws Exception {
        new BeanTester().testBean(Animal.class);
    }

    @Test
    public void hashAnimalTest() throws Exception {
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(new AnimalFactory());

    }

    @Test
    public void equalsLocationTest() throws Exception{
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(new LocationFactory());
    }

}


class AnimalFactory implements EquivalentFactory<Animal> {

    @Override
    public Animal create() {

        Food food = new Food();
        Set<Food> mockFood = new HashSet<>();
        mockFood.add(food);
        Animal mockAnimal = new Animal("Spencer", "sci name", "fun fact",
                "summary", 1, 1, "notes", mockFood);

        return mockAnimal;
    }
}