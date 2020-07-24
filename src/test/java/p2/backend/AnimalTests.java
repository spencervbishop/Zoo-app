package p2.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import p2.backend.Beans.Animal;
import p2.backend.Controller.AnimalController;
import p2.backend.Repository.AnimalRepository;
import p2.backend.Service.AnimalService;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BackendApplication.class)
@SpringBootTest
public class AnimalTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void getAnimals() throws Exception {

        Animal gator = new Animal("American Alligator", "Alligator Mississippiensis","The eyes and snout are positioned on the top of the head, enabling the American alligator to breathe and watch for prey, while the rest of the body is submerged.","Domestic American alligators range from long and slender to short and robust, possibly due to variations in factors such as growth rate, diet, and climate. American alligators have broad snouts, especially in captive individuals.",9,0,null);
        Animal elephant = new Animal("Asian Elephant", "Elephas Maximus","Their trunks can carry about 4 liters of water.","Asian elephants are the largest living land animals in Asia. The largest Asian elephant ever recorded was from the Garo Hills of Assam, India; it weighed 7.7 tons, stood 11.3 ft tall at the shoulder and was 26.4 ft long from head to tail. The distinctive trunk is an elongation of the nose and upper lip combined; the nostrils are at its tip, which has a one finger-like process. The trunk contains as many as 60,000 muscles, which consist of longitudinal and radiating sets.",4,0,null);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode().putPOJO("animal",gator);
        ObjectNode node1 = mapper.createObjectNode().putPOJO("animal",elephant);
        mockMvc.perform(MockMvcRequestBuilders.get("/Animal/").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]animalName").value(gator.getAnimalName()))
                .andExpect(jsonPath("$[2]animalName").value(elephant.getAnimalName())).andDo(print());
    }
}
