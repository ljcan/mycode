/**
 * 
 */
package cn.shinelon.vo;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import cn.shinelon.annotation.MyValidator;

/**
 * @author Shinelon
 *
 */
public class User {
	//使用jsonview来显示视图
	//1.声明接口
	public interface SimpleJsonView{}
	public interface DetailJsonView extends SimpleJsonView{}
	
	
	private int id;
	@MyValidator(message="这是一个自定义注解")
	public String username;
	//使用这个注解表示前台传回来的密码不能为空
	@NotBlank(message="密码不能为空")			//这个注解是hibernate validator中提供的开源项目
//	@NotBlank
	public String password;
	@Past(message="生日的日期必须是过去的时间")		//表示生日必须是过去的时间
//	@Past
	private Date birthday;
	public void setUsername(String username) {
		this.username = username;
	}
	//2.在值对象的get方法上指定视图
	@JsonView(SimpleJsonView.class)
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonView(DetailJsonView.class)		//因为DetailJsonView继承了SimpleJsonView接口，所以它既会显示密码还会显示username
	public String getPassword() {
		return password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@JsonView(SimpleJsonView.class)
	public Date getBirthday() {
		return birthday;
	}
}
