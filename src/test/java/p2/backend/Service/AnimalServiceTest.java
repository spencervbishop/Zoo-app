package p2.backend.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import p2.backend.Beans.Animal;
import p2.backend.Repository.AnimalRepository;

import javax.persistence.SecondaryTable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAnimals(){
        Set<Animal> animals = new HashSet<Animal>();
        animals.add(new Animal("Spencer", "Mathematician turnedprogrammer","likes math", "loves math", 1, 1, "notes"));
        animals.add(new Animal("Spencer2", "Mathematician turnedprogrammer","likes math", "loves math", 1, 1, "notes"));
        animals.add(new Animal("Spencer3", "Mathematician turnedprogrammer","likes math", "loves math", 1, 1, "notes"));
        when(animalRepository.findAnimalsByAnimalIdIsNotNull()).thenReturn(animals);

        assertEquals(3, animalService.allAnimals().size());
    }

    @Test
    public void testGetAnimalsException() throws Exception{
        Set<Animal> animals = new HashSet<Animal>();
       when(animalRepository.findAnimalsByAnimalIdIsNotNull()).thenReturn(animals);

        assertEquals(0, animalService.allAnimals().size());

    }

}