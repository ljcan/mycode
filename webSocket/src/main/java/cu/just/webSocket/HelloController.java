package cu.just.webSocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/ws")
	public String ws() {
		return "redirect:../html/index.html";
	}
}
