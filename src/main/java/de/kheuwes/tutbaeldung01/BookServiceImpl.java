package de.kheuwes.tutbaeldung01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(String title, String author) {
        Book book=new Book(title,author);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book createBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    @Override
    public Iterable<Book> createTestBooks() {
        createBook(new Book( "Bible", "God"));
        createBook(new Book( "Im Angesicht des Todes", "Giorgio Faletti"));
        createBook(new Book( "Io uccido", "Giorgio Faletti"));
        createBook(new Book( "Tom Sawyer", "Marc Twain"));
        return getAllBooks();
    }

    @Override
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByTitle(String title){
        List<Book> books = bookRepository.findByTitle(title);
        return (books.size()==0 ? null : books.get(0));
    }
}
