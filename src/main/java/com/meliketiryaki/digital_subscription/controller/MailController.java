package com.meliketiryaki.digital_subscription.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meliketiryaki.digital_subscription.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;
	
	@PostMapping("/send")
	public String sendTestMail(
			@RequestParam String to,
			@RequestParam String subject,
			@RequestParam String content
			) {
		mailService.sendEmail(to, subject, content);
		return "Mail gönderildi.";
	}
}
