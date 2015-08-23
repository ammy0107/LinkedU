/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author karvand
 */
public class UniversityBean {

    // These correspond to the form elements
    private String email;
    private String name;
    private String password;
    private String provider;
    private String phoneNumber;
    private String securityQuestion;
    private String securityAnswer;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String website;
    private String[] offeredMajors;
    private double minimumRequiredGPA;
    private double minimumRequiredPSATScore;
    private double minimumRequiredSATScore;
    private double minimumRequiredACTScore;
    private double minimumRequiredTOEFLScore;
    private String aboutUniversity;
    private UploadedFile image;
    DefaultStreamedContent fileToShow;
    private int count;
    private String latlong;
    private String subject;
    private String message;

    public UniversityBean() {
    }

    public UniversityBean(String email, String name, String password, String provider, String phoneNumber, String securityQuestion, String securityAnswer, String streetAddress, String city, String state, String zipCode, String country, String website, String[] offeredMajors, double minimumRequiredGPA, double minimumRequiredPSATScore, double minimumRequiredSATScore, double minimumRequiredACTScore, double minimumRequiredTOEFLScore, String aboutUniversity, UploadedFile image, int count, String latlong, String subject, String message) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.website = website;
        this.offeredMajors = offeredMajors;
        this.minimumRequiredGPA = minimumRequiredGPA;
        this.minimumRequiredPSATScore = minimumRequiredPSATScore;
        this.minimumRequiredSATScore = minimumRequiredSATScore;
        this.minimumRequiredACTScore = minimumRequiredACTScore;
        this.minimumRequiredTOEFLScore = minimumRequiredTOEFLScore;
        this.aboutUniversity = aboutUniversity;
        this.image = image;
        this.count = count;
        this.latlong = latlong;
        this.subject = subject;
        this.message = message;
    }

    public UniversityBean(String email, String name, String provider, String phoneNumber, String streetAddress, String city, String state, String zipCode, String country, String website, String[] offeredMajors, double minimumRequiredGPA, double minimumRequiredPSATScore, double minimumRequiredSATScore, double minimumRequiredACTScore, double minimumRequiredTOEFLScore, String aboutUniversity, UploadedFile image, int count, String latlong, String subject, String message) {
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.website = website;
        this.offeredMajors = offeredMajors;
        this.minimumRequiredGPA = minimumRequiredGPA;
        this.minimumRequiredPSATScore = minimumRequiredPSATScore;
        this.minimumRequiredSATScore = minimumRequiredSATScore;
        this.minimumRequiredACTScore = minimumRequiredACTScore;
        this.minimumRequiredTOEFLScore = minimumRequiredTOEFLScore;
        this.aboutUniversity = aboutUniversity;
        this.image = image;
        this.count = count;
        this.latlong = latlong;
        this.subject = subject;
        this.message = message;
    }

    public UniversityBean(String email, String name, String provider, String phoneNumber, String streetAddress, String city, String state, String zipCode, String country, String website, String[] offeredMajors, double minimumRequiredGPA, double minimumRequiredPSATScore, double minimumRequiredSATScore, double minimumRequiredACTScore, double minimumRequiredTOEFLScore, String aboutUniversity, DefaultStreamedContent fileToShow, int count, String latlong, String subject, String message) {
        this.email = email;
        this.name = name;
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.website = website;
        this.offeredMajors = offeredMajors;
        this.minimumRequiredGPA = minimumRequiredGPA;
        this.minimumRequiredPSATScore = minimumRequiredPSATScore;
        this.minimumRequiredSATScore = minimumRequiredSATScore;
        this.minimumRequiredACTScore = minimumRequiredACTScore;
        this.minimumRequiredTOEFLScore = minimumRequiredTOEFLScore;
        this.aboutUniversity = aboutUniversity;
        // this.image = image;
        this.fileToShow = fileToShow;
        this.count = count;
        this.latlong = latlong;
        this.subject = subject;
        this.message = message;
    }

    public String convertOfferedMajorsToString() {
        String offeredMajorsSt = "";
        for (int i = 0; i < this.offeredMajors.length - 1; i++) {
            offeredMajorsSt = offeredMajors[i] + "; " + offeredMajorsSt;
        }
        offeredMajorsSt = offeredMajorsSt + offeredMajors[this.offeredMajors.length - 1];
        return offeredMajorsSt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String[] getOfferedMajors() {
        return offeredMajors;
    }

    public void setOfferedMajors(String[] offeredMajors) {
        this.offeredMajors = offeredMajors;
    }

    public double getMinimumRequiredGPA() {
        return minimumRequiredGPA;
    }

    public void setMinimumRequiredGPA(double minimumRequiredGPA) {
        this.minimumRequiredGPA = minimumRequiredGPA;
    }

    public double getMinimumRequiredPSATScore() {
        return minimumRequiredPSATScore;
    }

    public void setMinimumRequiredPSATScore(double minimumRequiredPSATScore) {
        this.minimumRequiredPSATScore = minimumRequiredPSATScore;
    }

    public double getMinimumRequiredSATScore() {
        return minimumRequiredSATScore;
    }

    public void setMinimumRequiredSATScore(double minimumRequiredSATScore) {
        this.minimumRequiredSATScore = minimumRequiredSATScore;
    }

    public double getMinimumRequiredACTScore() {
        return minimumRequiredACTScore;
    }

    public void setMinimumRequiredACTScore(double minimumRequiredACTScore) {
        this.minimumRequiredACTScore = minimumRequiredACTScore;
    }

    public double getMinimumRequiredTOEFLScore() {
        return minimumRequiredTOEFLScore;
    }

    public void setMinimumRequiredTOEFLScore(double minimumRequiredTOEFLScore) {
        this.minimumRequiredTOEFLScore = minimumRequiredTOEFLScore;
    }

    public String getAboutUniversity() {
        return aboutUniversity;
    }

    public void setAboutUniversity(String aboutUniversity) {
        this.aboutUniversity = aboutUniversity;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public DefaultStreamedContent getFileToShow() {
        return fileToShow;
    }

    public void setFileToShow(DefaultStreamedContent fileToShow) {
        this.fileToShow = fileToShow;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public void incrementCount() {
        this.count++;
    }
}
