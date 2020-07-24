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
import p2.backend.Beans.Events;
import p2.backend.Service.EventsService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BackendApplication.class)
@SpringBootTest
public class EventsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private EventsService eventsService;
    Events evt1 = new Events("Event1", "location", "time");
    Events evt2 = new Events("Event2", "location", "time");



    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }
    @Test
    public void eventList() throws Exception {

        List<Events> result = new ArrayList<>();
        result.add(evt1);
        result.add(evt2);
        Mockito.when(eventsService.listOfEvents()).thenReturn(result);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/Events/").accept(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(response.getResponse());
        String expected = "[{\"what\": \"Event1\",\"where\": \"location\",\"when\": \"time\" }, {\"what\": \"Event2\",\"where\": \"location\",\"when\": \"time\"}]";
        JSONAssert.assertEquals(expected, response.getResponse().getContentAsString(), false);

    }

    @Test
    public void saveEvent() throws Exception   {
        Events mockEvent = evt1;

        Mockito.when(
                eventsService.saveEvent(Mockito.any(Events.class))).thenReturn("tested");
                String exampleEventJson = "{\"what\": \"Event1\",\"where\": \"location\",\"when\": \"time\" }";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Events/save")
                .accept(MediaType.APPLICATION_JSON).content(exampleEventJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

//        assertEquals("http://localhost/Events/",
//                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void deleteEvent() throws Exception {

        List<Events> result = new ArrayList<>();
        result.add(evt1);
        result.add(evt2);

        Events mockEvent = evt1;
        String exampleEventJson = "{\"what\": \"Event1\",\"where\": \"location\",\"when\": \"time\" }";

        Mockito.when(eventsService.deleteEvent(Mockito.any(Events.class)))
                .thenAnswer((Answer<String>) invocation -> {
                    result.remove(mockEvent);
                    return "tested";
                });

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/Events/delete")
                .accept(MediaType.APPLICATION_JSON).content(exampleEventJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult res = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = res.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(1, result.size());

    }
}