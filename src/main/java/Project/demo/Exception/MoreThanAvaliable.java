package Project.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MoreThanAvaliable extends RuntimeException{
    public MoreThanAvaliable(String message) {
        super(message);
    }
}
