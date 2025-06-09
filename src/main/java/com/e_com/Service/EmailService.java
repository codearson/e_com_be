package com.e_com.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Title: EmailService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String text) {
    	try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("codearson@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    	} catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
    
} 






//
//package com.e_com.Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
///**
// * Title: EmailService.java
// * Company: www.codearson.com
// * Copyright: Copyright (c) 2025
// * 
// * @author Bavithragithan Kuganesan
// * @date Feb 19, 2025
// * @version 1.0
// **/
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender emailSender;
//

//    public void sendEmail(String to, String subject, String text) {
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("codearson@gmail.com"); // Consider externalizing this to application.properties
//            message.setTo(to);
//            message.setSubject(subject);
//            message.setText(text);
//            emailSender.send(message);
//        } catch (Exception e) {
//            System.err.println("Error sending email: " + e.getMessage());
//        }
//    }
//}
//
