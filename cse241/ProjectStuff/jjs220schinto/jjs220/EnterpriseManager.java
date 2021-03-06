import java.sql.*;
import java.util.*;

public class EnterpriseManager {
    View theView;
    DataConnect dc;
    Connection conn;

    public EnterpriseManager(View theView, DataConnect dc) {
        this.theView = theView;
        this.dc = dc;
        conn = dc.getConnection();

        run();
    }

    private void run() {
        theView.println("Hello Enterprise Managers!");
        mainMenu();
    }

    private String[] mainoptions = { "Purchase online inventory",
            "Add New Product", "Update online price", "Update/add vendor price",
            "Business performance", "Logout" };

    private void mainMenu() {
        String pick = theView.readChoice("What would you like to do?",
                mainoptions);
        if (pick.equals("0")) {
            purchaseInv();
        }
        else if (pick.equals("1")) {
            addProduct();
        }
        else if (pick.equals("2")) {
            changePrice();
        }
        else if (pick.equals("3")) {
            vendorPrice();
        }
        else if (pick.equals("4")) {
            reporting();
        }
        else {
            theView.println("Logging off.");
            return;
        }
        mainMenu();
    }

    private void changePrice() {
        theView.print("What is the product_id for the product?: ");
        String pid = theView.readNum();
        theView.print("What is the new price for the product?: ");
        Double price = theView.readPrice();
        try {
            PreparedStatement ps = conn
                    .prepareStatement("UPDATE INVENTORY SET CURR_PRICE = ?"
                            + " WHERE INV_ID = ((SELECT INV_ID FROM INV_PRODUCT WHERE PRODUCT_ID = ?"
                            + ") minus (SELECT INV_ID FROM STORE_INV))");
            dc.setPS(ps);
            dc.setPSParam(1, price + "");
            dc.setPSParam(2, pid);
            ps.executeUpdate();
            theView.println("Price Updated successfully");
            ps.close();
            return;
        }
        catch (SQLException se) {
            theView.println("There was an error. Exiting to menu.");
            return;
        }
    }

