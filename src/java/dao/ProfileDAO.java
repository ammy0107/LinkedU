/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
//import java.util.Date;
import model.StudentBean;
import model.UniversityBean;

/**
 *
 * @author admin
 */
public interface ProfileDAO {

    public int createStudent(StudentBean aStudent);

    public int createUniversity(UniversityBean aUniversity);

    public ArrayList findAllStudents();

    public ArrayList findAllUniversities();

    public ArrayList findStudentProfile(String email);

    public ArrayList findUiversityProfile(String email);

    public ArrayList findByStudentUserID(String email, String password);

    public ArrayList findByUniversityUserID(String email, String password);

    public ArrayList findAllStudentUserId();

    public ArrayList findAllUniversityUserId();

    public ArrayList findAllByKeyword(String keyword);

    public ArrayList findAllUnivByKeyword(String keyword);

    public ArrayList findByScoreDate(String scoreType,/*Date*/ String minDate,/*Date*/ String maxDate);

    public ArrayList findByScore(String searchCriterion,/*Date*/ String min,/*Date*/ String max);

    public ArrayList findStudentByKeyword(String searchCriterion, String keyword);// either get one back or several if multiple same name allowed  

    public ArrayList findUniversityByKeyword(String searchCriterion, String keyword);

    public ArrayList findAllStudByKeyword(String keyword); //    public int updateStudentProfile(StudentBean pro);
    //
    //    public int updateUniversityProfile(UniversityBean pro);

    public int updateUniversityCount(UniversityBean pro);

    public int updateStudentCount(StudentBean pro);

    public ArrayList findAllStudByKeywordForCompare(String keyword);
   public ArrayList findUnivPostings(String email);
}
