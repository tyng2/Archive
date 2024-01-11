package com.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.main.comm.Common;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ExceptionHandlingController implements ErrorController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		log.info("ERROR CTRL");
		
		Object status	= request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			
			int statusCode			= Common.str2Int(status.toString());
			HttpStatus httpStatus	= HttpStatus.valueOf(statusCode);
			
			// 404
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("msg", httpStatus.getReasonPhrase());
				return "error/404";
				
			// 500
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "error/500";
				
			// ELSE
			} else {
				model.addAttribute("code", statusCode);
				return "error/error";
			}
		}
		return "error/error";
	}

}
