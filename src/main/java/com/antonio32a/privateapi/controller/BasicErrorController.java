package com.antonio32a.privateapi.controller;

import com.antonio32a.privateapi.responses.ErrorResponse;
import jakarta.annotation.Nullable;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public final class BasicErrorController implements ErrorController {
    @RequestMapping("/error")
    public ErrorResponse handleError(HttpServletRequest request) {
        @Nullable Throwable error = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (error != null) {
            return ErrorResponse.builder()
                .error(error.getMessage())
                .build();
        }

        @Nullable int statusCode = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.resolve(statusCode);
        if (status == null) {
            log.error("Unknown error status code: {}", statusCode);
            return ErrorResponse.builder()
                .error("Unknown error")
                .build();
        }

        return ErrorResponse.builder()
            .error(status.getReasonPhrase())
            .build();
    }
}
