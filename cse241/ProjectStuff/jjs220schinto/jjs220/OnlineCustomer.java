import java.util.*;
import java.sql.*;

public class OnlineCustomer {
    private View theView;
    private String user_id;
    private static DataConnect dc;
    private Connection conn;
    private ArrayList<Product> cart;
    private Store theStore;

    public OnlineCustomer(View intheView, DataConnect indc) {
        theStore = null;
        cart = new ArrayList<Product>();
        theView = intheView;
        dc = indc;
        conn = dc.getConnection();
        run();
    }

    private void run() {
        theView.println("Welcome to Big River Crossing!");
        theView.print("Are you a returning user? (Y/N): ");
        if (theView.readYN().equals("Y")) {
            user_id = oldUser();
        }
        else {
            user_id = newUser();
        }
        theView.println("Welcome, " + userFirstName + "!");
        mainMenu();
    }

    private String userFirstName = "";

    private String oldUser() {
        theView.print("Enter your username: ");
        String username = theView.readAlphaNum();
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT FIRST_NAME FROM CUSTOMER WHERE CUSTOMER_ID = ?");
            dc.setPS(ps);
            dc.setPSParam(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userFirstName = rs.getString(1);
                rs.close();
                ps.close();
                return username;
            }
            else {
                theView.println("Sorry, that is not a valid username.");
                theView.print("Would you like to retry? (Y/N): ");
                if (theView.readYN().equals("N")) {
                    theView.println("Exiting.");
                    rs.close();
                    ps.close();
                    dc.close();
                    System.exit(0);
                }
                rs.close();
                ps.close();
                return oldUser();
            }
        }
        catch (SQLException se) {
            theView.println(se.getMessage());
            theView.println(se.toString());
            theView.println("There was an error. Exiting.");
            System.exit(1);
        }
        return null;
    }

    private String newUser() {
        theView.print("Hello, what is your First Name?  Enter here: ");
        String first = theView.readAlphaNum();
        while (!theView.rangeCheck(first, 1, 50)) {
            theView.print("Invalid input length. Please retry: ");
            first = theView.readAlphaNum();
        }
        theView.print("Enter your Last Name: ");
        String last = theView.readAlphaNum();
        while (!theView.rangeCheck(last, 1, 50)) {
            theView.print("Invalid input length. Please retry: ");
            last = theView.readAlphaNum();
        }
        theView.print("Enter your Street Address: ");
        String address = theView.readAlphaNum();
        while (!theView.rangeCheck(address, 1, 50)) {
            theView.print("Invalid input length. Please retry: ");
            address = theView.readAlphaNum();
        }
        theView.print("Enter your Zipcode: ");
        String zipcode = theView.readNum();
        while (!theView.rangeCheck(zipcode, 1, 5)) {
            theView.print("Invalid input length. Please retry: ");
            zipcode = theView.readNum();
        }
        theView.print(
                "Would you be interested in our frequent shopper program for future savings? (Y/N): ");
        String age = "";
        String gender = "";
        String ifs = theView.readYN();
        if (ifs.equals("Y")) {
            theView.println(
                    "Please answer the next two questions honestly.  You may leave the question blank if you refuse to answer.");
            theView.print("What is your current age: ");
            age = theView.readNum();
            while (!theView.rangeCheck(age, 0, 3)) {
                theView.print("Invalid input length. Please retry: ");
                age = theView.readNum();
            }
            String[] genders = { "male", "female", "other" };
            gender = genders[Integer.parseInt(theView.readChoice(
                    "Which option best describes your gender: ", genders))];
        }
        theView.print(
                "Would you like to store a credit/debit card to be eligible for online shipped orders? (Y/N): ");
        String card_num = "";
        String cvv = "";
        String exp_date = "";
        String ios = theView.readYN();
        if (ios.equals("Y")) {
            theView.print(
                    "Enter your Card Number with no dashes/whitespace (16 digits): ");
            card_num = theView.readNum();
            while (!theView.rangeCheck(card_num, 16, 16)) {
                theView.print("Invalid input length. Please retry: ");
                card_num = theView.readNum();
            }
            theView.print("Enter your cvv number (3 digits): ");
            cvv = theView.readNum();
            while (!theView.rangeCheck(cvv, 3, 3)) {
                theView.print("Invalid input length. Please retry: ");
                cvv = theView.readNum();
            }
            theView.print(
                    "Enter the month for the expiration date (2 digits): ");
            String exp_date_month = theView.readNumMonth();
            while (!theView.rangeCheck(exp_date_month, 2, 2)) {
                theView.print("Invalid input length. Please retry: ");
                exp_date_month = theView.readNumMonth();
            }
            theView.print(
                    "Enter the year for the expiration date (2 digits): ");
            String exp_date_year = theView.readNumYear();
            while (!theView.rangeCheck(exp_date_year, 2, 2)) {
                theView.print("Invalid input length. Please retry: ");
                exp_date_year = theView.readNumYear();
            }
            exp_date = exp_date_month + "/" + exp_date_year;
        }

        return createUser(first, last, address, zipcode, ifs, ios, age, gender,
                card_num, cvv, exp_date);
    }

    private String createUser(String fName, String lName, String adr,
            String zip, String ifs, String ios, String inage, String gend,
            String incardnum, String incvv, String inexpdate) {
        theView.println("Please create a username.");
        theView.println(
                "Usernames must consist of 20 or less alphanumeric characters.");
        theView.print("Enter username: ");
        String username = theView.readAlphaNum();
        while (!theView.rangeCheck(username, 1, 20)) {
            theView.print("Invalid input length. Please retry: ");
            username = theView.readAlphaNum();
        }
        try {
            CallableStatement cs = conn.prepareCall(
                    "{call CREATE_CUSTOMER2(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            dc.setCS(cs);
            dc.setCSParam(1, username);
            userFirstName = fName;
            dc.setCSParam(2, fName);
            dc.setCSParam(3, lName);
            dc.setCSParam(4, adr);
            dc.setCSParam(5, theView.parseInt(zip));
            dc.setCSParam(6, ifs);
            dc.setCSParam(7, ios);
            dc.setCSParam(8, theView.parseInt(inage));
            dc.setCSParam(9, gend);
            dc.setCSParam(10, theView.parseLong(incardnum));
            dc.setCSParam(11, theView.parseInt(incvv));
            dc.setCSParam(12, inexpdate);
            cs.registerOutParameter(13, java.sql.Types.VARCHAR);
            cs.executeUpdate();
            if (cs.getString(13) != null && cs.getString(13).toLowerCase()
                    .equals("customer_id is not unique")) {
                theView.println("The username you chose is already in use.");
                theView.print(
                        "Would you like to try a different username? (Y/N): ");
                if (theView.readYN().equals("N")) {
                    theView.println("Exiting.");
                    cs.close();
                    dc.close();
                    System.exit(0);
                }
                cs.close();
                return createUser(fName, lName, adr, zip, ifs, ios, inage, gend,
                        incardnum, incvv, inexpdate);
            }
            cs.close();
            return username;
        }
        catch (SQLException se) {
            theView.println(se.getMessage());
            theView.println(se.toString());
            theView.println("There was an error. Exiting.");
            System.exit(1);
        }
        return null;
    }

    private final String[] menu = { "Add Items to Cart", "View Cart",
            "Edit Cart", "Checkout", "Update User Profile",
            "View Transaction History", "Logout" };

    private void mainMenu() {// TODO still finish
        String pick = theView.readChoice("What would you like to do?", menu);
        if (pick.equals("0")) { // Add Items to Cart
            addToCart();
        }
        else if (pick.equals("1")) { // View Cart
            viewCart();
        }
        else if (pick.equals("2")) { // Edit Cart
            editCart();
        }
        else if (pick.equals("3")) { // Checkout
            checkout();
        }
        else if (pick.equals("4")) { // Update User Profile
            updateProfile();
        }
        else if (pick.equals("5")) { // View Transaction History
            viewTransHistory();
        }
        else { // Logout
            theView.print(
                    "Are you sure you want to log off? Your cart will be cleared. (Y/N): ");
            if (theView.readYN().equals("N")) {
                mainMenu();
            }
            else {
                theView.println("Logging off.");
                return;
            }
        }
        mainMenu();
    }

    private void viewTransHistory() {
        ArrayList<ArrayList<String>> history = getDataList(
                "CUSTOMER_TRANSACTION.*, PRODUCT.PRODUCT_NAME, PRODUCT.\"SIZE\", BRAND.BRAND_NAME, STORE.ADDRESS",
                10,
                "CUSTOMER_TRANSACTION, INV_PRODUCT, PRODUCT, PRODUCT_BRAND, BRAND, CUST_PURCHASE LEFT OUTER JOIN STORE_INV ON STORE_INV.INV_ID = CUST_PURCHASE.INV_ID LEFT OUTER JOIN STORE ON STORE.STORE_ID = STORE_INV.STORE_ID",
                true,
                "CUSTOMER_TRANSACTION.TRANS_ID = CUST_PURCHASE.TRANS_ID AND INV_PRODUCT.INV_ID = CUST_PURCHASE.INV_ID AND PRODUCT.PRODUCT_ID = INV_PRODUCT.PRODUCT_ID AND PRODUCT.PRODUCT_ID = PRODUCT_BRAND.PRODUCT_ID AND BRAND.BRAND_ID = PRODUCT_BRAND.BRAND_ID AND CUST_PURCHASE.CUSTOMER_ID = ? ORDER BY CUSTOMER_TRANSACTION.TRANS_ID",
                user_id);
        if (history.size() > 0) {
            theView.printLine();
            theView.println("History format:");
            theView.println(
                    "[transaction #] | price per unit paid | amount bought | sale date YYYY-MM-DD | payment type | product name | product size | brand | store address");
            theView.printLine();
            for (int i = 1; i <= history.size(); i++) {
                ArrayList<String> h = history.get(i - 1);
                String adr = h.get(9);
                if (h.get(9) == null || h.get(9).equals("")) {
                    adr = "Online Store";
                }
                theView.println("[" + i + "] | $" + h.get(1) + " | " + h.get(2)
                        + " | " + h.get(3).substring(0, 10) + " | " + h.get(5)
                        + " | " + h.get(6) + " | " + h.get(7) + " | " + h.get(8)
                        + " | " + adr);
            }
        }
    }

    private void checkout() {
        if (cart.isEmpty()) {
            theView.println("Your cart is empty.");
            return;
        }
        String isonlineshopper = customerSelect("IS_ONLINE_SHOPPER");
        if (theStore.getStore_id() == null) {
            if (isonlineshopper.equals("N")) {
                theView.println(
                        "You must add a card to your account before checking out an online order.");
                return;
            }
        }
        try {
            ArrayList<Double> totalCost = new ArrayList<Double>();
            String inpaytype = "";
            Long incardnum = null;
            Integer incvv = null;
            String inexpdate = "";
            String inchecknum = "";
            for (Product p : cart) {
                String cid = user_id;
                String invid = "";
                Integer stid = theStore.getStore_id();
                Integer pid = p.getProduct_id();
                Integer inamount = p.getPlannedPurch();
                String inisonline = "Y";
                if (stid == null) {
                    inpaytype = "credit";
                    String storedcard = simpleSelect("CARD_NUM",
                            "ONLINE_SHOPPER WHERE CUSTOMER_ID = '" + cid + "'");
                    incardnum = theView.parseLong(storedcard);
                    incvv = theView.parseInt(simpleSelect("CVV",
                            "ONLINE_SHOPPER WHERE CUSTOMER_ID = '" + cid
                                    + "'"));
                    inexpdate = simpleSelect("EXP_DATE",
                            "ONLINE_SHOPPER WHERE CUSTOMER_ID = '" + cid + "'");
                    invid = simpleSelect("INV_PRODUCT.INV_ID",
                            "INV_PRODUCT LEFT OUTER JOIN STORE_INV ON INV_PRODUCT.INV_ID = STORE_INV.INV_ID WHERE INV_PRODUCT.PRODUCT_ID = "
                                    + pid + " AND STORE_INV.INV_ID IS NULL");
                }
                else {
                    if (inpaytype.equals("")) {
                        inpaytype = theView.readChoice(
                                "How will you be paying for your purchase?",
                                "credit", "check", "cash", "Go Back");
                        if (inpaytype.equals("0")) {
                            inpaytype = "credit";
                            if (isonlineshopper.equals("Y")) {
                                String storedcard = simpleSelect("CARD_NUM",
                                        "ONLINE_SHOPPER WHERE CUSTOMER_ID = '"
                                                + cid + "'");
                                theView.print(
                                        "Do you want to use your stored card ending in "
                                                + storedcard.substring(
                                                        storedcard.length() - 4)
                                                + "? (Y/N): ");
                                String ans = theView.readYN();
                                if (ans.equals("Y")) {
                                    incardnum = theView.parseLong(storedcard);
                                    incvv = theView.parseInt(simpleSelect("CVV",
                                            "ONLINE_SHOPPER WHERE CUSTOMER_ID = '"
                                                    + cid + "'"));
                                    inexpdate = simpleSelect("EXP_DATE",
                                            "ONLINE_SHOPPER WHERE CUSTOMER_ID = '"
                                                    + cid + "'");
                                }
                                else {
                                    String cardnum;
                                    String cvv;
                                    String expdate;
                                    theView.print(
                                            "Enter your Card Number with no dashes/whitespace (16 digits): ");
                                    cardnum = theView.readNum();
                                    while (!theView.rangeCheck(cardnum, 16,
                                            16)) {
                                        theView.print(
                                                "Invalid input length. Please retry: ");
                                        cardnum = theView.readNum();
                                    }
                                    theView.print(
                                            "Enter your cvv number (3 digits): ");
                                    cvv = theView.readNum();
                                    while (!theView.rangeCheck(cvv, 3, 3)) {
                                        theView.print(
                                                "Invalid input length. Please retry: ");
                                        cvv = theView.readNum();
                                    }
                                    theView.print(
                                            "Enter the month for the expiration date (2 digits): ");
                                    String exp_date_month = theView
                                            .readNumMonth();
                                    while (!theView.rangeCheck(exp_date_month,
                                            2, 2)) {
                                        theView.print(
                                                "Invalid input length. Please retry: ");
                                        exp_date_month = theView.readNumMonth();
                                    }
                                    theView.print(
                                            "Enter the year for the expiration date (2 digits): ");
                                    String exp_date_year = theView
                                            .readNumYear();
                                    while (!theView.rangeCheck(exp_date_year, 2,
                                            2)) {
                                        theView.print(
                                                "Invalid input length. Please retry: ");
                                        exp_date_year = theView.readNumYear();
                                    }
                                    expdate = exp_date_month + "/"
                                            + exp_date_year;
                                    incardnum = theView.parseLong(cardnum);
                                    incvv = theView.parseInt(cvv);
                                    inexpdate = expdate;
                                }
                            }
                            else {
                                String cardnum;
                                String cvv;
                                String expdate;
                                theView.print(
                                        "Enter your Card Number with no dashes/whitespace (16 digits): ");
                                cardnum = theView.readNum();
                                while (!theView.rangeCheck(cardnum, 16, 16)) {
                                    theView.print(
                                            "Invalid input length. Please retry: ");
                                    cardnum = theView.readNum();
                                }
                                theView.print(
                                        "Enter your cvv number (3 digits): ");
                                cvv = theView.readNum();
                                while (!theView.rangeCheck(cvv, 3, 3)) {
                                    theView.print(
                                            "Invalid input length. Please retry: ");
                                    cvv = theView.readNum();
                                }
                                theView.print(
                                        "Enter the month for the expiration date (2 digits): ");
                                String exp_date_month = theView.readNumMonth();
                                while (!theView.rangeCheck(exp_date_month, 2,
                                        2)) {
                                    theView.print(
                                            "Invalid input length. Please retry: ");
                                    exp_date_month = theView.readNumMonth();
                                }
                                theView.print(
                                        "Enter the year for the expiration date (2 digits): ");
                                String exp_date_year = theView.readNumYear();
                                while (!theView.rangeCheck(exp_date_year, 2,
                                        2)) {
                                    theView.print(
                                            "Invalid input length. Please retry: ");
                                    exp_date_year = theView.readNumYear();
                                }
                                expdate = exp_date_month + "/" + exp_date_year;
                                incardnum = theView.parseLong(cardnum);
                                incvv = theView.parseInt(cvv);
                                inexpdate = expdate;
                            }
                        }
                        else if (inpaytype.equals("1")) {
                            inpaytype = "check";
                            theView.print(
                                    "Enter your Check Number with no dashes/whitespace (25 digits max): ");
                            inchecknum = theView.readNum();
                            while (!theView.rangeCheck(inchecknum, 1, 25)) {
                                theView.print(
                                        "Invalid input length. Please retry: ");
                                inchecknum = theView.readNum();
                            }
                        }
                        else if (inpaytype.equals("2")) {
                            inpaytype = "cash";
                        }
                        else {
                            return;
                        }
                    }
                    invid = simpleSelect("INV_PRODUCT.INV_ID",
                            "INV_PRODUCT LEFT OUTER JOIN STORE_INV ON INV_PRODUCT.INV_ID = STORE_INV.INV_ID WHERE INV_PRODUCT.PRODUCT_ID = "
                                    + pid + " AND STORE_INV.INV_ID = " + stid);
                }
                CallableStatement cs = conn.prepareCall(
                        "{call INVENTORY_SALE(?,?,?,?,?,?,?,?,?,?)}");
                dc.setCS(cs);
                dc.setCSParam(1, cid);
                dc.setCSParam(2, invid);
                dc.setCSParam(3, inamount);
                dc.setCSParam(4, inisonline);
                dc.setCSParam(5, inpaytype);
                dc.setCSParam(6, incardnum);
                dc.setCSParam(7, incvv);
                dc.setCSParam(8, inexpdate);
                dc.setCSParam(9, inchecknum);
                cs.registerOutParameter(10, java.sql.Types.VARCHAR);
                cs.executeUpdate();
                if (cs.getString(10).equals("Invalid payment type")
                        || cs.getString(10).equals("Not enough inventory")) {
                    totalCost.add(0.0);
                }
                else {
                    totalCost.add(Double.parseDouble(cs.getString(10)));
                }
            }
            Double charge = 0.0;
            for (int x = 0; x < cart.size(); x++) {
                if (totalCost.get(x) == 0.0) {
                    theView.println("[" + (x + 1) + "] "
                            + cart.get(x).toString3()
                            + " was removed from your order because there wasn't enough inventory to fulfill the request.");
                }
                else {
                    Double totcost = (Math
                            .round((totalCost.get(x)
                                    * cart.get(x).getPlannedPurch() * 100))
                            / 100.0);
                    charge += totcost;
                    theView.println("[" + (x + 1) + "] You have been charged $"
                            + totcost + " for " + cart.get(x).getPlannedPurch()
                            + " " + cart.get(x).toString3() + ".");
                }
            }
            theView.println(
                    "The total charge for this order is $" + charge + ".");
            if (theStore.getAddress() == null) {
                theView.println("Your order has been shipped to your address: "
                        + customerSelect("ADDRESS"));
            }
            else {
                theView.println(
                        "Your order will be available for pickup at the Regork located at: "
                                + theStore.getAddress());
            }
            theView.println(
                    "Your cart has been cleared.  Thank you for your order!");
            cart.clear();
        }
        catch (SQLException se) {
            theView.println("It seems an error has occured.");
            theView.println(se.getMessage());
        }
    }

    private String simpleSelect(String att, String table) {
        try {
            PreparedStatement ps = conn
                    .prepareStatement("SELECT " + att + " FROM " + table);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        catch (SQLException se) {

        }
        return null;
    }

    private void editCart() {
        if (cart == null || cart.size() == 0) {
            theView.println("Your cart is empty. ");
            return;
        }
        String[] options = { "Remove item", "Update quantity", "Clear Cart",
                "Back to Main Menu" };
        String pick = theView.readChoice(
                "What would you like to edit about your cart?", options);
        String[] items = new String[cart.size() + 1];
        for (int i = 0; i < cart.size(); i++) {
            items[i] = cart.get(i).toString3();
        }
        items[cart.size()] = "Go Back";
        if (pick.equals("0")) {
            pick = theView.readChoice(
                    "What item would like to remove from your cart?", items);
            if (theView.parseInt(pick) == cart.size()) {
                editCart();
                return;
            }
            else {
                if (verifyUserChoice("remove "
                        + cart.get(theView.parseInt(pick)).getPlannedPurch()
                        + " " + cart.get(theView.parseInt(pick)).toString3()
                        + " from your cart")) {
                    cart.remove(theView.parseInt(pick).intValue());
                    theView.println("Item Removed.");
                    editCart();
                    return;
                }
                editCart();
                return;
            }
        }
        else if (pick.equals("1")) {
            pick = theView.readChoice(
                    "What item would like to update from your cart?", items);
            if (theView.parseInt(pick) == cart.size()) {
                editCart();
                return;
            }
            else {
                if (verifyUserChoice("Update the "
                        + cart.get(theView.parseInt(pick)).getPlannedPurch()
                        + " " + cart.get(theView.parseInt(pick)).toString3()
                        + " in your cart")) {
                    theView.print("Enter an updated amount up to "
                            + cart.get(theView.parseInt(pick)).getTotal()
                            + " to purchase: ");
                    String amount = theView.readNum();
                    while (amount.equals("")
                            || theView.parseInt(amount) > cart
                                    .get(theView.parseInt(pick)).getTotal()
                            || !theView.rangeCheck(amount, 1, 30)) {
                        theView.print(
                                "Invalid amount. Please enter a number between 0 and "
                                        + cart.get(theView.parseInt(pick))
                                                .getTotal()
                                        + ": ");
                        amount = theView.readNum();
                    }
                    cart.get(theView.parseInt(pick))
                            .setPlannedPurch(theView.parseInt(amount));
                    theView.println("Item Updated.");
                    editCart();
                    return;
                }
                editCart();
                return;
            }
        }
        else if (pick.equals("2")) {
            if (verifyUserChoice("clear the cart")) {
                cart = new ArrayList<Product>();
            }
            else {
                editCart();
            }
        }
    }

    private void viewCart() {
        if (cart.size() == 0) {
            theView.println("Your cart is empty.");
            return;
        }
        theView.println("Below is the contents of your cart: ");
        theView.println("[Amount in cart] Product: $total cost");
        theView.printLine();
        for (int i = 0; i < cart.size(); i++) {
            theView.println("[" + cart.get(i).getPlannedPurch() + "] "
                    + cart.get(i).toString2());
        }
    }

    private void addToCart() {
        if (theStore == null || cart.size() == 0) {
            theStore = null;
            if (customerSelect("IS_ONLINE_SHOPPER").equals("N")) {
                theView.println(
                        "There is no stored card attatched to your account.");
                theView.println(
                        "You may shop online, but in order to checkout you must add a card.");
            }
            if ((theStore = setShopId()) == null) {
                return;
            }
        }

        ArrayList<Type> types;
        if (theStore.getStore_id() == null) {
            theView.println(
                    "You are currently shopping from our online store.");
            theView.println(
                    "To change where you are buying from please checkout or clear your cart.");
            types = browseTypes(1, null);
        }
        else {
            theView.println(
                    "You are currently shopping from a Regork located at "
                            + theStore.getAddress());
            theView.println(
                    "To change where you are buying from please checkout or clear your cart.");
            types = browseTypes(2, null);
        }
        browseItems(types);
    }

    private void browseItems(ArrayList<Type> types) {
        String[] choices = new String[types.size() + 1];
        for (int i = 0; i < types.size(); i++) {
            choices[i] = "Search " + types.get(i).toString() + " products";
        }
        choices[0] = "Show " + types.get(0).toString() + " products";
        choices[types.size()] = "Go Back";
        String end = " or refine your search: ";
        if (types.size() <= 1) {
            end = "?";
        }
        String choice = theView.readChoice(choices[0] + end, choices);
        if (theView.parseInt(choice) == 0) {
            while (true) {
                ArrayList<Product> stock = getProducts(types.get(0));
                if (stock == null || stock.size() == 0) {
                    theView.println(
                            "Sorry, There are no products in stock for that type. ");
                    browseItems(types);
                    return;
                }
                String[] s = new String[stock.size() + 1];
                for (int i = 0; i < stock.size(); i++) {
                    s[i] = stock.get(i).toString();
                }
                s[stock.size()] = "Go Back";
                choice = theView.readChoice(
                        "Which product would you like to add to your cart?", s);
                if (theView.parseInt(choice) != stock.size()) {
                    Product selected = stock.get(theView.parseInt(choice));
                    if (selected.getTotal() == 0) {
                        theView.println(
                                "Unfortunatly, that product is out of stock.");
                        theView.println(
                                "Please try again later when more becomes available.");
                    }
                    else {
                        theView.println(selected.toString() + ".");
                        String buy2 = "";
                        for (Product p : cart) {
                            if (p.getProduct_id() == selected.getProduct_id()) {
                                buy2 = Math.min(p.getPlannedPurch(),
                                        selected.getTotal()) + "";
                                p.setPlannedPurch(Math.min(p.getPlannedPurch(),
                                        selected.getTotal()));
                            }
                        }
                        if (!buy2.equals("")) {
                            theView.println("You currently have " + buy2
                                    + " units of this in your cart.");
                        }
                        else {
                            buy2 = "0";
                        }
                        if (theView.parseInt(buy2) == selected.getTotal()) {
                            theView.println(
                                    "You currently have all available stock in your cart.");
                        }
                        while (theView.parseInt(buy2) != selected.getTotal()) {
                            theView.print(
                                    "What quantity do you want to add to your cart: ");
                            String buy = theView.readNum();
                            if (buy.equals("")) {
                                buy = "0";
                            }
                            String buy3 = (theView.parseInt(buy2)
                                    + theView.parseInt(buy)) + "";

                            if (theView.parseInt(buy3) > selected.getTotal()) {
                                theView.println(
                                        "Not enough items in stock please enter a lower quantity");
                            }
                            else {
                                boolean found = false;
                                for (Product p : cart) {
                                    if (p.getProduct_id() == selected
                                            .getProduct_id()) {
                                        found = true;
                                        p.setPlannedPurch(
                                                theView.parseInt(buy3));
                                    }
                                }
                                if (!found) {
                                    selected.setPlannedPurch(
                                            theView.parseInt(buy3));
                                    cart.add(selected);
                                }
                                theView.println("Item added to cart.");
                                theView.print("Exit to main menu? (Y/N): ");
                                if (theView.readYN().equals("Y")) {
                                    return;
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }
                }
                else {
                    browseItems(types);
                    return;
                }
            }
        }
        else if (theView.parseInt(choice) < types.size()) {
            Type picked = types.get(theView.parseInt(choice));
            ArrayList<Type> next = new ArrayList<Type>(picked.getChildren());
            next.add(0, picked);
            browseItems(next);
        }
        else {
            if (types.get(0).getSuperType() == null) {
                return;
            }
            else {
                Type previous = types.get(0).getSuperType();
                ArrayList<Type> last = new ArrayList<Type>(
                        previous.getChildren());
                last.add(0, previous);
                browseItems(last);
            }
        }
    }

    private ArrayList<Product> getProducts(Type productType) {
        ArrayList<Product> out = null;
        try {
            CallableStatement cs = conn
                    .prepareCall("{call GET_PRODUCTS(?,?,?)}");
            dc.setCS(cs);
            dc.setCSParam(1, theStore.getStore_id());
            dc.setCSParam(2, productType.getType_id());
            cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            cs.executeUpdate();
            ResultSet rs = cs.getObject(3, ResultSet.class);
            out = new ArrayList<Product>();
            while (rs.next()) {
                Integer product_id = rs.getInt(1);
                String product_name = rs.getString(2);
                String size = rs.getString(3);
                String brand_name = rs.getString(4);
                Integer total = rs.getInt(5);
                Double curr_price = rs.getDouble(6);
                String type_name = rs.getString(7);
                boolean repeat = false;
                for (Product p : out) {
                    if (p.getProduct_id() == product_id) {
                        repeat = true;
                        p.addType_name(type_name);
                    }
                }
                if (!repeat) {
                    ArrayList<String> type_nameArray = new ArrayList<String>();
                    type_nameArray.add(type_name);
                    out.add(new Product(product_id, product_name, size,
                            brand_name, total, curr_price, type_nameArray));
                }
            }
        }
        catch (SQLException se) {
            theView.println("An error has occured.");
            theView.println(se.getMessage());
        }
        return out;
    }

    private ArrayList<Type> browseTypes(Integer typeid, Type superType) {
        String typename = getDataList("TYPE_NAME", 1, "TYPE", true,
                "TYPE_ID" + " = ?", typeid).get(0).get(0);
        ArrayList<ArrayList<String>> subids = getDataList(
                "(NVL(FOOD.TYPE_ID, 0) + NVL(NOT_FOOD.TYPE_ID, 0)) AS TYPE_ID",
                1,
                "FOOD FULL OUTER JOIN NOT_FOOD ON NOT_FOOD.TYPE_ID = FOOD.TYPE_ID",
                true,
                "(NVL(FOOD.SUPER_ID, 0) + NVL(NOT_FOOD.SUPER_ID, 0))" + " = ?",
                typeid);
        ArrayList<Type> children = new ArrayList<Type>();
        Type theType = new Type(typeid, typename, null, superType);
        for (ArrayList<String> subid : subids) {
            children.add(browseTypes(theView.parseInt(subid.get(0)), theType)
                    .get(0));
        }
        theType.setChildren(children);
        ArrayList<Type> result = (new ArrayList<Type>(theType.getChildren()));
        result.add(0, theType);
        return result;
    }

    private Store setShopId() {
        String temp = theView.readChoice(
                "Would you like shop our online store or pickup from a physical location?",
                "Shop Online", "Pickup from Store", "Exit to Main Menu");
        if (temp.equals("0")) {
            return new Store(null, null, null);
        }
        else if (temp.equals("1")) {
            while (true) {
                temp = theView.readChoice(
                        "How would you like to search for our physical locations?",
                        "Search in your area", "Search by zipcode", "Go Back");
                String zip = "";
                if (temp.equals("0")) {
                    zip = customerSelect("ZIPCODE");
                }
                else if (temp.equals("1")) {
                    theView.print("Enter zipcode of store: ");
                    zip = theView.readNum();
                    while (!theView.rangeCheck(zip, 1, 5)) {
                        theView.print("Invalid zipcode please renter: ");
                        zip = theView.readNum();
                    }
                }
                else {
                    return setShopId();
                }
                ArrayList<Store> Stores = getStoreList("STORE_ID,ADDRESS", 2,
                        "STORE", true, "ZIPCODE", theView.parseInt(zip));
                Store[] s = new Store[Stores.size()];
                String[] s2 = new String[Stores.size() + 1];
                for (int i = 0; i < Stores.size(); i++) {
                    s[i] = Stores.get(i);
                    s2[i] = Stores.get(i).toString();
                }
                s2[s.length] = "Go Back";
                if (s.length > 0) {
                    String pick = theView.readChoice(
                            "Which store would you like to pick up from?", s2);
                    if (pick.equals(s.length + "")) {

                    }
                    else {
                        return s[theView.parseInt(pick)];
                    }
                }
                else {
                    theView.println(
                            "Unfortunately there are no stores in that area. Please try a different area.");
                }
            }
        }
        return null;
    }

    private <T> ArrayList<Store> getStoreList(String returnAtt, Integer atts,
            String table, boolean isWhere, String searchAtt, Integer where) {
        ArrayList<ArrayList<String>> Stores = getDataList(returnAtt, atts,
                table, isWhere, searchAtt + " = ?", where);
        ArrayList<Store> s = new ArrayList<Store>();
        for (int i = 0; i < Stores.size(); i++) {
            s.add(new Store(theView.parseInt(Stores.get(i).get(0)),
                    Stores.get(i).get(1), where));
        }
        return s;
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
            System.out.println(se.getMessage());
        }
        return new ArrayList<ArrayList<String>>();
    }

    private void updateProfile() {
        String pick = theView.readChoice("What would you like to update?",
                "First Name", "Last Name", "Address", "Zipcode",
                "Frequent Shopper Status", "Credit/Debit Card",
                "Delete Account", "Go Back to Main Menu");

        if (pick.equals("0")) {
            theView.println("Your saved first name is currently: "
                    + customerSelect("FIRST_NAME"));
            if (verifyUserChoice("change this")) {
                while (true) {
                    theView.print(
                            "What would you like to change your first name to: ");
                    String first = theView.readAlphaNum();
                    while (!theView.rangeCheck(first, 1, 50)) {
                        theView.print("Invalid input length. Please retry: ");
                        first = theView.readAlphaNum();
                    }
                    if (customerUpdate("FIRST_NAME", first)) {
                        break;
                    }
                    else {
                        theView.print(
                                "There was an error updating your information. Try again? (Y/N)");
                        if (theView.readYN().equals("N")) {
                            break;
                        }
                    }
                }
            }
            updateProfile();
        }
        else if (pick.equals("1")) {
            theView.println("Your saved last name is currently: "
                    + customerSelect("LAST_NAME"));
            if (verifyUserChoice("change this")) {
                while (true) {
                    theView.print(
                            "What would you like to change your last name to: ");
                    String last = theView.readAlphaNum();
                    while (!theView.rangeCheck(last, 1, 50)) {
                        theView.print("Invalid input length. Please retry: ");
                        last = theView.readAlphaNum();
                    }
                    if (customerUpdate("LAST_NAME", last)) {
                        break;
                    }
                    else {
                        theView.print(
                                "There was an error updating your information. Try again? (Y/N)");
                        if (theView.readYN().equals("N")) {
                            break;
                        }
                    }
                }
            }
            updateProfile();
        }
        else if (pick.equals("2")) {
            theView.println("Your saved address is currently: "
                    + customerSelect("ADDRESS"));
            if (verifyUserChoice("change this")) {
                while (true) {
                    theView.print(
                            "What would you like to change your address to: ");
                    String address = theView.readAlphaNum();
                    while (!theView.rangeCheck(address, 1, 50)) {
                        theView.print("Invalid input length. Please retry: ");
                        address = theView.readAlphaNum();
                    }
                    if (customerUpdate("ADDRESS", address)) {
                        break;
                    }
                    else {
                        theView.print(
                                "There was an error updating your information. Try again? (Y/N)");
                        if (theView.readYN().equals("N")) {
                            break;
                        }
                    }
                }
            }
            updateProfile();
        }
        else if (pick.equals("3")) {
            theView.println("Your saved zipcode is currently: "
                    + customerSelect("ZIPCODE"));
            if (verifyUserChoice("change this")) {
                while (true) {
                    theView.print(
                            "What would you like to change your first name to: ");
                    String zipcode = theView.readNum();
                    while (!theView.rangeCheck(zipcode, 1, 5)) {
                        theView.print("Invalid input length. Please retry: ");
                        zipcode = theView.readNum();
                    }
                    if (customerUpdate("ZIPCODE", theView.parseInt(zipcode))) {
                        break;
                    }
                    else {
                        theView.print(
                                "There was an error updating your information. Try again? (Y/N)");
                        if (theView.readYN().equals("N")) {
                            break;
                        }
                    }
                }
            }
            updateProfile();
        }
        else if (pick.equals("4")) {
            String ifs = customerSelect("IS_FREQUENT_SHOPPER");
            while (true) {
                String age = "";
                String gend = "";
                if (ifs.equals("Y")) {
                    theView.println(
                            "You are currently enrolled in the frequent shopper program");
                    if (verifyUserChoice(
                            "leave the frequent shopper program")) {
                        ifs = "N";
                    }
                    else {
                        break;
                    }
                }
                else {
                    theView.println(
                            "You are not currently enrolled in the frequent shopper program");
                    if (verifyUserChoice(
                            "enroll in the frequent shopper program")) {
                        ifs = "Y";
                        theView.println(
                                "Please answer the next two questions honestly.  You may leave the question blank if you refuse to answer.");
                        theView.print("What is your current age: ");
                        age = theView.readNum();
                        while (!theView.rangeCheck(age, 0, 3)) {
                            theView.print(
                                    "Invalid input length. Please retry: ");
                            age = theView.readNum();
                        }
                        String[] genders = { "male", "female", "other" };
                        gend = genders[Integer.parseInt(theView.readChoice(
                                "Which option best describes your gender: ",
                                genders))];
                    }
                    else {
                        break;
                    }
                }
                try {
                    CallableStatement cs = conn.prepareCall(
                            "{call UPDATE_CUSTOMER_GROUPS(?,?,?,?,?,?,?,?)}");
                    dc.setCS(cs);
                    dc.setCSParam(1, user_id);
                    dc.setCSParam(2, ifs);
                    dc.setCSParam(3, "");
                    dc.setCSParam(4, theView.parseInt(age));
                    dc.setCSParam(5, gend);
                    dc.setCSParam(6, null);
                    dc.setCSParam(7, null);
                    dc.setCSParam(8, "");
                    cs.executeUpdate();
                    cs.close();
                    theView.println("Update Successful!");
                    break;
                }
                catch (SQLException se) {
                    theView.print(
                            "There was an error updating your information. Try again? (Y/N)");
                    if (theView.readYN().equals("N")) {
                        break;
                    }
                }
            }
            updateProfile();
        }
        else if (pick.equals("5")) {
            String ios = customerSelect("IS_ONLINE_SHOPPER");
            while (true) {
                String cardnum = null;
                String cvv = null;
                String expdate = "";
                if (ios.equals("Y")) {
                    String onlineCustChoice = theView.readChoice(
                            "What do you want to do?",
                            "Update Credit/Debit Card", "Delete Stored Card",
                            "Go Back");
                    if (onlineCustChoice.equals("0")) {
                        String currcard = onlineCustomerSelect("CARD_NUM");
                        if (currcard == null) {
                            break;
                        }
                        theView.println(
                                "The current stored card has the last 4 digits of "
                                        + currcard.substring(
                                                currcard.length() - 4));
                        if (verifyUserChoice("change this card")) {
                            theView.print(
                                    "Enter your Card Number with no dashes/whitespace (16 digits): ");
                            cardnum = theView.readNum();
                            while (!theView.rangeCheck(cardnum, 16, 16)) {
                                theView.print(
                                        "Invalid input length. Please retry: ");
                                cardnum = theView.readNum();
                            }
                            theView.print("Enter your cvv number (3 digits): ");
                            cvv = theView.readNum();
                            while (!theView.rangeCheck(cvv, 3, 3)) {
                                theView.print(
                                        "Invalid input length. Please retry: ");
                                cvv = theView.readNum();
                            }
                            theView.print(
                                    "Enter the month for the expiration date (2 digits): ");
                            String exp_date_month = theView.readNumMonth();
                            while (!theView.rangeCheck(exp_date_month, 2, 2)) {
                                theView.print("Invalid input. Please retry: ");
                                exp_date_month = theView.readNumMonth();
                            }
                            theView.print(
                                    "Enter the year for the expiration date (2 digits): ");
                            String exp_date_year = theView.readNumYear();
                            while (!theView.rangeCheck(exp_date_year, 2, 2)) {
                                theView.print(
                                        "Invalid input length. Please retry: ");
                                exp_date_year = theView.readNumYear();
                            }
                            expdate = exp_date_month + "/" + exp_date_year;
                            ios = "Y";
                        }
                    }
                    else if (onlineCustChoice.equals("1")) {
                        String currcard = onlineCustomerSelect("CARD_NUM");
                        if (currcard == null) {
                            break;
                        }
                        theView.println(
                                "The current stored card has the last 4 digits of "
                                        + currcard.substring(
                                                currcard.length() - 4));
                        if (verifyUserChoice("delete this card")) {
                            ios = "N";
                        }
                    }
                    else {
                        break;
                    }
                }
                else {
                    String onlineCustChoice = theView.readChoice(
                            "What do you want to do?", "Add Credit/Debit Card",
                            "Go Back");
                    if (onlineCustChoice.equals("0")) {
                        theView.print(
                                "Enter your Card Number with no dashes/whitespace (16 digits): ");
                        cardnum = theView.readNum();
                        while (!theView.rangeCheck(cardnum, 16, 16)) {
                            theView.print(
                                    "Invalid input length. Please retry: ");
                            cardnum = theView.readNum();
                        }
                        theView.print("Enter your cvv number (3 digits): ");
                        cvv = theView.readNum();
                        while (!theView.rangeCheck(cvv, 3, 3)) {
                            theView.print(
                                    "Invalid input length. Please retry: ");
                            cvv = theView.readNum();
                        }
                        theView.print(
                                "Enter the month for the expiration date (2 digits): ");
                        String exp_date_month = theView.readNumMonth();
                        while (!theView.rangeCheck(exp_date_month, 2, 2)) {
                            theView.print(
                                    "Invalid input length. Please retry: ");
                            exp_date_month = theView.readNumMonth();
                        }
                        theView.print(
                                "Enter the year for the expiration date (2 digits): ");
                        String exp_date_year = theView.readNumYear();
                        while (!theView.rangeCheck(exp_date_year, 2, 2)) {
                            theView.print(
                                    "Invalid input length. Please retry: ");
                            exp_date_year = theView.readNumYear();
                        }
                        expdate = exp_date_month + "/" + exp_date_year;
                        ios = "Y";
                    }
                    else {
                        break;
                    }
                }
                try {
                    CallableStatement cs = conn.prepareCall(
                            "{call UPDATE_CUSTOMER_GROUPS(?,?,?,?,?,?,?,?)}");
                    dc.setCS(cs);
                    dc.setCSParam(1, user_id);
                    dc.setCSParam(2, "");
                    dc.setCSParam(3, ios);
                    dc.setCSParam(4, null);
                    dc.setCSParam(5, "");
                    dc.setCSParam(6, theView.parseLong(cardnum));
                    dc.setCSParam(7, theView.parseInt(cvv));
                    dc.setCSParam(8, expdate);
                    cs.executeUpdate();
                    cs.close();
                    theView.println("Update Successful!");
                    break;
                }
                catch (SQLException se) {
                    theView.println(
                            "There was an error updating your information. Going Back.");
                }
            }
            updateProfile();
        }
        else if (pick.equals("6")) {
            theView.println("This action cannot be undone.");
            if (verifyUserChoice("delete your profile")) {
                try {
                    PreparedStatement s = conn.prepareStatement(
                            "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = '"
                                    + user_id + "'");
                    s.executeUpdate();
                    s.close();
                    theView.println("Customer Profile Successfully Deleted!");
                    return;
                }
                catch (SQLException se) {
                    theView.println(
                            "An error has occured durring deletion. Exiting.");
                    dc.close();
                    System.exit(1);
                }
            }
        }
    }

    private String customerSelect(String att) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT " + att + " FROM CUSTOMER WHERE CUSTOMER_ID = ?");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        catch (SQLException se) {
        }
        theView.println("There was an error retrieving your profile. Exiting");
        dc.close();
        System.exit(1);
        return null;
    }

    private String onlineCustomerSelect(String att) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT " + att
                    + " FROM ONLINE_SHOPPER WHERE CUSTOMER_ID = ?");
            ps.setString(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        catch (SQLException se) {
        }
        theView.println("There was an error retrieving your payment data.");
        return null;
    }

    private <T> boolean customerUpdate(String att, T value) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE CUSTOMER SET "
                    + att + " = ? WHERE CUSTOMER_ID = '" + user_id + "'");
            dc.setPS(ps);
            dc.setPSParam(1, value);
            ps.executeUpdate();
            ps.close();
            theView.println("Update Successful!");
            return true;
        }
        catch (SQLException se) {
            return false;
        }
    }

    private boolean verifyUserChoice(String action) {
        theView.print("Are you sure you to " + action + "? (Y/N): ");
        if (theView.readYN().equals("N")) {
            return false;
        }
        return true;
    }
}
