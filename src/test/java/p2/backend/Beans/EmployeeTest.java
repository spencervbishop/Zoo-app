package p2.backend.Beans;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.lang.EquivalentFactory;
import org.meanbean.test.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import p2.backend.BackendApplication;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
//@WebMvcTest(value=AnimalController.class)
@SpringBootTest(classes = BackendApplication.class)
public class EmployeeTest {

//    @Test
//    public void getterAndSetterCorrectness() throws Exception {
//        Configuration config = new ConfigurationBuilder()
//                .ignoreProperty("animals")
//                .build();
//        new BeanTester().testBean(Employee.class, config);
//    }

    @Test
    public void hashEmployeeTest() throws Exception {
        HashCodeMethodTester tester = new HashCodeMethodTester();
        tester.testHashCodeMethod(new EmployeeFactory());

    }

    @Test
    public void equalsEmployeeTest() throws Exception{
        EqualsMethodTester tester = new EqualsMethodTester();
        tester.testEqualsMethod(new EmployeeFactory());
    }

}

class EmployeeFactory implements EquivalentFactory<Employee> {

    @Override
    public Employee create() {
        Animal mockAnimal1 = new Animal("Spencer", "sci name", "fun fact",
                "summary", 1, 1, "notes");
        Animal mockAnimal2 = new Animal("Spencer2", "sci name", "fun fact",
                "summary", 1, 1, "notes");
        mockAnimal1.setAnimalId(1);
        mockAnimal2.setAnimalId(2);
        Set<Animal> animals = new HashSet<>();
        animals.add(mockAnimal1);
        animals.add(mockAnimal2);
//        Employee mockEmployee = new Employee("First Name", "Last Name", "Username", "Password", 1);
//        mockEmployee.setAnimals(animals);
        Employee anotherMockEmployee = new Employee("First Name Also", "Last Name Also", "Username Also", "Password Also", 1, animals);
        return anotherMockEmployee;
    }

}