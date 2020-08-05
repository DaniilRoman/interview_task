package tinkoff.exception;

import org.springframework.http.HttpStatus;

public class ApplicationNotFoundException extends CommonException {
    public ApplicationNotFoundException(Integer contactId) {
        setMsg("Application by contact id: `" + contactId + "` not found.");
        setCode(HttpStatus.NOT_FOUND);
    }
}
