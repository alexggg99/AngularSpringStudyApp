package alexggg99.mvc.core.services.Exceptions;

/**
 * Created by alexggg99 on 03.12.15.
 */
public class BlogExistsException extends RuntimeException{

    public BlogExistsException() {
    }

    public BlogExistsException(String message) {
        super(message);
    }

    public BlogExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogExistsException(Throwable cause) {
        super(cause);
    }
}
