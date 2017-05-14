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
	private static final Logger logger = LoggerFactory.getLogger(WebControllerAdvice.class);
	
	@ModelAttribute
	public void globalAttributes(HttpServletRequest request, HttpServletResponse response, Model model) {
		String uri = request.getRequestURI();
		
		//현재 접속한 리소스에 대한 메뉴/사용자 정보를 설정한다.
		if(StringUtils.hasLength(uri) && uri.startsWith("/router/")) {
			model.addAttribute("router", uri.substring(8));
			logger.info("router = {}", uri.substring(8));
		}

    }
	
}
