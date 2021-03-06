import java.io.*;
import java.sql.*;
public class JayDeeBeeSea {
  public static void main (String[] arg) 
  throws SQLException, IOException, java.lang.ClassNotFoundException {
  try (
       Connection con=DriverManager.getConnection
         ("jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241","u","pw");
       Statement s=con.createStatement();
      ) {
      System.out.println("connection successfully made.");
      String q;
      ResultSet result;
      q = "select title, dept_name from course where title = 'foo'";
      result = s.executeQuery(q);
      if (!result.next()) System.out.println ("Empty result.");
      else {
        do {
          System.out.println (result.getString("title") + " " + result.getString("dept_name"));
        } while (result.next());
      }
      s.close();
      con.close();
    }
  }
}
