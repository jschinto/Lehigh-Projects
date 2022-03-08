import java.sql.SQLException;

public class jjs220 {
    private static View theView;
    private static DataConnect dc;
    private static String option;

    public static void main(String[] args) {
        theView = new View();
        try (DataConnect dc = new DataConnect()) {
            theView.print("Enter Oracle Password: ");
            dc.makeConnection(theView.readInput());
            theView.println("Connection made successfully!");
            option = theView.readChoice("Choose a User: ", "Customer",
                    "Enterprise Manager");

            if (option.equals("0")) {
                new OnlineCustomer(theView, dc);
            }
            else if (option.equals("1")) {
                new EnterpriseManager(theView, dc);
            }

            theView.println("Closing Connection.");
            dc.close();
        }
        catch (SQLException se) {
            if (se.getSQLState().equals("72000")) {
                theView.print("Login Failed.  Try Again?  (Y/N): ");
                if (theView.readYN().equals("N")) {
                    theView.println("Exiting.");
                    return;
                }
                else {
                    main(args);
                    return;
                }
            }
        }
    }

}
