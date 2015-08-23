/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.LoginController;
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
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author ajain5
 */
public class LoginDAOImpl {

    /**
     *
     * @param proCont
     * @return
     */
    public int updateUniversityProfile(UniversityBean pro) throws IOException {
        Connection DBConn = null;
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
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String insertString1;
            String insertString2;
            //Statement stmt = DBConn.createStatement();
            // "UPDATE Messages SET description = ?, author = ? WHERE id = ? AND seq_num = ?"
            insertString1 = "UPDATE FinalProject353.recruiter SET universityname = ?,provider = ?,phonenumber=?,securityquestion=?,securityanswer=?,streetaddress=?,city=?,state=?,zipcode=?,country=?,website=?,offeredmajors=?,minimumrequiredgpa=?,minimumrequiredpsatscore=?,minimumrequiredsatscore=?,minimumrequiredactscore=?,minimumrequiredtoeflscore=?,aboutuniversity=?,image=?, latlong=? WHERE email = ?";
            PreparedStatement pre = DBConn.prepareStatement(insertString1);
            InputStream fil = pro.getImage().getInputstream();

            pre.setString(1, pro.getName());
            pre.setString(2, pro.getProvider());
            pre.setString(3, pro.getPhoneNumber());
            pre.setString(4, pro.getSecurityQuestion());
            pre.setString(5, pro.getSecurityAnswer());
            pre.setString(6, pro.getStreetAddress());
            pre.setString(7, pro.getCity());
            pre.setString(8, pro.getState());
            pre.setString(9, pro.getZipCode());
            pre.setString(10, pro.getCountry());
            pre.setString(11, pro.getWebsite());
            pre.setString(12, pro.convertOfferedMajorsToString());
            pre.setDouble(13, pro.getMinimumRequiredGPA());
            pre.setDouble(14, pro.getMinimumRequiredPSATScore());
            pre.setDouble(15, pro.getMinimumRequiredSATScore());
            pre.setDouble(16, pro.getMinimumRequiredACTScore());
            pre.setDouble(17, pro.getMinimumRequiredTOEFLScore());
            pre.setString(18, pro.getAboutUniversity());
            pre.setBinaryStream(19, fil);
            pre.setString(20, pro.getLatlong());
            pre.setString(21, pro.getEmail());

            rowCount1 = pre.executeUpdate();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount1;

    }

