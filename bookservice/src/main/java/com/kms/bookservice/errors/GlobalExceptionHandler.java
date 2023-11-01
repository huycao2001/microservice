package com.kms.bookservice.errors;

import com.kms.bookservice.entities.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final int FAIL_ERROR_CODE = -1;

    private final int SUCCESS_ERROR_CODE = 0;
    @ExceptionHandler(Exception.class)
    ResponseEntity<ResponseDTO> handleUnexpectedException(Exception ex) {
        log.error("Global exception " + ex.getMessage() );
        String message = ex.getLocalizedMessage();
        ResponseDTO responseDTO = new ResponseDTO(FAIL_ERROR_CODE, message, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setProperty("errorMessage", ex.getLocalizedMessage());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        problemDetail.setProperty("errorMessage", errors);
        problemDetail.setProperty("errorCode", "INVALID_REQUEST");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problemDetail);
    }
}
