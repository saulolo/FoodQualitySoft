package edu.Sena.FoodQualitySoft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)//Al lanzarse esta excepción, se reportara con un código 400
public class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }


}
