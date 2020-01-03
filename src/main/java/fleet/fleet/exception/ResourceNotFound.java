package fleet.fleet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Create exception used when Http status received is NOT_FOUND
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends Exception {
    private static final long serialVersionUID = 1L;

    public ResourceNotFound(String message) {
        super(message);
    }

}
