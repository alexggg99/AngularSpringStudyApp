package alexggg99.mvc.core.services.Exceptions;

/**
 * Created by alexggg99 on 03.12.15.
 */
public class AccountDoesNotExist extends RuntimeException {

    public AccountDoesNotExist() {
    }

    public AccountDoesNotExist(String message) {
        super(message);
    }

    public AccountDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExist(Throwable cause) {
        super(cause);
    }
}
