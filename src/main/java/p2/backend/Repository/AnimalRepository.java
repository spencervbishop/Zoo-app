package p2.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import p2.backend.Beans.Animal;
import p2.backend.Beans.Employee;
import p2.backend.Beans.Food;

import java.util.Set;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Integer> {

    Animal findAnimalByAnimalName(String name);
    Set<Animal> findAnimalsByAnimalIdIsNotNull();
    Animal findAnimalByAnimalId(int id);

}
