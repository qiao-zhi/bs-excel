package cn.qs.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.qs.utils.system.SystemUtils;

@Controller
public class Index {

	@RequestMapping("/index")
	public String index(ModelMap map, HttpServletRequest request) {
		request.setAttribute("productName", SystemUtils.getProductName());
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome(ModelMap map) {
		return "welcome";
	}

}
