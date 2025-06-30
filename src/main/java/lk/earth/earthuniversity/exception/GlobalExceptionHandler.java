package lk.earth.earthuniversity.exception;

import jakarta.servlet.http.HttpServletRequest;
import lk.ashan.demo.model.response.APIErrorResponse;
import lk.ashan.demo.model.response.ErrorCode;
import lk.ashan.demo.util.APIResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceExistsException.class)
    public ResponseEntity<APIErrorResponse> handleExistsException(
            ResourceExistsException e,
            HttpServletRequest request
    ) {
        return APIResponseBuilder.error(
                ErrorCode.EMPLOYEE_ALREADY_EXISTS,
                e.getMessage(),
                request
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleNotFoundException(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        return APIResponseBuilder.error(
                ErrorCode.EMPLOYEE_NOT_FOUND,
                e.getMessage(),
                request
        );
    }

    // Add generic fallback for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorResponse> handleGenericException(
            Exception e,
            HttpServletRequest request
    ) {
        return APIResponseBuilder.error(
                ErrorCode.UNKNOWN_ERROR,
                e.getMessage(),
                request
        );
    }
}
