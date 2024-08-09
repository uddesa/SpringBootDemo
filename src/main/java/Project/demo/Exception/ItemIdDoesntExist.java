package Project.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemIdDoesntExist extends RuntimeException{
    public ItemIdDoesntExist(String message) {
        super(message);
    }
}
