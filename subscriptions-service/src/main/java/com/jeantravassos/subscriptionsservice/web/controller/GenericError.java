package com.jeantravassos.subscriptionsservice.web.controller;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
class GenericError {
    private Date timestamp;
    private String exception;
    private String message;

}
