package cn.microboot.base.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.microboot.base.controller.HelloController;

//@ControllerAdvice
public class MyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MyException() {
		super("The denominitor can not zero");
	}

}
