import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.Scanner;

public class DataPop {
    private static String user;
    private static String pass;
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> firstNames = new ArrayList<String>();
    private static ArrayList<String> lastNames = new ArrayList<String>();
    private static ArrayList<String> roads = new ArrayList<String>();
    private static int fnlength;
    private static int lnlength;
    private static int rdlength;
    private static Statement st;
    private static Connection conn;

    public static void main(String[] arg)
            throws SQLException, IOException, java.lang.ClassNotFoundException {
        login();
        try (Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241", user,
                pass); Statement s = con.createStatement();) {
            st = s;
            conn = con;
            System.out.println("connection successfully made.");

            File file1 = new File("census-derived-all-first.txt");
            File file2 = new File("dist.all.last.txt");
            Scanner scF = new Scanner(file1);
            Scanner scL = new Scanner(file2);
            while (scF.hasNextLine()) {
                firstNames.add(scF.nextLine().replaceAll("[^A-Za-z]", ""));
                // System.out.println(firstNames.toString());
            }
            scF.close();
            fnlength = firstNames.size();
            while (scL.hasNextLine()) {
                lastNames.add(scL.nextLine().replaceAll("[^A-Za-z]", ""));
                // System.out.println(firstNames.toString());
            }
            scL.close();
            lnlength = lastNames.size();

            roads.add("Road");
            roads.add("Place");
            roads.add("Avenue");
            roads.add("Street");
            roads.add("Lane");
            roads.add("Hill");
            roads.add("Square");
            rdlength = roads.size();

            System.out.print("Enter mode: ");
            String mode = sc.nextLine();

            if (mode.equals("1")) { // type creation
                System.out.println("table: ");
                mode = sc.nextLine();
                while (true) {
                    System.out.print("Enter food type: ");
                    String input = sc.nextLine();
                    System.out.print("Enter super food type: ");
                    String input2 = sc.nextLine();
                    if (input == null || input.equals("stop")) {
                        input = "";
                        break;
                    }
                    String q;
                    // ResultSet result;
                    q = "INSERT INTO TYPE (TYPE_NAME) VALUES('" + input + "')";
                    // System.out.print(q);
                    s.executeQuery(q);
                    q = "INSERT INTO " + mode
                            + " (TYPE_ID, SUPER_ID) VALUES((SELECT TYPE_ID FROM TYPE WHERE TYPE_NAME = '"
                            + input
                            + "'), (SELECT TYPE_ID FROM TYPE WHERE TYPE_NAME = '"
                            + input2 + "'))";
                    // System.out.print(q);
                    s.executeQuery(q);
                }
            }
            else if (mode.equals("2")) { // product creation
                while (true) {
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter product size: ");
                    String size = sc.nextLine();
                    System.out.print("Enter product type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter product brand: ");
                    String brand = sc.nextLine();
                    String price = randNum(6) + "." + randNum(6);
                    if (name == null || name.equals("stop")
                            || name.equals("")) {
                        name = "";
                        break;
                    }
                    String q;
                    // ResultSet result;
                    q = "CALL CREATE_PRODUCT('" + name + "', '" + size + "', '"
                            + type + "', '" + brand + "', '" + price + "')";
                    // System.out.print(q);
                    s.executeUpdate(q);

                    System.out.print("Second type?: ");
                    type = sc.nextLine();
                    if (type != null && !type.equals("")) {
                        q = "INSERT INTO PRODUCT_TYPE (TYPE_ID, PRODUCT_ID) VALUES ((SELECT TYPE_ID FROM TYPE WHERE TYPE_NAME = '"
                                + type
                                + "'), (SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NAME = '"
                                + name + "' AND \"SIZE\" = '" + size + "'))";
                        // System.out.print(q);
                        s.executeUpdate(q);
                    }
                }
            }
            else if (mode.equals("3")) { // customer creation
                System.out.print("Enter iterations: ");
                Integer i = Integer.parseInt(sc.nextLine());
                for (; i > 0; i--) {
                    String first = randName(0);
                    String last = randName(1);
                    String adr = randName(2);
                    String zip = randNum(0) + "";
                    String ifs = "N";
                    if (randPick(35)) {
                        ifs = "Y";
                    }
                    String ios = "N";
                    if (randPick(70)) {
                        ios = "Y";
                    }
                    String age = randNum(1) + "";
                    String cid = first + age;
                    if (randPick(10)) {
                        age = "";
                    }
                    String gender = "";
                    if (randPick(90)) {
                        gender = "other";
                        if (randPick(92)) {
                            gender = "male";
                            if (randPick(50)) {
                                gender = "female";
                            }
                        }
                    }
                    String cardNum = randNum(2) + "";
                    String cvv = randNum(3) + "";
                    String exp = randNum(4) + "/" + randNum(5);
                    String q;
                    // ResultSet result;
                    q = "CALL CREATE_CUSTOMER('" + cid + "', '" + first + "', '"
                            + last + "', '" + adr + "', '" + zip + "', '" + ifs
                            + "', '" + ios + "', '" + age + "', '" + gender
                            + "', '" + cardNum + "', '" + cvv + "', '" + exp
                            + "')";
                    // System.out.println(q);
                    s.executeUpdate(q);
                }
            }
            else if (mode.equals("4")) { // store/vendor/brand creation
                System.out.print("Enter iterations: ");
                Integer i = Integer.parseInt(sc.nextLine());
                for (; i > 0; i--) {
                    String adr = randName(0);
                    // String zip = randNum(0) + "";
                    String q;
                    // ResultSet result;
                    q = "INSERT INTO BRAND (BRAND_NAME) VALUES ('" + adr + "')";
                    // System.out.print(q);
                    s.executeUpdate(q);
                }
            }
            else if (mode.equals("5")) { // vendor price creation
                System.out.print("Enter iterations: ");
                Integer i = Integer.parseInt(sc.nextLine());
                ArrayList<String> pids = getIdList("PRODUCT_ID", "PRODUCT");
                ArrayList<String> vids = getIdList("VENDOR_ID", "VENDOR");
                String q;
                for (; i > 0; i--) {
                    String vid = vids.get((int) (Math.random() * vids.size()));
                    String pid = pids.get((int) (Math.random() * pids.size()));
                    String Price = randNum(6) + "." + randNum(6);
                    // String q;
                    // ResultSet result;
                    q = "CALL UPDATE_VENDOR_PRICE('" + vid + "', '" + pid
                            + "', '" + Price + "')";
                    // System.out.println(q);
                    s.executeUpdate(q);
                }
            }
            else if (mode.equals("6")) { // buy some inventory
                System.out.print("Enter iterations: ");
                Integer i = Integer.parseInt(sc.nextLine());
                ArrayList<String> vids = getIdList("VENDOR_ID", "VENDOR");
                ArrayList<String> vids2 = new ArrayList<String>();
                ArrayList<ArrayList<String>> pids = new ArrayList<ArrayList<String>>();
                for (int z = 0; z < vids.size(); z++) {
                    ArrayList<String> insert = getIdList("PRODUCT_ID",
                            "VENDOR_PRODUCT WHERE VENDOR_ID = " + vids.get(z));
                    if (insert.size() != 0) {
                        pids.add(insert);
                        vids2.add(vids.get(z));
                    }
                }
                // System.out.println(pids);
                // System.out.println(vids2);
                ArrayList<String> stids = getIdList("STORE_ID", "STORE");
                String q;
                for (; i > 0; i--) {
                    int vidindex = (int) (Math.random() * vids2.size());
                    String vid = vids2.get(vidindex);
                    ArrayList<String> pids2 = pids.get(vidindex);
                    String pid = pids2
                            .get((int) (Math.random() * pids2.size()));
                    String stid = stids
                            .get((int) (Math.random() * stids.size()));
                    if (randPick(0)) {
                        stid = "0";
                    }
                    String pricechange = "N";
                    if (randPick(50)) {
                        pricechange = "Y";
                    }
                    String inprice = randNum(6) + "." + randNum(6);
                    String inamount = randNum(7) + "";
                    // String q;
                    // ResultSet result;
                    CallableStatement cs = con.prepareCall("{call INVENTORY_BUY(?,?,?,?,?,?,?)}");
                    cs.setInt(1, Integer.parseInt(vid));
                    cs.setInt(2, Integer.parseInt(pid));
                    cs.setInt(3, Integer.parseInt(stid));
                    cs.setString(4, pricechange);
                    cs.setDouble(5, Double.parseDouble(inprice));
                    cs.setInt(6, Integer.parseInt(inamount));
                    cs.registerOutParameter(7, java.sql.Types.VARCHAR);
                    //System.out.println(cs.toString());
                    cs.executeUpdate();
                    //System.out.println(cs.getString(7));
                    cs.close();
                }
            }
            else if (mode.equals("7")) { // sell some inventory
                System.out.print("Enter iterations: ");
                Integer i = Integer.parseInt(sc.nextLine());
                ArrayList<String> cids = getIdList("CUSTOMER_ID", "CUSTOMER");
                ArrayList<String> invids = getIdList("INV_ID", "INVENTORY");
                String q;
                for (; i > 0; i--) {
                    String cid = cids.get((int) (Math.random() * cids.size()));
                    String invid = invids
                            .get((int) (Math.random() * invids.size()));
                    String inamount = (randNum(7) / 2) + "";
                    String inisonline = "N";
                    if (randPick(60)) {
                        inisonline = "Y";
                    }
                    String inpaytype = "cash";
                    if (randPick(75)) {
                        inpaytype = "credit";
                        if (randPick(5)) {
                            inpaytype = "check";
                        }
                    }
                    String incardnum = randNum(2) + "";
                    String incvv = randNum(3) + "";
                    String inexpdate = randNum(4) + "/" + randNum(5);
                    String inchecknum = ((randNum(2) + "")
                            + ((randNum(6) / 10l) + "")
                            + ((randNum(6) / 10l) + "")
                            + ((randNum(6) / 10l) + "")
                            + ((randNum(6) / 10l) + ""));
                    // String q;
                    // ResultSet result;
                    CallableStatement cs = con.prepareCall("{call INVENTORY_SALE(?,?,?,?,?,?,?,?,?,?)}");
                    cs.setString(1, cid);
                    cs.setInt(2, Integer.parseInt(invid));
                    cs.setInt(3, Integer.parseInt(inamount));
                    cs.setString(4, inisonline);
                    cs.setString(5, inpaytype);
                    cs.setLong(6, Long.parseLong(incardnum));
                    cs.setInt(7, Integer.parseInt(incvv));
                    cs.setString(8, inexpdate);
                    cs.setString(9, inchecknum);
                    cs.registerOutParameter(10, java.sql.Types.VARCHAR);
                    //System.out.println(q);
                    cs.executeUpdate();
                    System.out.println(cs.getString(10));
                    cs.close();
                }
            }
            s.close();
            con.close();
        }
        catch (SQLException se) {
            if (se.getSQLState().equals("72000")) {
                System.out.print("login failed.  Try Again? (Y/N): ");
                String ans = sc.nextLine();
                if (ans.equals("Y") || ans.equals("y")) {
                    main(null);
                    return;
                }
            }
            else if (se.getSQLState().equals("42000")) {
                System.out.println(se.getMessage());
                System.out.println("Invalid course ID.  Exiting.");
            }
            else {
                System.out.println("An error has occured.");
                System.out.println(se.getSQLState() + ": " + se.getMessage());
            }
        }
        sc.close();
    }

    private static String randName(int x) {
        if (x == 0) {
            String randFirst = firstNames.get((int) (Math.random() * fnlength))
                    .toLowerCase();
            randFirst = randFirst.substring(0, 1).toUpperCase()
                    + randFirst.substring(1);
            return randFirst;
        }
        else if (x == 1) {
            String randLast = lastNames.get((int) (Math.random() * lnlength))
                    .toLowerCase();
            randLast = randLast.substring(0, 1).toUpperCase()
                    + randLast.substring(1);
            return randLast;
        }
        else {
            String roadName = ((int) (Math.random() * 150) + 1) + " "
                    + randName((int) (Math.random() * 2));
            String randRoad = roads.get((int) (Math.random() * rdlength))
                    .toLowerCase();
            randRoad = randRoad.substring(0, 1).toUpperCase()
                    + randRoad.substring(1);
            return roadName + " " + randRoad;
        }
    }

    private static boolean randPick(int prob) {
        if (((int) (Math.random() * 100) + 1) <= prob) {
            return true;
        }
        return false;
    }

    private static long randNum(int x) {
        if (x == 0) { // zipcode
            return 18000 + (int) (Math.random() * 120);
        }
        else if (x == 1) { // age
            return (int) (Math.random() * 75) + 18;
        }
        else if (x == 2) { // card-num
            return (long) (Math.random() * 10000000000000000l);
        }
        else if (x == 3) { // cvv
            return (int) (Math.random() * 900) + 100;
        }
        else if (x == 4) { // exp-month
            return (int) (Math.random() * 12) + 1;
        }
        else if (x == 5) { // exp-year
            return (int) (Math.random() * 5) + 19;
        }
        else if (x == 6) { // price
            return (int) (Math.random() * 100);
        }
        else if (x == 7) { // amount
            return (int) (Math.random() * 96) + 5;
        }
        return 0;
    }

    private static ArrayList<String> getIdList(String id, String table) {
        login();
        try {
            // System.out.println("connection successfully made.");
            String q;
            q = "SELECT " + id + " FROM " + table + "";
            ResultSet r = st.executeQuery(q);
            ArrayList<String> pids = new ArrayList<String>();
            while (r.next()) {
                pids.add(r.getString(id));
            }
            return pids;
        }
        catch (SQLException se) {
            System.out.println("there was an error");
            System.out.println(se.getMessage());
        }
        return new ArrayList<String>();
    }

    private static void login() {
        // System.out.print("userId: ");
        // user = sc.nextLine();
        // System.out.print("password: ");
        // pass = sc.nextLine();
        user = "jjs220";
        pass = "J$chinto98";
    }
}