/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LoginDAOImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Login;
import model.StudentBean;
import model.UniversityBean;

/**
 *
 * @author karvand
 */
@ManagedBean
@SessionScoped
public class UpdateController implements Serializable{

    private Login theModel;
    private String response;
    private boolean result = false;
    private StudentBean student;
    private List<StudentBean> students;
    private UniversityBean university;
    private List<UniversityBean> universities;
    private String updateStatus;

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }
    private String role = "";
  

   /**
     * Creates a new instance of TableBeanWithDummyProfile
     */
    public UpdateController() {
        theModel = new Login();
    }


//    public String logged(ComponentSystemEvent event) {
//        String navi = null;
//        LoginController login = new LoginController();
//        Boolean test = login.isResult();
//        if (test == true) {
//            role = "log";
//        }
//
//        if (!role.equals("log")) {
//            FacesContext fc = FacesContext.getCurrentInstance();
//            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
//            nav.performNavigation("StudentLogin?faces-redirect=true");
//        }
//        return navi;
//    }

      public String retrieveStudProfile(String email) {
        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
         this.students= aProfileDAO.findStudentProfile(email);
         student = (StudentBean) students.get(0);
         if (student != null) 
         return "StudentUpdate.xhtml";
         else
            return "error.xhtml"; 
         
    }
public String retrieveUnivProfile(String email) {
        LoginDAOImpl aProfileDAO = new LoginDAOImpl();
         this.universities= aProfileDAO.findUiversityProfile(email);
         university = (UniversityBean) universities.get(0);
         if (university != null) 
         return "UniversityUpdate.xhtml";
         else
            return "error.xhtml"; 
}
/**
     *
     */
    public void updateThisStud() {
       LoginDAOImpl aProfileDAO = new LoginDAOImpl();   // Creating a new object each time.
        int status = aProfileDAO.updateStudentProfile(student); // Doing anything with the object after this?
        if (status == 2) {
            updateStatus = "Record updated successfully ...";
        } else {
            updateStatus = "Record update failed!";
        }
      
    }
     public void updateThisUniv() throws IOException {
       LoginDAOImpl aProfileDAO = new LoginDAOImpl();   // Creating a new object each time.
        int status = aProfileDAO.updateUniversityProfile(university); // Doing anything with the object after this?
        if (status == 2) {
            updateStatus = "Record updated successfully ...";
        } else {
            updateStatus = "Record update failed!";
        }

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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