    public int updateStudentProfile(StudentBean pro) {
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
            updateString = "UPDATE FinalProject353.students SET firstname = ?,lastname = ?,gender=?,provider=?,phonenumber=?,securityquestion=?,securityanswer =?,dateofbirth = ?, streetaddress =?, city=?,state=?,zipcode=?,country=?,highschoolname=?,gpa=?,psat_nmsqtscore=?,satscore=?,actscore=?,toeflscore=?,universitiesofinterest=?,majorsofinterest=?,activities=?,ratinginactivity=?,aboutstudent=?, image=?,document=?, latlong=? WHERE email = ?";
            PreparedStatement pre = DBConn.prepareStatement(updateString);
            InputStream file = pro.getFile().getInputstream();
            InputStream document = pro.getFile().getInputstream();

            pre.setString(1, pro.getFirstName());
            pre.setString(2, pro.getLastName());
            pre.setString(3, pro.getGender());
            pre.setString(4, pro.getProvider());
            pre.setString(5, pro.getPhoneNumber());
            pre.setString(6, pro.getSecurityQuestion());
            pre.setString(7, pro.getSecurityAnswer());
            pre.setString(8, pro.getDateOfBirth());
            pre.setString(9, pro.getStreetAddress());
            pre.setString(10, pro.getCity());
            pre.setString(11, pro.getState());
            pre.setString(12, pro.getZipCode());
            pre.setString(13, pro.getCountry());
            pre.setString(14, pro.getHighSchoolName());
            pre.setDouble(15, pro.getGpa());
            pre.setDouble(16, pro.getPsatScore());
            pre.setDouble(17, pro.getSatScore());
            pre.setDouble(18, pro.getActScore());
            pre.setDouble(19, pro.getToeflScore());
            pre.setString(20, pro.convertUniversitiesOfInterestToString());
            pre.setString(21, pro.convertMajorsOfInterestToString());
            pre.setString(22, pro.getActivities());
            pre.setString(23, pro.getRatingInActivity());
            pre.setString(24, pro.getAboutStudent());
            pre.setBinaryStream(25, file);
            pre.setBinaryStream(26, document);
            pre.setString(27, pro.getLatlong());
            pre.setString(28, pro.getEmail());

            rowCount = pre.executeUpdate();
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(LoginDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount;

    }

    public boolean validateLogin(LoginController proCont) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        boolean valid = false;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.LOGININFOSTUD ";
            query += "WHERE email = '" + proCont.getTheModel().getEmail() + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String userid = proCont.getTheModel().getEmail().toLowerCase();
            String pass = proCont.getTheModel().getPassword();
            while (rs.next()) {
                String dbEmail = rs.getString("email").toLowerCase();
                String dbPassword = rs.getString("password");
                valid = userid.equalsIgnoreCase(dbEmail) && pass.equals(dbPassword);
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
        return valid;
    }

    public String forgotPassword(LoginController proCont) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        String pass = "";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.LOGININFOSTUD ";
            query += "WHERE email = '" + proCont.getTheModel().getEmail() + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String email = proCont.getTheModel().getEmail().toLowerCase();

            while (rs.next()) {
                String dbUserId = rs.getString("email").toLowerCase();
                //String dbPassword = rs.getString("password");
                if (email.equalsIgnoreCase(dbUserId)) {
                    pass = "Your user id is " + rs.getString("email").toLowerCase() + " your pass is " + rs.getString("password");
                }
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
        return pass;
    }

    public String forgotPasswordPhone(LoginController proCont) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        String pass = "";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.STUDENTS";
            query += "WHERE PHONENUMBER = '" + proCont.getTheModel().getNumber() + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String number = proCont.getTheModel().getNumber();

            while (rs.next()) {
                String phoneNumber = rs.getString("phonenumber");
                //String dbPassword = rs.getString("password");
                if (number.equalsIgnoreCase(phoneNumber)) {
                    pass = "Your user id is " + rs.getString("email") + " your pass is " + rs.getString("password");
                }
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
        return pass;
    }

    public String forgotPasswordPhoneProvider(LoginController proCont) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        String pass = "";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.STUDENTS";
            query += "WHERE PHONENUMBER = '" + proCont.getTheModel().getNumber() + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String number = proCont.getTheModel().getNumber();

            while (rs.next()) {
                String phoneNumber = rs.getString("phonenumber");
                //String dbPassword = rs.getString("password");
                if (number.equalsIgnoreCase(phoneNumber)) {
                    pass = rs.getString("provider");
                }
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
        return pass;
    }

    public boolean validateLoginRect(LoginController proCont) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        boolean valid = false;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.LOGININFORECT ";
            query += "WHERE email = '" + proCont.getTheModel().getEmail() + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String userid = proCont.getTheModel().getEmail().toLowerCase();
            String pass = proCont.getTheModel().getPassword();
            while (rs.next()) {
                String dbEmail = rs.getString("email").toLowerCase();
                String dbPassword = rs.getString("password");
                valid = userid.equalsIgnoreCase(dbEmail) && pass.equals(dbPassword);
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
        return valid;
    }

    public String forgotPasswordRect(LoginController proCont) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        String pass = "";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.LOGININFORECT ";
            query += "WHERE email = '" + proCont.getTheModel().getEmail() + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String email = proCont.getTheModel().getEmail().toLowerCase();

            while (rs.next()) {
                String dbUserId = rs.getString("email").toLowerCase();
                //String dbPassword = rs.getString("password");
                if (email.equalsIgnoreCase(dbUserId)) {
                    pass = "Your user id is " + rs.getString("email").toLowerCase() + " your pass is " + rs.getString("password");
                }
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
        return pass;
    }

    public String forgotPasswordRectPhone(LoginController proCont) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        String pass = "";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.RECRUITER ";
            query += "WHERE PHONENUMBER = '" + proCont.getTheModel().getNumber() + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String number = proCont.getTheModel().getNumber();

            while (rs.next()) {
                String dbUserId = rs.getString("phonenumber");
                //String dbPassword = rs.getString("password");
                if (number.equalsIgnoreCase(dbUserId)) {
                    pass = "Your user id is " + rs.getString("email") + " your pass is " + rs.getString("password");
                }
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
        return pass;
    }

    public String forgotPasswordRectPhoneProvider(LoginController proCont) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        String pass = "";
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.RECRUITER ";
            query += "WHERE PHONENUMBER = '" + proCont.getTheModel().getNumber() + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String number = proCont.getTheModel().getNumber();

            while (rs.next()) {
                String dbUserId = rs.getString("phonenumber");
                //String dbPassword = rs.getString("password");
                if (number.equalsIgnoreCase(dbUserId)) {
                    pass = rs.getString("provider");
                }
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
        return pass;
    }

    public ArrayList findStudentProfile(String email) {

        String query = "SELECT * FROM FinalProject353.students" + " where Email='" + email + "'";
        ArrayList aStudentCollection = selectStudentsFromDB(query);
        return aStudentCollection;

    }

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

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String firstName, lastName, email, gender, phoneNumber, dateOfBirth, streetAddress, city, state, zipCode, country,
                    highSchoolName, universitiesOfInterest, majorsOfInterest, activities,
                    ratingInActivity, aboutStudent, provider, latlong, subject, message/*, file*/;
            double gpa, psatScore, satScore, actScore, toeflScore;
            byte[] filecoded, documentcoded;
            int count;
            //  DefaultStreamedContent file, document;
            //Date dateOfBirth;
//            String exam1,exam2,exam3;
            StudentBean aStudentBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
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
                DefaultStreamedContent fileToShow = new DefaultStreamedContent(new ByteArrayInputStream(filecoded), "image/jpeg");
                DefaultStreamedContent documentToShow = new DefaultStreamedContent(new ByteArrayInputStream(documentcoded), "application/pdf", "Uploaded File");
                latlong = rs.getString("latlong");
                subject = rs.getString("subject");
                message = rs.getString("message");
                String tempUniv[] = universitiesOfInterest.split(";");
                String tempMajor[] = majorsOfInterest.split(";");
                count = rs.getInt("count");
                aStudentBean = new StudentBean(firstName, lastName, email, gender, provider, phoneNumber, dateOfBirth,
                        streetAddress, city, state, zipCode, country, highSchoolName, gpa, psatScore, satScore,
                        actScore, toeflScore/*tempScore*/, tempUniv, tempMajor, activities, ratingInActivity, aboutStudent, fileToShow, documentToShow, latlong, subject, message, count);
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

            // With the connection made, create a statement to talk to the DB server.
            // Create a SQL statement to query, retrieve the rows one by one (by going to the
            // columns), and formulate the result string to send back to the client.
            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String name, email, provider, phoneNumber, streetAddress, city, state, zipCode, country, website,
                    offeredMajors, aboutUniversity, latlong, subject, message;
            double minimumRequiredGPA, minimumRequiredPSATScore, minimumRequiredSATScore,
                    minimumRequiredACTScore, minimumRequiredTOEFLScore;
            byte[] filecoded;
            int count;
//Date dateOfBirth;
//            String exam1,exam2,exam3;
            UniversityBean aUniversityBean;
            while (rs.next()) {
                // 1. if a float (say PRICE) is to be retrieved, use rs.getFloat("PRICE");
                // 2. Instead of using column name, can alternatively use: rs.getString(1); // not 0
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

    public String deleteProfile(String email, String password) {
        String s = "";
        Boolean valid = false;
        Connection DBConn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int a, c;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.LOGININFOSTUD ";
            query += "WHERE email = '" + email + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String userid = email.toLowerCase();
            //   LoginController delete = new LoginController();
            String pass = password;
            while (rs.next()) {
                String dbEmail = rs.getString("email").toLowerCase();
                String dbPassword = rs.getString("password");
                valid = userid.equalsIgnoreCase(dbEmail) && pass.equals(dbPassword);
            }
            rs.close();
            stmt.close();
            if (valid == true) {
                // With the connection made, create a statement to talk to the DB server.
                // Create a SQL statement to query, retrieve the rows one by one (by going to the
                // columns), and formulate the result string to send back to the client.
                String query1 = "DELETE FROM FINALPROJECT353.STUDENTS WHERE EMAIL = ?";
                //     String query2 = "DELETE FROM FINALPROJECT353.RECRUITER WHERE EMAIL = " + "('" + email + "');";
                String query3 = "DELETE FROM FINALPROJECT353.LOGININFOSTUD WHERE EMAIL = ?";
                //    String query4 = "DELETE FROM FINALPROJECT353.LOGININFORECT WHERE EMAIL = " + "('" + email + "');";

                preparedStatement = DBConn.prepareStatement(query1);
                preparedStatement1 = DBConn.prepareStatement(query3);
                preparedStatement.setString(1, email);
                preparedStatement1.setString(1, email);
                //     Statement stmt = DBConn.createStatement() ;
                a = preparedStatement.executeUpdate();
                //    int b = stmt.executeUpdate(query2);
                c = preparedStatement1.executeUpdate();
                //     int d = stmt.executeUpdate(query4);

                if ((a > 0 && c > 0)) {
                    preparedStatement.close();
                    s = "Home.xhtml";
                } else {
                    preparedStatement1.close();
                    s = "error.xhtml";

                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }

        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return s;
    }

    public String deleteUniversityProfileDAO(String email, String password) {
        String s = "";
        Boolean valid = false;
        Connection DBConn = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        int a, c;
        try {
            DBHelper.loadDriver("org.apache.derby.jdbc.ClientDriver");
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DBHelper.connect2DB(myDB, "itkstu", "student");

            String query = "SELECT * FROM FinalProject353.LOGININFORECT ";
            query += "WHERE email = '" + email + "'";

            Statement stmt = DBConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String userid = email.toLowerCase();
            String pass = password;
            while (rs.next()) {
                String dbEmail = rs.getString("email").toLowerCase();
                String dbPassword = rs.getString("password");
                valid = userid.equalsIgnoreCase(dbEmail) && pass.equals(dbPassword);
            }
            rs.close();
            stmt.close();
            if (valid == true) {
                // With the connection made, create a statement to talk to the DB server.
                // Create a SQL statement to query, retrieve the rows one by one (by going to the
                // columns), and formulate the result string to send back to the client.
                // String query1 = "DELETE FROM FINALPROJECT353.STUDENTS WHERE EMAIL = ?";
                String query2 = "DELETE FROM FINALPROJECT353.RECRUITER WHERE EMAIL = ?";
                //  String query3 = "DELETE FROM FINALPROJECT353.LOGININFOSTUD WHERE EMAIL = ?";
                String query4 = "DELETE FROM FINALPROJECT353.LOGININFORECT WHERE EMAIL = ?";

                preparedStatement = DBConn.prepareStatement(query2);
                preparedStatement1 = DBConn.prepareStatement(query4);
                preparedStatement.setString(1, email);
                preparedStatement1.setString(1, email);
                //     Statement stmt = DBConn.createStatement() ;
                a = preparedStatement.executeUpdate();
                //    int b = stmt.executeUpdate(query2);
                c = preparedStatement1.executeUpdate();
                //     int d = stmt.executeUpdate(query4);

                if ((a > 0 && c > 0)) {
                    preparedStatement.close();
                    s = "Home.xhtml";
                } else {
                    preparedStatement1.close();
                    s = "error.xhtml";

                }

            }
        } catch (Exception e) {
            System.err.println("ERROR: Problems with SQL select");
            e.printStackTrace();
        }
        try {
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return s;
    }

    public int updateUniversityPassword(String email, String newPassword) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        int rowCount1 = 0;
        int rowCount2 = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String updateString1, updateString2;
            //   Statement stmt = DBConn.createStatement();

            updateString1 = "UPDATE FinalProject353.Recruiter SET password = ? where email = ?";

            updateString2 = "UPDATE FinalProject353.logininforect SET password = ? where email = ?";

            preparedStatement = DBConn.prepareStatement(updateString1);
            preparedStatement1 = DBConn.prepareStatement(updateString2);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement1.setString(1, newPassword);
            preparedStatement1.setString(2, email);
            rowCount1 = preparedStatement.executeUpdate();
            rowCount2 = preparedStatement1.executeUpdate();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount1 + rowCount2;

    }

    public int updateStudentPassword(String email, String newPassword) {
        Connection DBConn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        int rowCount1 = 0;
        int rowCount2 = 0;
        try {
            String myDB = "jdbc:derby://localhost:1527/FinalProject353";
            DBConn = DriverManager.getConnection(myDB, "itkstu", "student");

            String updateString1, updateString2;
            //   Statement stmt = DBConn.createStatement();

            updateString1 = "UPDATE FinalProject353.students SET password = ? where email = ?";

            updateString2 = "UPDATE FinalProject353.logininfostud SET password = ? where email = ?";

            preparedStatement = DBConn.prepareStatement(updateString1);
            preparedStatement1 = DBConn.prepareStatement(updateString2);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement1.setString(1, newPassword);
            preparedStatement1.setString(2, email);
            rowCount1 = preparedStatement.executeUpdate();
            rowCount2 = preparedStatement1.executeUpdate();

            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // if insert is successful, rowCount will be set to 1 (1 row inserted successfully). Else, insert failed.
        return rowCount1 + rowCount2;

    }
}
