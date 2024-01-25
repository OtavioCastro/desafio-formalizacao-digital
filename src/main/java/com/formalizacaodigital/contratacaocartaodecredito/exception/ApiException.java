package com.formalizacaodigital.contratacaocartaodecredito.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiException {
    private HttpStatusCode status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String mensagem;
    private String debugMessage;

    private ApiException() {
        timestamp = LocalDateTime.now();
    }

    ApiException(HttpStatusCode status) {
        this();
        this.status = status;
    }

    ApiException(HttpStatusCode status, Throwable ex) {
        this();
        this.status = status;
        this.mensagem = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiException(HttpStatusCode status, String message, Throwable ex) {
        this();
        this.status = status;
        this.mensagem = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
