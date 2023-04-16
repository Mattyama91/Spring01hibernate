package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;
    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book)); }

    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b")
                .getResultList();
    }

    public List<Book> findAllByRating(Integer rating) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.rating =:rating")
                .setParameter("rating", rating)
                .getResultList();
    }

//    Uzupełnij klasę BookDao o metodę do pobierania listy wszystkich książek, które mają jakiegokolwiek wydawcę.
    public List<Book> findAllWithPublisher(){
        return entityManager.createQuery("SELECT b FROM Book b JOIN b.publisher")
                .getResultList();
    }
//    Uzupełnij klasę BookDao o metodę do pobierania listy wszystkich książek, które mają określonego w parametrze wydawcę.
    public List<Book> findAllWithSpecificPublisher(Publisher publisher) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher=:publisher")
                .setParameter("publisher", publisher)
                .getResultList();
    }
//    Uzupełnij klasę BookDao o metodę do pobierania listy wszystkich książek, które mają określonego w parametrze autora.

    public List<Book> findAllWithSpecificAuthor(Author author) {
        return entityManager.createQuery("SELECT distinct b FROM Book b join FETCH b.authors WHERE :author member of b.authors")
                .setParameter("author", author)
                .getResultList();
    }
}
