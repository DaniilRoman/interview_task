package tinkoff.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@NoArgsConstructor
@AllArgsConstructor
abstract public class CommonException extends RuntimeException {
    private HttpStatus code;
    private String msg;
}