    private void vendorPrice() {
        theView.print("What is the product_id that you want to buy?: ");
        String pid = theView.readNum();
        theView.print("What is the vendor_id that will sell?: ");
        String vid = theView.readNum();
        String price = theView.readPrice() + "";
        String q;
        q = "CALL UPDATE_VENDOR_PRICE('" + vid + "', '" + pid + "', '" + price
                + "')";
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(q);
            s.close();
            theView.println("Update Successful");
        }
        catch (SQLException se) {
            theView.println("Vendor update failed. Exiting to menu.");
            return;
        }
    }

    private void reporting() {
        try {
            String pick = theView.readChoice("What report?", "Gross Profit",
                    "Sales by product", "Sales by zipcode", "Go Back");
            if (pick.equals("3")) {
                return;
            }
            else if (pick.equals("0")) {
                Statement s = conn.createStatement();
                String q = "SELECT SUM(VENDOR_TRANSACTION.PRICE * VENDOR_TRANSACTION.AMOUNT) AS EXPENSES FROM VENDOR_TRANSACTION";
                ResultSet rs = s.executeQuery(q);
                Double exp = 0.0;
                if (rs.next()) {
                    exp = rs.getDouble(1);
                }
                s.close();
                s = conn.createStatement();
                q = "SELECT SUM(CUSTOMER_TRANSACTION.PRICE * CUSTOMER_TRANSACTION.AMOUNT) AS SALES FROM CUSTOMER_TRANSACTION";
                rs = s.executeQuery(q);
                Double sale = 0.0;
                if (rs.next()) {
                    sale = rs.getDouble(1);
                }
                s.close();
                Double noi = sale - exp;
                theView.printLine();
                theView.println("Total sales for BRC = " + sale + " dollars.");
                theView.println("Total expenses from inventory for BRC = " + exp
                        + " dollars.");
                theView.println("Gross Profit for BRC = " + noi + " dollars.");
            }
            else if (pick.equals("1")) {
                Statement s = conn.createStatement();
                String q = "SELECT PRODUCT.PRODUCT_ID, PRODUCT.PRODUCT_NAME, PRODUCT.\"SIZE\", SUM(CUSTOMER_TRANSACTION.PRICE * CUSTOMER_TRANSACTION.AMOUNT) AS SALES, rank() over (order by SUM(CUSTOMER_TRANSACTION.PRICE * CUSTOMER_TRANSACTION.AMOUNT) DESC) FROM CUSTOMER_TRANSACTION INNER JOIN CUST_PURCHASE ON CUST_PURCHASE.TRANS_ID = CUSTOMER_TRANSACTION.TRANS_ID INNER JOIN INV_PRODUCT ON inv_product.inv_id = CUST_PURCHASE.INV_ID INNER JOIN PRODUCT ON product.product_id = INV_PRODUCT.PRODUCT_ID GROUP BY PRODUCT.PRODUCT_ID,PRODUCT.PRODUCT_NAME,PRODUCT.\"SIZE\"";
                ResultSet rs = s.executeQuery(q);
                theView.printLine();
                theView.println("[RANK] PRODUCT_NAME | SIZE | SALES");
                while (rs.next()) {
                    theView.println("[" + rs.getString(5) + "] "
                            + rs.getString(2) + " | " + rs.getString(3) + " | $"
                            + rs.getString(4));
                }
                s.close();
            }
            else if (pick.equals("2")) {
                Statement s = conn.createStatement();
                String q = "SELECT STORE.ZIPCODE, SUM(CUSTOMER_TRANSACTION.PRICE * CUSTOMER_TRANSACTION.AMOUNT) AS SALES, rank() over (order by SUM(CUSTOMER_TRANSACTION.PRICE * CUSTOMER_TRANSACTION.AMOUNT) DESC) FROM CUSTOMER_TRANSACTION INNER JOIN CUST_PURCHASE ON CUST_PURCHASE.TRANS_ID = CUSTOMER_TRANSACTION.TRANS_ID INNER JOIN STORE_INV ON STORE_INV.inv_id = CUST_PURCHASE.INV_ID INNER JOIN STORE ON STORE.STORE_ID = STORE_INV.STORE_ID GROUP BY STORE.ZIPCODE";
                ResultSet rs = s.executeQuery(q);
                theView.printLine();
                theView.println("[RANK] ZIPCODE | SALES");
                while (rs.next()) {
                    theView.println("[" + rs.getString(3) + "] "
                            + rs.getString(1) + " | $" + rs.getString(2));
                }
                s.close();
            }
        }
        catch (SQLException se) {
            theView.println("An error occurred. Exiting to menu.");
            theView.println(se.getMessage());
            return;
        }
    }

    private void addProduct() {
        String pName = "";
        String pSize = "";
        String pType = "";
        ArrayList<String> addType = new ArrayList<String>();
        String pBrand = "";
        String pPrice = "";
        theView.print("What is the new product name: ");
        pName = theView.readAlphaNum();
        theView.print("What is the new product size: ");
        pSize = theView.readAlphaNum();
        theView.print("What is the new product type: ");
        pType = theView.readAlphaNum();
        while (simpleSelect("count(TYPE_NAME)",
                "TYPE WHERE TYPE_NAME = '" + pType + "'").equals("0")) {
            theView.print("That is not a valid type. Try again? (Y/N): ");
            if (theView.readYN().equals("N")) {
                return;
            }
            theView.print("What is the new product type: ");
            pType = theView.readAlphaNum();
        }
        theView.print("Does the product have any additional types? (Y/N): ");
        String pType2 = "";
        while (theView.readYN().equals("Y")) {
            theView.print("What is the new product type: ");
            pType2 = theView.readAlphaNum();
            while (simpleSelect("count(TYPE_NAME)",
                    "TYPE WHERE TYPE_NAME = '" + pType2 + "'").equals("0")) {
                theView.print("That is not a valid type. Try again? (Y/N): ");
                if (theView.readYN().equals("N")) {
                    return;
                }
                theView.print("What is the new product type: ");
                pType2 = theView.readAlphaNum();
            }
            addType.add(pType2);
            theView.print("Does the product have any more types? (Y/N): ");
        }
        theView.print("What is the new product brand: ");
        pBrand = theView.readAlphaNum();
        while (simpleSelect("count(BRAND_NAME)",
                "BRAND WHERE BRAND_NAME = '" + pBrand + "'").equals("0")) {
            theView.print("That is not a valid brand. Try again? (Y/N): ");
            if (theView.readYN().equals("N")) {
                return;
            }
            theView.print("What is the new product brand: ");
            pBrand = theView.readAlphaNum();
        }
        theView.print("What is the new product price: ");
        pPrice = theView.readPrice() + "";

        try {
            CallableStatement cs = conn
                    .prepareCall("{call CREATE_PRODUCT(?,?,?,?,?)}");
            dc.setCS(cs);
            dc.setCSParam(1, pName);
            dc.setCSParam(2, pSize);
            dc.setCSParam(3, pType);
            dc.setCSParam(4, pBrand);
            dc.setCSParam(5, pPrice);
            cs.executeUpdate();
            cs.close();
            String q;
            for (String t : addType) {
                q = "INSERT INTO PRODUCT_TYPE (TYPE_ID, PRODUCT_ID) VALUES ((SELECT TYPE_ID FROM TYPE WHERE TYPE_NAME = '"
                        + t
                        + "'), (SELECT PRODUCT_ID FROM PRODUCT WHERE PRODUCT_NAME = '"
                        + pName + "' AND \"SIZE\" = '" + pSize + "'))";
                PreparedStatement ps = conn.prepareStatement(q);
                ps.executeUpdate();
                ps.close();
            }
            theView.println("Product Addition Successful");
            return;
        }
        catch (SQLException se) {
            theView.println(
                    "Couldn't complete the new product process. exiting to menu.");
            return;
        }
    }

    private void purchaseInv() {
        theView.print("What is the product_id that you want to buy?: ");
        String product_id = theView.readAlphaNum();
        ArrayList<Store> vendors = getVendorList(
                "VENDOR.VENDOR_ID,VENDOR_PRICE,ADDRESS,ZIPCODE", 4,
                "VENDOR_PRODUCT,VENDOR", true,
                "VENDOR_PRODUCT.VENDOR_ID = VENDOR.VENDOR_ID AND PRODUCT_ID = ?",
                theView.parseInt(product_id));
        if (vendors.size() == 0) {
            theView.println("No vendors sell that product_id");
            return;
        }
        String[] vends = new String[vendors.size() + 1];
        for (int i = 0; i < vendors.size(); i++) {
            vends[i] = vendors.get(i).vendorString();
        }
        vends[vendors.size()] = "Go Back";
        String option = theView.readChoice("Choose a vendor", vends);
        if (theView.parseInt(option) == vendors.size()) {
            return;
        }
        else {
            Store theVendor = vendors.get(theView.parseInt(option));
            theView.print("How many units to buy? ");
            String inamount = theView.readNum();
            while (inamount == null || inamount.equals("")) {
                theView.print("Invalid number. How many units to buy?");
                inamount = theView.readNum();
            }
            try {
                CallableStatement cs = conn
                        .prepareCall("{call INVENTORY_BUY(?,?,?,?,?,?,?)}");
                dc.setCS(cs);
                dc.setCSParam(1, theVendor.getStore_id());
                dc.setCSParam(2, theView.parseInt(product_id));
                dc.setCSParam(3, 0);
                dc.setCSParam(4, "N");
                dc.setCSParam(5, null);
                dc.setCSParam(6, theView.parseInt(inamount));
                cs.registerOutParameter(7, java.sql.Types.VARCHAR);
                cs.executeUpdate();
                cs.close();
                theView.println("Purchase Successful");
                return;
            }
            catch (SQLException se) {
                theView.println(
                        "Couldn't complete the purchase. exiting to menu.");
                return;
            }
        }
    }

    private <T> ArrayList<Store> getVendorList(String returnAtt, Integer atts,
            String table, boolean isWhere, String searchAtt, Integer where) {
        ArrayList<ArrayList<String>> Stores = getDataList(returnAtt, atts,
                table, isWhere, searchAtt, where);
        ArrayList<Store> s = new ArrayList<Store>();
        for (int i = 0; i < Stores.size(); i++) {
            s.add(new Store(theView.parseInt(Stores.get(i).get(0)),
                    Double.parseDouble(Stores.get(i).get(1)),
                    Stores.get(i).get(2),
                    theView.parseInt(Stores.get(i).get(3))));
        }
        return s;
    }

    private String simpleSelect(String att, String table) {
        try {
            PreparedStatement ps = conn
                    .prepareStatement("SELECT " + att + " FROM " + table);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getString(1);
            }
        }
        catch (SQLException se) {
        }
        return null;
    }

    private <T> ArrayList<ArrayList<String>> getDataList(String returnAtt,
            Integer atts, String table, boolean isWhere, String searchAtt,
            T where) {
        try {
            PreparedStatement ps;
            if (isWhere) {
                ps = conn.prepareStatement("SELECT " + returnAtt + " FROM "
                        + table + " WHERE " + searchAtt);
                dc.setPS(ps);
                dc.setPSParam(1, where);
            }
            else {
                ps = conn.prepareStatement(
                        "SELECT " + returnAtt + " FROM " + table);
            }
            ResultSet r = ps.executeQuery();
            ArrayList<ArrayList<String>> pids = new ArrayList<ArrayList<String>>();
            while (r.next()) {
                ArrayList<String> curr = new ArrayList<String>();
                for (int i = 1; i <= atts; i++) {
                    curr.add(r.getString(i));
                }
                pids.add(curr);
            }
            return pids;
        }
        catch (SQLException se) {
            System.out.println("there was an error");
        }
        return new ArrayList<ArrayList<String>>();
    }

}
