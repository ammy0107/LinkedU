/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class Login {
    // These correspond to the form elements

    private String email;
    private String password;
    private String selection;
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Login(String email, String password, String selection, String number) {
        this.email = email;
        this.password = password;
        this.selection = selection;
        this.number = number;
    }
    private String number;

    public Login(String email, String password, String selection, String newPassword, String number) {
        this.email = email;
        this.password = password;
        this.selection = selection;
        this.newPassword = newPassword;
        this.number = number;
    }

    /**
     * Creates a new instance of ProfileBean
     */
    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = email;

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
     * @return the selection
     */
    public String getSelection() {
        return selection;
    }

    /**
     * @param selection the selection to set
     */
    public void setSelection(String selection) {
        this.selection = selection;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
}
