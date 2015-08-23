
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.StudentBean;
import model.UniversityBean;
import model.UniversityPosting;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author admin
 */
public class ProfileDAOImpl implements ProfileDAO {

    @Override
    public int createStudent(StudentBean aStudent) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount1 = 0;
        int rowCount2 = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String insertString1;
            String insertString2;
            Statement stmt = DBConn.createStatement();
            String firstName = aStudent.getFirstName();
            String lastName = aStudent.getLastName();
            String securityAnswer = aStudent.getSecurityAnswer();
            InputStream fil = aStudent.getFile().getInputstream();
            InputStream doc = aStudent.getDocument().getInputstream();

            if (firstName.indexOf("'") != -1) {
                firstName = firstName.replace("'", "''");
            }
            if (lastName.indexOf("'") != -1) {
                lastName = lastName.replace("'", "''");
            }
            if (lastName.indexOf("'") != -1) {
                securityAnswer = securityAnswer.replace("'", "''");
            }

            insertString1 = "INSERT INTO FinalProject353.students VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = DBConn.prepareStatement(insertString1);
            pre.setString(1, firstName);
            pre.setString(2, lastName);
            pre.setString(3, aStudent.getEmail());
            pre.setString(4, aStudent.getPassword());
            pre.setString(5, aStudent.getGender());
            pre.setString(6, aStudent.getProvider());
            pre.setString(7, aStudent.getPhoneNumber());
            pre.setString(8, aStudent.getSecurityQuestion());
            pre.setString(9, securityAnswer);
            pre.setString(10, aStudent.getDateOfBirth());
            pre.setString(11, aStudent.getStreetAddress());
            pre.setString(12, aStudent.getCity());
            pre.setString(13, aStudent.getState());
            pre.setString(14, aStudent.getZipCode());
            pre.setString(15, aStudent.getCountry());
            pre.setString(16, aStudent.getHighSchoolName());
            pre.setDouble(17, aStudent.getGpa());
            pre.setDouble(18, aStudent.getPsatScore());
            pre.setDouble(19, aStudent.getActScore());
            pre.setDouble(20, aStudent.getSatScore());
            pre.setDouble(21, aStudent.getToeflScore());
            pre.setString(22, aStudent.convertUniversitiesOfInterestToString()); //needs to be filled
            pre.setString(23, aStudent.convertMajorsOfInterestToString()); //needs to be filled
            pre.setString(24, aStudent.getActivities());
            pre.setString(25, aStudent.getRatingInActivity());
            pre.setString(26, aStudent.getAboutStudent());
            pre.setBinaryStream(27, fil);
            pre.setBinaryStream(28, doc);
            pre.setString(29, aStudent.getLatlong());
            pre.setString(30, aStudent.getSubject());
            pre.setString(31, aStudent.getMessage());
            pre.setInt(32, aStudent.getCount());

            rowCount1 = pre.executeUpdate();
            insertString2 = "INSERT INTO FinalProject353.loginInfoStud VALUES ('"
                    + aStudent.getEmail()
                    + "','" + aStudent.getPassword()
                    + "')";

