package peaksoft.exceptions;

/**
 * @author Beksultan
 */
public class NoResultException extends RuntimeException {
    public NoResultException() {
    }

    public NoResultException(String message) {
        super(message);
    }
}
