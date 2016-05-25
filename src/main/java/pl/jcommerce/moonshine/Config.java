package pl.jcommerce.moonshine;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@EnableWebMvc
public class Config extends WebMvcConfigurerAdapter{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index2.html");
		registry.addViewController("").setViewName("forward:/index2.html");
		registry.addViewController("").setViewName("forward:/html/index2.html");
		registry.addViewController("/").setViewName("forward:/html/index2.html");
		registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
		super.addViewControllers(registry);
	}
}
