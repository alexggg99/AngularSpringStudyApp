package alexggg99.mvc.core.services.Exceptions;

/**
 * Created by alexggg99 on 03.12.15.
 */
public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogNotFoundException(String message) {
        super(message);
    }

    public BlogNotFoundException() {
    }
}
