package cn.microboot.base.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FreeMarkerController {
	@RequestMapping("/index")
	public String index(ModelMap model) {
		model.addAttribute("name", "shinelon");
		model.addAttribute("sex", 1);   //	1表示男，0表示女
		List<String> list=new ArrayList<String>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		model.addAttribute("userlist",list);
		return "index";
	}

}
