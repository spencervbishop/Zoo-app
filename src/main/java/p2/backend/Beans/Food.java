package p2.backend.Beans;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "Food")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "foodId")
public class Food {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "foodId")
    private int foodId;

    @Column(name = "foodName")
    private String foodName;

    @Column(name ="amount")
    private int amount;

    @Column(name="nextDelivery")
    private String nextDelivery;

    @Column(name="notes")
    private String notes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Food_Animal", joinColumns = @JoinColumn(name = "foodId", referencedColumnName = "foodId"),
            inverseJoinColumns = @JoinColumn(name = "animalId", referencedColumnName = "animalId"))
    @JsonBackReference
    private Set<Animal> animalFood;

    public Food(){

    }

    public Food(String foodName, int amount, String nextDelivery, String notes) {
        this.foodName = foodName;
        this.amount = amount;
        this.nextDelivery = nextDelivery;
        this.notes = notes;
    }

    public Set<Animal> getAnimalFood() {
        return animalFood;
    }

    public void setAnimalFood(Set<Animal> animalFood) {
        this.animalFood = animalFood;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNextDelivery() {
        return nextDelivery;
    }

    public void setNextDelivery(String nextDelivery) {
        this.nextDelivery = nextDelivery;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return foodId == food.foodId &&
                amount == food.amount &&
                Objects.equals(foodName, food.foodName) &&
                Objects.equals(nextDelivery, food.nextDelivery) &&
                Objects.equals(notes, food.notes) &&
                Objects.equals(animalFood, food.animalFood);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFoodId(), getFoodName(), getAmount(), getNextDelivery(), getNotes());
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("foodName",foodName)
                    .put("amount",amount)
                    .put("nextDelivery",nextDelivery)
                    .put("notes",notes);
//            JSONArray foodArray = new JSONArray();
//            this.animalFood.forEach(animal -> {
//                JSONObject animalFood = new JSONObject();
//                try {
//                    animalFood.put("animalId",animal.getAnimalId());
//                    animalFood.put("animalName",animal.getAnimalName());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                foodArray.put(animalFood);
//            });
//            json.put("Animal",foodArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

}
