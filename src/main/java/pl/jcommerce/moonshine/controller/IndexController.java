package pl.jcommerce.moonshine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	/**
	 * Returns index page
	 * @return index page
	 */
	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
}
