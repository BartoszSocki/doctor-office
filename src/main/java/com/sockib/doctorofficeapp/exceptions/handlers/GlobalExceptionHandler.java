package com.sockib.doctorofficeapp.exceptions.handlers;

import com.sockib.doctorofficeapp.exceptions.PlannedVisitAlreadyTakenException;
import com.sockib.doctorofficeapp.exceptions.UnableToGetResourceException;
import com.sockib.doctorofficeapp.exceptions.UserAlreadyRegisteredException;
import com.sockib.doctorofficeapp.exceptions.UserNotFoundException;
import com.sockib.doctorofficeapp.model.dto.ExceptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserAlreadyRegisteredException.class)
    public ResponseEntity<?> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex) {
        return ResponseEntity.badRequest().body(ExceptionDto.toDto(ex));
    }
    
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.badRequest().body(ExceptionDto.toDto(ex));
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.badRequest().body(ExceptionDto.toDto(ex));
    }

    @ExceptionHandler(value = UnableToGetResourceException.class)
    public ResponseEntity<?> handleUnableToGetResourceException(UnableToGetResourceException ex) {
        return ResponseEntity.badRequest().body(ExceptionDto.toDto(ex));
    }

    @ExceptionHandler(value = PlannedVisitAlreadyTakenException.class)
    public ResponseEntity<?> handlePlannedVisitAlreadyTakenException(PlannedVisitAlreadyTakenException ex) {
        return ResponseEntity.badRequest().body(ExceptionDto.toDto(ex));
    }

}
