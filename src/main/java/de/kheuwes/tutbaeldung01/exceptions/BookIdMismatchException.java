package de.kheuwes.tutbaeldung01.exceptions;

public class BookIdMismatchException extends Throwable {
    public BookIdMismatchException() {
        super("Test", null);
    }
}
