package p2.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import p2.backend.Beans.Location;
import p2.backend.Service.LocationService;

@CrossOrigin
@RestController
@RequestMapping("/Location")
public class LocationController {

    private LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService){
        this.locationService =locationService;
    }

    @RequestMapping("/")
    public Iterable<Location> locationList(){
        return locationService.listofLocations();
    }

}
