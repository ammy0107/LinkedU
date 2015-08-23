/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author admin
 */
public class StudentBean {

    // These correspond to the form elements
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String provider;
    private String phoneNumber;
    private String securityQuestion;
    private String securityAnswer;
    private /*Date*/ String dateOfBirth;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String highSchoolName;
    private double gpa;
    private double psatScore;
    private double satScore;
    private double actScore;
    private double toeflScore;
    private String[] universitiesOfInterest;
    private String[] majorsOfInterest;
    private String/*[]*/ activities;
    private String/*[]*/ ratingInActivity;
    private String aboutStudent;
    private UploadedFile file;
    private UploadedFile document;
    DefaultStreamedContent fileToShow;
    DefaultStreamedContent documentToShow;
    private String latlong;
    private String subject;
    private String message;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DefaultStreamedContent getFileToShow() {
        return fileToShow;
    }

    public void setFileToShow(DefaultStreamedContent fileToShow) {
        this.fileToShow = fileToShow;
    }

    public DefaultStreamedContent getDocumentToShow() {
        return documentToShow;
    }

    public void setDocumentToShow(DefaultStreamedContent documentToShow) {
        this.documentToShow = documentToShow;
    }

    public StudentBean(String firstName, String lastName, String email, String gender, String provider, String phoneNumber, String dateOfBirth, String streetAddress, String city, String state, String zipCode, String country, String highSchoolName, double gpa, double psatScore, double satScore, double actScore, double toeflScore, String[] universitiesOfInterest, String[] majorsOfInterest, String activities, String ratingInActivity, String aboutStudent, DefaultStreamedContent fileToShow, DefaultStreamedContent documentToShow, String latlong, String subject, String message, int count) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.highSchoolName = highSchoolName;
        this.gpa = gpa;
        this.psatScore = psatScore;
        this.satScore = satScore;
        this.actScore = actScore;
        this.toeflScore = toeflScore;
        this.universitiesOfInterest = universitiesOfInterest;
        this.majorsOfInterest = majorsOfInterest;
        this.activities = activities;
        this.ratingInActivity = ratingInActivity;
        this.aboutStudent = aboutStudent;
        this.fileToShow = fileToShow;
        this.documentToShow = documentToShow;
        this.latlong = latlong;
        this.subject = subject;
        this.message = message;
        this.count = count;
    }

    public StudentBean(String firstName, String lastName, String email, String password, String gender, String provider, String phoneNumber, String securityQuestion, String securityAnswer, String dateOfBirth, String streetAddress, String city, String state, String zipCode, String country, String highSchoolName, double gpa, double psatScore, double satScore, double actScore, double toeflScore, String[] universitiesOfInterest, String[] majorsOfInterest, String activities, String ratingInActivity, String aboutStudent, UploadedFile file, UploadedFile document, String latlong, String subject, String message, int count) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.dateOfBirth = dateOfBirth;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.highSchoolName = highSchoolName;
        this.gpa = gpa;
        this.psatScore = psatScore;
        this.satScore = satScore;
        this.actScore = actScore;
        this.toeflScore = toeflScore;
        this.universitiesOfInterest = universitiesOfInterest;
        this.majorsOfInterest = majorsOfInterest;
        this.activities = activities;
        this.ratingInActivity = ratingInActivity;
        this.aboutStudent = aboutStudent;
        this.file = file;
        this.document = document;
        this.latlong = latlong;
        this.subject = subject;
        this.message = message;
        this.count = count;
    }

    public StudentBean(String firstName, String lastName, String email, String gender, String provider, String phoneNumber, String dateOfBirth, String streetAddress, String city, String state, String zipCode, String country, String highSchoolName, double gpa, double psatScore, double satScore, double actScore, double toeflScore, String[] universitiesOfInterest, String[] majorsOfInterest, String activities, String ratingInActivity, String aboutStudent, UploadedFile file, UploadedFile document, String latlong, String subject, String message, int count) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.highSchoolName = highSchoolName;
        this.gpa = gpa;
        this.psatScore = psatScore;
        this.satScore = satScore;
        this.actScore = actScore;
        this.toeflScore = toeflScore;
        this.universitiesOfInterest = universitiesOfInterest;
        this.majorsOfInterest = majorsOfInterest;
        this.activities = activities;
        this.ratingInActivity = ratingInActivity;
        this.aboutStudent = aboutStudent;
        this.file = file;
        this.document = document;
        this.latlong = latlong;
        this.subject = subject;
        this.message = message;
        this.count = count;
    }

    public UploadedFile getDocument() {
        return document;
    }

    public void setDocument(UploadedFile document) {
        this.document = document;
    }

    public String getLatlong() {

        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates a new instance of ProfileBean
     */
    public StudentBean() {
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmPassword
     */
//    public String getConfirmPassword() {
//        return confirmPassword;
//    }
//
//    /**
//     * @param confirmPassword the confirmPassword to set
//     */
//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }
    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the securityQuestion
     */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
     * @param securityQuestion the securityQuestion to set
     */
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    /**
     * @return the securityAnswer
     */
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    /**
     * @param securityAnswer the securityAnswer to set
     */
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    /**
     * @return the dateOfBirth
     */
    public String/*Date*/ getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String/*Date*/ dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the highSchoolName
     */
    public String getHighSchoolName() {
        return highSchoolName;
    }

    /**
     * @param highSchoolName the highSchoolName to set
     */
    public void setHighSchoolName(String highSchoolName) {
        this.highSchoolName = highSchoolName;
    }

    /**
     * @return the gpa
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * @param gpa the gpa to set
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * @return the psatScore
     */
    public double getPsatScore() {
        return psatScore;
    }

    /**
     * @param psatScore the psatScore to set
     */
    public void setPsatScore(double psatScore) {
        this.psatScore = psatScore;
    }

    /**
     * @return the satScore
     */
    public double getSatScore() {
        return satScore;
    }

    /**
     * @param satScore the satScore to set
     */
    public void setSatScore(double satScore) {
        this.satScore = satScore;
    }

    /**
     * @return the actScore
     */
    public double getActScore() {
        return actScore;
    }

    /**
     * @param actScore the actScore to set
     */
    public void setActScore(double actScore) {
        this.actScore = actScore;
    }

    /**
     * @return the toeflScore
     */
    public double getToeflScore() {
        return toeflScore;
    }

    /**
     * @param toeflScore the toeflScore to set
     */
    public void setToeflScore(double toeflScore) {
        this.toeflScore = toeflScore;
    }

    public String convertUniversitiesOfInterestToString() {
        String universitiesOfInterstSt = "";
        for (int i = 0; i < this.universitiesOfInterest.length - 1; i++) {
            universitiesOfInterstSt = universitiesOfInterest[i] + "; " + universitiesOfInterstSt;
        }
        universitiesOfInterstSt = universitiesOfInterstSt + universitiesOfInterest[this.universitiesOfInterest.length - 1];
        return universitiesOfInterstSt;

    }

    /**
     * @param universitiesOfInterest the universitiesOfInterest to set
     */
    public void setUniversitiesOfInterest(String[] universitiesOfInterest) {
        this.universitiesOfInterest = universitiesOfInterest;
    }

    /**
     * @return the majorsOfInterest
     */
    public String convertMajorsOfInterestToString() {
        String majorsOfInterstSt = "";
        for (int i = 0; i < this.majorsOfInterest.length - 1; i++) {
            majorsOfInterstSt = majorsOfInterest[i] + "; " + majorsOfInterstSt;
        }
        majorsOfInterstSt = majorsOfInterstSt + majorsOfInterest[this.majorsOfInterest.length - 1];
        return majorsOfInterstSt;

    }

    /**
     * @param majorsOfInterest the majorsOfInterest to set
     */
    public void setMajorsOfInterest(String[] majorsOfInterest) {
        this.majorsOfInterest = majorsOfInterest;
    }

    /**
     * @return the activities
     */
    public String getActivities() {
//        String activitiesSt="";
//        for(int i=0; i<this.activities.length-1;i++)
//        {
//           activitiesSt=activities[i]+"; "+activitiesSt;
//        }
//        activitiesSt=activitiesSt+activities[this.activities.length-1];
        return activities;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(String/*[]*/ activities) {
        this.activities = activities;
    }

    /**
     * @return the ratingInActivity
     */
    public String getRatingInActivity() {
//       String ratingInActivitySt="";
//        for(int i=0; i<this.ratingInActivity.length-1;i++)
//        {
//           ratingInActivitySt=ratingInActivity[i]+"; "+ratingInActivitySt;
//        }
//        ratingInActivitySt=ratingInActivitySt+activities[this.activities.length-1];
        return ratingInActivity;
    }

    /**
     * @param ratingInActivity the ratingInActivity to set
     */
    public void setRatingInActivity(String/*[]*/ ratingInActivity) {
        this.ratingInActivity = ratingInActivity;
    }

    /**
     * @return the aboutStudent
     */
    public String getAboutStudent() {
        return aboutStudent;
    }

    /**
     * @param aboutStudent the aboutStudent to set
     */
    public void setAboutStudent(String aboutStudent) {
        this.aboutStudent = aboutStudent;
    }

    /**
     * @return the universitiesOfInterest
     */
    public String[] getUniversitiesOfInterest() {
        return universitiesOfInterest;
    }

    /**
     * @return the majorsOfInterest
     */
    public String[] getMajorsOfInterest() {
        return majorsOfInterest;
    }

    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void incrementCount() {
        this.count++;
    }

}

//    public StudentBean(String firstName, String lastName, String email, String password, /*String confirmPassword*/
//            String gender, String phoneNumber, String securityQuestion, String securityAnswer,
//            String/*Date*/ dateOfBirth, String streetAddress, String city, String state, String zipCode,
//            String country, String highSchoolName, double gpa, double psatScore, double satScore, double actScore,
//            double toeflScore, String[] universitiesOfInterest, String[] majorsOfInterest, String/*[]*/ activities,
//            String/*[]*/ ratingInActivity, String aboutStudent) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
////        this.confirmPassword = confirmPassword;
//    public StudentBean(String firstName, String lastName, String email, String password, String gender, String provider, String phoneNumber, String securityQuestion, String securityAnswer, String dateOfBirth, String streetAddress, String city, String state, String zipCode, String country, String highSchoolName, double gpa, double psatScore, double satScore, double actScore, double toeflScore, String[] universitiesOfInterest, String[] majorsOfInterest, String activities, String ratingInActivity, String aboutStudent, String path/* UploadedFile file*/) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.gender = gender;
//        this.provider = provider;
//        this.phoneNumber = phoneNumber;
//        this.securityQuestion = securityQuestion;
//        this.securityAnswer = securityAnswer;
//        this.dateOfBirth = dateOfBirth;
//        this.streetAddress = streetAddress;
//        this.city = city;
//        this.state = state;
//        this.zipCode = zipCode;
//        this.country = country;
//        this.highSchoolName = highSchoolName;
//        this.gpa = gpa;
//        this.psatScore = psatScore;
//        this.satScore = satScore;
//        this.actScore = actScore;
//        this.toeflScore = toeflScore;
//        this.universitiesOfInterest = universitiesOfInterest;
//        this.majorsOfInterest = majorsOfInterest;
//        this.activities = activities;
//        this.ratingInActivity = ratingInActivity;
//        this.aboutStudent = aboutStudent;
//        this.path = path;
//        //  this.file = file;
//    }
//
//    public StudentBean(String firstName, String lastName, String email, String gender, String provider, String phoneNumber, String dateOfBirth, String streetAddress, String city, String state, String zipCode, String country, String highSchoolName, double gpa, double psatScore, double satScore, double actScore, double toeflScore, String[] universitiesOfInterest, String[] majorsOfInterest, String activities, String ratingInActivity, String aboutStudent, String path/*, UploadedFile file*/) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.gender = gender;
//        this.provider = provider;
//        this.phoneNumber = phoneNumber;
//        this.dateOfBirth = dateOfBirth;
//        this.streetAddress = streetAddress;
//        this.city = city;
//        this.state = state;
//        this.zipCode = zipCode;
//        this.country = country;
//        this.highSchoolName = highSchoolName;
//        this.gpa = gpa;
//        this.psatScore = psatScore;
//        this.satScore = satScore;
//        this.actScore = actScore;
//        this.toeflScore = toeflScore;
//        this.universitiesOfInterest = universitiesOfInterest;
//        this.majorsOfInterest = majorsOfInterest;
//        this.activities = activities;
//        this.ratingInActivity = ratingInActivity;
//        this.aboutStudent = aboutStudent;
//        this.path = path;
//        //  this.file = file;
//    }

//    }
