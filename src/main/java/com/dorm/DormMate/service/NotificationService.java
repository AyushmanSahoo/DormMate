package com.dorm.DormMate.service;


import com.dorm.DormMate.entity.Notification;
import com.dorm.DormMate.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.mail.SimpleMailMessage;
import java.time.Instant;
import java.util.Base64;
import java.util.List;

import static java.awt.SystemColor.text;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repository;
    private final JavaMailSender mailSender;

    @Value("${twilio.accountSid}")
    private String twilioSid;
    @Value("${twilio.authToken}")
    private String twilioToken;
    @Value("${twilio.from}")
    private String twilioFrom;

//    @Value("${mailgun.apiKey}")
//    private String mailgunApiKey;
//    @Value("${mailgun.domain}")
//    private String mailgunDomain;
//    @Value("${mailgun.from}")
//    private String mailgunFrom;

    public List<Notification> getUserNotifications(String userId) {
        return repository.findByUserId(userId);
    }

    public Notification sendNotification(String userId, String title, String message, String channel) {
        Notification notification = Notification.builder()
                .userId(userId)
                .channel(channel)
                .title(title)
                .message(message)
                .read(false)
                .sentAt(Instant.now())
                .build();

        switch (channel.toUpperCase()) {
            case "SMS":
                sendSms("+91" + userId, message); // assuming userId is phone number
                break;
            case "EMAIL":
                sendEmail(userId, title, message); // assuming userId is email
                break;
            case "IN_APP":
            default:
                // stored in DB only
                break;
        }

        return repository.save(notification);
    }

    public Notification broadcastNotification(String title, String message, String channel) {
        // Extend this to broadcast logic if needed
        return sendNotification(null, title, message, channel);
    }

    private void sendSms(String to, String body) {
        Twilio.init(twilioSid, twilioToken);
        Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(twilioFrom),
                body
        ).create();
    }

    private void sendEmail(String to, String subject, String body) {
//        try {
//            String url = "https://api.mailgun.net/v3/" + mailgunDomain + "/messages";
//
//            HttpPost post = new HttpPost(url);
//            post.setHeader("Authorization", "Basic " +
//                    Base64.getEncoder().encodeToString(("api:" + mailgunApiKey).getBytes()));
//
//            String payload = "from=" + mailgunFrom +
//                    "&to=" + to +
//                    "&subject=" + subject +
//                    "&text=" + body;
//
//            post.setEntity(new StringEntity(payload));
//            post.setHeader("Content-Type", "application/x-www-form-urlencoded");
//
//            HttpClient client = HttpClients.createDefault();
//            HttpResponse response = client.execute(post);
//            EntityUtils.consume(response.getEntity());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ayushmansahoo176@gmail.com"); // must match spring.mail.username
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
