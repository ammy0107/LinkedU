/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LoginDAOImpl;
import isu.ISUSMS_Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.ws.WebServiceRef;
import model.Login;
import model.StudentBean;
import model.UniversityBean;

/**
 *
 * @author User
 */
@ManagedBean
@ApplicationScoped
public class LoginController implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/gfish.it.ilstu.edu_8080/ISUTextSMS/ISUSMS.wsdl")
    private ISUSMS_Service service;

    private Login theModel;
    private String response;
    private static int attempt = 0;
    private boolean result = false;
    private String fpass;
    private StudentBean student;
    private List<StudentBean> students;
    private UniversityBean university;
    private List<UniversityBean> universities;
    private String coordinates;
    private String name;
    private String updateStatus;

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    /**
     * Creates a new instance of TableBeanWithDummyProfile
     */
    public LoginController() {
        theModel = new Login();
    }

    public String validate() {

        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
        setResult(aProfileDAO.validateLogin(this));
        if (isResult() == true) {
//           ProfileDAOImpl aProDAO = new ProfileDAOImpl(); 
            ArrayList aProfile = aProfileDAO.findStudentProfile(theModel.getEmail()); // Doing anything with the object after this?
            student = (StudentBean) aProfile.get(0); // if multiple found, just p 
            return "StudentMain.xhtml";
        } else {
            attempt++;
            if (attempt >= 3) {
                return "AccountLock.xhtml";
            } else {
                response = "Sorry incorrect userId or password. Please try again";
                return response;
            }
        }
    }

    public String retrieveStudProfile(String email) {
        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
        this.students = aProfileDAO.findStudentProfile(email);
        student = (StudentBean) students.get(0);

        if (student != null) {
            return "StudentLoginProfile.xhtml";
        } else {
            return "error.xhtml";
        }
    }

    public String changeStudentPassword(String email) {

        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
        setResult(aProfileDAO.validateLogin(this));
        if (isResult() == true) {
            int check = aProfileDAO.updateStudentPassword(email, theModel.getNewPassword());
            if (check == 2) {

                return "LoggedOut.xhtml";
            } else {
                response = "Sorry failed to change the password. Please try again";
                return response;
            }
        } else {
            response = "The provided password is not correct.";
            return response;

        }
    }

    public String retrieveUnivProfile(String email) {
        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
        this.universities = aProfileDAO.findUiversityProfile(email);
        university = (UniversityBean) universities.get(0);
        if (university != null) {
            return "UniversityLoginProfile.xhtml";
        } else {
            return "error.xhtml";
        }

    }

    public String validateRectRect() {

        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
        setResult(aProfileDAO.validateLoginRect(this));
        if (isResult() == true) {
//           ProfileDAOImpl aProDAO = new ProfileDAOImpl(); 
            ArrayList aProfile = aProfileDAO.findUiversityProfile(theModel.getEmail()); // Doing anything with the object after this?
            university = (UniversityBean) aProfile.get(0); // if multiple found, just p 
            return "UniversityMain.xhtml";
        } else {
            attempt++;
            if (attempt >= 3) {
                return "AccountLock.xhtml";
            } else {
                response = "Sorry incorrect userId or password. Please try again";
                return response;
            }
        }
    }

    public String changeUniversityPassword(String email) {

        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
        setResult(aProfileDAO.validateLoginRect(this));
        if (isResult() == true) {
            int check = aProfileDAO.updateUniversityPassword(email, theModel.getNewPassword());
            if (check == 2) {

                return "LoggedOut.xhtml";
            } else {
                response = "Sorry failed to change the password. Please try again";
                return response;
            }
        } else {
            response = "The provided password is not correct.";
            return response;

        }
    }

    public String forgotPass() {
        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
        if (theModel.getSelection().equals("email")) {
            setFpass(aProfileDAO.forgotPassword(this));
            if (getFpass() == "" || getFpass() == null) {
                response = "Please enter a valid email address.";
                // return response;
            } else {
                sendMailForgotPassword();
                response = "Your login Id and password has been sent to your email address.";
                //   return response;
            }
        } else if (theModel.getSelection().equals("text")) {
            setFpass(aProfileDAO.forgotPasswordPhone(this));
            if (getFpass() == "" || getFpass() == null) {
                response = "Please enter a valid phone number.";
                // return response;
            } else {
                String provider = aProfileDAO.forgotPasswordPhoneProvider(this);
                String msg = getFpass();
                sendSMS(provider, theModel.getNumber(), msg);
                response = "Your login Id and password has been sent to your phone.";
                //   return response;
            }
        } else {
            response = "Something went wrong!!";
        }
        return response;
    }

    public String forgotPassRect() {
        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
        if (theModel.getSelection().equals("email")) {
            setFpass(aProfileDAO.forgotPasswordRect(this));
            if (getFpass() == "" || getFpass() == null) {
                response = "Please enter a valid email address.";
                // return response;
            } else {
                sendMailForgotPassword();
                response = "Your login Id and password has been sent to your email address.";
                //  return response;
            }
        } else if (theModel.getSelection().equals("text")) {
            setFpass(aProfileDAO.forgotPasswordRectPhone(this));
            if (getFpass() == "" || getFpass() == null) {
                response = "Please enter a valid phone number.";
                // return response;
            } else {
                String provider = aProfileDAO.forgotPasswordRectPhoneProvider(this);
                String msg = getFpass();
                sendSMS(provider, theModel.getNumber(), msg);
                response = "Your login Id and password has been sent to your phone.";
                //   return response;
            }
        } else {
            return "Something went wrong!!";
        }
        return response;
    }

    public void sendMailForgotPassword() {

        String to = theModel.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "ajain5@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "smtp.ilstu.edu";

        // Get system properties
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
            message.setSubject("Confirmation Email: Meeting Request");

            // Send the actual HTML message, as big as you like
            message.setContent("<center><img src =\"http://s27.postimg.org/rxe9xv1f7/Linked_U.jpg\"><h2>LinkedU&trade;</h2></center>" + "You have requested a meeting on " + getFpass(),
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "Home.xhtml";
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

//    /**
//     * @return the theModel
//     */
//    public Login getTheModel() {
//        return theModel;
//    }
//
//    /**
//     * @param theModel the theModel to set
//     */
//    public void setTheModel(Login theModel) {
//        this.theModel = theModel;
//    }
    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
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
     * @return the result
     */
    public boolean isResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * @return the students
     */
    public List<StudentBean> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<StudentBean> students) {
        this.students = students;
    }

    /**
     * @return the university
     */
    public UniversityBean getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(UniversityBean university) {
        this.university = university;
    }

    /**
     * @return the universities
     */
    public List<UniversityBean> getUniversities() {
        return universities;
    }

    /**
     * @param universities the universities to set
     */
    public void setUniversities(List<UniversityBean> universities) {
        this.universities = universities;
    }

    /**
     * @return the theModel
     */
    public Login getTheModel() {
        return theModel;
    }

    /**
     * @param theModel the theModel to set
     */
    public void setTheModel(Login theModel) {
        this.theModel = theModel;
    }

    /**
     * @return the student
     */
    public StudentBean getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public void updateThisStud() {
        student.setLatlong(mapStud());
        LoginDAOImpl aProfileDAO = new LoginDAOImpl();   // Creating a new object each time.
        int status = aProfileDAO.updateStudentProfile(student); // Doing anything with the object after this?
        if (status != 0) {
            updateStatus = "Record updated successfully ...";
        } else {
            updateStatus = "Record update failed!";
        }

    }

    public void updateThisUniv() throws IOException {
        university.setLatlong(mapUniv());
        LoginDAOImpl aProfileDAO = new LoginDAOImpl();   // Creating a new object each time.
        int status = aProfileDAO.updateUniversityProfile(university); // Doing anything with the object after this?
        if (status != 0) {
            updateStatus = "Record updated successfully ...";
        } else {
            updateStatus = "Record update failed!";
        }
    }

    private String sendSMS(java.lang.String provider, java.lang.String number, java.lang.String message) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        isu.ISUSMS port = service.getISUSMSPort();
        return port.sendSMS(provider, number, message);
    }
    private static final String GEO_CODE_SERVER = "http://maps.googleapis.com/maps/api/geocode/json?";

    public String mapUniv() {
        String response = getLocation(university.getZipCode());
        String[] result = parseLocation(response);
        String latitude = result[0] + "," + result[1];
        return latitude;
    }

    public String mapStud() {
        String response = getLocation(student.getZipCode());
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
