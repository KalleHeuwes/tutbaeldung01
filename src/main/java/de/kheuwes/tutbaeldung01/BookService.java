package de.kheuwes.tutbaeldung01;

public interface BookService {

    public Book createBook(String title, String author);

    public Book createBook(Book book);

    public Iterable<Book> createTestBooks();

    public Iterable<Book> getAllBooks();
}
