package com.taylietech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@Autowired
	private MessageSource  messageSource;
	
    @RequestMapping("/")
    public String index() {
        return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
    }
}