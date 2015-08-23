/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import model.StudentBean;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author karvand
 */
@Named(value = "studentSearchController")
@SessionScoped
public class StudentSearchController implements Serializable {

    private List<StudentBean> students;
    private StudentBean student;
    private String scoreDateSearchCriterion = "All";
    private String otherSearchCriterion = "All";
    private String keyword;
    private boolean showTable1;
    private boolean showTable2;
    private boolean value = false;
    private String min;//Date;
    private String max;//Date;
    private String keyword1;
    private String keyword2;

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    /**
     * Creates a new instance of StudentSearchController
     */
    public StudentSearchController() {
        student = new StudentBean();
    }

    public ArrayList retrieveStudentName() {

        ProfileDAOImpl aProfileDAOImpl = new ProfileDAOImpl();    // Creating a new object each time.
        this.students = aProfileDAOImpl.findAllStudents(); // Doing anything with the object after this?
        //atimeslot = ((ScheduleBean) result.get(0)).getTimeSlot();
        ArrayList<String> result = new ArrayList<String>();
        if (result != null) {
            for (int i = 0; i < students.size(); i++) {
                String name = students.get(i).getFirstName()+" "+students.get(i).getLastName();

                result.add(name);
            }
        }
        return result;
    }
    ArrayList<StudentBean> merge;

    public ArrayList<StudentBean> getMerge() {
        return merge;
    }

    public String studentCompareTable() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
           merge = new ArrayList<StudentBean>();
        List<StudentBean> listA = new ArrayList<StudentBean>();
        List<StudentBean> listB = new ArrayList<StudentBean>();
        List<StudentBean> listC = new ArrayList<StudentBean>();
        listA = aProfileDAO.findAllStudByKeywordForCompare(keyword);
        listB = aProfileDAO.findAllStudByKeywordForCompare(keyword1);
        listC = aProfileDAO.findAllStudByKeywordForCompare(keyword2);
        this.merge.addAll(listA);
        this.merge.addAll(listB);
        this.merge.addAll(listC);

