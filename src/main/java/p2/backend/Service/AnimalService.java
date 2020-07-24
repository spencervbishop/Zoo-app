package p2.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p2.backend.Beans.Animal;
import p2.backend.Beans.Employee;
import p2.backend.Repository.AnimalRepository;

import java.util.Set;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    public Animal byAnimal(String name){
        return animalRepository.findAnimalByAnimalName(name);
    }

    public Animal byAnimalID(int id){
        return animalRepository.findAnimalByAnimalId(id);
    }

    public Set<Animal> allAnimals(){
        return animalRepository.findAnimalsByAnimalIdIsNotNull();
    }

    public void saveAnimal (Animal save){
        animalRepository.save(save);
    }

    public void deleteAnimal(Animal delete){
        animalRepository.delete(delete);
    }

}