            rowCount2 = stmt.executeUpdate(insertString2);
            System.out.println("insert string =" + insertString2);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ProfileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount1 + rowCount2;
    }

    @Override
    public ArrayList findAllUnivByKeyword(String keyword) {
        String query = "SELECT * FROM FinalProject353.recruiter"
                + " WHERE UPPER(UniversityName) LIKE UPPER('%" +keyword+ "%')"
                + " ORDER BY Count DESC";
        ArrayList aUniversityCollection = selectUniversitiesFromDB(query);
        return aUniversityCollection;
    }

    @Override
    public int createUniversity(UniversityBean aUniversity) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        int rowCount1 = 0;
        int rowCount2 = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            String insertString1;
            String insertString2;
            Statement stmt = DBConn.createStatement();
            insertString1 = "INSERT INTO FinalProject353.recruiter VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = DBConn.prepareStatement(insertString1);
            InputStream fil = aUniversity.getImage().getInputstream();
            pre.setString(1, aUniversity.getEmail());
            pre.setString(2, aUniversity.getName());
            pre.setString(3, aUniversity.getPassword());
            pre.setString(4, aUniversity.getProvider());
            pre.setString(5, aUniversity.getPhoneNumber());
            pre.setString(6, aUniversity.getSecurityQuestion());
            pre.setString(7, aUniversity.getSecurityAnswer());
            pre.setString(8, aUniversity.getStreetAddress());
            pre.setString(9, aUniversity.getCity());
            pre.setString(10, aUniversity.getState());
            pre.setString(11, aUniversity.getZipCode());
            pre.setString(12, aUniversity.getCountry());
            pre.setString(13, aUniversity.getWebsite());
            pre.setString(14, aUniversity.convertOfferedMajorsToString());
            pre.setDouble(15, aUniversity.getMinimumRequiredGPA());
            pre.setDouble(16, aUniversity.getMinimumRequiredPSATScore());
            pre.setDouble(17, aUniversity.getMinimumRequiredSATScore());
            pre.setDouble(18, aUniversity.getMinimumRequiredACTScore());
            pre.setDouble(19, aUniversity.getMinimumRequiredTOEFLScore());
            pre.setString(20, aUniversity.getAboutUniversity());
            pre.setBinaryStream(21, fil);
            pre.setInt(22, aUniversity.getCount());
            pre.setString(23, aUniversity.getLatlong());
            pre.setString(24, aUniversity.getSubject());
            pre.setString(25, aUniversity.getMessage());

            rowCount1 = pre.executeUpdate();
            System.out.println("insert string =" + insertString1);
            insertString2 = "INSERT INTO FinalProject353.loginInfoRect VALUES ('"
                    + aUniversity.getEmail()
                    + "','" + aUniversity.getPassword()
                    + "')";

            rowCount2 = stmt.executeUpdate(insertString2);
            System.out.println("insert string =" + insertString2);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ProfileDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rowCount1 + rowCount2;
    }

    @Override
    public ArrayList findAllStudents() {

        String query = "SELECT * FROM FinalProject353.students";
        ArrayList aStudentCollection = selectStudentsFromDB(query);
        return aStudentCollection;

    }

    @Override
    public ArrayList findAllUniversities() {

        String query = "SELECT * FROM FinalProject353.recruiter";
        ArrayList aUniversityCollection = selectUniversitiesFromDB(query);
        return aUniversityCollection;

    }

    @Override
    public ArrayList findAllByKeyword(String keyword) {

        String query = "SELECT * FROM FinalProject353.students"
                + " WHERE FirstName='" + keyword + "' ||"
                + " LastName='" + keyword + "' ||"
                + " Email='" + keyword + "' ||"
                + " Gender='" + keyword + "' ||"
                + " PhoneNumber='" + keyword + "' ||"
                + " DateOfBirth='" + keyword + "' ||"
                + " StreetAddress='" + keyword + "' ||"
                + " City='" + keyword + "' ||"
                + " State='" + keyword + "' ||"
                + " ZipCode='" + keyword + "' ||"
                + " Country='" + keyword + "' ||"
                + " HighSchoolName='" + keyword + "' ||"
                + " GPA=" + keyword + " ||"
                + " PSAT_NMSQTScore=" + keyword + " ||"
                + " SATScore=" + keyword + " ||"
                + " ACTScore=" + keyword + " ||"
                + " TOEFLScore=" + keyword + " ||"
                + " UniversitiesOfInterest='" + keyword + "' ||"
                + " MajorsOfInterest='" + keyword + "' ||"
                + " Activities='" + keyword + "' ||"
                + " RatingInActivity='" + keyword + "' ||"
                + " AboutStudent='" + keyword + "'";
        ArrayList aStudentCollection = selectStudentsFromDB(query);
        return aStudentCollection;

    }

    @Override
    public ArrayList findAllStudByKeyword(String keyword) {

        String query = "SELECT * FROM FinalProject353.students"
                + " WHERE (UPPER(FirstName) LIKE UPPER('%" + keyword + "%') OR"
                + " UPPER(LastName) LIKE UPPER('%" + keyword + "%')) OR"
                + "(UPPER(FirstName ||' '|| LastName) LIKE UPPER('"+keyword+"'))" + " ORDER BY Count DESC";
        ArrayList aStudentCollection = selectStudentsFromDB(query);
        return aStudentCollection;
    }

    @Override
    public ArrayList findAllStudByKeywordForCompare(String keyword) {

        String query = "SELECT * FROM FinalProject353.students"
                + " WHERE (UPPER(FirstName ||' '|| LastName) LIKE UPPER('"+keyword+"'))";
        ArrayList aStudentCollection = selectStudentsFromDB(query);
        return aStudentCollection;

    }

    @Override
    public ArrayList findByStudentUserID(String email, String password) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM FinalProject353.loginInfoStud ";
        query += "WHERE email = '" + email + "' and password='" + password + "'";

        return selectStudUserPassword(query);
    }

    private ArrayList selectStudUserPassword(String query) {
        ArrayList aStudentBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String userids, password;
            while (rs.next()) {
                userids = rs.getString("email");
                password = rs.getString("password");
                aStudentBeanCollection.add(userids);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aStudentBeanCollection;
    }

    @Override
    public ArrayList findByUniversityUserID(String email, String password) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM FinalProject353.loginInfoRect ";
        query += "WHERE email = '" + email + "' and password='" + password + "'";

        return selectUserUnivPassword(query);
    }

    private ArrayList selectUserUnivPassword(String query) {
        ArrayList aUniversityBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String userids, password;
            while (rs.next()) {
                userids = rs.getString("email");
                password = rs.getString("password");
                aUniversityBeanCollection.add(userids);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aUniversityBeanCollection;
    }

    @Override
    public ArrayList findStudentProfile(String email) {

        String query = "SELECT * FROM FinalProject353.students" + " where Email='" + email + "'";
        ArrayList aStudentCollection = selectStudentsFromDB(query);
        return aStudentCollection;

    }

    @Override
    public ArrayList findUiversityProfile(String email) {

        String query = "SELECT * FROM FinalProject353.recruiter" + " where Email='" + email + "'";
        ArrayList aUniversityCollection = selectUniversitiesFromDB(query);
        return aUniversityCollection;

    }

    private ArrayList selectStudentsFromDB(String query) {
        ArrayList aStudentBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalproject353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String firstName, lastName, email, gender, provider, phoneNumber, dateOfBirth, streetAddress, city, state, zipCode, country,
                    highSchoolName, universitiesOfInterest, majorsOfInterest, activities,
                    ratingInActivity, aboutStudent, latlong, subject, message/*,file*/;
            double gpa, psatScore, satScore, actScore, toeflScore;
            byte[] filecoded, documentcoded;
            int count;
            StudentBean aStudentBean;
            while (rs.next()) {
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                email = rs.getString("Email");
                gender = rs.getString("Gender");
                provider = rs.getString("provider");
                phoneNumber = rs.getString("PhoneNumber");
                dateOfBirth = /*new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(*/ rs.getString("DateOfBirth");//);
                streetAddress = rs.getString("StreetAddress");
                city = rs.getString("City");
                state = rs.getString("State");
                zipCode = rs.getString("ZipCode");
                country = rs.getString("Country");
                highSchoolName = rs.getString("HighSchoolName");
                gpa = rs.getDouble("GPA");
                psatScore = rs.getDouble("PSAT_NMSQTScore");
                satScore = rs.getDouble("SATScore");
                actScore = rs.getDouble("ACTScore");
                toeflScore = rs.getDouble("TOEFLScore");
                universitiesOfInterest = rs.getString("UniversitiesOfInterest");
                majorsOfInterest = rs.getString("MajorsOfInterest");
                activities = rs.getString("Activities");
                ratingInActivity = rs.getString("RatingInActivity");
                aboutStudent = rs.getString("AboutStudent");
                filecoded = rs.getBytes("image");
                documentcoded = rs.getBytes("document");
                DefaultStreamedContent file = new DefaultStreamedContent(new ByteArrayInputStream(filecoded), "image/jpeg");
                DefaultStreamedContent document = new DefaultStreamedContent(new ByteArrayInputStream(documentcoded), "application/pdf");
                latlong = rs.getString("latlong");
                subject = rs.getString("subject");
                message = rs.getString("message");
                count = rs.getInt("count");
                String tempUniv[] = universitiesOfInterest.split(";");
                String tempMajor[] = majorsOfInterest.split(";");
                aStudentBean = new StudentBean(firstName, lastName, email, gender, provider, phoneNumber, dateOfBirth,
                        streetAddress, city, state, zipCode, country, highSchoolName, gpa, psatScore, satScore,
                        actScore, toeflScore, tempUniv, tempMajor, activities, ratingInActivity, aboutStudent, file, document,
                        latlong, subject, message, count);
                aStudentBeanCollection.add(aStudentBean);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aStudentBeanCollection;
    }

    private ArrayList selectUniversitiesFromDB(String query) {
        ArrayList aUniversityBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalproject353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String name, email, provider, phoneNumber, streetAddress, city, state, zipCode, country, website,
                    offeredMajors, aboutUniversity, latlong, subject, message;
            double minimumRequiredGPA, minimumRequiredPSATScore, minimumRequiredSATScore, minimumRequiredACTScore, minimumRequiredTOEFLScore;
            byte[] filecoded;
            int count;
            UniversityBean aUniversityBean;
            while (rs.next()) {
                email = rs.getString("Email");
                name = rs.getString("UniversityName");
                provider = rs.getString("provider");
                phoneNumber = rs.getString("PhoneNumber");
                streetAddress = rs.getString("StreetAddress");
                city = rs.getString("City");
                state = rs.getString("State");
                zipCode = rs.getString("ZipCode");
                country = rs.getString("Country");
                website = rs.getString("Website");
                offeredMajors = rs.getString("OfferedMajors");
                minimumRequiredGPA = rs.getDouble("MinimumRequiredGPA");
                minimumRequiredPSATScore = rs.getDouble("MinimumRequiredPSATScore");
                minimumRequiredSATScore = rs.getDouble("MinimumRequiredSATScore");
                minimumRequiredACTScore = rs.getDouble("MinimumRequiredACTScore");
                minimumRequiredTOEFLScore = rs.getDouble("MinimumRequiredTOEFLScore");
                aboutUniversity = rs.getString("AboutUniversity");
                filecoded = rs.getBytes("image");
                DefaultStreamedContent fileToShow = new DefaultStreamedContent(new ByteArrayInputStream(filecoded), "image/jpeg");
                latlong = rs.getString("latlong");
                subject = rs.getString("subject");
                message = rs.getString("message");
                count = rs.getInt("count");
                String tempMajor[] = offeredMajors.split(";");

                // make a ProfileBean object out of the values
                aUniversityBean = new UniversityBean(email, name, provider, phoneNumber, streetAddress, city, state, zipCode, country, website,
                        tempMajor, minimumRequiredGPA, minimumRequiredPSATScore,
                        minimumRequiredSATScore, minimumRequiredACTScore,
                        minimumRequiredTOEFLScore, aboutUniversity, fileToShow, count, latlong, subject, message);
                // add the newly created object to the collection
                aUniversityBeanCollection.add(aUniversityBean);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aUniversityBeanCollection;
    }

    @Override
    public ArrayList findByScoreDate(String searchCriterion,/*Date*/ String min,/*Date*/ String max) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM FinalProject353.students ";
        if (searchCriterion.equals("DateOfBirth")) {
            query += "WHERE " + searchCriterion + " between '" + min + "' AND '" + max + "'";
        } else {
            double minScore = Double.parseDouble(min);
            double maxScore = Double.parseDouble(max);
            query += "WHERE " + searchCriterion + " between " + minScore + " AND " + maxScore;
        }
        ArrayList aStudentCollection = selectStudentsFromDB(query);
        return aStudentCollection;
    }

    @Override
    public ArrayList findByScore(String searchCriterion,/*Date*/ String min,/*Date*/ String max) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM FinalProject353.recruiter ";

        double minScore = Double.parseDouble(min);
        double maxScore = Double.parseDouble(max);
        query += "WHERE " + searchCriterion + " between " + minScore + " AND " + maxScore;

        ArrayList aUniversityCollection = selectUniversitiesFromDB(query);
        return aUniversityCollection;
    }

    @Override
    public ArrayList findStudentByKeyword(String searchCriterion, String keyword) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM FinalProject353.students ";
        query += "WHERE UPPER(" + searchCriterion + ") LIKE UPPER('%" + keyword + "%')";

        ArrayList aStudentCollection = selectStudentsFromDB(query);
        return aStudentCollection;
    }

    @Override
    public ArrayList findUniversityByKeyword(String searchCriterion, String keyword) {
        // if interested in matching wild cards, use: LIKE and '%" + aName + "%'";
        String query = "SELECT * FROM FinalProject353.recruiter ";
        query += "WHERE UPPER(" + searchCriterion + ") LIKE UPPER('%" + keyword + "%')";

        ArrayList aUniversityCollection = selectUniversitiesFromDB(query);
        return aUniversityCollection;
    }

    private ArrayList selectUserIds(String query) {
        ArrayList aProfileBeanCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String userid;
            while (rs.next()) {
                userid = rs.getString("EMAIL");
                aProfileBeanCollection.add(userid);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aProfileBeanCollection;
    }

    @Override
    public ArrayList findAllStudentUserId() {

        String query = "SELECT Email FROM FinalProject353.students";
        ArrayList aProfileCollection = selectUserIds(query);
        return aProfileCollection;

    }

    @Override
    public ArrayList findAllUniversityUserId() {

        String query = "SELECT Email FROM FinalProject353.recruiter";
        ArrayList aProfileCollection = selectUserIds(query);
        return aProfileCollection;

    }

    public boolean checkUserExist(String userId) {
        ArrayList studentUserList = findAllStudentUserId();
        ArrayList universityUserList = findAllUniversityUserId();
        if ((studentUserList.contains(userId)) || (universityUserList.contains(userId))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int updateUniversityCount(UniversityBean pro) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int rowCount = 0;
        //  int rowCount2 = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String updateString;//, updateString2;
            Statement stmt = DBConn.createStatement();
            updateString = "UPDATE FinalProject353.Recruiter SET "
                    + " Count= " + pro.getCount() + " WHERE email = '" + pro.getEmail() + "'";

            rowCount = stmt.executeUpdate(updateString);
            System.out.println("updateString =" + updateString);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;
    }

    @Override
    public int updateStudentCount(StudentBean pro) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int rowCount = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String updateString;
            Statement stmt = DBConn.createStatement();
            updateString = "UPDATE FinalProject353.students SET "
                    + " Count= " + pro.getCount() + " WHERE email = '" + pro.getEmail() + "'";

            rowCount = stmt.executeUpdate(updateString);

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rowCount;

    }
     @Override
     public ArrayList findUnivPostings(String email){
       

        String query = "SELECT * FROM FinalProject353.UniversityPosting" + " where Email='" + email + "'";
        ArrayList aPostingCollection = selectPostingsFromDB(query);
        return aPostingCollection;

     }
     private ArrayList selectPostingsFromDB(String query) {
        ArrayList aUniversityPostingCollection = new ArrayList();
        Connection DBConn = null;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            // if doing the above in Oracle: DBHelper.loadDriver("oracle.jdbc.driver.OracleDriver");
            String myDB = "jdbc:derby://localhost:1527/finalproject353";
            // if doing the above in Oracle:  String myDB = "jdbc:oracle:thin:@oracle.itk.ilstu.edu:1521:ora478";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String email, semester,dueDate;
            double cost;
          
            UniversityPosting aUniversityPosting;
            while (rs.next()) {
                email = rs.getString("Email");
                semester = rs.getString("Semester");
                dueDate = rs.getString("ApplicationDueDate");
                cost = rs.getDouble("Cost");
               

                // make a ProfileBean object out of the values
                aUniversityPosting = new UniversityPosting(email,semester,dueDate,cost);
                // add the newly created object to the collection
                aUniversityPostingCollection.add(aUniversityPosting);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return aUniversityPostingCollection;
    }

}
