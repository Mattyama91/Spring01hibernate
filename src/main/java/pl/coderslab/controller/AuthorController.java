package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.model.Author;

@Controller
public class AuthorController {
    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
    @RequestMapping(value = "/author/add", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String hello() {
        Author author = new Author();
        author.setFirstName("First name");
        author.setLastName("Last name");
        authorDao.saveAuthor(author);
        return "Id dodanego autora to:" + author.getId();
    }

    @RequestMapping(value = "/author/get/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @RequestMapping(value = "/author/update/{id}/{firstName}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String firstName ) {
        Author author = authorDao.findById(id);
        author.setFirstName(firstName);
        authorDao.update(author);
        return author.toString();
    }

    @RequestMapping(value = "/author/delete/{id}", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "deleted";
    }
}
