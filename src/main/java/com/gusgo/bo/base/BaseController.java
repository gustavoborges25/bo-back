package com.gusgo.bo.base;

import com.gusgo.bo.dto.response.ErrorResponseDTO;
import com.gusgo.bo.dto.response.ResponseDTO;
import com.gusgo.bo.exception.ServiceException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    private final MessageSource messageSource;

    public BaseController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(ServiceException exception) {
        String message = messageSource.getMessage(exception.getErrorCode(), exception.getParameters(), LocaleContextHolder.getLocale());
        ErrorResponseDTO error = new ErrorResponseDTO(exception.getErrorCode() + ": " + message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDTO.withError(error));
    }

}
