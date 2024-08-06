package com.learn.ecom.util;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.learn.ecom.model.ProductOrder;
import com.learn.ecom.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class CommonUtil {

	@Autowired
	private JavaMailSender mailsender;

	@Autowired
	private UserService userService;

	public Boolean sendMail(String url, String recipientEmail) throws UnsupportedEncodingException, MessagingException {
	    MimeMessage message = mailsender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);

	    helper.setFrom("kisnapurohit1@gmail.com", "Shopping Cart");
	    helper.setTo(recipientEmail);
	    
	    String user = userService.getUsernameByEmail(recipientEmail);

	    String content = "<html>" +
	                     "<body style='background-color: #000000; color: #ffffff;'>" +
	                     "<div style='font-family: Arial, sans-serif; color: #ffffff; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #333333; border-radius: 10px;'>" +
	                     "<h2 style='color: #FF5733; text-align: center;'>Password Reset Request</h2>" +
	                     "<p>Dear <b style='color: #FFD700;'>" + user + "</b>,</p>" +
	                     "<p>You have requested to reset your password. Click the button below to proceed:</p>" +
	                     "<p style='text-align: center;'>" +
	                     "<a href='" + url + "' style='display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color: #FF5733; text-decoration: none; border-radius: 5px;'>Change my password</a>" +
	                     "</p>" +
	                     "<p>If you did not request a password reset, please ignore this email or contact support if you have questions.</p>" +
	                     "<p>Thank you,</p>" +
	                     "<p><b>Team Kisna</b></p>" +
	                     "<p style='text-align: center;'>Follow us on <a href='https://facebook.com/yourpage' style='text-decoration: none; color: #FF5733;'>Facebook</a> | <a href='https://twitter.com/yourpage' style='text-decoration: none; color: #FF5733;'>Twitter</a></p>" +
	                     "<p style='font-size: 12px; color: #999999; text-align: center;'>Ghar, Jodhpur, India</p>" +
	                     "</div>" +
	                     "</body>" +
	                     "</html>";

	    helper.setSubject("Password Reset Link");
	    helper.setText(content, true);
	    mailsender.send(message);

	    return true;
	}




	public static String generateUrl(HttpServletRequest request) {

		// http://localhost:8080/forgot-password
		String siteUrl = request.getRequestURL().toString();

		return siteUrl.replace(request.getServletPath(), "");

	}

	public Boolean sendPasswordChangeEmail(String recipientEmail) throws UnsupportedEncodingException, MessagingException {
	    MimeMessage message = mailsender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);

	    helper.setFrom("kisnapurohit1@gmail.com", "Shopping Cart");
	    helper.setTo(recipientEmail);
	    
	    String user = userService.getUsernameByEmail(recipientEmail);

	    String content = "<html>" +
	                     "<body>" +
	                     "<div style='font-family: Arial, sans-serif; color: #333333; max-width: 600px; margin: auto; padding: 20px; border: 1px solid #dddddd; border-radius: 10px;'>" +
	                     "<h2 style='color: #4CAF50; text-align: center;'>Password Changed Successfully</h2>" +
	                     "<p>Dear <b>" + user + "</b>,</p>" +
	                     "<p>Your password has been changed successfully. If you did not make this change, please contact our support team immediately.</p>" +
	                     "<p>Regards,</p>" +
	                     "<p><b>Team Kisna</b></p>" +
	                     "<div style='text-align: center;'>" +
	                     "<img src='' alt='' style='width: 100px; margin-top: 20px;'/>" +
	                     "</div>" +
	                     "</div>" +
	                     "</body>" +
	                     "</html>";

	    helper.setSubject("Password Successfully Changed");
	    helper.setText(content, true);
	    mailsender.send(message);

	    return true;
	}

	
	String msg=null;;
	
	public Boolean sendMailForProductOrder(ProductOrder order, String status) throws Exception {
	    String msg = "<html>" +
	                 "<body>" +
	                 "<p>Dear <b>[[name]]</b>,</p>" +
	                 "<p>Thank you for your order. Your order status is: <b>[[orderStatus]]</b>.</p>" +
	                 "<p><b>Product Details:</b></p>" +
	                 "<table style='border: 1px solid #dddddd; border-collapse: collapse; width: 100%;'>" +
	                 "<tr style='background-color: #f2f2f2;'>" +
	                 "<th style='border: 1px solid #dddddd; padding: 8px;'>Name</th>" +
	                 "<th style='border: 1px solid #dddddd; padding: 8px;'>Category</th>" +
	                 "<th style='border: 1px solid #dddddd; padding: 8px;'>Quantity</th>" +
	                 "<th style='border: 1px solid #dddddd; padding: 8px;'>Price</th>" +
	                 "<th style='border: 1px solid #dddddd; padding: 8px;'>Payment Type</th>" +
	                 "</tr>" +
	                 "<tr>" +
	                 "<td style='border: 1px solid #dddddd; padding: 8px;'>[[productName]]</td>" +
	                 "<td style='border: 1px solid #dddddd; padding: 8px;'>[[category]]</td>" +
	                 "<td style='border: 1px solid #dddddd; padding: 8px;'>[[quantity]]</td>" +
	                 "<td style='border: 1px solid #dddddd; padding: 8px;'>[[price]]</td>" +
	                 "<td style='border: 1px solid #dddddd; padding: 8px;'>[[paymentType]]</td>" +
	                 "</tr>" +
	                 "</table>";

	    if (status.equals("Out for Delivery")) {
	        String deliveryAgentName = "Mr. Baldev Shah";
	        String deliveryAgentMobile = "1478523690";

	        msg += "<p><b>Delivery Agent Details:</b></p>" +
	               "<p>Name: " + deliveryAgentName + "</p>" +
	               "<p>Mobile: " + deliveryAgentMobile + "</p>" ;
	    }

	    msg += "<p>We appreciate your business and hope you enjoy your purchase!</p>" +
	           "<p>Best regards,</p>" +
	           "<p><b>Team Kisna</b></p>" +
	           "</body>" +
	           "</html>";

	    MimeMessage message = mailsender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);

	    helper.setFrom("kisnapurohit1@gmail.com", "Shopping Cart");
	    helper.setTo(order.getOrderAddress().getEmail());

	    msg = msg.replace("[[name]]", order.getOrderAddress().getFirstName());
	    msg = msg.replace("[[orderStatus]]", status);
	    msg = msg.replace("[[productName]]", order.getProduct().getTitle());
	    msg = msg.replace("[[category]]", order.getProduct().getCategory());
	    msg = msg.replace("[[quantity]]", order.getQuantity().toString());
	    msg = msg.replace("[[price]]", order.getPrice().toString());
	    msg = msg.replace("[[paymentType]]", order.getPaymentType());

	    helper.setSubject("Your Order Status - " + status);
	    helper.setText(msg, true);
	    mailsender.send(message);
	    return true;
	}



}
