package com.realiota.waste.exceptionhandler;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.realiota.waste.dto.Response;
import com.realiota.waste.exception.WasteManagementException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.PersistenceException;

@Component
@Slf4j
@ControllerAdvice
public class WasteCollectionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {WasteManagementException.class})
    protected ResponseEntity<Response> handleWasteCollectionException(RuntimeException ex,
                                                                      WebRequest request) {
        log.error("WasteManagementException occoured - {}", ExceptionUtils.getFullStackTrace(ex));

        WasteManagementException WasteManagementException;
        if (ex instanceof WasteManagementException) {
            WasteManagementException = (WasteManagementException) ex;
            return new ResponseEntity<>(
                    buildResponse(WasteManagementException.getAppErrorCode(), WasteManagementException.getAppErrorMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(buildResponse(500, "Something went wrong"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {PersistenceException.class})
    protected ResponseEntity<Response> handleSqlException(RuntimeException ex,
                                                          WebRequest request) {
        String message = null;

        // This is to handle the unique constraint errors while persisting entities.
        // We do not check if entities persisted are unique.
        // We rely on the MySQL Constraint Exception to ensure uniqueness.
        if (ex.getCause() != null
                && ex.getCause().getCause() != null
                && ex.getCause()
                .getCause() instanceof MySQLIntegrityConstraintViolationException) {

            MySQLIntegrityConstraintViolationException exception = (MySQLIntegrityConstraintViolationException) ex
                    .getCause().getCause();
            message = exception.getMessage();
        } else {
            log.error("Sql Exception occoured - {}", ExceptionUtils.getFullStackTrace(ex));
            message = "Failed to save";
        }

        return new ResponseEntity<>(buildResponse(500, message),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Response> handleUnknownException(RuntimeException ex,
                                                              WebRequest request) {
        log.error("Exception occoured - {}", ex);
        return new ResponseEntity<>(buildResponse(500, "Something went wrong!"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Response buildResponse(Integer errorCode, String errorMessage) {
        return Response.builder().appErrorCode(errorCode).errorMessage(errorMessage)
                .build();
    }

}
