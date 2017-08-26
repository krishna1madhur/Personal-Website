/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.km;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Contact extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
      
    System.out.println("reached here!"+ request.getParameter("name"));
    
    String name = request.getParameter("name");
	String email = request.getParameter("email");
	String messageBody = request.getParameter("message");
	String to = "krishna1madhur@gmail.com";
	String from = "krishna1madhur@gmail.com";
	messageBody = "Sender's EMail address: "+email+"\n" + messageBody;
	
	final String username = "krishna4madhur";
	final String password = "kmwebapp";

	Properties props = new Properties();
//	props.put("mail.smtp.auth", "true");
//	props.put("mail.smtp.starttls.enable", "true");
//	props.put("mail.smtp.host", "smtp.gmail.com");
//	props.put("mail.smtp.port", "587");
//	
	
//	Session session = Session.getInstance(props,
//			  new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(username, password);
//				}
//			  });
	
	Session session = Session.getDefaultInstance(props, null);
	

	try {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(to));
		message.setSubject("Message from Website: " +name);
		message.setText(messageBody);
		
		Transport.send(message);

		response.sendRedirect("ThankYou.html");
        
	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}

  }
}
