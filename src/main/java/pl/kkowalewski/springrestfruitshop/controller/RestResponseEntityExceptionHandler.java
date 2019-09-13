package pl.kkowalewski.springrestfruitshop.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.kkowalewski.springrestfruitshop.exception.ResourceNotFoundException;

import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.RESOURCE_NOT_FOUND;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(
            Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(RESOURCE_NOT_FOUND, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
