import java.sql.*;
public class SimpleInsert {
  public static void main(String[] args) {
    try (
         Connection con=DriverManager.getConnection("jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241","u","pw");
         Statement s=con.createStatement();
         ) {
           String q;
           ResultSet result;
           int i;
           q = "insert into enemies2 select name, salary/10000 from instructor";
           i = s.executeUpdate(q);
           System.out.println ("value returned: " + i);
         } catch(Exception e){e.printStackTrace();}
  }
}
