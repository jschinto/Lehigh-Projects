import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class TookCourse {
    private static String user;
    private static String pass;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] arg)
            throws SQLException, IOException, java.lang.ClassNotFoundException {
        login();
        try (Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241",
                user, pass);
                Statement s = con.createStatement();) {
            System.out.println("connection successfully made.");
            System.out.print("Enter courseID: ");
            String cID = sc.nextLine();
            if(cID == null ){
                cID = "";
            }
            String q;
            ResultSet result;
            q = "SELECT STUDENT.ID, STUDENT.NAME AS Student, TAKES.YEAR AS Year, TAKES.SEMESTER AS Semester, TAKES.SEC_ID AS Section"
                    + " FROM STUDENT, TAKES"
                    + " WHERE STUDENT.ID = TAKES.ID"
                    + " AND COURSE_ID = " + cID
                    + " ORDER BY STUDENT.NAME, TAKES.YEAR, TAKES.SEMESTER DESC, TAKES.SEC_ID";
            result = s.executeQuery(q);
            if (!result.next()) {
                q = "SELECT COURSE.COURSE_ID"
                        + " FROM COURSE"
                        + " WHERE COURSE.COURSE_ID = " + cID;
                result = s.executeQuery(q);
                if(!result.next()) {
                    System.out.println("Course does not exist.");
                    return;
                }
                else {
                    q = "SELECT COURSE.COURSE_ID"
                            + " FROM COURSE, SECTION"
                            + " WHERE COURSE.COURSE_ID = SECTION.COURSE_ID"
                            + " AND COURSE.COURSE_ID = " + cID;
                    result = s.executeQuery(q);
                    
                    if(!result.next()) {
                        System.out.println("Course does not have any Sections.");
                        return;
                    }
                }
                System.out.println("Course has no Students.");
                return;
            }
            else {
                System.out.println(String.format("%-5s %-20s %-4s %-8s %-7s", "ID", "Student", "Year", "Semester", "Section"));
                do {
                    System.out.println(String.format("%-5s %-20s %-4s %-8s %-7s", result.getString("ID"),
                            result.getString("Student"),
                            result.getString("Year"),
                            result.getString("Semester"),
                            "    " + result.getString("Section")));
                }
                while (result.next());
            }
            s.close();
            con.close();
        }
        catch(SQLException se){
            if(se.getSQLState().equals("72000")) {
                System.out.print("login failed.  Try Again? (Y/N): ");
                String ans = sc.nextLine();
                if(ans.equals("Y") || ans.equals("y")){
                    main(null);
                    return;
                }
            }
            else if(se.getSQLState().equals("42000")) {
                System.out.println("Invalid course ID.  Exiting.");
            }
            else {
                System.out.println("An error has occured.");
                System.out.println(se.getSQLState()+": "+se.getMessage());
            }
        }
        sc.close();
    }
    
    private static void login() {
        System.out.print("userId: ");
        user = sc.nextLine();
        System.out.print("password: ");
        pass = sc.nextLine();
    }
}