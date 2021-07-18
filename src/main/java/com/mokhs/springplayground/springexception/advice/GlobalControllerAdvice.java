package com.mokhs.springplayground.springexception.advice;

import com.mokhs.springplayground.springexception.dto.Error;
import com.mokhs.springplayground.springexception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@ControllerAdvice
public class GlobalControllerAdvice {


    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        System.out.println(e.getClass().getName());
        System.out.println(e.getLocalizedMessage());

        return ResponseEntity.internalServerError().body("");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        System.out.println(e.getLocalizedMessage());

        List<Error> errorList = new ArrayList<>();

        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;

            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            String value = fieldError.getRejectedValue().toString();

            Error errorMessage = new Error();
            errorMessage.setMessage(message);
            errorMessage.setField(fieldName);
            errorMessage.setInvalidValue(value);

            errorList.add(errorMessage);
        });

        ErrorResponse response = new ErrorResponse();
        response.setErrorList(errorList);
        response.setMessage("");
        response.setRequestUrl(request.getRequestURI());
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setResultCode("FAIL");
        return ResponseEntity.badRequest()
                .body(response);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        System.out.println(e.getLocalizedMessage());

        List<Error> errorList = new ArrayList<>();

        e.getConstraintViolations().forEach(error -> {
            Stream<Path.Node> stream = StreamSupport.stream(error.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());

            String field = list.get(list.size() - 1).getName();
            String message = error.getMessage();
            String invalidValue = error.getInvalidValue().toString();

            Error errorMessage = new Error();
            errorMessage.setMessage(message);
            errorMessage.setField(field);
            errorMessage.setInvalidValue(invalidValue);

            errorList.add(errorMessage);
        });

        ErrorResponse response = new ErrorResponse();
        response.setErrorList(errorList);
        response.setMessage("");
        response.setRequestUrl(request.getRequestURI());
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setResultCode("FAIL");

        return ResponseEntity.badRequest()
                .body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        System.out.println(e.getLocalizedMessage());

        List<Error> errorList = new ArrayList<>();

        String fieldName = e.getParameterName();
        String message = e.getMessage();

        Error errorMessage = new Error();
        errorMessage.setField(fieldName);
        errorMessage.setMessage(message);

        ErrorResponse response = new ErrorResponse();
        response.setErrorList(errorList);
        response.setMessage("");
        response.setRequestUrl(request.getRequestURI());
        response.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        response.setResultCode("FAIL");

        return ResponseEntity.badRequest()
                .body(response);
    }
}
