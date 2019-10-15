/**
 * 
 */
package cn.shinelon.springboot.bootfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Shinelon
 *
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    @RequestMapping(value="/test1")
    public String test1(RedirectAttributes model) {
    	System.out.println("==============");
//    	model.addAttribute("name", "me");
    	model.addFlashAttribute("user",new User("jack"));
    	return "redirect:/test2";
    }
    @RequestMapping("/test2")
    public String test2(Model model) {
//    	if(name!=null) {
//    		String arg=name;
//    		System.out.println("传递过来的参数是："+arg);
//    	}else {
//    		System.out.println("没有传递过来参数");
//    	}
    	if(model.containsAttribute("user")) {
    		System.out.println(model.containsAttribute("user"));
    	}else {
    		System.out.println("failure");
    	}
    	return "success";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}

class User{
	private String name;
	public User(String name) {
		this.name=name;
	}
}