package ru.kc4kt4.data.distributor.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kc4kt4.data.distributor.annotations.JsonRestController;
import ru.kc4kt4.data.distributor.exception.ConnectionException;
import ru.kc4kt4.data.distributor.exception.InternalServerErrorException;
import ru.kc4kt4.data.distributor.exception.NotFoundException;
import ru.kc4kt4.data.distributor.response.ErrorResponse;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice(annotations = JsonRestController.class)
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorResponse handleException(NotFoundException e, HttpServletResponse httpResponse) {
        log.error(e.getMessage(), e);
        httpResponse.setStatus(HttpStatus.SC_NOT_FOUND);
        return new ErrorResponse(HttpStatus.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ConnectionException.class)
    @ResponseBody
    public ErrorResponse handleException(ConnectionException e, HttpServletResponse httpResponse) {
        log.error(e.getMessage(), e);
        httpResponse.setStatus(HttpStatus.SC_BAD_GATEWAY);
        return new ErrorResponse(HttpStatus.SC_BAD_GATEWAY, e.getMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseBody
    public ErrorResponse handleException(InternalServerErrorException e, HttpServletResponse httpResponse) {
        log.error(e.getMessage(), e);
        httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return new ErrorResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
