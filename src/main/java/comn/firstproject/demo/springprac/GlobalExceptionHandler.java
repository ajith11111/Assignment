package comn.firstproject.demo.springprac;

import comn.firstproject.demo.springprac.assesment.entities.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorObject> catchEntityError(MethodArgumentTypeMismatchException e) {
        System.out.println(">>   MethodArgumentTypeMismatchException handler ran !");
        return new ResponseEntity<>(new ErrorObject(LocalTime.now(), "Please Make a Valid Request :" + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> wrongDataFormat(SQLIntegrityConstraintViolationException e) {
        System.out.println(">>   SQLIntegrityConstraintViolationException handler ran !");
        return new ResponseEntity<>(new ErrorObject(LocalTime.now(), e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorObject> wrongDataFormat(NoSuchElementException e) {
        System.out.println(">>   NoSuchElementException handler ran !");
        return new ResponseEntity<>(new ErrorObject(LocalTime.now(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> catchJSONError(HttpMessageNotReadableException e) {
        System.out.println(">>   HttpMessageNotReadableException handler ran !");
        return new ResponseEntity<>(new ErrorObject(LocalTime.now(), "Please Provide a Valid Rating and Rating Type :" + e.getMessage()), HttpStatus.BAD_REQUEST);
    }

  /*  @ExceptionHandler
    public ResponseEntity<String> catchAllError(RuntimeException e)
    {
        System.out.println(">>   Exception handler ran !");
        return new ResponseEntity<>("Please Make a Valid Request :"+e.getMessage(), HttpStatus.BAD_REQUEST);
    }

   */

}
