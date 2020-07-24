package p2.backend.Beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Location")
public class Location {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "locationId")
    private int locationId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "animalId")
    @JsonManagedReference
    private Animal animal;

    public Location() {
    }

    public Location(Double latitude, Double longitude, Animal animal) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.animal = animal;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId == location.locationId &&
                Objects.equals(latitude, location.latitude) &&
                Objects.equals(longitude, location.longitude) &&
                Objects.equals(animal, location.animal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(locationId, latitude, longitude, animal);
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("id",locationId)
                .put("latitude",latitude)
                .put("longitude",longitude).putPOJO("animal",animal);
        return json.toString();
    }
}
