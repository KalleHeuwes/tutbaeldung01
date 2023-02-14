package de.kheuwes.tutbaeldung01;

import de.kheuwes.tutbaeldung01.exceptions.BookIdMismatchException;
import de.kheuwes.tutbaeldung01.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/author/{bookAuthor}")
    public List findByAuthor(@PathVariable String bookAuthor) {
        System.out.printf("Suche Bücher des Autors <%s>\n", bookAuthor);
        return bookRepository.findByAuthor(bookAuthor);
    }

    @GetMapping("/{id}")
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElse(new Book("Buch nicht gefunden", ""));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public Iterable<Book> createBooks() {
        System.out.println("POST createBooks ...");
        return bookService.createTestBooks();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }
    @DeleteMapping("/")
    public void deleteAll() {
        bookRepository.deleteAll();
    }
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) throws BookIdMismatchException {
        if (book.getId() != id) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(book);
    }
}
