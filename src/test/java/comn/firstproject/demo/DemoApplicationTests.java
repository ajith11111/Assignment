package comn.firstproject.demo;

import static org.assertj.core.api.Assertions.assertThat;

import comn.firstproject.demo.springprac.assesment.CustomerRatingApi;
import comn.firstproject.demo.springprac.assesment.entities.Customers;
import comn.firstproject.demo.springprac.assesment.services.CustomerRatingService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class) // So this might be using Junit5
//@WebMvcTest(SmallGetRESTAPI.class)// So this might be using Junit5
class DemoApplicationTests {


    // When you use @InjectMocks and @Mock We should add @ExtendWith(MockitoExtension.class) to the class annotation
    // But if we use spring-boot-starter-test (@SpringBootTest) it automatically does for us


    @InjectMocks // This is to be used when we inject mocks into this
    private CustomerRatingApi customerRatingApi;
    @MockBean  // when you use @Mock it automatically creates a mock object and injects it at required position
    private CustomerRatingService customerRatingService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        // do this before every test !
        System.out.println(">>  This happens before every test !");
    }

    @AfterEach
    public void after() {
        // do this after every test !
        System.out.println(">>  This happens after every test !");
    }

    @Test
    void contextLoads() {
        String hello = "Hello";
        assertThat("Hello").isEqualTo("Hello");
        System.out.println(">>  Ran contextLoads() !");
    }

    @Test
    void makeGetReqTest() {
        Customers customers = new Customers(8, "Bala", "bala@gmail.com", "1234567890", null, null);
        when(customerRatingService.getCustomerByIdForDisplay(anyInt())).thenReturn(customers);
        assertThat(customerRatingApi.getCustomerById(56)).isEqualTo(customers);
        System.out.println(">>  Ran makeGetReqTest() !");

        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);

        //The below line checks whether the mock objects method is called  verify (mock_obj).mockMethod() during the test
        verify(customerRatingService, times(1)).getCustomerByIdForDisplay(captor.capture());

        List<Integer> allCapturedValues = captor.getAllValues();
        System.out.println(">>  Captured :" + allCapturedValues+ " from mockTest()");

    }

    @Test
    public void sampleHelloTest() throws Exception {
        Customers customers = new Customers(8, "Bala", "bala@gmail.com", "1234567890", null, null);
        when(customerRatingService.getCustomerByIdForDisplay(anyInt())).thenReturn(customers);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/customer/7")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andReturn();

        System.out.println(">>  Response from Mock API :" + mvcResult.getResponse().getContentAsString());
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("{\"id\":8,\"name\":\"Bala\",\"email\":\"bala@gmail.com\",\"phone\":\"1234567890\",\"averageRating\":null}");
    }
}
