/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import isu.ISUSMS_Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Properties;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.ws.WebServiceRef;
import model.UniversityBean;

/**
 *
 * @author karvand
 */
@ManagedBean
@SessionScoped
public class UniversityProfileController implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/gfish.it.ilstu.edu_8080/ISUTextSMS/ISUSMS.wsdl")
    private ISUSMS_Service service;

    // This corresponds to the response to be sent back to the client
    private String response;
    private UniversityBean theModel;

    /**
     * Creates a new instance of UniversityProfileController
     */
    public UniversityProfileController() {
        theModel = new UniversityBean();
    }

    public UniversityBean getTheModel() {
        return theModel;
    }

    public void setTheModel(UniversityBean theModel) {
        this.theModel = theModel;
    }

    public String getResponse() {

        response = "<h3>Hello " + theModel.getName() + "</h3><br/>";
        response += "<h4>Thanks for signing up with LinkedU. Please login to access your account. We have sent you an email also that contain all the details related to your account.</h4>";

        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String createProfile() {
        theModel.setLatlong(map()); // change in update also
        ProfileDAO aProfileDAO = new ProfileDAOImpl();    // Creating a new object each time.
        int rowCount = aProfileDAO.createUniversity(theModel); // Doing anything with the object after this?

        if (rowCount == 2) {

            String msg = "Congrats!! your profile has been created on the LinkedU";
            String provider = theModel.getProvider();
            String number = theModel.getPhoneNumber();
            sendSMS(provider, number, msg);

            String to = theModel.getEmail();

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
                        + "<h3>Congratulations,</h><p>You have successfully signed up to LinkedU.</p>"
                        + "<p>Your account information is as follows:<br></p><p><b>Username:  </b>" + theModel.getEmail()
                        + "</p><p><b>Password:  </b>" + theModel.getPassword() + "</p><p><b>First Name:  </b>"
                        + theModel.getName()
                        + "</p><p><b>Security Question:  </b>"
                        + theModel.getSecurityQuestion() + "<p/><p><b>Security Answer:  </b>" + theModel.getSecurityAnswer() + "</p>",
                        "text/html");
                Transport.send(message);
                System.out.println("Email Sent successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }
        } else {
            return "error.xhtml";
        }
        return "UniversityAfterSignUpPage.xhtml";
    }

    private String sendSMS(java.lang.String provider, java.lang.String number, java.lang.String message) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        isu.ISUSMS port = service.getISUSMSPort();
        return port.sendSMS(provider, number, message);
    }
    private static final String GEO_CODE_SERVER = "http://maps.googleapis.com/maps/api/geocode/json?";

    public String map() {
        String response = getLocation(theModel.getZipCode());
        String[] result = parseLocation(response);
        String latitude = result[0] + "," + result[1];
        return latitude;
    }

    private static String getLocation(String code) {
        String address = buildUrl(code);

        String content = null;

        try {
            URL url = new URL(address);

            InputStream stream = url.openStream();

            try {
                int available = stream.available();

                byte[] bytes = new byte[available];

                stream.read(bytes);

                content = new String(bytes);
            } finally {
                stream.close();
            }

            return (String) content.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String buildUrl(String code) {
        StringBuilder builder = new StringBuilder();

        builder.append(GEO_CODE_SERVER);

        builder.append("address=");
        builder.append(code.replaceAll(" ", "+"));
        builder.append("&sensor=false");

        return builder.toString();
    }

    private static String[] parseLocation(String response) {
        // Look for location using brute force.
        // There are much nicer ways to do this, e.g. with Google's JSON library: Gson
        //     https://sites.google.com/site/gson/gson-user-guide

        String[] lines = response.split("\n");

        String lat = null;
        String lng = null;

        for (int i = 0; i < lines.length; i++) {
            if ("\"location\" : {".equals(lines[i].trim())) {
                lat = getOrdinate(lines[i + 1]);
                lng = getOrdinate(lines[i + 2]);
                break;
            }
        }

        return new String[]{lat, lng};
    }

    private static String getOrdinate(String s) {
        String[] split = s.trim().split(" ");

        if (split.length < 1) {
            return null;
        }

        String ord = split[split.length - 1];

        if (ord.endsWith(",")) {
            ord = ord.substring(0, ord.length() - 1);
        }

        // Check that the result is a valid double
        Double.parseDouble(ord);

        return ord;
    }

}
