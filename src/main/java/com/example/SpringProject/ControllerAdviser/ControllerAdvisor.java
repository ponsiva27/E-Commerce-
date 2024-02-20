package com.example.SpringProject.ControllerAdviser;

import com.example.SpringProject.DTOs.ErrorResponseDTO;
import com.example.SpringProject.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDTO> ArithmeticException() {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setErrorMessage("An Arithmetic Exception has occurred while processing the Request: ");

        ResponseEntity responseEntity = new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> NullPointerException() {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO();
        errorResponse.setErrorMessage("A Null Pointer Exception has occurred while processing the Request: ");

        ResponseEntity responseEntity = new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> ProductNotFound( ProductNotFoundException prodNotFound) {

        ErrorResponseDTO  errorResponse = new ErrorResponseDTO();
        errorResponse.setErrorMessage(prodNotFound.getMessage());

        ResponseEntity  responseEntity = new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }
}
