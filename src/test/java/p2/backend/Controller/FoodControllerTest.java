package p2.backend.Controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import p2.backend.BackendApplication;
import p2.backend.Beans.Food;
import p2.backend.Service.FoodService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BackendApplication.class)
@SpringBootTest
public class FoodControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private FoodService foodService;
    Food food1 = new Food("food1", 1, "delivery", "notes");
    Food food2 = new Food("food2", 1, "delivery", "notes");



    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void foodListTest() throws Exception {

        List<Food> result = new ArrayList<>();
        result.add(food1);
        result.add(food2);
        Mockito.when(foodService.listofFood()).thenReturn(result);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/Food/").accept(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(response.getResponse());
        String expected = "[{\"foodName\": \"food1\",\"amount\": 1,\"nextDelivery\": \"delivery\", \"notes\": \"notes\" }, {\"foodName\": \"food2\",\"amount\": 1,\"nextDelivery\": \"delivery\", \"notes\": \"notes\" }]";
        JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);
    }

    @Test
    public void saveFoodTest() throws Exception {
        List<Food> list = new ArrayList<>();
        Food mockFood = food2;
        list.add(food1);

        Mockito.doAnswer((Answer) invocation -> {
            list.add(mockFood);
            return null;
        }).when(foodService).saveFood(Mockito.any(Food.class));

        String exampleJson = "{\"foodName\": \"food2\",\"amount\": 1,\"nextDelivery\": \"delivery\", \"notes\": \"notes\" }";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Food/save")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(2, list.size());
    }

    @Test
    public void deleteFoodTest() throws Exception {

        List<Food> list = new ArrayList<>();
        list.add(food1);
        list.add(food2);

        Food mockFood = food2;
        Mockito.doAnswer((Answer) invocation -> {
            list.remove(mockFood);
            return null;
        }).when(foodService).deleteFood(Mockito.any(Food.class));
        String exampleJson = "{\"foodName\": \"food2\",\"amount\": 1,\"nextDelivery\": \"delivery\", \"notes\": \"notes\" }";


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Food/delete")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult res = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = res.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(1, list.size());
    }
}