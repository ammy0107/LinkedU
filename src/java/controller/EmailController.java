/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Email;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class EmailController implements Serializable {

    private Email theEmailModel;
    private String response;
    private String fpass;

    /**
     * Creates a new instance of EmailController
     */
    public EmailController() {
        theEmailModel = new Email();
    }

    public String sendEmailStudent(String receiver, String sender) {
        // setResponse("Request Sent!!");
        sendMailTo(receiver, sender);
        sendMailFrom(receiver, sender);
        response = "Meeting request has been sent.";
        return "StudentMeting.xhtml";

    }

    public String sendEmailUniversity(String receiver, String sender) {
        // setResponse("Request Sent!!");
        sendMailTo(receiver, sender);
        sendMailFrom(receiver, sender);
        response = "Meeting request has been sent.";
        return "UniversityMeting.xhtml";

    }

    public void sendMailTo(String receiver, String sender) {

        // Recipient's email ID needs to be mentioned.
        // String to = getTheEmailModel().getToEmail();
        // Sender's email ID needs to be mentioned
        String from = "ajain5@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";

        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "yourID"); // if needed
        properties.setProperty("mail.password", "yourPassword"); // if needed

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(receiver));

            // Set Subject: header field
            message.setSubject("Meeting Request");

            // Send the actual HTML message, as big as you like
            message.setContent("<center><img src =\"http://s27.postimg.org/rxe9xv1f7/Linked_U.jpg\"><h2>LinkedU&trade;</h2></center>" + "This email has been sent on behalf of " + sender
                    + "</br></br>" + "The person has requested a meeting on " + theEmailModel.getDate() + " at " + theEmailModel.getTime(), "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void sendMailFrom(String receiver, String sender) {

        // Recipient's email ID needs to be mentioned.
        String to = getTheEmailModel().getFromEmail();

        // Sender's email ID needs to be mentioned
        String from = "ajain5@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";

        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.user", "yourID"); // if needed
        properties.setProperty("mail.password", "yourPassword"); // if needed

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(sender));

            // Set Subject: header field
            message.setSubject("Confirmation Email: Meeting Request");

            // Send the actual HTML message, as big as you like
            message.setContent("<center><img src =\"http://s27.postimg.org/rxe9xv1f7/Linked_U.jpg\"><h2>LinkedU&trade;</h2></center>" + "You have requested a meeting on " + theEmailModel.getDate() + " at " + theEmailModel.getTime() + " with " + receiver,
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    /**
     * @return the fpass
     */
    public String getFpass() {
        return fpass;
    }

    /**
     * @param fpass the fpass to set
     */
    public void setFpass(String fpass) {
        this.fpass = fpass;
    }

    /**
     * @return the theEmailModel
     */
    public Email getTheEmailModel() {
        return theEmailModel;
    }

    /**
     * @param theEmailModel the theEmailModel to set
     */
    public void setTheEmailModel(Email theEmailModel) {
        this.theEmailModel = theEmailModel;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }
}
