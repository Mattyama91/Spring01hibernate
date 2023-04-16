package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.dao.PersonDetailsDao;
import pl.coderslab.model.Person;
import pl.coderslab.model.PersonDetails;

@Controller
public class PersonController {
    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }
    @RequestMapping(value = "/person/add")
    @ResponseBody
    public String addPerson() {
        Person person = new Person();
        person.setLogin("Login");
        person.setPassword("Password");
        person.setEmail("Email");
        personDao.savePerson(person);
        PersonDetails personDetails = new PersonDetails();
        personDetails.setFirstName("First Name");
        personDetails.setLastName("Last Name");
        personDetails.setStreetNumber(1);
        personDetails.setStreet("Street");
        personDetails.setCity("City");
        personDetails.setPerson(person);
        personDetailsDao.savePersonDetails(personDetails);
        return "Id dodanego użytkownika to:" + person.getId();
    }

    @RequestMapping(value = "/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id) {
        Person person = personDao.findById(id);
        return person.toString();
    }

    @RequestMapping(value = "/person/update/{id}/{login}")
    @ResponseBody
    public String updatePerson(@PathVariable long id, @PathVariable String login ) {
        Person person = personDao.findById(id);
        person.setLogin(login);
        personDao.update(person);
        return person.toString();
    }

    @RequestMapping(value = "/person/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Person person = personDao.findById(id);
        personDao.delete(person);
        return "deleted";
    }
}
