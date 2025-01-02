package ie.atu.userservice;


import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonRepository personRepository;
    private final PersonService personService;

    private AccountServiceClient accountServiceClient;
    private PaymentServiceClient paymentServiceClient;
    private final PaymentNotificationListener paymentNotificationListener;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    List<Person> people = new ArrayList<>();

    public PersonController(PersonRepository personRepository, PersonService personService, AccountServiceClient accountServiceClient, PaymentServiceClient paymentServiceClient, PaymentNotificationListener paymentNotificationListener, RabbitTemplate rabbitTemplate) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.accountServiceClient = accountServiceClient;
        this.paymentServiceClient = paymentServiceClient;
        this.paymentNotificationListener = paymentNotificationListener;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public List<Person> getPeople(){
        return personRepository.findAll();
    }

    //Return one person. Optional helps you to handle cases where a person might not exist
    //while still returning a single person object when one is found
    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable String id){
        return personService.getPersonById(id);
    }

    @PostMapping
    public ResponseEntity<List<Person>>createPerson(@Valid @RequestBody Person person){
        people = personService.createPerson(person);
        return ResponseEntity.ok(people);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<Person>>updatePerson(@PathVariable String id, @Valid @RequestBody Person updatedPerson){
        people = personService.updatePerson(id, updatedPerson);
        return ResponseEntity.ok(people);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Person>>deletePerson(@PathVariable String id){
        people = personService.deletePerson(id);
        return ResponseEntity.ok(people);
    }

    //Get the message from Account Service
    @PostMapping("/register-accounts")
    public String registerAccounts(@RequestBody Person person){
        String affirm = accountServiceClient.getDetails(person);
        return affirm;
    }

    /*@PostMapping("/payments-notifications")
    public String notifyPayments(@Valid @RequestBody Payment payment){
        String notification = paymentServiceClient.getPayments(payment);
        return notification;
    }*/


    @PostMapping("/sendToAccountService")
    public String accountInformation(@Valid @RequestBody Person person){
        String accountInfo = String.format("Create account with the following details: Name: %s, Email: %s, Account Type: %s",
                person.getName(), person.getEmail(), person.getAccountType());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "accountQueue", accountInfo);
        return accountInfo;
    }
}
