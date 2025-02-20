package in.jp.trial.sb.sample.application.errorhandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class SampleApplicationControllerErrorHandler {

    @ExceptionHandler(exception = NoSuchElementException.class)
    public ResponseEntity<String> noElementFoundExceptionHandler(){
        return ResponseEntity.internalServerError().body("No Element with the provided Id was found");
    }


}
