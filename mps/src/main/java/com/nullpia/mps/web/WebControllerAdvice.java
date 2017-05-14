package com.nullpia.mps.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Controller의 공통 Model 및 예외처리를 한다
 * @author nullpia
 *
 */
@ControllerAdvice
public class WebControllerAdvice {
	private static final Logger log = LoggerFactory.getLogger(WebControllerAdvice.class);
	
	
	@ModelAttribute
	public void globalAttributes(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		
		String uri = request.getRequestURI();
		if(StringUtils.hasLength(uri) && uri.startsWith("/router/")) {
			model.addAttribute("router", uri.substring(8));
			log.info("router = {}", uri.substring(8));
		}
    }
	
}
