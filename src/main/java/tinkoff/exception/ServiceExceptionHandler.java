package tinkoff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ResponseEntity<String> onException(CommonException exception) {
        return new ResponseEntity<>(exception.getMsg(), exception.getCode());
    }


    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ResponseEntity<String> onSqlException(SQLException exception) {
        return new ResponseEntity<>("Cannot extrct data from database.", HttpStatus.SERVICE_UNAVAILABLE);
    }


}
