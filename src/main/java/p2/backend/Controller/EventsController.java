package p2.backend.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import p2.backend.Beans.Events;
import p2.backend.Service.EventsService;

@CrossOrigin
@RestController
@RequestMapping("/Events")
public class EventsController {

    private EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService){
        this.eventsService = eventsService;
    }
    @RequestMapping("/")
    public Iterable<Events> EventList(){
        return eventsService.listOfEvents();
    }

    @PostMapping("/save")
    public String saveEvent(@RequestBody Events save){
        eventsService.saveEvent(save);
        return "tested";
    }

    @PostMapping("/delete")
    public String deleteEvent(@RequestBody Events delete){
        eventsService.deleteEvent(delete);
        return "tested";
    }
}
