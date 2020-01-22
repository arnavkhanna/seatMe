package com.reservation.services;
import com.reservation.repositories.ReservationRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Value("${spring.init.twilio-accountsid}")
    public String accountSid;

    @Value("${spring.init.twilio-authtoken}")
    public String authToken;

    @Value("${spring.init.twilio-phonenumber}")
    public String phoneNumber1;


    public SmsService() {
    }

    public void sendText(String messageText, String phoneNumber){
        Twilio.init(accountSid, authToken);

        Message message = Message
                .creator(new com.twilio.type.PhoneNumber(phoneNumber), // to
                        new com.twilio.type.PhoneNumber(phoneNumber1), // from
                        messageText)
                .create();

    }


}
