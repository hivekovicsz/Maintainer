package com.example.maintainer.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.maintainer.exception.MaintainerException;
import com.example.maintainer.model.ErrorMessage;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler({ MaintainerException.class })
	public ResponseEntity<Object> handleCommonException(Exception ex, WebRequest request) throws Exception {

		if (ex instanceof MaintainerException) {
			String responseCode = ((MaintainerException) ex).getCode();

			ErrorMessage resultBody = new ErrorMessage();
			resultBody.setCode(responseCode);

			return new ResponseEntity<>(resultBody, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return null;

	}
}
