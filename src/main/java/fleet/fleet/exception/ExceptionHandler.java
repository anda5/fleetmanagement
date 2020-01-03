package fleet.fleet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
/**
 * Handler used for show exception in body response of request
 **/
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFound ex, WebRequest request) {
        ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> gkobalExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetail errorDetails = new ErrorDetail(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
