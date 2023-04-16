package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Publisher;

import java.util.HashSet;
import java.util.Set;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }
    @RequestMapping("/book/add")
    @ResponseBody
    public String addBook() {
        Publisher publisher = new Publisher();
        publisher.setName("publisher");
        publisherDao.savePublisher(publisher);
        Set<Author> authors = new HashSet<>();
        authors.add(authorDao.findById(2));
        authors.add(authorDao.findById(3));
        Book book = new Book();
        book.setTitle("Thinking in Java");
        book.setRating(1);
        book.setDescription("description");
        book.setPublisher(publisher);
        book.setAuthors(authors);
        bookDao.saveBook(book);
        return "Id dodanej książki to:" + book.getId();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title ) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }
}
