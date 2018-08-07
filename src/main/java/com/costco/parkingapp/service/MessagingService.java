package com.costco.parkingapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MessagingService {
	 
	 @Value("${twilio.phoneNumber}")
	 private String twilioNumber;
	 
	 @Value("${twilio.accountSID}")
	 private String accountSid;
	 
	 @Value("${twilio.authToken}")
	 private String authToken;
	 
	 /**
	 * @param phoneNumber : should be of format +{countrycode}{phone number} : eg :- +5732024197. No dashes allowed.
	 * @param smsMessage : Can be any string
	 * @throws TwilioRestException 
	 */
	public String sendSms(String phoneNumber, String smsMessage) {
		Twilio.init(accountSid, authToken);

		Message message = Message.creator(
		    new PhoneNumber(phoneNumber),  // To number
		    new PhoneNumber(twilioNumber),  // From number
		    "Hello world!"                    // SMS body
		).create();
		
		return message.getSid();
	}
}
