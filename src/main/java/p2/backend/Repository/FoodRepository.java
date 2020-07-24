package p2.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import p2.backend.Beans.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

}
