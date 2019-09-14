package com.a6raywa1cher.javahackraiffemulator.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Safe transfer flag violation")
public class SafeTransferFlagViolationException extends RuntimeException {
}
