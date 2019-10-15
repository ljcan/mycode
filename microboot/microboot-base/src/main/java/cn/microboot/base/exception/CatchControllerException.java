package cn.microboot.base.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CatchControllerException {
	@ExceptionHandler(MyException.class)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String,Object> printException(MyException e) {
		Map<String,Object> exc=new HashMap<String,Object>();
		exc.put("message", e.getMessage());
		return exc;
	}
}
