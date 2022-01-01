package com.Email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Email.model.EmailRequest;
import com.Email.model.EmailResponse;
import com.Email.service.EmailService;

import ch.qos.logback.core.subst.Token;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

	@Autowired
	private EmailService emailservice;
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "this is my email api";
		 
	}
	
//api to send email	
	
@RequestMapping(value="/sendemail",method=RequestMethod.POST)	
public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
{
	
boolean result=this.emailservice.emailSend(request.getTo(),request.getSubject(),request.getMessage());
if(result)
{
return ResponseEntity.ok(new EmailResponse("email is send successfull...."));
		
}

else {
	
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sending"));
	
}



//System.out.println(request);
//return ResponseEntity.ok("Done");
}
}
