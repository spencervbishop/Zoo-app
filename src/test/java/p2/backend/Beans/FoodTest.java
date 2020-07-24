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
public class FoodTest {

    @Test
    public void getterAndSetterCorrectness() throws Exception {
        new BeanTester().testBean(Food.class);
    }

    @Test
    public void hashLocationTest() throws Exception {
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(new FoodFactory());

    }

    @Test
    public void equalsLocationTest() throws Exception{
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(new FoodFactory());
    }

}

class FoodFactory implements EquivalentFactory<Food> {

    @Override
    public Food create() {
        Animal mockAnimal1 = new Animal("Spencer", "sci name", "fun fact",
                "summary", 1, 1, "notes");
        Animal mockAnimal2 = new Animal("Spencer2", "sci name", "fun fact",
                "summary", 1, 1, "notes");
        mockAnimal1.setAnimalId(1);
        mockAnimal2.setAnimalId(2);

        Food mockFood = new Food("food", 1, "date", "notes");
        mockFood.setFoodId(1);

        Set<Animal> animals = new HashSet<>();
        animals.add(mockAnimal1);
        animals.add(mockAnimal2);
        mockFood.setAnimalFood(animals);

        return mockFood;
    }

}