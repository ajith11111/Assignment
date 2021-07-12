package comn.firstproject.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import comn.firstproject.demo.springprac.assesment.CustomerRatingApi;

import comn.firstproject.demo.springprac.assesment.entities.Customers;
import comn.firstproject.demo.springprac.assesment.services.CustomerRatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(CustomerRatingApi.class)
public class CustomerRatingApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRatingService customerRatingService;

    @Test
    public void getReqTest() throws Exception {
        Customers customers = new Customers(8, "Bala", "bala@gmail.com", "1234567890", null, null);
        when(customerRatingService.getCustomerByIdForDisplay(anyInt())).thenReturn(customers);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/customer/7")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc
                .perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(content().json("{\"id\":8,    \"name\":\"Bala\",\"email\":\"bala@gmail.com\",\"phone\":\"1234567890\",\"averageRating\":null}"))
                .andReturn();
        System.out.println(">>  Response from Mock API :" + mvcResult.getResponse().getContentAsString());
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("{\"id\":8,\"name\":\"Bala\",\"email\":\"bala@gmail.com\",\"phone\":\"1234567890\",\"averageRating\":null}");
    }

    @Test
    public void postReqTest() throws Exception {

        String sampleCustomer = "{\"id\":8,\"name\":\"Bala\",\"email\":\"bala@gmail.com\",\"phone\":\"9234567890\",\"averageRating\":null}";

        Customers customers = new Customers(8, "Bala", "bala@gmail.com", "9234567890", null, null);
        when(customerRatingService.saveANewCustomer(any(Customers.class))).thenReturn(customers);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sampleCustomer);
        MvcResult mvcResult = mockMvc.perform(request)
                .andExpect(content().json("{\"id\":8,    \"name\":\"Bala\",\"email\":\"bala@gmail.com\",\"phone\":\"9234567890\",\"averageRating\":null}"))
                .andExpect(status().is(201))
                .andReturn();
        System.out.println(">>  Response from Mock API :" + mvcResult.getResponse().getContentAsString());

    }
}
