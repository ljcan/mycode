/**
 * 
 */
package cn.shinelon.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cn.shinelon.service.impl.HelloServiceImpl;

/**
 * @author Shinelon
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyValidator, Object> {

	@Autowired
	public HelloServiceImpl helloServiceImpl;
	@Override
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
		helloServiceImpl.hello(" "+arg0);
		System.out.println(arg0);
		//返回true或者false表示是否校验成功
		return false;
	}

	@Override
	public void initialize(MyValidator arg0) {
		System.out.println("my validator init");
	}

}
