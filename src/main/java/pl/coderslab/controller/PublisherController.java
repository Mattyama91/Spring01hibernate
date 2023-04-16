package pl.coderslab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Publisher;

import javax.transaction.Transactional;

@Slf4j
@Controller
@Transactional
public class PublisherController {
    private final PublisherDao publisherDao;
    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }
    @RequestMapping("/publisher/add")
    @ResponseBody
    public String hello() {
        Publisher publisher = new Publisher();
        publisher.setName("publisher name");
        publisherDao.savePublisher(publisher);
        return "Id dodanego wydawcy:" + publisher.getId();
    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/update/{id}/{name}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String name ) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "deleted";
    }

    @RequestMapping("/publisher/all")
    @ResponseBody
    public void findAllPublisher() {
        publisherDao.findAll().forEach(publisher -> log.info(publisher.toString()));
        log.info("siema");
    }
}
