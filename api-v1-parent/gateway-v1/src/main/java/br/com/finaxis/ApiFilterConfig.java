package br.com.finaxis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
@Controller
public class ApiFilterConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptorAdapter a = new HandlerInterceptorAdapter() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
				String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
				if (bestMatchPattern.contains("/api/")) {
					System.out.println(request.getCharacterEncoding());
					System.out.println(request.getContentType());
					System.out.println(request.getMethod());
					System.out.println(request.getRemoteAddr());
					System.out.println(request.getRequestURI());
					System.out.println(request.getLocale());
					System.out.println(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
				}
				return super.preHandle(request, response, handler);
			}
		};
		registry.addInterceptor(a);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@GetMapping("/index")
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("swagger-ui.html");
	}

	@PostMapping("/credential")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "teste";
	}
}
