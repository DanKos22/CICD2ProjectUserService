package ie.atu.userservice;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonRepository personRepository;
    private final PersonService personService;

    List<Person> people = new ArrayList<>();

    public PersonController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getPeople(){
        return personRepository.findAll();
    }

    //Return one person
    @GetMapping("/{id}")
    public ResponseEntity<List<Person>>getPersonById(@PathVariable String id){
        people = personService.getPersonById(id);
        return ResponseEntity.ok(people);
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
}
