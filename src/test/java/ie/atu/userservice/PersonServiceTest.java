package ie.atu.userservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
//import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreatePerson() {
        Person person = new Person("1","Jane Doe", "janedoe@atu.ie", "Savings Account");
        when(personRepository.save(person)).thenReturn(person);
        Person newPerson = personService.createPerson(person);
        assertEquals("Jane Doe", newPerson.getName());
        assertEquals("Savings Account", newPerson.getAccountType());
    }


}
