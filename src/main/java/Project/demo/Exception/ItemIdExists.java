package Project.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ItemIdExists extends RuntimeException{
    public ItemIdExists(String message) {
        super(message);
    }
}