        if (merge != null) {
            return "CompareStudentPosting.xhtml"; // navigate to "update2.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String retrieveStudByOtherCrit() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        if (otherSearchCriterion.equals("All")) {
            this.students = aProfileDAO.findAllStudents();
        }// Creating a new object each time.
        else {
            this.students = aProfileDAO.findStudentByKeyword(otherSearchCriterion, keyword);
        }
        if (students != null) {
            return "AdvanceSearch.xhtml"; // navigate to "update2.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String retrieveStudByAnyKeyword() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        this.students = aProfileDAO.findAllStudByKeyword(keyword);
        if (students != null) {
            return "StudentSearchResults.xhtml"; // navigate to "update2.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String retrieveStudByScoreDateCrit() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        if (scoreDateSearchCriterion.equals("All")) {
            this.students = aProfileDAO.findAllStudents();
        }// Creating a new object each time.
        else {
            this.students = aProfileDAO.findByScoreDate(scoreDateSearchCriterion, min, max);
        }
        if (students != null) {
            return "AdvanceSearch.xhtml"; // navigate to "update2.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String retriveProfile(String email) {
        String result = "";
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        this.students = aProfileDAO.findStudentProfile(email);
        student = (StudentBean) students.get(0);
        if (students != null) {
            student.incrementCount();
            String fullName = student.getFirstName() + " " + student.getLastName();
            init(student.getLatlong(), fullName);
            int status = aProfileDAO.updateStudentCount(student);
           
            return "StudentProfile.xhtml";
        } else {
            return "error.xhtml";
        }
    }

    /**
     * @return the otherSearchCriterion
     */
    public String getOtherSearchCriterion() {
        return otherSearchCriterion;
    }

    /**
     * @param otherSearchCriterion the otherSearchCriterion to set
     */
    public void setOtherSearchCriterion(String otherSearchCriterion) {
        this.otherSearchCriterion = otherSearchCriterion;
    }

    public boolean isDisabledScore() {

        if (this.scoreDateSearchCriterion.equals("All")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isDisabledOther() {

        if (this.otherSearchCriterion.equals("All")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedGender() {

        if (this.otherSearchCriterion.equals("Gender")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedPhone() {

        if (this.otherSearchCriterion.equals("PhoneNumber")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedDOB() {

        if (this.scoreDateSearchCriterion.equals("DateOfBirth")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedStreetAddress() {

        if (this.otherSearchCriterion.equals("StreetAddress")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedCity() {

        if (this.otherSearchCriterion.equals("City")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedState() {

        if (this.otherSearchCriterion.equals("State")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedZip() {

        if (this.otherSearchCriterion.equals("ZipCode")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedCountry() {

        if (this.otherSearchCriterion.equals("Country")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedHighSchool() {

        if (this.otherSearchCriterion.equals("HighSchoolName")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedUniv() {

        if (this.otherSearchCriterion.equals("UniversitiesOfInterest")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedMaj() {

        if (this.otherSearchCriterion.equals("MajorsOfInterest")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedActivities() {

        if (this.otherSearchCriterion.equals("Activities")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedRateAct() {

        if (this.otherSearchCriterion.equals("RatingInActivity")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedAboutStud() {

        if (this.otherSearchCriterion.equals("AboutStudent")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedPSAT() {

        if (this.scoreDateSearchCriterion.equals("PSAT_NMSQTScore")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedSAT() {

        if (this.scoreDateSearchCriterion.equals("SATScore")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedACT() {

        if (this.scoreDateSearchCriterion.equals("ACTScore")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedTOEFL() {

        if (this.scoreDateSearchCriterion.equals("TOEFLScore")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
     * @return the minDate
     */
    public /*Date*/ String getMin()/*Date*/ {
        return min/*Date*/;
    }

    /**
     * @param minDate the minDate to set
     */
    public void setMin/*Date*/(String/*Date*/ min/*Date*/) {
        this.min/*Date*/ = min/*Date*/;
    }

    /**
     * @return the maxDate
     */
    public /*Date*/ String getMax/*Date*/() {
        return max/*Date*/;
    }

    /**
     * @param maxDate the maxDate to set
     */
    public void setMax/*Date*/(/*Date*/String max/*Date*/) {
        this.max/*Date*/ = max/*Date*/;
    }

    /**
     * @return the dateSearchCriterion
     */
    public String getScoreDateSearchCriterion() {
        return scoreDateSearchCriterion;
    }

    /**
     * @param dateSearchCriterion the dateSearchCriterion to set
     */
    public void setScoreDateSearchCriterion(String scoreDateSearchCriterion) {
        this.scoreDateSearchCriterion = scoreDateSearchCriterion;
    }

    /**
     * @return the firstName
     */
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
     * @return the showTable1
     */
    public boolean isShowTable1() {
        return showTable1;
    }

    /**
     * @param showTable1 the showTable1 to set
     */
    public void setShowTable1(boolean showTable1) {
        this.showTable1 = showTable1;
    }

    /**
     * @return the showTable2
     */
    public boolean isShowTable2() {
        return showTable2;
    }

    /**
     * @param showTable2 the showTable2 to set
     */
    public void setShowTable2(boolean showTable2) {
        this.showTable2 = showTable2;
    }

    public String enableTable1() {
        this.showTable1 = true;
        this.showTable2 = false;
        return "";
    }

    public String enableTable2() {
        this.showTable1 = false;
        this.showTable2 = true;
        return "";
    }

    private MapModel simpleModel;

    public void init(String coordinates, String name) {

        simpleModel = new DefaultMapModel();

        // LoginController log = new LoginController();
        coordinates = coordinates;
        name = name;
        String[] parts = coordinates.split(",");
        String part1 = parts[0]; // 004
        String part2 = parts[1];
        Double lat = Double.parseDouble(part1);
        Double longi = Double.parseDouble(part2);
        LatLng coord1 = new LatLng(lat, longi);
        simpleModel.addOverlay(new Marker(coord1, name));
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

}
