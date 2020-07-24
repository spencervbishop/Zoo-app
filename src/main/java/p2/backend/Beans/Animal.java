package p2.backend.Beans;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "animal")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "animalId")
public class Animal {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "animalId")
    private int animalId;

    @Column(name = "animalName")
    private String animalName;

    @Column(name = "scientificName")
    private String scientificName;

    @Column(name = "funFact",columnDefinition = "text")
    private String funFact;

    @Column(name = "summary",columnDefinition = "text")
    private String summary;

    @Column(name = "numOfAnimal")
    private int numOfAnimal;

    @Column(name = "tracking")
    private int tracking;

    @Column(name = "notes",columnDefinition = "text")
    private String notes;

    @ManyToMany(mappedBy = "animalFood")
    private Set<Food> food;


    public Animal(){

    }

    public Animal(String animalName, String scientificName, String funFact, String summary, int numOfAnimal, int tracking, String notes) {
        this.animalName = animalName;
        this.scientificName = scientificName;
        this.funFact = funFact;
        this.summary = summary;
        this.numOfAnimal = numOfAnimal;
        this.tracking = tracking;
        this.notes = notes;
    }

    public Animal(String animalName, String scientificName, String funFact, String summary, int numOfAnimal, int tracking, String notes, Set<Food> food) {
        this.animalName = animalName;
        this.scientificName = scientificName;
        this.funFact = funFact;
        this.summary = summary;
        this.numOfAnimal = numOfAnimal;
        this.tracking = tracking;
        this.notes = notes;
        this.food = food;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getNumOfAnimal() {
        return numOfAnimal;
    }

    public void setNumOfAnimal(int numOfAnimal) {
        this.numOfAnimal = numOfAnimal;
    }

    public int getTracking() {
        return tracking;
    }

    public void setTracking(int tracking) {
        this.tracking = tracking;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Food> getFood() {
        return food;
    }

    public void setFood(Set<Food> food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return animalId == animal.animalId &&
                numOfAnimal == animal.numOfAnimal &&
                tracking == animal.tracking &&
                Objects.equals(animalName, animal.animalName) &&
                Objects.equals(scientificName, animal.scientificName) &&
                Objects.equals(funFact, animal.funFact) &&
                Objects.equals(summary, animal.summary) &&
                Objects.equals(notes, animal.notes) &&
                Objects.equals(food, animal.food);
    }

    @Override
    public int hashCode() {

        return Objects.hash(animalId, animalName, scientificName, funFact, summary, numOfAnimal, tracking, notes);
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("animalName",this.animalName)
                    .put("scientificName",scientificName)
                    .put("funFact",funFact)
                    .put("summary",summary)
                    .put("numOfAnimal",numOfAnimal)
                    .put("tracking",tracking)
                    .put("notes",notes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
