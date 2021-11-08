package com.challenge.java.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Alexis
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private final Map<String,Object> response = new HashMap<>();

    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> badRequestErrorResponse(Exception e, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(e,req.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoAuthorizeException.class)
    public ResponseEntity<ErrorMessage> unauthorizedErrorResponse(Exception ex, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(ex,req.getRequestURI()),HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundErrorResponse(Exception ex, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(ex,req.getRequestURI()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorMessage> forbiddenResponse(Exception e, HttpServletRequest req){
        return new ResponseEntity<>(new ErrorMessage(e,req.getRequestURI()),HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        response.clear();
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField().concat(" ").concat(Objects.requireNonNull(err.getDefaultMessage())))
                .collect(Collectors.toList());
        response.put("timestamp",LocalDateTime.now());
        response.put("errors",errors);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
