package ie.atu.userservice;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    PersonRepository personRepository;

    //private List<Person> personList = new ArrayList<>();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPersonById(String id){
        return personRepository.findById(id);
    }

    public List<Person>createPerson(Person person){
        personRepository.save(person);
        return personRepository.findAll();
    }

    public List<Person>updatePerson(String id, Person person){
        if(personRepository.existsById(id)){
            person.setId(id);
            personRepository.save(person);
        }
        return personRepository.findAll();
    }

    public List<Person>deletePerson(String id){
        if(personRepository.existsById(id)){
            personRepository.deleteById(id);
        }
        return personRepository.findAll();
    }
}
