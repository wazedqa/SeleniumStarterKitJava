package de.zalando.project.selenium.exception;

public class PageException extends RuntimeException {

    public PageException(final String message, final Throwable throwable) {
        super(message, throwable);
    }

}
