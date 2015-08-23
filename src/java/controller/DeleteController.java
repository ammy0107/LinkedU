/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LoginDAOImpl;
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
import model.Login;

/**
 *
 * @author User
 */
@ManagedBean
@SessionScoped
public class DeleteController implements Serializable{

    private Login myBean;
    private String response;

    /**
     * Creates a new instance of DeleteController
     */
    public DeleteController() {
        myBean = new Login();
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

    /**
     * @return the myBean
     */
    public Login getMyBean() {
        return myBean;
    }

    /**
     * @param myBean the myBean to set
     */
    public void setMyBean(Login myBean) {
        this.myBean = myBean;
    }

    public String deleteProfile(String email) {
        LoginDAOImpl aProfileDAO1 = new LoginDAOImpl();
        //  LoginController aLoginController = new LoginController();
        //   String userName = aLoginController.getStudent().getEmail();
        String deletion = aProfileDAO1.deleteProfile(email, myBean.getPassword());

        String to = email;

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
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Successfull Sign Up to LinkedU");

            message.setContent("<center><img src =\"http://s27.postimg.org/rxe9xv1f7/Linked_U.jpg\"><h2>LinkedU&trade;</h2></center>"
                    + "<h3>Your profile has been deleted from the LinkedU. We will miss you!", "text/html");
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        response = "We will miss you :(";
        return deletion;

    }

    public String deleteUniversityProfile(String email) {
        LoginDAOImpl aProfileDAO1 = new LoginDAOImpl();
        String deletion = aProfileDAO1.deleteUniversityProfileDAO(email, myBean.getPassword());

        String to = email;

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
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Successfull Sign Up to LinkedU");

            message.setContent("<center><img src =\"http://s27.postimg.org/rxe9xv1f7/Linked_U.jpg\"><h2>LinkedU&trade;</h2></center>"
                    + "<h3>Your profile has been deleted from the LinkedU. We will miss you!", "text/html");
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        response = "We will miss you :(";
        return deletion;

    }
}
