package edu.Sena.FoodQualitySoft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody //Indica que esta excepción al lanzarse desde un controlador, formará parte del cuerpo de respuesta de la petición
@ResponseStatus(HttpStatus.NOT_FOUND)//Aal lanzarse esta excepción, se reportara con un código 404, Sprin/ creará un ResponseEntity por nosotros al detectar el lazamiento de esta excepción
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}


