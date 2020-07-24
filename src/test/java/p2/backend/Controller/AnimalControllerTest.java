package p2.backend.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import p2.backend.BackendApplication;
import p2.backend.Beans.Animal;
import p2.backend.Service.AnimalService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
//@WebMvcTest(value=AnimalController.class)
@SpringBootTest(classes = BackendApplication.class)
public class AnimalControllerTest {

    //@Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private AnimalService animalService;

    Animal mockAnimal = new Animal("Spencer", "Mathematician turnedprogrammer","likes math", "loves math", 1, 1, "notes");
    String exampleAnimalJson = "{\"animalName\":\"Spencer\",\"scientificName\":\"Mathematician turnedprogrammer\",\"funFact\":\"likes math\",\"summary\":\"loves math\",\"numOfAnimal\":1,\"tracking\":1, \"notes\":\"notes\"}";

    @Before
    public void setup() throws Exception{
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        Mockito.when(animalService.byAnimal(Mockito.anyString())).thenReturn(mockAnimal);
    }

    @Test
    public void testByAnimal() throws Exception{
//        Mockito.when(animalService.byAnimal(Mockito.anyString())).thenReturn(mockAnimal);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//                "/Animal?name=someanimal").accept(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println(result.getResponse());
//        String expected = exampleAnimalJson;//"{animalId : 1, animalName : Spencer, scientificName : Mathematician turnedprogrammer, funFact : likes math, summary : loves math, numOfAnimal:1, tracking : 1, notes : notes}";
//        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testById() throws Exception{
        Mockito.when(animalService.byAnimalID(Mockito.anyInt())).thenReturn(mockAnimal);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
                "/Animal/1").accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FOUND.value(), response.getStatus());
        JSONAssert.assertEquals(mockAnimal.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testByName() throws Exception{
        Mockito.when(animalService.byAnimal(Mockito.anyString())).thenReturn(mockAnimal);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
                "/Animal?name=something").accept(MediaType.APPLICATION_JSON)).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FOUND.value(), response.getStatus());
        JSONAssert.assertEquals(mockAnimal.toString(), result.getResponse().getContentAsString(), false);
    }

    @Test
    public void saveAnimalTest() throws Exception{
        List<Animal> animals = new ArrayList<>();

        Mockito.doAnswer((Answer) invocation -> {
            animals.add(mockAnimal);
            return null;
        }).when(animalService).saveAnimal(Mockito.any(Animal.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Animal/save")
                .accept(MediaType.APPLICATION_JSON).content(exampleAnimalJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(1, animals.size());
    }

    @Test
    public void deleteAnimalTest()throws Exception{
        List<Animal> animals = new ArrayList<>();
        animals.add(mockAnimal);

        Mockito.doAnswer((Answer) invocation -> {
            animals.remove(mockAnimal);
            return null;
        }).when(animalService).deleteAnimal(Mockito.any(Animal.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Animal/delete")
                .accept(MediaType.APPLICATION_JSON).content(exampleAnimalJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(0, animals.size());

    }
}