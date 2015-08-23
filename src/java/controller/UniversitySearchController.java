/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfileDAO;
import dao.ProfileDAOImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.UniversityBean;
import model.UniversityPosting;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author karvand
 */
@Named(value = "universitySearchController")
@SessionScoped
public class UniversitySearchController implements Serializable {

    private List<UniversityBean> universities;
    private UniversityBean university;
    private List<UniversityPosting> postings;
    private UniversityPosting posting;
    private String scoreSearchCriterion = "All";
    private String otherSearchCriterion = "All";
    private String keyword;
    private String keyword1;
    private String keyword2;
    ArrayList<UniversityBean> merge;

    public ArrayList<UniversityBean> getMerge() {
        return merge;
    }

    public void setMerge(ArrayList<UniversityBean> merge) {
        this.merge = merge;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }
    private boolean value = false;
    private String min;//Date;
    private String max;//Date;
    private boolean showTable1 = false;
    private boolean showTable2 = false;

    /**
     * @return the showTable1
     */
    public boolean isShowTable1() {
        return showTable1;
    }

    public String enableTable1() {
        this.showTable1 = true;
        this.showTable2 = false;
        return "";
    }

    /**
     * @return the showTable2
     */
    public boolean isShowTable2() {
        return showTable2;
    }

    public String enableTable2() {
        this.showTable1 = false;
        this.showTable2 = true;
        return "";
    }

    /**
     * @param showTable1 the showTable1 to set
     */
    public void setShowTable1(boolean showTable1) {
        this.showTable1 = showTable1;
    }

    /**
     * @param showTable2 the showTable2 to set
     */
    public void setShowTable2(boolean showTable2) {
        this.showTable2 = showTable2;
    }

    /**
     * Creates a new instance of StudentSearchController
     */
    public UniversitySearchController() {
        university = new UniversityBean();
    }

    public ArrayList retrieveuniversityName() {

        ProfileDAOImpl aProfileDAOImpl = new ProfileDAOImpl();    // Creating a new object each time.
        this.universities = aProfileDAOImpl.findAllUniversities(); // Doing anything with the object after this?
        //atimeslot = ((ScheduleBean) result.get(0)).getTimeSlot();
        ArrayList<String> result = new ArrayList<String>();
        if (result != null) {
            for (int i = 0; i < universities.size(); i++) {
                String name = universities.get(i).getName();

                result.add(name);
            }
        }
        return result;
    }

    public String retrieveUnivByOtherCrit() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        if (otherSearchCriterion.equals("All")) {
            this.universities = aProfileDAO.findAllUniversities();
        }// Creating a new object each time.
        else {
            this.universities = aProfileDAO.findUniversityByKeyword(otherSearchCriterion, keyword);
        }

