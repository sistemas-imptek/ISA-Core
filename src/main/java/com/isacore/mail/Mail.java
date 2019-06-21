package com.isacore.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class Mail {

	@Autowired
	private JavaMailSender sender;

	public boolean sendEmail(String contacts, String filePath, String subject, String msg) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(InternetAddress.parse(contacts));
			helper.setText(msg);
			helper.setSubject(subject);
			File f = new File(filePath);
			helper.addAttachment(f.getName(), f);
			 System.out.println("Nombre Archivo" + f.getName());
			sender.send(message);
			return true;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}

}
