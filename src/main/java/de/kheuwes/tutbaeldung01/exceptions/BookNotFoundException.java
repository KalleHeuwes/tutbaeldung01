package de.kheuwes.tutbaeldung01.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public BookNotFoundException() {
        //super("Test", null);
        System.out.println("Buch nicht gefunden");
    }
}
