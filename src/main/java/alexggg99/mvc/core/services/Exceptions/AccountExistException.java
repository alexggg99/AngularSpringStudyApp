package alexggg99.mvc.core.services.Exceptions;

/**
 * Created by alexggg99 on 03.12.15.
 */
public class AccountExistException extends RuntimeException {
    public AccountExistException() {
    }

    public AccountExistException(String message) {
        super(message);
    }

    public AccountExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
