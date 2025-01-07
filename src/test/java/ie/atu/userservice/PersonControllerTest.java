package ie.atu.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    PersonRepository personRepository;

    @MockBean
    AccountServiceClient accountServiceClient;

    @MockBean
    PaymentServiceClient paymentServiceClient;

    @MockBean
    PaymentNotificationListener paymentNotificationListener;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetPersonById() throws Exception {
        Person person = new Person("1", "Mike", "mike@atu.ie", "Bank Account");
        when(personService.getPersonById("1")).thenReturn(Optional.of(person));

        mockMvc.perform(get("/people/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mike"));
    }

    @Test
    void testUpdatePerson() throws Exception {
        Person updatedPerson = new Person("2", "Mike", "mike@atu.ie", "Savings Account");
        when(personService.updatePerson("2", updatedPerson)).thenReturn(updatedPerson);

        ObjectMapper om = new ObjectMapper();
        String valueJson = om.writeValueAsString(updatedPerson);

        mockMvc.perform(put("/people/2").contentType("application/json").content(valueJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mike"));
    }
}