        if (universities != null) {
            return "AdvanceSearch.xhtml"; // navigate to "update2.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String retrieveUnivByAnyKeyword() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
//        if (otherSearchCriterion.equals("All"))
//                {
//                    this.students = aProfileDAO.findAll();
//                }// Creating a new object each time.
//        else {
        this.universities = aProfileDAO.findAllUnivByKeyword(keyword);

        // Doing anything with the object after this?
        //   theModel = (StudentBean) result.get(0); // if multiple found, just pick the 1st one. If none?
        if (universities != null) {
            return "StudentMain.xhtml"; // navigate to "update2.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String compareTable() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        merge = new ArrayList<UniversityBean>();
        List<UniversityBean> listA = new ArrayList<UniversityBean>();
        List<UniversityBean> listB = new ArrayList<UniversityBean>();
        List<UniversityBean> listC = new ArrayList<UniversityBean>();
        listA = aProfileDAO.findAllUnivByKeyword(keyword);
        listB = aProfileDAO.findAllUnivByKeyword(keyword1);
        listC = aProfileDAO.findAllUnivByKeyword(keyword2);
        this.merge.addAll(listA);
        this.merge.addAll(listB);
        this.merge.addAll(listC);

        if (merge != null) {
            return "CompareUniversityPosting.xhtml"; // navigate to "update2.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String retrieveUnivByScoreCrit() {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        if (scoreSearchCriterion.equals("All")) {
            this.universities = aProfileDAO.findAllUniversities();
        }// Creating a new object each time.
        else {
            this.universities = aProfileDAO.findByScore(scoreSearchCriterion, min, max);
        }
        if (universities != null) {
            return "AdvanceSearch.xhtml"; // navigate to "update2.xhtml"
        } else {
            return "error.xhtml";
        }
    }

    public String retriveProfile(String email) {
        String result = "";
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
        this.universities = aProfileDAO.findUiversityProfile(email);
        university = (UniversityBean) universities.get(0);
        if (universities != null) {
            university.incrementCount();
            init(university.getLatlong(), university.getName());
            int status = aProfileDAO.updateUniversityCount(university); // Doing anything with the object after this?

            result = "UniversityProfile.xhtml";
        } else {
            result = "error.xhtml";

        }
        return result;
    }

    public String retrieveUnivPosting(String email) {
        ProfileDAO aProfileDAO = new ProfileDAOImpl();
//        if (otherSearchCriterion.equals("All"))
//                {
//                    this.students = aProfileDAO.findAll();
//                }// Creating a new object each time.
//        else {
        this.postings = aProfileDAO.findUnivPostings(email);

        posting = (UniversityPosting) postings.get(0); // if multiple found, just pick the 1st one. If none?
        if (postings != null) {
            return "UniversityPosting.xhtml"; // navigate to "update2.xhtml"
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

        if (this.scoreSearchCriterion.equals("All")) {
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

    public boolean isRenderedEmail() {

        if (this.otherSearchCriterion.equals("Email")) {
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

    public boolean isRenderedUniv() {

        if (this.otherSearchCriterion.equals("UniversitiesOfInterest")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedMaj() {

        if (this.otherSearchCriterion.equals("OfferedMajors")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedAboutUniv() {

        if (this.otherSearchCriterion.equals("AboutUniversity")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedPSAT() {

        if (this.scoreSearchCriterion.equals("MinimumRequiredPSATScore")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedGPA() {

        if (this.scoreSearchCriterion.equals("MinimumRequiredGPA")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedSAT() {

        if (this.scoreSearchCriterion.equals("MinimumRequiredSATScore")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedACT() {

        if (this.scoreSearchCriterion.equals("MinimumRequiredACTScore")) {
            value = true;
        } else {
            value = false;
        }
        return value;
    }

    public boolean isRenderedTOEFL() {

        if (this.scoreSearchCriterion.equals("MinimumRequiredTOEFLScore")) {
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
     * @return the scoreSearchCriterion
     */
    public String getScoreSearchCriterion() {
        return scoreSearchCriterion;
    }

    /**
     * @param scoreSearchCriterion the scoreSearchCriterion to set
     */
    public void setScoreSearchCriterion(String scoreSearchCriterion) {
        this.scoreSearchCriterion = scoreSearchCriterion;
    }

    /**
     * @return the value
     */
    public boolean isValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(boolean value) {
        this.value = value;
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

    /**
     * @return the postings
     */
    public List<UniversityPosting> getPostings() {
        return postings;
    }

    /**
     * @param postings the postings to set
     */
    public void setPostings(List<UniversityPosting> postings) {
        this.postings = postings;
    }

    /**
     * @return the posting
     */
    public UniversityPosting getPosting() {
        return posting;
    }

    /**
     * @param posting the posting to set
     */
    public void setPosting(UniversityPosting posting) {
        this.posting = posting;
    }
    /**
     * @return the criterion
     */
//    public String getCriterion() {
//        String swCriteria="";
//        if(!scoreSearchCriterion.equals("All")){
//        switch(scoreSearchCriterion){
//            case "GPA": swCriteria="gpa" ;
//            case "PSAT_NMSQTScore": swCriteria="psatScore" ;
//            case "SATScore": swCriteria="satScore" ;
//            case "ACTScore": swCriteria="actScore" ;
//            case "TOEFLScore": swCriteria="toeflScore" ;
//             case "DateOfBirth": swCriteria="dateOfBirth";
//                 break;
//            default: break;
//          }
//        }else
//            if(!this.otherSearchCriterion.equals("All")){
//                switch(otherSearchCriterion){
//                   case "FirstName": swCriteria="firstName" ;
//                   case "LastName": swCriteria="lastName" ;
//                   case "Email": swCriteria="email" ;
//                   case "Gender": swCriteria="gender" ;
//                   case "StreetAddress": swCriteria="streetAddress" ;
//                   case "City": swCriteria="city" ;
//                   case "State": swCriteria="state" ;
//                   case "ZipCode": swCriteria="zipCode" ;
//                   case "Country": swCriteria="country" ;
//                   case "HighSchoolName": swCriteria="highSchoolName" ;
//                   case "UniversitiesOfInterest": swCriteria="universitiesOfInterest" ;
//                   case "MajorsOfInterest": swCriteria="majorsOfInterest" ;  
//                   case "Activities": swCriteria="activities" ;
//                       break;
//            default: break;
//                 }
//            }
//        criterion=swCriteria;
//        return criterion;
//    }
//
//    /**
//     * @param criterion the criterion to set
//     */
//    public void setCriterion(String criterion) {
//        
//        this.criterion = criterion;
//    }
}
