package p2.backend.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import p2.backend.BackendApplication;
import p2.backend.Beans.Animal;
import p2.backend.Beans.Employee;
import p2.backend.Service.EmployeeService;
import p2.backend.security.SecurityConstants;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BackendApplication.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc;
    private BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(15);
    }

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private EmployeeService employeeService;
    Employee mockEmployee = new Employee("alpha", "omega", "usrnm", "psswrd", 1);
    Animal mockAnimal = new Animal("Spencer", "Mathematician turnedprogrammer","likes math", "loves math", 1, 1, "notes");
    Set<Animal> animals = new HashSet<>();

    @MockBean
    private JWT jwt;



    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void info() throws Exception{
        mockAnimal.setAnimalId(1);
        animals.add(mockAnimal);
        mockEmployee.setAnimals(animals);
        String exampleJson = "{\"username\": \"usrnm\", \"password\": \"psswrd\"}";

        Mockito.when(employeeService.getByID(Mockito.anyInt())).thenReturn(mockEmployee);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/users/info").accept(MediaType.APPLICATION_JSON).content(exampleJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(response.getResponse().getContentAsString());
        String expected = mockEmployee.toString();
        assertEquals(expected, response.getResponse().getContentAsString());

    }

    @Test
    public void signUp() throws Exception{
//        List<Employee> employees = new ArrayList<>();
//        String exampleJson = "{\"firstName\": \"alpha\", \"lastName\": \"omega\", \"username\": \"usrnm\", \"password\": \"psswrd\", \"role\": 1}";
//
//        Mockito.when(employeeService.saveEmployee(Mockito.any(Employee.class)))
//                .thenAnswer((Answer<String>) invocation -> {
//                    employees.add(mockEmployee);
//                    return "tested";
//                });
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/users/sign-up")
//                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
//
//        assertEquals(HttpStatus.OK.value(), response.getStatus());
//
//        assertEquals("http://localhost/Events/",
//                response.getHeader(HttpHeaders.LOCATION));
//        assertEquals(1, employees.size());
    }

    @Test
    public void signIn() throws Exception{
        mockEmployee.setEmployeeId(1);
        String password = bCryptPasswordEncoder().encode("psswrd");
        mockEmployee.setPassword(password);
        animals.add(mockAnimal);
        mockEmployee.setAnimals(animals);

        String exampleJson = "{\"firstName\": \"alpha\", \"lastName\": \"omega\", \"username\": \"usrnm\", \"password\": \"psswrd\", \"role\": 1}";
        //Algorithm algorithmHS = Algorithm.HMAC512(SecurityConstants.SECRET);

        Mockito.when(employeeService.byUsername(Mockito.anyString())).thenReturn(mockEmployee);
        //Mockito.when(jwt.create().withAudience(Mockito.anyString()).withIssuer(Mockito.anyString()).withSubject(Mockito.anyString()).withExpiresAt(Mockito.any(Date.class)).sign(algorithmHS)).thenReturn("token");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/signin")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void signInFail() throws Exception{
        mockEmployee.setEmployeeId(1);
        String password = bCryptPasswordEncoder().encode("wrongpassword");
        mockEmployee.setPassword(password);
        animals.add(mockAnimal);
        mockEmployee.setAnimals(animals);

        String exampleJson = "{\"firstName\": \"alpha\", \"lastName\": \"omega\", \"username\": \"usrnm\", \"password\": \"psswrd\", \"role\": 1}";
        //Algorithm algorithmHS = Algorithm.HMAC512(SecurityConstants.SECRET);

        Mockito.when(employeeService.byUsername(Mockito.anyString())).thenReturn(mockEmployee);
        //Mockito.when(jwt.create().withAudience(Mockito.anyString()).withIssuer(Mockito.anyString()).withSubject(Mockito.anyString()).withExpiresAt(Mockito.any(Date.class)).sign(algorithmHS)).thenReturn("token");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/signin")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
    }
}