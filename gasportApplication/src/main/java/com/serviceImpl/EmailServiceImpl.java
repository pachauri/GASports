package com.serviceImpl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.config.AmazonClient;
import com.constants.GASportConstant;
import com.db.Email;
import com.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vipul pachauri
 */
@Service
public class EmailServiceImpl implements EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private AmazonClient awsConfig;

    @Override
    public void sendMail(String emailId) {
        logger.info("Creating Email.");
        Email email = new Email();
        email.setTo("vipulpachauri12@gmail.com");
        //email.setTo(emailId);
        email.setFrom(awsConfig.getEmail());
        email.setSubject(GASportConstant.SUBJECT);
        email.setBody(GASportConstant.BODY);
        logger.info("Email created.");
        sendMail(email);

    }

    private void sendMail(Email email){

        logger.info("Sending Mail.");
        System.setProperty("aws.accessKeyId", awsConfig.getAccessKey());
        System.setProperty("aws.secretKey", awsConfig.getSecretKey());

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.US_WEST_2).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(email.getTo()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(email.getBody()))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData("")))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(email.getSubject())))
                    .withSource(email.getFrom());
            client.sendEmail(request);
            logger.info("Mail Sent.");
            System.out.println("Email sent!");
        } catch (Exception ex) {
            logger.error("The email was not sent. Error message: "
                    + ex.getMessage());
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }
}
